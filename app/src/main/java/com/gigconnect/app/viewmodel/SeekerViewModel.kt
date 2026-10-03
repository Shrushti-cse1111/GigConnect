package com.gigconnect.app.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gigconnect.app.data.demo.DemoDataProvider
import com.gigconnect.app.data.model.*
import kotlinx.coroutines.Job as CoroutineJob
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class SeekerViewModel : ViewModel() {

    // ─── Service Request Draft State ─────────────────────────────────────
    private val _requestDraft = MutableStateFlow(ServiceRequestDraft())
    val requestDraft: StateFlow<ServiceRequestDraft> = _requestDraft.asStateFlow()

    // ─── Workers ────────────────────────────────────────────────────────
    private val _workers = MutableStateFlow<List<Worker>>(emptyList())
    val workers: StateFlow<List<Worker>> = _workers.asStateFlow()

    private val _isLoadingWorkers = MutableStateFlow(false)
    val isLoadingWorkers: StateFlow<Boolean> = _isLoadingWorkers.asStateFlow()

    private val _selectedService = MutableStateFlow<ServiceCategory?>(null)
    val selectedService: StateFlow<ServiceCategory?> = _selectedService.asStateFlow()

    private val _selectedWorker = MutableStateFlow<Worker?>(null)
    val selectedWorker: StateFlow<Worker?> = _selectedWorker.asStateFlow()

    // ─── SmartMatch & FairMatch ──────────────────────────────────────────
    private val _smartMatchResults = MutableStateFlow<List<SmartMatchResult>>(emptyList())
    val smartMatchResults: StateFlow<List<SmartMatchResult>> = _smartMatchResults.asStateFlow()

    private val _isSmartMatchRunning = MutableStateFlow(false)
    val isSmartMatchRunning: StateFlow<Boolean> = _isSmartMatchRunning.asStateFlow()

    private val _smartMatchProgress = MutableStateFlow(0f)
    val smartMatchProgress: StateFlow<Float> = _smartMatchProgress.asStateFlow()

    private val _smartMatchSteps = MutableStateFlow<List<Pair<String, Boolean>>>(emptyList())
    val smartMatchSteps: StateFlow<List<Pair<String, Boolean>>> = _smartMatchSteps.asStateFlow()

    private val _recommendedWorker = MutableStateFlow<SmartMatchResult?>(null)
    val recommendedWorker: StateFlow<SmartMatchResult?> = _recommendedWorker.asStateFlow()

    // ─── Booking & Escrow Lifecycle ──────────────────────────────────────
    private val _currentBooking = MutableStateFlow<Booking?>(null)
    val currentBooking: StateFlow<Booking?> = _currentBooking.asStateFlow()

    private val _bookings = MutableStateFlow(DemoDataProvider.demoBookings)
    val bookings: StateFlow<List<Booking>> = _bookings.asStateFlow()

    private val _bookingStatus = MutableStateFlow(BookingStatus.CONFIRMED)
    val bookingStatus: StateFlow<BookingStatus> = _bookingStatus.asStateFlow()

    private val _escrowStatus = MutableStateFlow(EscrowStatus.AUTHORIZED_HELD)
    val escrowStatus: StateFlow<EscrowStatus> = _escrowStatus.asStateFlow()

    // ─── Tracking & Location Sharing ─────────────────────────────────────
    private val _trackingEta = MutableStateFlow(12)
    val trackingEta: StateFlow<Int> = _trackingEta.asStateFlow()

    private val _locationSharingConsentGranted = MutableStateFlow(true)
    val locationSharingConsentGranted: StateFlow<Boolean> = _locationSharingConsentGranted.asStateFlow()

    // ─── Disputes & Grievances ───────────────────────────────────────────
    private val _disputes = MutableStateFlow<List<DisputeRecord>>(emptyList())
    val disputes: StateFlow<List<DisputeRecord>> = _disputes.asStateFlow()

    private val _activeDispute = MutableStateFlow<DisputeRecord?>(null)
    val activeDispute: StateFlow<DisputeRecord?> = _activeDispute.asStateFlow()

    // ─── Ratings & Reviews ───────────────────────────────────────────────
    private val _ratedBookingIds = MutableStateFlow<Set<String>>(setOf("BK1024"))
    val ratedBookingIds: StateFlow<Set<String>> = _ratedBookingIds.asStateFlow()

    private var trackingJob: CoroutineJob? = null

    val serviceCategories = DemoDataProvider.serviceCategories

    // ─── Service Request Draft Actions ───────────────────────────────────

    fun initServiceRequest(category: ServiceCategory, cityName: String) {
        _selectedService.value = category
        val minP = (category.basePrice * 0.9).toInt()
        val maxP = (category.basePrice * 1.25).toInt()
        _requestDraft.value = _requestDraft.value.copy(
            categoryId = category.id,
            categoryName = category.name,
            city = cityName,
            basePriceEstimate = category.basePrice,
            estimatedMinPrice = minP,
            estimatedMaxPrice = maxP,
            isServiceable = validateServiceArea(cityName, _requestDraft.value.pincode)
        )
        loadWorkers(cityName, category.id)
    }

    fun updateDraftDescription(desc: String) {
        _requestDraft.value = _requestDraft.value.copy(description = desc)
    }

    fun updateDraftUrgency(urgency: String) {
        val base = _selectedService.value?.basePrice ?: 500
        val multiplier = when (urgency) {
            "Emergency" -> 1.35
            "Urgent" -> 1.15
            else -> 1.0
        }
        val adjusted = (base * multiplier).toInt()
        _requestDraft.value = _requestDraft.value.copy(
            urgency = urgency,
            basePriceEstimate = adjusted,
            estimatedMinPrice = (adjusted * 0.9).toInt(),
            estimatedMaxPrice = (adjusted * 1.2).toInt()
        )
    }

    fun addDraftMedia(name: String, isVideo: Boolean = false) {
        val current = _requestDraft.value.mediaItems.toMutableList()
        current.add(ServiceMediaItem("M${System.currentTimeMillis() % 10000}", name, isVideo, if (isVideo) "8.4 MB" else "1.8 MB"))
        _requestDraft.value = _requestDraft.value.copy(mediaItems = current)
    }

    fun removeDraftMedia(mediaId: String) {
        val current = _requestDraft.value.mediaItems.filterNot { it.id == mediaId }
        _requestDraft.value = _requestDraft.value.copy(mediaItems = current)
    }

    fun updateDraftLocation(address: String, locality: String, city: String, pincode: String) {
        val serviceable = validateServiceArea(city, pincode)
        _requestDraft.value = _requestDraft.value.copy(
            address = address,
            locality = locality,
            city = city,
            pincode = pincode,
            isServiceable = serviceable
        )
    }

    fun updateDraftSchedule(date: String, time: String, isInstant: Boolean) {
        _requestDraft.value = _requestDraft.value.copy(
            selectedDate = date,
            selectedTime = time,
            isInstant = isInstant
        )
    }

    fun validateServiceArea(cityName: String, pincode: String): Boolean {
        // Supported cities in GigConnect Cooperative Network
        val validCity = DemoDataProvider.demoCities.any { it.name.equals(cityName, ignoreCase = true) }
        val workersInCity = DemoDataProvider.getWorkersForCity(cityName)
        return validCity && workersInCity.isNotEmpty()
    }

    fun selectService(service: ServiceCategory, cityName: String) {
        _selectedService.value = service
        initServiceRequest(service, cityName)
    }

    fun selectWorker(worker: Worker) {
        _selectedWorker.value = worker
    }

    fun setSelectedWorker(worker: Worker) {
        _selectedWorker.value = worker
    }

    fun selectWorkerById(workerId: String) {
        val worker = DemoDataProvider.getWorkerById(workerId)
        if (worker != null) {
            _selectedWorker.value = worker
        }
    }

    fun loadWorkers(cityName: String, serviceId: String = "") {
        viewModelScope.launch {
            _isLoadingWorkers.value = true
            delay(400)
            _workers.value = if (serviceId.isNotBlank()) {
                DemoDataProvider.getWorkersForService(cityName, serviceId)
            } else {
                DemoDataProvider.getWorkersForCity(cityName)
            }
            _isLoadingWorkers.value = false
        }
    }

    // ─── FairMatch Execution ─────────────────────────────────────────────

    fun runSmartMatch() {
        val service = _selectedService.value ?: serviceCategories.first()
        val cityName = _requestDraft.value.city.ifBlank { DemoDataProvider.selectedCity.name }
        val workerList = DemoDataProvider.getWorkersForService(cityName, service.id)

        viewModelScope.launch {
            _isSmartMatchRunning.value = true
            _smartMatchProgress.value = 0f
            val steps = listOf(
                "Skill & Certification verification" to false,
                "Cooperative service area & distance check" to false,
                "Real-time availability & slot verification" to false,
                "Fair rotation & workload equity scoring" to false,
                "Rating, customer safety & performance history" to false
            )
            _smartMatchSteps.value = steps

            steps.forEachIndexed { index, _ ->
                delay(350)
                val updated = _smartMatchSteps.value.toMutableList()
                updated[index] = updated[index].first to true
                _smartMatchSteps.value = updated
                _smartMatchProgress.value = (index + 1) / steps.size.toFloat()
            }

            delay(200)
            val results = DemoDataProvider.computeSmartMatch(workerList, service.id)
            _smartMatchResults.value = results
            _recommendedWorker.value = results.firstOrNull()
            _isSmartMatchRunning.value = false
        }
    }

    // ─── Booking Confirmation & Escrow Authorization ─────────────────────

    fun confirmBooking(
        address: String = _requestDraft.value.address,
        date: String = _requestDraft.value.selectedDate,
        time: String = _requestDraft.value.selectedTime,
        instructions: String = _requestDraft.value.description,
        paymentMethod: String = "UPI"
    ) {
        val worker = _selectedWorker.value ?: _recommendedWorker.value?.worker ?: DemoDataProvider.puneWorkers.first()
        val service = _selectedService.value ?: serviceCategories.first()
        val basePrice = _requestDraft.value.basePriceEstimate
        val platform = (basePrice * 0.10).toInt()
        val welfare = (basePrice * 0.04).toInt()
        val workerEarnings = basePrice - welfare

        val booking = Booking(
            id = "BK${(1100..9999).random()}",
            customerId = "u1",
            customerName = "Demo User",
            workerId = worker.id,
            workerName = worker.name,
            serviceCategory = service.name,
            serviceDetail = if (instructions.isNotBlank()) instructions else service.description,
            date = date,
            time = time,
            address = address,
            additionalInstructions = instructions,
            status = BookingStatus.CONFIRMED,
            pricing = PricingBreakdown(basePrice, platform, workerEarnings, welfare),
            createdAt = "Just now",
            urgency = _requestDraft.value.urgency,
            paymentMethod = paymentMethod,
            escrowStatus = EscrowStatus.AUTHORIZED_HELD,
            mediaCount = _requestDraft.value.mediaItems.size
        )

        _currentBooking.value = booking
        _bookingStatus.value = BookingStatus.CONFIRMED
        _escrowStatus.value = EscrowStatus.AUTHORIZED_HELD
        _bookings.value = listOf(booking) + _bookings.value
        simulateTracking()
    }

    fun loadBookingForTracking(booking: Booking) {
        _currentBooking.value = booking
        _bookingStatus.value = booking.status
        _escrowStatus.value = booking.escrowStatus
    }

    // ─── Tracking Lifecycle Simulator ────────────────────────────────────

    private fun simulateTracking() {
        trackingJob?.cancel()
        trackingJob = viewModelScope.launch {
            _bookingStatus.value = BookingStatus.WORKER_ASSIGNED
            delay(1500)
            _bookingStatus.value = BookingStatus.WORKER_ON_WAY
            _trackingEta.value = 12

            for (m in 11 downTo 1) {
                delay(1000)
                _trackingEta.value = m
            }

            _bookingStatus.value = BookingStatus.WORKER_ARRIVED
            delay(1500)
            _bookingStatus.value = BookingStatus.SERVICE_STARTED
            delay(2000)
            _bookingStatus.value = BookingStatus.SERVICE_COMPLETED
        }
    }

    fun setBookingStatus(status: BookingStatus) {
        _bookingStatus.value = status
        _currentBooking.value = _currentBooking.value?.copy(status = status)
    }

    // ─── Customer Completion Confirmation & Settlement ──────────────────

    fun confirmCompletionAndReleaseEscrow(bookingId: String) {
        _bookingStatus.value = BookingStatus.PAYMENT_SETTLED
        _escrowStatus.value = EscrowStatus.RELEASED_SETTLED

        val current = _currentBooking.value
        if (current != null && current.id == bookingId) {
            _currentBooking.value = current.copy(
                status = BookingStatus.PAYMENT_SETTLED,
                escrowStatus = EscrowStatus.RELEASED_SETTLED
            )
        }

        _bookings.value = _bookings.value.map { b ->
            if (b.id == bookingId) {
                b.copy(status = BookingStatus.PAYMENT_SETTLED, escrowStatus = EscrowStatus.RELEASED_SETTLED)
            } else b
        }
    }

    // ─── Dispute / Grievance Raising ─────────────────────────────────────

    fun raiseDispute(
        bookingId: String,
        category: DisputeCategory,
        description: String,
        evidencePhotos: List<String> = emptyList()
    ) {
        val dispute = DisputeRecord(
            id = "DISP-${(1000..9999).random()}",
            bookingId = bookingId,
            category = category,
            description = description,
            evidencePhotos = evidencePhotos,
            status = DisputeStatus.OPEN,
            createdAt = "Just now"
        )

        _disputes.value = listOf(dispute) + _disputes.value
        _activeDispute.value = dispute
        _bookingStatus.value = BookingStatus.DISPUTED
        _escrowStatus.value = EscrowStatus.FROZEN_DISPUTE

        val current = _currentBooking.value
        if (current != null && current.id == bookingId) {
            _currentBooking.value = current.copy(
                status = BookingStatus.DISPUTED,
                escrowStatus = EscrowStatus.FROZEN_DISPUTE,
                disputeRecord = dispute
            )
        }

        _bookings.value = _bookings.value.map { b ->
            if (b.id == bookingId) {
                b.copy(status = BookingStatus.DISPUTED, escrowStatus = EscrowStatus.FROZEN_DISPUTE, disputeRecord = dispute)
            } else b
        }
    }

    // ─── Rating & Review Submission ──────────────────────────────────────

    fun submitReview(
        bookingId: String,
        rating: Int,
        tags: List<String>,
        comment: String
    ): Boolean {
        if (_ratedBookingIds.value.contains(bookingId)) {
            return false // Duplicate rating prevention
        }

        _ratedBookingIds.value = _ratedBookingIds.value + bookingId
        _bookingStatus.value = BookingStatus.RATED

        val reviewText = if (tags.isNotEmpty()) {
            "[${tags.joinToString(", ")}] $comment".trim()
        } else comment

        val current = _currentBooking.value
        if (current != null && current.id == bookingId) {
            _currentBooking.value = current.copy(
                status = BookingStatus.RATED,
                customerRating = rating.toFloat(),
                customerReview = reviewText
            )
        }

        _bookings.value = _bookings.value.map { b ->
            if (b.id == bookingId) {
                b.copy(status = BookingStatus.RATED, customerRating = rating.toFloat(), customerReview = reviewText)
            } else b
        }
        return true
    }
}

