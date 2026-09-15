package com.gigconnect.app.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gigconnect.app.data.demo.DemoDataProvider
import com.gigconnect.app.data.model.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class SeekerViewModel : ViewModel() {

    // ─── Workers ────────────────────────────────────────────────────────
    private val _workers = MutableStateFlow<List<Worker>>(emptyList())
    val workers: StateFlow<List<Worker>> = _workers.asStateFlow()

    private val _isLoadingWorkers = MutableStateFlow(false)
    val isLoadingWorkers: StateFlow<Boolean> = _isLoadingWorkers.asStateFlow()

    private val _selectedService = MutableStateFlow<ServiceCategory?>(null)
    val selectedService: StateFlow<ServiceCategory?> = _selectedService.asStateFlow()

    private val _selectedWorker = MutableStateFlow<Worker?>(null)
    val selectedWorker: StateFlow<Worker?> = _selectedWorker.asStateFlow()

    // ─── SmartMatch ──────────────────────────────────────────────────────
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

    // ─── Booking ─────────────────────────────────────────────────────────
    private val _currentBooking = MutableStateFlow<Booking?>(null)
    val currentBooking: StateFlow<Booking?> = _currentBooking.asStateFlow()

    private val _bookings = MutableStateFlow(DemoDataProvider.demoBookings)
    val bookings: StateFlow<List<Booking>> = _bookings.asStateFlow()

    private val _bookingStatus = MutableStateFlow(BookingStatus.PENDING)
    val bookingStatus: StateFlow<BookingStatus> = _bookingStatus.asStateFlow()

    // ─── Tracking ─────────────────────────────────────────────────────────
    private val _trackingEta = MutableStateFlow(12)
    val trackingEta: StateFlow<Int> = _trackingEta.asStateFlow()

    val serviceCategories = DemoDataProvider.serviceCategories

    fun selectService(service: ServiceCategory, cityName: String) {
        _selectedService.value = service
        loadWorkers(cityName, service.id)
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
            delay(800) // Simulate network
            _workers.value = if (serviceId.isNotBlank()) {
                DemoDataProvider.getWorkersForService(cityName, serviceId)
            } else {
                DemoDataProvider.getWorkersForCity(cityName)
            }
            _isLoadingWorkers.value = false
        }
    }

    fun runSmartMatch() {
        val service = _selectedService.value ?: return
        val cityName = DemoDataProvider.selectedCity.name
        val workerList = DemoDataProvider.getWorkersForService(cityName, service.id)

        viewModelScope.launch {
            _isSmartMatchRunning.value = true
            _smartMatchProgress.value = 0f
            val steps = listOf(
                "Skill compatibility" to false,
                "Distance analysis" to false,
                "Real-time availability" to false,
                "Current workload" to false,
                "Rating & reviews" to false,
                "Fair job distribution" to false
            )
            _smartMatchSteps.value = steps

            val stepDelay = 600L
            steps.forEachIndexed { index, _ ->
                delay(stepDelay)
                val updated = _smartMatchSteps.value.toMutableList()
                updated[index] = updated[index].first to true
                _smartMatchSteps.value = updated
                _smartMatchProgress.value = (index + 1) / steps.size.toFloat()
            }

            delay(400)
            val results = DemoDataProvider.computeSmartMatch(workerList, service.id)
            _smartMatchResults.value = results
            _recommendedWorker.value = results.firstOrNull()
            _isSmartMatchRunning.value = false
        }
    }

    fun confirmBooking(
        address: String,
        date: String,
        time: String,
        instructions: String
    ) {
        val worker = _recommendedWorker.value?.worker ?: _selectedWorker.value ?: return
        val service = _selectedService.value ?: return
        val basePrice = service.basePrice
        val welfare = (basePrice * 0.04).toInt()
        val platform = (basePrice * 0.10).toInt()
        val workerEarnings = basePrice - welfare

        val booking = Booking(
            id = "BK${(1100..9999).random()}",
            customerId = "u1",
            customerName = "Demo User",
            workerId = worker.id,
            workerName = worker.name,
            serviceCategory = service.name,
            serviceDetail = service.description,
            date = date,
            time = time,
            address = address,
            additionalInstructions = instructions,
            status = BookingStatus.CONFIRMED,
            pricing = PricingBreakdown(basePrice, platform, workerEarnings, welfare),
            createdAt = "Now"
        )
        _currentBooking.value = booking
        _bookings.value = listOf(booking) + _bookings.value
        simulateTracking()
    }

    private fun simulateTracking() {
        _bookingStatus.value = BookingStatus.WORKER_ASSIGNED
        viewModelScope.launch {
            delay(2000)
            _bookingStatus.value = BookingStatus.WORKER_ON_WAY
            repeat(12) {
                delay(1000)
                _trackingEta.value = 12 - it
            }
            _bookingStatus.value = BookingStatus.SERVICE_STARTED
        }
    }
}
