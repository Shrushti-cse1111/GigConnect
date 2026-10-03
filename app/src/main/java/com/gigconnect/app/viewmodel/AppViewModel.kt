package com.gigconnect.app.viewmodel

import androidx.lifecycle.ViewModel
import com.gigconnect.app.data.demo.DemoDataProvider
import com.gigconnect.app.data.model.DemoCity
import com.gigconnect.app.data.model.User
import com.gigconnect.app.data.model.UserRole
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

/**
 * App-level ViewModel shared across all screens.
 * Controls role switching, demo location, authentication, language, and connectivity simulation.
 */
class AppViewModel : ViewModel() {

    // ─── Authentication & User Session ──────────────────────────────────
    private val _isLoggedIn = MutableStateFlow(true)
    val isLoggedIn: StateFlow<Boolean> = _isLoggedIn.asStateFlow()

    private val _currentUser = MutableStateFlow<User?>(DemoDataProvider.seekerUser)
    val currentUser: StateFlow<User?> = _currentUser.asStateFlow()

    // ─── Language ────────────────────────────────────────────────────────
    val supportedLanguages = listOf(
        "English" to "English",
        "हिंदी" to "Hindi",
        "मराठी" to "Marathi",
        "ಕನ್ನಡ" to "Kannada",
        "தமிழ்" to "Tamil",
        "తెలుగు" to "Telugu",
        "বাংলা" to "Bengali",
        "ગુજરાતી" to "Gujarati"
    )

    private val _selectedLanguage = MutableStateFlow("English")
    val selectedLanguage: StateFlow<String> = _selectedLanguage.asStateFlow()

    // ─── Role & Location ─────────────────────────────────────────────────
    private val _currentRole = MutableStateFlow(UserRole.SEEKER)
    val currentRole: StateFlow<UserRole> = _currentRole.asStateFlow()

    private val _selectedCity = MutableStateFlow(DemoDataProvider.demoCities[0])
    val selectedCity: StateFlow<DemoCity> = _selectedCity.asStateFlow()

    private val _currentCity = MutableStateFlow(DemoDataProvider.demoCities[0].name)
    val currentCity: StateFlow<String> = _currentCity.asStateFlow()

    private val _isOffline = MutableStateFlow(false)
    val isOffline: StateFlow<Boolean> = _isOffline.asStateFlow()

    private val _showRoleSwitcher = MutableStateFlow(false)
    val showRoleSwitcher: StateFlow<Boolean> = _showRoleSwitcher.asStateFlow()

    private val _showLocationSwitcher = MutableStateFlow(false)
    val showLocationSwitcher: StateFlow<Boolean> = _showLocationSwitcher.asStateFlow()

    fun login(phone: String, name: String, role: UserRole) {
        val user = when (role) {
            UserRole.SEEKER -> DemoDataProvider.seekerUser.copy(name = name.ifBlank { "Customer" }, phone = phone)
            UserRole.WORKER -> DemoDataProvider.workerUser.copy(name = name.ifBlank { "Ramesh Kumar" }, phone = phone)
            UserRole.ADMIN -> DemoDataProvider.adminUser.copy(name = name.ifBlank { "Admin" }, phone = phone)
        }
        _currentUser.value = user
        _isLoggedIn.value = true
        _currentRole.value = role
    }

    fun logout() {
        _isLoggedIn.value = false
        _currentUser.value = null
    }

    fun setLanguage(language: String) {
        _selectedLanguage.value = language
    }

    fun setRole(role: UserRole) {
        _currentRole.value = role
    }

    fun setCity(city: DemoCity) {
        _selectedCity.value = city
        _currentCity.value = city.name
        DemoDataProvider.selectedCity = city
    }

    fun setCity(cityName: String) {
        val found = DemoDataProvider.demoCities.find { it.name.equals(cityName, ignoreCase = true) }
            ?: DemoDataProvider.demoCities.first()
        setCity(found)
    }

    fun toggleOffline() {
        _isOffline.value = !_isOffline.value
    }

    fun showRoleSwitcher() { _showRoleSwitcher.value = true }
    fun hideRoleSwitcher() { _showRoleSwitcher.value = false }
    fun showLocationSwitcher() { _showLocationSwitcher.value = true }
    fun hideLocationSwitcher() { _showLocationSwitcher.value = false }

    val demoCities = DemoDataProvider.demoCities
}

