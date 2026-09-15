package com.gigconnect.app.navigation

object Routes {
    // Onboarding
    const val WELCOME = "welcome"
    const val LOCATION_SELECTION = "location_selection"
    const val ROLE_SELECTION = "role_selection"

    // Seeker
    const val SEEKER_HOME = "seeker_home"
    const val WORKER_LIST = "worker_list/{serviceId}"
    const val WORKER_PROFILE = "worker_profile/{workerId}"
    const val SMART_MATCH = "smart_match"
    const val BOOKING = "booking"
    const val BOOKING_CONFIRMATION = "booking_confirmation"
    const val LIVE_TRACKING = "live_tracking"
    const val PAYMENT = "payment"
    const val PAYMENT_SUCCESS = "payment_success"
    const val RATING = "rating"
    const val BOOKINGS_LIST = "bookings_list"
    const val SEEKER_PROFILE = "seeker_profile"

    // Worker
    const val WORKER_HOME = "worker_home"
    const val JOBS_LIST = "jobs_list"
    const val JOB_DETAIL = "job_detail/{jobId}"
    const val EARNINGS = "earnings"
    const val EQUITY_WALLET = "equity_wallet"
    const val SKILL_UP = "skill_up"
    const val VOICE_ASSISTANT = "voice_assistant"
    const val SAFETY_CENTER = "safety_center"
    const val WORKER_PASSPORT = "worker_passport"

    // Admin
    const val ADMIN_DASHBOARD = "admin_dashboard"
    const val DEMAND_INTELLIGENCE = "demand_intelligence"
    const val FAIR_MATCH = "fair_match"
    const val WORKER_MANAGEMENT = "worker_management"
    const val WELFARE = "welfare"
    const val DIGITAL_PANCHAYAT = "digital_panchayat"
    const val COOPERATIVE_VOTING = "cooperative_voting"
    const val COOPERATIVE_STRUCTURE = "cooperative_structure"
    const val ARCHITECTURE = "architecture"

    fun workerList(serviceId: String) = "worker_list/$serviceId"
    fun workerProfile(workerId: String) = "worker_profile/$workerId"
    fun jobDetail(jobId: String) = "job_detail/$jobId"
}
