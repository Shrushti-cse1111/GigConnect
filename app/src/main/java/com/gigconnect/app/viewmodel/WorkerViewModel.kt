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

    val worker = DemoDataProvider.puneWorkers.first()

    fun toggleAvailability() {
        _isAvailable.value = !_isAvailable.value
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
            _todayJobs.value += 1
            delay(500)
            // Simulate earnings update
            _weeklyEarnings.value += updated.find { it.id == jobId }?.estimatedEarnings ?: 0
        }
    }

    fun rejectJob(jobId: String) {
        // Rejecting a job does NOT penalize the worker — just removes it from queue
        _jobs.value = _jobs.value.filter { it.id != jobId }
    }

    fun simulateVoiceInput(language: String = "English") {
        viewModelScope.launch {
            _isVoiceListening.value = true
            _voiceResult.value = ""
            _detectedLanguage.value = language
            delay(2500) // Simulated listening
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
