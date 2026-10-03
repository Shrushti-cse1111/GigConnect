package com.gigconnect.app.data.model

// ─────────────────────────────────────────────
//  Core enums
// ─────────────────────────────────────────────

enum class UserRole { SEEKER, WORKER, ADMIN }

enum class BookingStatus {
    PENDING, MATCHING, CONFIRMED, WORKER_ASSIGNED, WORKER_ON_WAY,
    WORKER_ARRIVED, SERVICE_STARTED, SERVICE_COMPLETED,
    PAYMENT_SETTLED, CANCELLED, DISPUTED, RATED
}

enum class EscrowStatus {
    PENDING, AUTHORIZED_HELD, RELEASED_SETTLED, REFUNDED, FROZEN_DISPUTE
}

enum class DisputeCategory(val label: String, val icon: String) {
    WORKER_NO_SHOW("Worker did not arrive", "🚶‍♂️"),
    INCOMPLETE_WORK("Service incomplete", "⏳"),
    POOR_QUALITY("Quality below standard", "⚠️"),
    BILLING_ISSUE("Pricing / Payment issue", "💰"),
    SAFETY_CONCERN("Safety or conduct concern", "🛡️"),
    PROPERTY_DAMAGE("Damaged property", "🔨"),
    OTHER("Other issue", "📝")
}

enum class DisputeStatus(val label: String) {
    OPEN("Open — Under Investigation"),
    UNDER_REVIEW("Reviewing with Panchayat"),
    ACTION_REQUIRED("Action Required from User"),
    RESOLVED("Resolved & Closed")
}

data class DisputeRecord(
    val id: String,
    val bookingId: String,
    val category: DisputeCategory,
    val description: String,
    val evidencePhotos: List<String> = emptyList(),
    val status: DisputeStatus = DisputeStatus.OPEN,
    val resolutionNotes: String? = null,
    val createdAt: String = "Just now"
)

data class ServiceMediaItem(
    val id: String,
    val name: String,
    val isVideo: Boolean = false,
    val sizeText: String = "1.2 MB"
)

data class ServiceRequestDraft(
    val categoryId: String = "plumbing",
    val categoryName: String = "Plumbing",
    val description: String = "",
    val urgency: String = "Normal", // Normal, Urgent (Within 2 hrs), Emergency
    val mediaItems: List<ServiceMediaItem> = emptyList(),
    val address: String = "Flat 3B, Demo Tower, Koregaon Park, Pune",
    val locality: String = "Koregaon Park",
    val city: String = "Pune",
    val pincode: String = "411001",
    val selectedDate: String = "Tomorrow, 17 Sep",
    val selectedTime: String = "10:00 AM",
    val isInstant: Boolean = false,
    val isServiceable: Boolean = true,
    val basePriceEstimate: Int = 500,
    val estimatedMinPrice: Int = 450,
    val estimatedMaxPrice: Int = 600
)

enum class JobStatus { NEW, ACCEPTED, ON_THE_WAY, ARRIVED, IN_PROGRESS, COMPLETED, REJECTED }

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
    val createdAt: String,
    val urgency: String = "Normal",
    val paymentMethod: String = "UPI",
    val escrowStatus: EscrowStatus = EscrowStatus.AUTHORIZED_HELD,
    val mediaCount: Int = 0,
    val customerRating: Float? = null,
    val customerReview: String? = null,
    val disputeRecord: DisputeRecord? = null,
    val completionNotes: String? = "Service delivered with cooperative safety standards."
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
    val urgency: String = "Normal",
    val matchExplanation: String = "Certified skill • 2.1 km distance • Within active service hours • High cooperative fairness balance",
    val customerPayment: Int = estimatedEarnings + 70,
    val platformAllocation: Int = 0,
    val coopAllocation: Int = 35,
    val welfareContribution: Int = 20,
    val equityContribution: Int = 15,
    val rejectionReason: String? = null,
    val startedAt: String? = null,
    val completedAt: String? = null,
    val completionNotes: String? = null
)

data class WelfareClaim(
    val id: String,
    val type: String,
    val amount: Int,
    val date: String,
    val status: String,
    val description: String
)

data class WorkerKycInfo(
    val idType: String = "Aadhaar Card",
    val status: String = "VERIFIED",
    val verifiedAt: String = "15 Jan 2024",
    val cooperativeSociety: String = "Pune Urban Labour Co-op Society Ltd.",
    val cooperativeFederation: String = "Maharashtra State Gig Workers Federation",
    val memberRegId: String = "PUN-COOP-2023-8821"
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
