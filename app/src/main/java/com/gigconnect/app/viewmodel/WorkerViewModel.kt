package com.gigconnect.app.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gigconnect.app.data.demo.DemoDataProvider
import com.gigconnect.app.data.model.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class WorkerViewModel : ViewModel() {

    private val _jobs = MutableStateFlow(DemoDataProvider.demoJobs)
    val jobs: StateFlow<List<Job>> = _jobs.asStateFlow()

    private val _selectedJob = MutableStateFlow<Job?>(null)
    val selectedJob: StateFlow<Job?> = _selectedJob.asStateFlow()

    private val _isAvailable = MutableStateFlow(true)
    val isAvailable: StateFlow<Boolean> = _isAvailable.asStateFlow()

    private val _weeklyEarnings = MutableStateFlow(8450)
    val weeklyEarnings: StateFlow<Int> = _weeklyEarnings.asStateFlow()

    private val _todayJobs = MutableStateFlow(3)
    val todayJobs: StateFlow<Int> = _todayJobs.asStateFlow()

    private val _equityBalance = MutableStateFlow(12450)
    val equityBalance: StateFlow<Int> = _equityBalance.asStateFlow()

    private val _thisMonthEquity = MutableStateFlow(1250)
    val thisMonthEquity: StateFlow<Int> = _thisMonthEquity.asStateFlow()

    private val _equityEntries = MutableStateFlow(DemoDataProvider.equityEntries)
    val equityEntries: StateFlow<List<EquityEntry>> = _equityEntries.asStateFlow()

    private val _skillRecommendations = MutableStateFlow(DemoDataProvider.skillRecommendations)
    val skillRecommendations: StateFlow<List<SkillRecommendation>> = _skillRecommendations.asStateFlow()

    private val _isVoiceListening = MutableStateFlow(false)
    val isVoiceListening: StateFlow<Boolean> = _isVoiceListening.asStateFlow()

    private val _voiceResult = MutableStateFlow("")
    val voiceResult: StateFlow<String> = _voiceResult.asStateFlow()

    private val _detectedLanguage = MutableStateFlow("English")
    val detectedLanguage: StateFlow<String> = _detectedLanguage.asStateFlow()

    private val _welfareBalance = MutableStateFlow(4250)
    val welfareBalance: StateFlow<Int> = _welfareBalance.asStateFlow()

    private val _welfareClaims = MutableStateFlow(
        listOf(
            WelfareClaim("WC-101", "Health Insurance Premium Contribution", 450, "01 Sep 2026", "SETTLED", "Cooperative medical cover for family"),
            WelfareClaim("WC-102", "Emergency Safety Allowance", 1200, "15 Aug 2026", "SETTLED", "Accident protection grant approved by Panchayat"),
            WelfareClaim("WC-103", "Pension Scheme (NPS-Lite Credit)", 600, "01 Aug 2026", "SETTLED", "Monthly co-op retirement contribution")
        )
    )
    val welfareClaims: StateFlow<List<WelfareClaim>> = _welfareClaims.asStateFlow()

    private val _kycInfo = MutableStateFlow(WorkerKycInfo())
    val kycInfo: StateFlow<WorkerKycInfo> = _kycInfo.asStateFlow()

    private val _certifiedSkills = MutableStateFlow(
        listOf("Pipe Repair", "Bathroom Plumbing", "Water Tank Repair", "Leakage Detection", "Water Purifier Service")
    )
    val certifiedSkills: StateFlow<List<String>> = _certifiedSkills.asStateFlow()

    private val _serviceRadiusKm = MutableStateFlow(10)
    val serviceRadiusKm: StateFlow<Int> = _serviceRadiusKm.asStateFlow()

    private val _workingHours = MutableStateFlow("8:00 AM – 7:00 PM")
    val workingHours: StateFlow<String> = _workingHours.asStateFlow()

    val worker = DemoDataProvider.puneWorkers.first()

    fun toggleAvailability() {
        _isAvailable.value = !_isAvailable.value
    }

    fun updateServiceRadius(radiusKm: Int) {
        _serviceRadiusKm.value = radiusKm
    }

    fun updateWorkingHours(hours: String) {
        _workingHours.value = hours
    }

    fun getJobById(id: String): Job? {
        return _jobs.value.find { it.id == id } ?: DemoDataProvider.demoJobs.find { it.id == id }
    }

    fun selectJob(job: Job) {
        _selectedJob.value = job
    }

    fun acceptJob(jobId: String) {
        viewModelScope.launch {
            val updated = _jobs.value.map {
                if (it.id == jobId) it.copy(status = JobStatus.ACCEPTED) else it
            }
            _jobs.value = updated
            _selectedJob.value = updated.find { it.id == jobId }
        }
    }

    fun rejectJobWithReason(jobId: String, reason: String) {
        viewModelScope.launch {
            // Rejection is structured and completely NON-PUNITIVE.
            // Records structured feedback for matching analytics without penalizing worker rating.
            val updated = _jobs.value.map {
                if (it.id == jobId) it.copy(status = JobStatus.REJECTED, rejectionReason = reason) else it
            }
            _jobs.value = updated
            _selectedJob.value = updated.find { it.id == jobId }
        }
    }

    fun rejectJob(jobId: String) {
        rejectJobWithReason(jobId, "Declined by worker")
    }

    fun startTransit(jobId: String) {
        viewModelScope.launch {
            val updated = _jobs.value.map {
                if (it.id == jobId) it.copy(status = JobStatus.ON_THE_WAY) else it
            }
            _jobs.value = updated
            _selectedJob.value = updated.find { it.id == jobId }
        }
    }

    fun markArrived(jobId: String) {
        viewModelScope.launch {
            val updated = _jobs.value.map {
                if (it.id == jobId) it.copy(status = JobStatus.ARRIVED) else it
            }
            _jobs.value = updated
            _selectedJob.value = updated.find { it.id == jobId }
        }
    }

    fun startJob(jobId: String) {
        viewModelScope.launch {
            val updated = _jobs.value.map {
                if (it.id == jobId) it.copy(status = JobStatus.IN_PROGRESS, startedAt = "Today, 11:15 AM") else it
            }
            _jobs.value = updated
            _selectedJob.value = updated.find { it.id == jobId }
        }
    }

    fun completeJob(jobId: String, notes: String = "Service completed satisfactorily with cooperative safety checks.") {
        viewModelScope.launch {
            val jobToComplete = _jobs.value.find { it.id == jobId }
            val earnings = jobToComplete?.estimatedEarnings ?: 430
            val equity = jobToComplete?.equityContribution ?: 15
            val welfare = jobToComplete?.welfareContribution ?: 20

            val updated = _jobs.value.map {
                if (it.id == jobId) it.copy(
                    status = JobStatus.COMPLETED,
                    completedAt = "Today, 12:45 PM",
                    completionNotes = notes
                ) else it
            }
            _jobs.value = updated
            _selectedJob.value = updated.find { it.id == jobId }
            _todayJobs.value += 1
            _weeklyEarnings.value += earnings
            _equityBalance.value += equity
            _thisMonthEquity.value += equity
            _welfareBalance.value += welfare

            val newEquityEntry = EquityEntry(
                jobId = jobId,
                amount = equity,
                date = "Today",
                description = "${jobToComplete?.serviceDetail ?: "Service"} — ${jobToComplete?.address ?: "Pune"}"
            )
            _equityEntries.value = listOf(newEquityEntry) + _equityEntries.value
        }
    }

    fun completeSkillTraining(skillName: String) {
        viewModelScope.launch {
            val updatedRecs = _skillRecommendations.value.map {
                if (it.skillName == skillName) it.copy(progressPercent = 100) else it
            }
            _skillRecommendations.value = updatedRecs
            if (!_certifiedSkills.value.contains(skillName)) {
                _certifiedSkills.value = _certifiedSkills.value + skillName
            }
        }
    }

    fun updateCourseProgress(skillName: String, progress: Int) {
        viewModelScope.launch {
            val updatedRecs = _skillRecommendations.value.map {
                if (it.skillName == skillName) it.copy(progressPercent = progress.coerceIn(0, 100)) else it
            }
            _skillRecommendations.value = updatedRecs
            if (progress >= 100 && !_certifiedSkills.value.contains(skillName)) {
                _certifiedSkills.value = _certifiedSkills.value + skillName
            }
        }
    }

    fun submitKycDocument(docType: String) {
        viewModelScope.launch {
            _kycInfo.value = _kycInfo.value.copy(
                idType = docType,
                status = "UNDER_REVIEW",
                verifiedAt = "Pending review by Co-op Panchayat"
            )
            delay(1500)
            _kycInfo.value = _kycInfo.value.copy(
                status = "VERIFIED",
                verifiedAt = "Verified by Pune Co-op Committee"
            )
        }
    }

    fun simulateVoiceInput(language: String = "English") {
        viewModelScope.launch {
            _isVoiceListening.value = true
            _voiceResult.value = ""
            _detectedLanguage.value = language
            delay(2000)
            _isVoiceListening.value = false
            _voiceResult.value = when (language) {
                "Hindi" -> "आज मेरे 3 काम हैं। पहला काम 11 बजे कोरेगांव पार्क में है।"
                "Marathi" -> "आज तुमचे 3 काम आहेत. पहिले काम 11 वाजता आहे."
                "Tamil" -> "இன்று உங்களுக்கு 3 வேலைகள் உள்ளன."
                "Telugu" -> "నేడు మీకు 3 పనులు ఉన్నాయి."
                "Kannada" -> "ಇಂದು ನಿಮಗೆ 3 ಕೆಲಸಗಳಿವೆ."
                else -> "You have 3 jobs today. First job at 11 AM in Koregaon Park."
            }
        }
    }
}
