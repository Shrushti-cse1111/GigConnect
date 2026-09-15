package com.gigconnect.app.data.demo

import com.gigconnect.app.data.model.*

/**
 * GigConnect Demo Data Provider
 * Provides realistic, location-aware mock data for all 12 Indian cities.
 * All data is clearly labeled as DEMO DATA.
 */
object DemoDataProvider {

    // ─── Demo Cities ─────────────────────────────────────────────────────
    val demoCities = listOf(
        DemoCity("Pune", "Maharashtra", "Koregaon Park", "411001", 18.5362, 73.8945, listOf("Marathi", "Hindi", "English")),
        DemoCity("Mumbai", "Maharashtra", "Andheri West", "400053", 19.1196, 72.8468, listOf("Marathi", "Hindi", "English")),
        DemoCity("Nagpur", "Maharashtra", "Sitabuldi", "440012", 21.1458, 79.0882, listOf("Marathi", "Hindi", "English")),
        DemoCity("Bengaluru", "Karnataka", "Koramangala", "560034", 12.9352, 77.6245, listOf("Kannada", "Hindi", "English")),
        DemoCity("Hyderabad", "Telangana", "Banjara Hills", "500034", 17.4126, 78.4490, listOf("Telugu", "Hindi", "English", "Urdu")),
        DemoCity("Delhi", "Delhi", "Lajpat Nagar", "110024", 28.5653, 77.2434, listOf("Hindi", "Punjabi", "English")),
        DemoCity("Chennai", "Tamil Nadu", "T. Nagar", "600017", 13.0418, 80.2341, listOf("Tamil", "English", "Hindi")),
        DemoCity("Kolkata", "West Bengal", "Salt Lake", "700064", 22.5726, 88.4312, listOf("Bengali", "Hindi", "English")),
        DemoCity("Jaipur", "Rajasthan", "Malviya Nagar", "302017", 26.8467, 75.8234, listOf("Hindi", "Rajasthani", "English")),
        DemoCity("Kochi", "Kerala", "Ernakulam", "682016", 9.9312, 76.2673, listOf("Malayalam", "English", "Hindi")),
        DemoCity("Ahmedabad", "Gujarat", "Navrangpura", "380009", 23.0225, 72.5714, listOf("Gujarati", "Hindi", "English")),
        DemoCity("Lucknow", "Uttar Pradesh", "Hazratganj", "226001", 26.8467, 80.9462, listOf("Hindi", "Urdu", "English"))
    )

    var selectedCity: DemoCity = demoCities[0]

    // ─── Service Categories ───────────────────────────────────────────────
    val serviceCategories = listOf(
        ServiceCategory("plumbing", "Plumbing", "🔧", 500, "Pipe repair, fittings & water systems"),
        ServiceCategory("electrical", "Electrical", "⚡", 450, "Wiring, switches & electrical repairs"),
        ServiceCategory("carpentry", "Carpentry", "🪚", 600, "Furniture, doors & woodwork"),
        ServiceCategory("painting", "Painting", "🎨", 400, "Interior & exterior painting"),
        ServiceCategory("cleaning", "Cleaning", "🧹", 350, "Home & office deep cleaning"),
        ServiceCategory("appliance", "Appliance Repair", "🔨", 550, "AC, washing machine, refrigerator"),
        ServiceCategory("gardening", "Gardening", "🌿", 300, "Garden maintenance & landscaping"),
        ServiceCategory("driving", "Driving", "🚗", 500, "Personal driver & cab service"),
        ServiceCategory("caregiving", "Caregiving", "❤️", 700, "Elder care & patient assistance"),
        ServiceCategory("domestic", "Domestic Help", "🏠", 300, "Cooking, cleaning & household tasks"),
        ServiceCategory("technician", "Technician", "💻", 600, "Computer, mobile & IT support"),
        ServiceCategory("other", "Other Services", "⭐", 400, "Other local services")
    )

    // ─── Workers (location-aware) ─────────────────────────────────────────
    val puneWorkers = listOf(
        Worker(id = "w1", name = "Ramesh Kumar", skills = listOf("Pipe Repair", "Bathroom Plumbing", "Water Tank Repair", "Leakage Detection"),
            primarySkill = "Plumbing", rating = 4.8f, totalReviews = 125, completedJobs = 125, distanceKm = 2.4f,
            languages = listOf("Hindi", "Marathi"), experience = "7+ years", basePrice = 500,
            currentWorkload = 1, fairRotationScore = 0.82f, equityBalance = 12450, weeklyEarnings = 8450),
        Worker(id = "w2", name = "Suresh Patil", skills = listOf("Wiring", "Switch Repair", "MCB Installation", "Inverter Setup"),
            primarySkill = "Electrical", rating = 4.6f, totalReviews = 98, completedJobs = 98, distanceKm = 3.1f,
            languages = listOf("Marathi", "Hindi"), experience = "5+ years", basePrice = 450,
            currentWorkload = 3, fairRotationScore = 0.65f, equityBalance = 9800, weeklyEarnings = 7200),
        Worker(id = "w3", name = "Anita Devi", skills = listOf("Deep Cleaning", "Kitchen Cleaning", "Office Cleaning"),
            primarySkill = "Cleaning", rating = 4.9f, totalReviews = 212, completedJobs = 212, distanceKm = 1.8f,
            languages = listOf("Hindi", "Marathi", "English"), experience = "4+ years", basePrice = 350,
            currentWorkload = 0, fairRotationScore = 0.91f, equityBalance = 7600, weeklyEarnings = 6800),
        Worker(id = "w4", name = "Pradeep Sharma", skills = listOf("AC Repair", "Refrigerator", "Washing Machine"),
            primarySkill = "Appliance Repair", rating = 4.7f, totalReviews = 156, completedJobs = 156, distanceKm = 4.2f,
            languages = listOf("Hindi", "English"), experience = "8+ years", basePrice = 600,
            currentWorkload = 2, fairRotationScore = 0.75f, equityBalance = 15200, weeklyEarnings = 9500),
        Worker(id = "w5", name = "Meena Bhosale", skills = listOf("Cooking", "House Cleaning", "Child Care"),
            primarySkill = "Domestic Help", rating = 4.5f, totalReviews = 88, completedJobs = 88, distanceKm = 0.9f,
            languages = listOf("Marathi", "Hindi"), experience = "6+ years", basePrice = 300,
            currentWorkload = 1, fairRotationScore = 0.88f, equityBalance = 5400, weeklyEarnings = 4900)
    )

    val bengaluruWorkers = listOf(
        Worker(id = "w6", name = "Ravi Kumar", skills = listOf("AC Service", "Refrigerator Repair", "Microwave"),
            primarySkill = "Appliance Repair", rating = 4.9f, totalReviews = 302, completedJobs = 302, distanceKm = 1.5f,
            languages = listOf("Kannada", "Hindi", "English"), experience = "10+ years", basePrice = 600,
            currentWorkload = 1, fairRotationScore = 0.78f, equityBalance = 22000, weeklyEarnings = 12000),
        Worker(id = "w7", name = "Lakshmi S", skills = listOf("Deep Cleaning", "Sofa Cleaning", "Carpet Cleaning"),
            primarySkill = "Cleaning", rating = 4.7f, totalReviews = 189, completedJobs = 189, distanceKm = 2.8f,
            languages = listOf("Kannada", "Tamil", "English"), experience = "5+ years", basePrice = 400,
            currentWorkload = 0, fairRotationScore = 0.92f, equityBalance = 8900, weeklyEarnings = 7400),
        Worker(id = "w8", name = "Mohan Gowda", skills = listOf("Pipe Repair", "Bore Well", "Drainage"),
            primarySkill = "Plumbing", rating = 4.6f, totalReviews = 142, completedJobs = 142, distanceKm = 3.5f,
            languages = listOf("Kannada", "Hindi"), experience = "9+ years", basePrice = 500,
            currentWorkload = 2, fairRotationScore = 0.70f, equityBalance = 11200, weeklyEarnings = 8800)
    )

    val delhiWorkers = listOf(
        Worker(id = "w9", name = "Mukesh Singh", skills = listOf("Wiring", "Fan Installation", "Geyser Fitting"),
            primarySkill = "Electrical", rating = 4.5f, totalReviews = 234, completedJobs = 234, distanceKm = 2.1f,
            languages = listOf("Hindi", "Punjabi"), experience = "12+ years", basePrice = 500,
            currentWorkload = 4, fairRotationScore = 0.55f, equityBalance = 18900, weeklyEarnings = 11200),
        Worker(id = "w10", name = "Rajesh Tiwari", skills = listOf("Carpentry", "Door Fitting", "Furniture Repair"),
            primarySkill = "Carpentry", rating = 4.8f, totalReviews = 178, completedJobs = 178, distanceKm = 1.7f,
            languages = listOf("Hindi", "English"), experience = "15+ years", basePrice = 650,
            currentWorkload = 1, fairRotationScore = 0.85f, equityBalance = 16700, weeklyEarnings = 10400)
    )

    val hyderabadWorkers = listOf(
        Worker(id = "w11", name = "Venkat Rao", skills = listOf("Painting", "Waterproofing", "Texture Coating"),
            primarySkill = "Painting", rating = 4.7f, totalReviews = 119, completedJobs = 119, distanceKm = 3.2f,
            languages = listOf("Telugu", "Hindi"), experience = "8+ years", basePrice = 450,
            currentWorkload = 2, fairRotationScore = 0.72f, equityBalance = 9600, weeklyEarnings = 7800),
        Worker(id = "w12", name = "Sravani Devi", skills = listOf("Elder Care", "Patient Assistance", "Physiotherapy Aid"),
            primarySkill = "Caregiving", rating = 4.9f, totalReviews = 87, completedJobs = 87, distanceKm = 2.0f,
            languages = listOf("Telugu", "English", "Hindi"), experience = "6+ years", basePrice = 800,
            currentWorkload = 0, fairRotationScore = 0.95f, equityBalance = 12100, weeklyEarnings = 11000)
    )

    fun getWorkerById(id: String): Worker? {
        val all = puneWorkers + bengaluruWorkers + delhiWorkers + hyderabadWorkers
        return all.find { it.id == id } ?: puneWorkers.firstOrNull()
    }

    fun getWorkersForCity(cityName: String): List<Worker> {
        return when (cityName) {
            "Pune" -> puneWorkers
            "Bengaluru" -> bengaluruWorkers
            "Delhi" -> delhiWorkers
            "Hyderabad" -> hyderabadWorkers
            else -> puneWorkers.map { it.copy(distanceKm = (1.0f + Math.random() * 5).toFloat()) }
        }
    }

    fun getWorkersForService(cityName: String, serviceId: String): List<Worker> {
        val all = getWorkersForCity(cityName)
        return when (serviceId) {
            "plumbing" -> all.filter { it.primarySkill == "Plumbing" }.ifEmpty { all.take(3) }
            "electrical" -> all.filter { it.primarySkill == "Electrical" }.ifEmpty { all.take(3) }
            "cleaning" -> all.filter { it.primarySkill == "Cleaning" }.ifEmpty { all.take(3) }
            "appliance" -> all.filter { it.primarySkill == "Appliance Repair" }.ifEmpty { all.take(3) }
            "caregiving" -> all.filter { it.primarySkill == "Caregiving" }.ifEmpty { all.take(3) }
            "domestic" -> all.filter { it.primarySkill == "Domestic Help" }.ifEmpty { all.take(3) }
            "carpentry" -> all.filter { it.primarySkill == "Carpentry" }.ifEmpty { all.take(3) }
            "painting" -> all.filter { it.primarySkill == "Painting" }.ifEmpty { all.take(3) }
            else -> all
        }
    }

    // ─── SmartMatch ───────────────────────────────────────────────────────
    fun computeSmartMatch(workers: List<Worker>, serviceId: String): List<SmartMatchResult> {
        return workers.map { w ->
            val skillScore = if (w.primarySkill.lowercase().contains(serviceId.take(4).lowercase())) 98 else 85
            val distScore = ((10 - w.distanceKm) / 10 * 100).toInt().coerceIn(40, 100)
            val availScore = if (w.currentWorkload == 0) 100 else (100 - w.currentWorkload * 15).coerceIn(20, 100)
            val ratingScore = (w.rating / 5.0 * 100).toInt()
            val workloadScore = (100 - w.currentWorkload * 20).coerceIn(0, 100)
            val fairnessScore = (w.fairRotationScore * 100).toInt()
            val overall = (skillScore * 0.30 + distScore * 0.20 + availScore * 0.20 + ratingScore * 0.15 + workloadScore * 0.10 + fairnessScore * 0.05).toInt()
            SmartMatchResult(w, overall, skillScore, distScore, availScore, ratingScore, workloadScore, fairnessScore)
        }.sortedByDescending { it.overallScore }
    }

    // ─── FairMatch Demo ───────────────────────────────────────────────────
    fun getFairMatchCandidates(): List<FairMatchCandidate> {
        val w = puneWorkers
        return listOf(
            FairMatchCandidate(w[0], skillMatch = 95, distanceKm = 2.0f, currentJobs = 5, rating = 4.8f,
                isRecommended = false, reason = "High skill match but higher workload"),
            FairMatchCandidate(w[1], skillMatch = 91, distanceKm = 3.0f, currentJobs = 1, rating = 4.6f,
                isRecommended = true, reason = "Similar skill fit + lower workload → improves fair job distribution"),
        )
    }

    // ─── Bookings ─────────────────────────────────────────────────────────
    val demoBookings = listOf(
        Booking(id = "BK1024", customerId = "u1", customerName = "Demo User", workerId = "w1", workerName = "Ramesh Kumar",
            serviceCategory = "Plumbing", serviceDetail = "Bathroom Pipe Repair", date = "16 Sep 2026",
            time = "10:00 AM", address = "Flat 3B, Koregaon Park, Pune", status = BookingStatus.COMPLETED,
            pricing = PricingBreakdown(500, 50, 430, 20), createdAt = "14 Sep 2026"),
        Booking(id = "BK1025", customerId = "u1", customerName = "Demo User", workerId = "w3", workerName = "Anita Devi",
            serviceCategory = "Cleaning", serviceDetail = "Deep Home Cleaning", date = "17 Sep 2026",
            time = "9:00 AM", address = "Flat 3B, Koregaon Park, Pune", status = BookingStatus.CONFIRMED,
            pricing = PricingBreakdown(350, 35, 300, 15), createdAt = "15 Sep 2026"),
        Booking(id = "BK1026", customerId = "u1", customerName = "Demo User", workerId = "w4", workerName = "Pradeep Sharma",
            serviceCategory = "Appliance Repair", serviceDetail = "AC Service", date = "18 Sep 2026",
            time = "2:00 PM", address = "Flat 3B, Koregaon Park, Pune", status = BookingStatus.PENDING,
            pricing = PricingBreakdown(600, 60, 510, 30), createdAt = "15 Sep 2026")
    )

    // ─── Worker Jobs ──────────────────────────────────────────────────────
    val demoJobs = listOf(
        Job(id = "J2041", customerId = "u2", customerName = "Priya Mehta", customerVerified = true,
            serviceCategory = "Plumbing", serviceDetail = "Bathroom Pipe Repair", distanceKm = 1.8f,
            estimatedDuration = "2 hours", estimatedEarnings = 430, aiMatchScore = 92, status = JobStatus.NEW,
            address = "12 Shivajinagar, Pune", scheduledDate = "16 Sep 2026", scheduledTime = "11:00 AM"),
        Job(id = "J2042", customerId = "u3", customerName = "Amit Shah", customerVerified = true,
            serviceCategory = "Plumbing", serviceDetail = "Water Tank Cleaning", distanceKm = 3.2f,
            estimatedDuration = "1.5 hours", estimatedEarnings = 380, aiMatchScore = 85, status = JobStatus.NEW,
            address = "45 Kothrud, Pune", scheduledDate = "16 Sep 2026", scheduledTime = "3:00 PM"),
        Job(id = "J2039", customerId = "u4", customerName = "Sunita Rao", customerVerified = true,
            serviceCategory = "Plumbing", serviceDetail = "Leakage Detection", distanceKm = 2.5f,
            estimatedDuration = "1 hour", estimatedEarnings = 300, aiMatchScore = 78, status = JobStatus.COMPLETED,
            address = "88 Viman Nagar, Pune", scheduledDate = "15 Sep 2026", scheduledTime = "10:00 AM")
    )

    // ─── Equity Wallet ────────────────────────────────────────────────────
    val equityEntries = listOf(
        EquityEntry("J1024", 35, "14 Sep 2026", "Plumbing — Koregaon Park"),
        EquityEntry("J1025", 42, "13 Sep 2026", "Pipe Repair — Shivajinagar"),
        EquityEntry("J1026", 28, "12 Sep 2026", "Water Tank — Kothrud"),
        EquityEntry("J1023", 38, "11 Sep 2026", "Bathroom Fitting — Kalyani Nagar"),
        EquityEntry("J1022", 45, "10 Sep 2026", "Emergency Plumbing — Aundh"),
        EquityEntry("J1021", 30, "09 Sep 2026", "Drainage Fix — Camp Area"),
        EquityEntry("J1020", 33, "08 Sep 2026", "Pipe Installation — Hinjewadi")
    )

    // ─── Skill Recommendations ────────────────────────────────────────────
    val skillRecommendations = listOf(
        SkillRecommendation("AC Maintenance", 32, "4 hours", 72,
            "Customers near your area frequently request AC maintenance. Completing this module may improve your eligible job opportunities.", true),
        SkillRecommendation("Solar Panel Cleaning", 18, "2 hours", 0,
            "Solar panel installations are increasing in Pune. This skill can help you access new job categories.", false),
        SkillRecommendation("Sensor-based Leak Detection", 24, "3 hours", 45,
            "Smart home devices are growing. Upskilling in sensor-based detection improves your job match score.", false),
        SkillRecommendation("Water Purifier Service", 15, "2 hours", 100,
            "Water purifier service is a high-frequency request. You are already certified!", false)
    )

    // ─── Admin Stats ──────────────────────────────────────────────────────
    val adminStats = AdminStats(
        verifiedWorkers = 1248, activeJobs = 286, completedToday = 512,
        welfareEnrollmentPercent = 94, totalRevenue = 2450000, pendingVerifications = 23
    )

    val demandForecasts = listOf(
        DemandForecast("Pune", "Maharashtra", "Plumbing", DemandLevel.HIGH, 28, listOf(42, 55, 61, 48, 52, 67, 70)),
        DemandForecast("Mumbai", "Maharashtra", "Cleaning", DemandLevel.HIGH, 35, listOf(90, 102, 98, 115, 88, 120, 130)),
        DemandForecast("Nagpur", "Maharashtra", "Electrical", DemandLevel.MEDIUM, 12, listOf(28, 32, 30, 35, 27, 33, 36)),
        DemandForecast("Bengaluru", "Karnataka", "Appliance Repair", DemandLevel.HIGH, 45, listOf(68, 72, 80, 77, 85, 90, 95)),
        DemandForecast("Delhi", "Delhi", "Caregiving", DemandLevel.MEDIUM, 20, listOf(45, 48, 52, 44, 55, 58, 60)),
        DemandForecast("Hyderabad", "Telangana", "Painting", DemandLevel.LOW, -5, listOf(22, 20, 18, 25, 19, 21, 22)),
        DemandForecast("Chennai", "Tamil Nadu", "Carpentry", DemandLevel.MEDIUM, 18, listOf(35, 38, 40, 37, 42, 39, 44)),
        DemandForecast("Kolkata", "West Bengal", "Domestic Help", DemandLevel.HIGH, 30, listOf(55, 62, 68, 60, 72, 75, 80)),
        DemandForecast("Jaipur", "Rajasthan", "Gardening", DemandLevel.LOW, 8, listOf(15, 18, 16, 20, 14, 17, 19)),
        DemandForecast("Kochi", "Kerala", "Technician", DemandLevel.MEDIUM, 22, listOf(28, 30, 35, 29, 33, 36, 38)),
        DemandForecast("Ahmedabad", "Gujarat", "Plumbing", DemandLevel.MEDIUM, 15, listOf(38, 42, 40, 45, 38, 44, 47)),
        DemandForecast("Lucknow", "Uttar Pradesh", "Electrical", DemandLevel.HIGH, 38, listOf(50, 55, 60, 52, 65, 68, 72))
    )

    // ─── Welfare ──────────────────────────────────────────────────────────
    val welfareStats = WelfareStats(
        insuranceEnrolledPercent = 94, pensionEnrolledPercent = 81,
        grievancesResolvedPercent = 96, skillsTrainingPercent = 68,
        totalInsuredWorkers = 1173, activeClaims = 14
    )

    // ─── Grievances ───────────────────────────────────────────────────────
    val grievanceCases = listOf(
        GrievanceCase("G001", "Ramesh Kumar", "Payment Dispute", "Payment not received for job J2033 completed on 12 Sep", "Under Review", "13 Sep 2026"),
        GrievanceCase("G002", "Anita Devi", "Customer Behaviour", "Customer was rude and did not allow break time", "Resolved", "10 Sep 2026", "12 Sep 2026"),
        GrievanceCase("G003", "Suresh Patil", "Safety Concern", "Electrical panel was unsafe — requested better PPE support", "Resolved", "08 Sep 2026", "10 Sep 2026"),
        GrievanceCase("G004", "Meena Bhosale", "Job Cancellation", "Job cancelled 1 hour before without proper notice", "Pending", "14 Sep 2026"),
        GrievanceCase("G005", "Pradeep Sharma", "Platform Issue", "App crashed during job acceptance", "Resolved", "05 Sep 2026", "07 Sep 2026"),
        GrievanceCase("G006", "Unknown Worker", "Rating Dispute", "Received 1-star rating without reason — requesting review", "Under Review", "15 Sep 2026")
    )

    // ─── Voting Proposals ─────────────────────────────────────────────────
    val votingProposals = listOf(
        VotingProposal("V001", "Skill Development Fund Allocation",
            "Should 2% of platform contribution be allocated toward worker skill development programs?",
            "Federation Committee", approveVotes = 834, rejectVotes = 124, totalEligibleVoters = 1248,
            deadline = "30 Sep 2026", status = "ACTIVE"),
        VotingProposal("V002", "Emergency Welfare Enhancement",
            "Increase the emergency welfare payout from ₹5,000 to ₹10,000 per eligible incident.",
            "Workers' Welfare Committee", approveVotes = 1021, rejectVotes = 87, totalEligibleVoters = 1248,
            deadline = "25 Sep 2026", status = "ACTIVE"),
        VotingProposal("V003", "Night Shift Safety Protocol",
            "Implement mandatory 2-hourly safety check-in for all jobs starting after 8 PM.",
            "Safety Committee", approveVotes = 1189, rejectVotes = 15, totalEligibleVoters = 1248,
            deadline = "20 Sep 2026", status = "PASSED")
    )

    // ─── Demo Users ───────────────────────────────────────────────────────
    val seekerUser = User(id = "u1", name = "Demo Seeker", phone = "+91 98765 43210",
        role = UserRole.SEEKER, location = Location("Maharashtra", "Pune", "Koregaon Park", "411001"))

    val workerUser = User(id = "w1", name = "Ramesh Kumar", phone = "+91 87654 32109",
        role = UserRole.WORKER, location = Location("Maharashtra", "Pune", "Shivajinagar", "411005"))

    val adminUser = User(id = "a1", name = "Admin — Pune Federation", phone = "+91 76543 21098",
        role = UserRole.ADMIN, location = Location("Maharashtra", "Pune", "Pune District", "411001"))
}
