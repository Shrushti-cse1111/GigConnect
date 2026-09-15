package com.gigconnect.app.data.model

// ─────────────────────────────────────────────
//  Core enums
// ─────────────────────────────────────────────

enum class UserRole { SEEKER, WORKER, ADMIN }

enum class BookingStatus {
    PENDING, CONFIRMED, WORKER_ASSIGNED, WORKER_ON_WAY,
    SERVICE_STARTED, COMPLETED, CANCELLED, RATED
}

enum class JobStatus { NEW, ACCEPTED, IN_PROGRESS, COMPLETED, REJECTED }

enum class VerificationStatus { VERIFIED, PENDING, NEEDS_REVIEW }

enum class PaymentMethod { UPI, CARD, WALLET }

enum class DemandLevel { HIGH, MEDIUM, LOW }

enum class VoteChoice { APPROVE, REJECT, ABSTAIN }

// ─────────────────────────────────────────────
//  User
// ─────────────────────────────────────────────

data class User(
    val id: String,
    val name: String,
    val phone: String,
    val role: UserRole,
    val profileImageUrl: String = "",
    val location: Location = Location(),
    val languages: List<String> = listOf("Hindi", "English")
)

// ─────────────────────────────────────────────
//  Location
// ─────────────────────────────────────────────

data class Location(
    val state: String = "Maharashtra",
    val city: String = "Pune",
    val locality: String = "Koregaon Park",
    val pincode: String = "411001",
    val latitude: Double = 18.5362,
    val longitude: Double = 73.8945
)

data class DemoCity(
    val name: String,
    val state: String,
    val locality: String,
    val pincode: String,
    val latitude: Double,
    val longitude: Double,
    val primaryLanguages: List<String>
)

// ─────────────────────────────────────────────
//  Worker
// ─────────────────────────────────────────────

data class Worker(
    val id: String,
    val name: String,
    val skills: List<String>,
    val primarySkill: String,
    val rating: Float,
    val totalReviews: Int,
    val completedJobs: Int,
    val distanceKm: Float,
    val languages: List<String>,
    val isVerified: Boolean = true,
    val verificationStatus: VerificationStatus = VerificationStatus.VERIFIED,
    val isAvailable: Boolean = true,
    val profileImageUrl: String = "",
    val experience: String,
    val basePrice: Int,
    val serviceArea: String = "Within 10 km",
    val currentWorkload: Int = 0, // active jobs count
    val fairRotationScore: Float = 0f,
    val welfareEnrolled: Boolean = true,
    val cooperativeMember: Boolean = true,
    val equityBalance: Int = 0,
    val weeklyEarnings: Int = 0,
    val location: Location = Location()
)

// ─────────────────────────────────────────────
//  Service
// ─────────────────────────────────────────────

data class ServiceCategory(
    val id: String,
    val name: String,
    val icon: String, // emoji icon
    val basePrice: Int,
    val description: String
)

// ─────────────────────────────────────────────
//  Booking
// ─────────────────────────────────────────────

data class Booking(
    val id: String,
    val customerId: String,
    val customerName: String,
    val workerId: String,
    val workerName: String,
    val serviceCategory: String,
    val serviceDetail: String,
    val date: String,
    val time: String,
    val address: String,
    val additionalInstructions: String = "",
    val status: BookingStatus,
    val pricing: PricingBreakdown,
    val createdAt: String
)

data class PricingBreakdown(
    val servicePrice: Int,
    val platformContribution: Int,
    val workerEarnings: Int,
    val welfareContribution: Int
) {
    val total: Int get() = servicePrice + platformContribution + welfareContribution
}

// ─────────────────────────────────────────────
//  Transaction / Equity
// ─────────────────────────────────────────────

data class Transaction(
    val id: String,
    val bookingId: String,
    val servicePrice: Int,
    val platformContribution: Int,
    val workerEarnings: Int,
    val welfareContribution: Int,
    val equityCredit: Int,
    val timestamp: String
)

data class EquityEntry(
    val jobId: String,
    val amount: Int,
    val date: String,
    val description: String
)

// ─────────────────────────────────────────────
//  Jobs (Worker view of a booking request)
// ─────────────────────────────────────────────

data class Job(
    val id: String,
    val customerId: String,
    val customerName: String,
    val customerVerified: Boolean = true,
    val serviceCategory: String,
    val serviceDetail: String,
    val distanceKm: Float,
    val estimatedDuration: String,
    val estimatedEarnings: Int,
    val aiMatchScore: Int,
    val status: JobStatus,
    val address: String,
    val scheduledDate: String,
    val scheduledTime: String,
    val urgency: String = "Normal"
)

// ─────────────────────────────────────────────
//  Admin / Federation
// ─────────────────────────────────────────────

data class AdminStats(
    val verifiedWorkers: Int,
    val activeJobs: Int,
    val completedToday: Int,
    val welfareEnrollmentPercent: Int,
    val totalRevenue: Int,
    val pendingVerifications: Int,
    val activeWorkers: Int = verifiedWorkers,
    val pendingVerification: Int = pendingVerifications,
    val jobsToday: Int = completedToday,
    val totalPayout: Int = totalRevenue
)

data class DemandForecast(
    val city: String,
    val state: String,
    val service: String,
    val demandLevel: DemandLevel,
    val percentChange: Int,
    val forecast7Days: List<Int>, // daily job counts
    val serviceCategory: String = service,
    val urgencyLevel: String = demandLevel.name,
    val area: String = city,
    val predictedDemand: Int = forecast7Days.lastOrNull() ?: 50,
    val availableWorkers: Int = ((forecast7Days.lastOrNull() ?: 50) * 0.7).toInt().coerceAtLeast(1),
    val gap: Int = (forecast7Days.lastOrNull() ?: 50) - ((forecast7Days.lastOrNull() ?: 50) * 0.7).toInt().coerceAtLeast(1),
    val aiInsight: String = "High demand expected based on historical seasonal trends."
)

data class FairMatchCandidate(
    val worker: Worker,
    val skillMatch: Int,
    val distanceKm: Float,
    val currentJobs: Int,
    val rating: Float,
    val isRecommended: Boolean,
    val reason: String,
    val workerName: String = worker.name,
    val workerRating: Float = rating,
    val fairMatchScore: Int = ((skillMatch * 0.5f) + ((10f - distanceKm).coerceAtLeast(0f) * 3f) + ((5 - currentJobs).coerceAtLeast(0) * 4f)).toInt().coerceIn(60, 99),
    val currentWorkload: Int = currentJobs,
    val idleSince: String = "2 hours ago"
)

// ─────────────────────────────────────────────
//  Welfare
// ─────────────────────────────────────────────

data class WelfareStats(
    val insuranceEnrolledPercent: Int,
    val pensionEnrolledPercent: Int,
    val grievancesResolvedPercent: Int,
    val skillsTrainingPercent: Int,
    val totalInsuredWorkers: Int,
    val activeClaims: Int,
    val insuredWorkers: Int = totalInsuredWorkers,
    val totalFundCollected: Int = 1850000,
    val claimsThisMonth: Int = activeClaims
)

// ─────────────────────────────────────────────
//  Digital Panchayat
// ─────────────────────────────────────────────

data class GrievanceCase(
    val id: String,
    val raisedBy: String,
    val category: String,
    val description: String,
    val status: String,
    val raisedDate: String,
    val resolvedDate: String? = null
)

// ─────────────────────────────────────────────
//  Cooperative Voting
// ─────────────────────────────────────────────

data class VotingProposal(
    val id: String,
    val title: String,
    val description: String,
    val proposedBy: String,
    val approveVotes: Int,
    val rejectVotes: Int,
    val totalEligibleVoters: Int,
    val deadline: String,
    val status: String, // ACTIVE, PASSED, REJECTED, EXPIRED
    val userVote: VoteChoice? = null
)

// ─────────────────────────────────────────────
//  Skill-Up AI
// ─────────────────────────────────────────────

data class SkillRecommendation(
    val skillName: String,
    val demandTrend: Int, // percentage increase
    val courseDuration: String,
    val progressPercent: Int,
    val aiReason: String,
    val isRecommended: Boolean = false
)

// ─────────────────────────────────────────────
//  SmartMatch
// ─────────────────────────────────────────────

data class SmartMatchResult(
    val worker: Worker,
    val overallScore: Int,
    val skillScore: Int,
    val distanceScore: Int,
    val availabilityScore: Int,
    val ratingScore: Int,
    val workloadScore: Int,
    val fairnessScore: Int
)
