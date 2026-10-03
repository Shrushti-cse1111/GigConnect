package com.gigconnect.app.ui.seeker

import androidx.compose.animation.*
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.shape.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.*
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.gigconnect.app.data.demo.DemoDataProvider
import com.gigconnect.app.data.model.*
import com.gigconnect.app.ui.components.*
import com.gigconnect.app.ui.theme.*
import com.gigconnect.app.viewmodel.SeekerViewModel

// ─────────────────────────────────────────────────────────────────────────────
//  1. Booking Confirmation Screen
// ─────────────────────────────────────────────────────────────────────────────
@Composable
fun BookingConfirmationScreen(
    seekerViewModel: SeekerViewModel,
    onTrack: () -> Unit,
    onHome: () -> Unit
) {
    val booking by seekerViewModel.currentBooking.collectAsState()
    val b = booking ?: DemoDataProvider.demoBookings.first()

    Scaffold(
        topBar = { GigTopBar(title = "Booking Confirmed", onBack = onHome) },
        bottomBar = {
            Surface(shadowElevation = 8.dp, color = GigSurface) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    Button(
                        onClick = onTrack,
                        modifier = Modifier.fillMaxWidth().height(52.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = GigPrimaryBlue),
                        shape = RoundedCornerShape(14.dp)
                    ) {
                        Icon(Icons.Filled.Navigation, null, modifier = Modifier.size(20.dp), tint = Color.White)
                        Spacer(Modifier.width(8.dp))
                        Text("Track Worker & Live Status", fontWeight = FontWeight.Bold, color = Color.White)
                    }

                    OutlinedButton(
                        onClick = onHome,
                        modifier = Modifier.fillMaxWidth().height(48.dp),
                        shape = RoundedCornerShape(14.dp),
                        border = BorderStroke(1.dp, GigPrimaryBlue)
                    ) {
                        Text("Back to Home", color = GigPrimaryBlue, fontWeight = FontWeight.SemiBold)
                    }
                }
            }
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(GigBackground)
                .padding(padding)
                .verticalScroll(rememberScrollState())
                .padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            DemoDataBadge()

            Box(
                modifier = Modifier
                    .size(80.dp)
                    .clip(CircleShape)
                    .background(GigSuccessContainer),
                contentAlignment = Alignment.Center
            ) {
                Icon(Icons.Filled.CheckCircle, null, tint = GigSuccess, modifier = Modifier.size(50.dp))
            }

            Text(
                "Service Request Confirmed!",
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold,
                color = GigOnSurface
            )

            Text(
                "Booking ID: ${b.id}",
                style = MaterialTheme.typography.bodyMedium,
                color = GigPrimaryBlue,
                fontWeight = FontWeight.Bold
            )

            // Escrow Guarantee Card
            Surface(
                color = GigTealContainer,
                shape = RoundedCornerShape(14.dp),
                border = BorderStroke(1.dp, GigPrimaryBlue),
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier.padding(14.dp),
                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text("🛡️", fontSize = 22.sp)
                    Column {
                        Text(
                            "Escrow Payment Protection Active",
                            fontWeight = FontWeight.Bold,
                            style = MaterialTheme.typography.titleSmall,
                            color = OnGigTealContainer
                        )
                        Text(
                            "Your payment is securely authorized and held in escrow. Funds are transferred to the worker only after you verify completion.",
                            style = MaterialTheme.typography.bodySmall,
                            color = OnGigTealContainer
                        )
                    }
                }
            }

            // Worker card
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = GigSurface),
                border = BorderStroke(1.dp, GigOutlineVariant)
            ) {
                Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
                    Text("Assigned Professional", style = MaterialTheme.typography.labelMedium, color = GigSubtleText)
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(horizontalArrangement = Arrangement.spacedBy(12.dp), verticalAlignment = Alignment.CenterVertically) {
                            Box(
                                modifier = Modifier.size(46.dp).clip(CircleShape).background(GigTealContainer),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(b.workerName.take(2).uppercase(), fontWeight = FontWeight.Bold, color = GigPrimaryBlue)
                            }
                            Column {
                                Text(b.workerName, fontWeight = FontWeight.Bold, style = MaterialTheme.typography.titleMedium, color = GigOnSurface)
                                Text("${b.serviceCategory} • Verified by Cooperative", style = MaterialTheme.typography.bodySmall, color = GigSubtleText)
                            }
                        }
                        VerifiedBadge(isVerified = true)
                    }
                }
            }

            // Schedule details card
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = GigSurface),
                border = BorderStroke(1.dp, GigOutlineVariant)
            ) {
                Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
                    Row(horizontalArrangement = Arrangement.spacedBy(10.dp), verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Filled.Event, null, tint = GigPrimaryBlue)
                        Column {
                            Text("Scheduled Slot & Urgency", style = MaterialTheme.typography.labelSmall, color = GigSubtleText)
                            Text("${b.date} at ${b.time} (${b.urgency})", fontWeight = FontWeight.SemiBold, color = GigOnSurface)
                        }
                    }
                    HorizontalDivider(color = GigOutlineVariant)
                    Row(horizontalArrangement = Arrangement.spacedBy(10.dp), verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Filled.Place, null, tint = GigPrimaryBlue)
                        Column {
                            Text("Service Location", style = MaterialTheme.typography.labelSmall, color = GigSubtleText)
                            Text(b.address, fontWeight = FontWeight.SemiBold, color = GigOnSurface)
                        }
                    }
                }
            }

            PriceBreakdownCard(pricing = b.pricing)
        }
    }
}

// ─────────────────────────────────────────────────────────────────────────────
//  2. Live Tracking Screen (Privacy-Aware Map & Interactive Progress)
// ─────────────────────────────────────────────────────────────────────────────
@Composable
fun LiveTrackingScreen(
    seekerViewModel: SeekerViewModel,
    onPayment: () -> Unit,
    onDispute: () -> Unit,
    onBack: () -> Unit
) {
    val booking by seekerViewModel.currentBooking.collectAsState()
    val status by seekerViewModel.bookingStatus.collectAsState()
    val eta by seekerViewModel.trackingEta.collectAsState()
    val b = booking ?: DemoDataProvider.demoBookings.first()

    Scaffold(
        topBar = { GigTopBar(title = "Live Service Tracking", onBack = onBack) },
        bottomBar = {
            Surface(shadowElevation = 8.dp, color = GigSurface) {
                Column(modifier = Modifier.fillMaxWidth().padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    if (status == BookingStatus.SERVICE_COMPLETED) {
                        Button(
                            onClick = onPayment,
                            modifier = Modifier.fillMaxWidth().height(52.dp),
                            colors = ButtonDefaults.buttonColors(containerColor = GigSuccess),
                            shape = RoundedCornerShape(14.dp)
                        ) {
                            Icon(Icons.Filled.CheckCircle, null, tint = Color.White)
                            Spacer(Modifier.width(8.dp))
                            Text("Confirm Completion & Settle Escrow (₹${b.pricing.total})", fontWeight = FontWeight.Bold, color = Color.White)
                        }
                        OutlinedButton(
                            onClick = onDispute,
                            modifier = Modifier.fillMaxWidth().height(46.dp),
                            shape = RoundedCornerShape(14.dp),
                            border = BorderStroke(1.dp, GigError),
                            colors = ButtonDefaults.outlinedButtonColors(contentColor = GigError)
                        ) {
                            Icon(Icons.Filled.ReportProblem, null, modifier = Modifier.size(18.dp), tint = GigError)
                            Spacer(Modifier.width(6.dp))
                            Text("Report an Issue / Dispute", fontWeight = FontWeight.SemiBold)
                        }
                    } else {
                        Button(
                            onClick = onPayment,
                            modifier = Modifier.fillMaxWidth().height(52.dp),
                            colors = ButtonDefaults.buttonColors(containerColor = GigPrimaryBlue),
                            shape = RoundedCornerShape(14.dp)
                        ) {
                            Text("Proceed to Escrow Checkout (₹${b.pricing.total})", fontWeight = FontWeight.Bold, color = Color.White)
                        }
                    }
                }
            }
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(GigBackground)
                .padding(padding)
                .verticalScroll(rememberScrollState())
        ) {
            DemoDataBadge(modifier = Modifier.padding(16.dp))

            // Simulated Map Box with Animated Wave / Radar
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(220.dp)
                    .background(GigSurfaceDark),
                contentAlignment = Alignment.Center
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Box(
                        modifier = Modifier
                            .size(54.dp)
                            .clip(CircleShape)
                            .background(GigPrimaryBlue),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(Icons.Filled.Navigation, null, tint = Color.White, modifier = Modifier.size(30.dp))
                    }
                    Spacer(Modifier.height(8.dp))
                    Surface(
                        color = GigSurface,
                        shape = RoundedCornerShape(20.dp),
                        shadowElevation = 4.dp
                    ) {
                        Row(
                            modifier = Modifier.padding(horizontal = 14.dp, vertical = 6.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(6.dp)
                        ) {
                            Box(modifier = Modifier.size(8.dp).clip(CircleShape).background(if (status == BookingStatus.SERVICE_COMPLETED) GigSuccess else GigPrimaryBlue))
                            Text(
                                when (status) {
                                    BookingStatus.WORKER_ASSIGNED -> "Worker accepted job"
                                    BookingStatus.WORKER_ON_WAY -> "Worker arriving in $eta mins"
                                    BookingStatus.WORKER_ARRIVED -> "Worker arrived at location"
                                    BookingStatus.SERVICE_STARTED -> "Service currently in progress"
                                    BookingStatus.SERVICE_COMPLETED -> "Service completed by worker"
                                    else -> "Tracking live connection"
                                },
                                fontWeight = FontWeight.Bold,
                                fontSize = 13.sp,
                                color = GigOnSurface
                            )
                        }
                    }
                }
            }

            Column(
                modifier = Modifier.padding(20.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                // Privacy Consent Banner
                Surface(
                    color = GigTealContainer,
                    shape = RoundedCornerShape(12.dp),
                    border = BorderStroke(1.dp, GigPrimaryBlue)
                ) {
                    Row(
                        modifier = Modifier.padding(12.dp),
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(Icons.Filled.Security, null, tint = GigPrimaryBlue, modifier = Modifier.size(18.dp))
                        Text(
                            "Privacy Protected: Worker location sharing operates only while en-route to your address.",
                            style = MaterialTheme.typography.bodySmall,
                            color = OnGigTealContainer
                        )
                    }
                }

                // Worker contact bar
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = GigSurface),
                    border = BorderStroke(1.dp, GigOutlineVariant)
                ) {
                    Row(
                        modifier = Modifier.padding(16.dp).fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(horizontalArrangement = Arrangement.spacedBy(12.dp), verticalAlignment = Alignment.CenterVertically) {
                            Box(modifier = Modifier.size(46.dp).clip(CircleShape).background(GigTealContainer), contentAlignment = Alignment.Center) {
                                Text(b.workerName.take(2).uppercase(), fontWeight = FontWeight.Bold, color = GigPrimaryBlue)
                            }
                            Column {
                                Text(b.workerName, fontWeight = FontWeight.Bold, style = MaterialTheme.typography.titleMedium, color = GigOnSurface)
                                Text("⭐ 4.8 • Verified Professional", style = MaterialTheme.typography.bodySmall, color = GigSubtleText)
                            }
                        }

                        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                            FilledTonalIconButton(onClick = {}) {
                                Icon(Icons.Filled.Call, "Call", tint = GigPrimaryBlue)
                            }
                            FilledTonalIconButton(onClick = {}) {
                                Icon(Icons.AutoMirrored.Filled.Message, "Chat", tint = GigPrimaryBlue)
                            }
                        }
                    }
                }

                // Customer Completion Prompt (When service is done)
                if (status == BookingStatus.SERVICE_COMPLETED) {
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(16.dp),
                        colors = CardDefaults.cardColors(containerColor = GigSuccessContainer),
                        border = BorderStroke(1.5.dp, GigSuccess)
                    ) {
                        Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                            Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                                Icon(Icons.Filled.TaskAlt, null, tint = GigSuccess)
                                Text("Service Finished", fontWeight = FontWeight.Bold, color = GigSuccess, style = MaterialTheme.typography.titleSmall)
                            }
                            Text(
                                "The professional has marked this job as completed. Please inspect the finished work.",
                                style = MaterialTheme.typography.bodySmall,
                                color = GigOnSurface
                            )
                        }
                    }
                }

                // Tracking Timeline Steps
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = GigSurface),
                    border = BorderStroke(1.dp, GigOutlineVariant)
                ) {
                    Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(14.dp)) {
                        Text("Live Service Progress", fontWeight = FontWeight.Bold, style = MaterialTheme.typography.titleSmall, color = GigOnSurface)

                        TrackingStepItem("Booking Confirmed", "Request acknowledged & escrow authorized", isDone = true)
                        TrackingStepItem("Worker Assigned", "${b.workerName} accepted your job", isDone = true)
                        TrackingStepItem("Worker En Route", if (eta > 0) "Arriving in ~$eta mins" else "Arrived at location", isDone = eta <= 5 || status == BookingStatus.WORKER_ARRIVED || status == BookingStatus.SERVICE_STARTED || status == BookingStatus.SERVICE_COMPLETED, isCurrent = status == BookingStatus.WORKER_ON_WAY)
                        TrackingStepItem("Service Execution", "Work in progress with cooperative quality standards", isDone = status == BookingStatus.SERVICE_STARTED || status == BookingStatus.SERVICE_COMPLETED, isCurrent = status == BookingStatus.SERVICE_STARTED)
                        TrackingStepItem("Customer Verification", "Customer confirmation & escrow release", isDone = status == BookingStatus.PAYMENT_SETTLED, isCurrent = status == BookingStatus.SERVICE_COMPLETED)
                    }
                }

                // Interactive Simulator Toggle (Allows instant testing)
                Surface(
                    color = GigSurfaceVariant,
                    shape = RoundedCornerShape(12.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Row(
                        modifier = Modifier.padding(12.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text("Quick Flow Simulation:", style = MaterialTheme.typography.labelSmall, color = GigOnSurface)
                        Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                            TextButton(onClick = { seekerViewModel.setBookingStatus(BookingStatus.SERVICE_STARTED) }) {
                                Text("Start", fontSize = 11.sp)
                            }
                            TextButton(onClick = { seekerViewModel.setBookingStatus(BookingStatus.SERVICE_COMPLETED) }) {
                                Text("Complete", fontSize = 11.sp, fontWeight = FontWeight.Bold)
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun TrackingStepItem(
    title: String,
    subtitle: String,
    isDone: Boolean,
    isCurrent: Boolean = false
) {
    Row(horizontalArrangement = Arrangement.spacedBy(12.dp), verticalAlignment = Alignment.CenterVertically) {
        Box(
            modifier = Modifier
                .size(24.dp)
                .clip(CircleShape)
                .background(if (isDone) GigSuccess else if (isCurrent) GigPrimaryBlue else GigOutlineVariant),
            contentAlignment = Alignment.Center
        ) {
            if (isDone) {
                Icon(Icons.Filled.Check, null, tint = Color.White, modifier = Modifier.size(16.dp))
            } else if (isCurrent) {
                Box(modifier = Modifier.size(8.dp).clip(CircleShape).background(Color.White))
            }
        }
        Column {
            Text(title, fontWeight = FontWeight.SemiBold, fontSize = 14.sp, color = GigOnSurface)
            Text(subtitle, fontSize = 12.sp, color = GigSubtleText)
        }
    }
}

// ─────────────────────────────────────────────────────────────────────────────
//  3. Payment Screen (Escrow Authorization)
// ─────────────────────────────────────────────────────────────────────────────
@Composable
fun PaymentScreen(
    seekerViewModel: SeekerViewModel,
    onSuccess: () -> Unit,
    onBack: () -> Unit
) {
    val booking by seekerViewModel.currentBooking.collectAsState()
    val b = booking ?: DemoDataProvider.demoBookings.first()
    var selectedMethod by remember { mutableStateOf("UPI") }

    Scaffold(
        topBar = { GigTopBar(title = "Escrow Checkout", onBack = onBack) },
        bottomBar = {
            Surface(shadowElevation = 8.dp, color = GigSurface) {
                Column(modifier = Modifier.fillMaxWidth().padding(16.dp)) {
                    Button(
                        onClick = {
                            seekerViewModel.confirmCompletionAndReleaseEscrow(b.id)
                            onSuccess()
                        },
                        modifier = Modifier.fillMaxWidth().height(52.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = GigPrimaryBlue),
                        shape = RoundedCornerShape(14.dp)
                    ) {
                        Text("Authorize & Settle ₹${b.pricing.total} via $selectedMethod", fontWeight = FontWeight.Bold, color = Color.White)
                    }
                }
            }
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(GigBackground)
                .padding(padding)
                .verticalScroll(rememberScrollState())
                .padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            DemoDataBadge()

            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = GigTealContainer)
            ) {
                Column(modifier = Modifier.padding(20.dp), horizontalAlignment = Alignment.CenterHorizontally) {
                    Text("Total Transparent Payment", style = MaterialTheme.typography.bodyMedium, color = OnGigTealContainer)
                    Spacer(Modifier.height(4.dp))
                    Text("₹${b.pricing.total}", style = MaterialTheme.typography.headlineLarge, fontWeight = FontWeight.ExtraBold, color = GigPrimaryBlue)
                    Spacer(Modifier.height(6.dp))
                    Text("Includes ₹${b.pricing.welfareContribution} worker health & welfare allocation", fontSize = 12.sp, color = OnGigTealContainer)
                }
            }

            Text("Select Payment Method", fontWeight = FontWeight.Bold, style = MaterialTheme.typography.titleMedium, color = GigOnBackground)

            PaymentMethodOption("UPI Payment", "Google Pay, PhonePe, Paytm, BHIM", "⚡", selectedMethod == "UPI") { selectedMethod = "UPI" }
            PaymentMethodOption("Debit / Credit Card", "Visa, Mastercard, RuPay", "💳", selectedMethod == "Card") { selectedMethod = "Card" }
            PaymentMethodOption("Net Banking", "All major Indian banks supported", "🏦", selectedMethod == "NetBanking") { selectedMethod = "NetBanking" }
            PaymentMethodOption("Cash / Escrow On Completion", "Pay cash after customer inspection", "💵", selectedMethod == "Cash") { selectedMethod = "Cash" }

            PriceBreakdownCard(pricing = b.pricing)
        }
    }
}

@Composable
private fun PaymentMethodOption(
    title: String,
    subtitle: String,
    icon: String,
    isSelected: Boolean,
    onSelect: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth().clickable { onSelect() },
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(containerColor = if (isSelected) GigTealContainer else GigSurface),
        border = BorderStroke(if (isSelected) 1.5.dp else 1.dp, if (isSelected) GigPrimaryBlue else GigOutlineVariant)
    ) {
        Row(
            modifier = Modifier.padding(14.dp).fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(horizontalArrangement = Arrangement.spacedBy(12.dp), verticalAlignment = Alignment.CenterVertically) {
                Text(icon, fontSize = 24.sp)
                Column {
                    Text(title, fontWeight = FontWeight.Bold, style = MaterialTheme.typography.titleSmall, color = GigOnSurface)
                    Text(subtitle, fontSize = 11.sp, color = GigSubtleText)
                }
            }
            RadioButton(selected = isSelected, onClick = onSelect, colors = RadioButtonDefaults.colors(selectedColor = GigPrimaryBlue))
        }
    }
}

// ─────────────────────────────────────────────────────────────────────────────
//  4. Payment Success Screen (Settlement & Ledger Breakdown)
// ─────────────────────────────────────────────────────────────────────────────
@Composable
fun PaymentSuccessScreen(
    seekerViewModel: SeekerViewModel,
    onRate: () -> Unit,
    onHome: () -> Unit
) {
    val booking by seekerViewModel.currentBooking.collectAsState()
    val b = booking ?: DemoDataProvider.demoBookings.first()

    Scaffold(
        bottomBar = {
            Surface(shadowElevation = 8.dp, color = GigSurface) {
                Column(modifier = Modifier.fillMaxWidth().padding(16.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
                    Button(
                        onClick = onRate,
                        modifier = Modifier.fillMaxWidth().height(52.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = GigPrimaryBlue),
                        shape = RoundedCornerShape(14.dp)
                    ) {
                        Text("Rate & Review ${b.workerName}", fontWeight = FontWeight.Bold, color = Color.White)
                    }
                    OutlinedButton(
                        onClick = onHome,
                        modifier = Modifier.fillMaxWidth().height(48.dp),
                        shape = RoundedCornerShape(14.dp),
                        border = BorderStroke(1.dp, GigPrimaryBlue)
                    ) {
                        Text("Back to Home", color = GigPrimaryBlue, fontWeight = FontWeight.SemiBold)
                    }
                }
            }
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(GigBackground)
                .padding(padding)
                .verticalScroll(rememberScrollState())
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Spacer(Modifier.height(10.dp))

            Box(
                modifier = Modifier.size(88.dp).clip(CircleShape).background(GigSuccessContainer),
                contentAlignment = Alignment.Center
            ) {
                Icon(Icons.Filled.Verified, null, tint = GigSuccess, modifier = Modifier.size(56.dp))
            }

            Text("Payment Settled Successfully!", style = MaterialTheme.typography.headlineMedium, fontWeight = FontWeight.ExtraBold, color = GigOnSurface)
            Text("₹${b.pricing.total} deposited via Escrow", color = GigSubtleText, fontWeight = FontWeight.SemiBold)

            // Transparent Settlement Ledger
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = GigSurface),
                border = BorderStroke(1.dp, GigOutlineVariant)
            ) {
                Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
                    Text("Settlement Ledger & Allocation", fontWeight = FontWeight.Bold, style = MaterialTheme.typography.titleSmall, color = GigOnSurface)
                    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                        Text("Worker Direct Earning (96%)", color = GigSubtleText, fontSize = 13.sp)
                        Text("₹${b.pricing.workerEarnings}", fontWeight = FontWeight.Bold, fontSize = 13.sp, color = GigOnSurface)
                    }
                    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                        Text("Worker Welfare & Insurance (4%)", color = GigSubtleText, fontSize = 13.sp)
                        Text("₹${b.pricing.welfareContribution}", fontWeight = FontWeight.Bold, fontSize = 13.sp, color = GigSuccess)
                    }
                    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                        Text("Cooperative Operations (10%)", color = GigSubtleText, fontSize = 13.sp)
                        Text("₹${b.pricing.platformContribution}", fontWeight = FontWeight.Bold, fontSize = 13.sp, color = GigPrimaryBlue)
                    }
                    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                        Text("Cooperative Equity Points", color = GigSubtleText, fontSize = 13.sp)
                        Text("+35 Pts Earned", fontWeight = FontWeight.Bold, fontSize = 13.sp, color = GigWarning)
                    }
                }
            }

            // Cooperative Impact Card
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = GigSaffronContainer)
            ) {
                Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(6.dp)) {
                    Text("🤝 Cooperative Social Impact", fontWeight = FontWeight.Bold, color = GigSaffronDark)
                    Text(
                        "₹${b.pricing.welfareContribution} of your payment was routed directly to ${b.workerName}'s cooperative healthcare & pension fund. Thank you for supporting democratic fair labor!",
                        style = MaterialTheme.typography.bodySmall,
                        color = GigOnSurface
                    )
                }
            }
        }
    }
}

// ─────────────────────────────────────────────────────────────────────────────
//  5. Rating & Review Screen (Duplicate Prevention & Tags)
// ─────────────────────────────────────────────────────────────────────────────
@Composable
fun RatingScreen(
    seekerViewModel: SeekerViewModel,
    onDone: () -> Unit
) {
    val booking by seekerViewModel.currentBooking.collectAsState()
    val b = booking ?: DemoDataProvider.demoBookings.first()
    var rating by remember { mutableStateOf(5) }
    var reviewText by remember { mutableStateOf("") }
    val tags = listOf("Punctual", "Professional", "Fair Price", "High Quality", "Courteous", "Clean Work")
    val selectedTags = remember { mutableStateListOf<String>() }
    var submitted by remember { mutableStateOf(false) }

    Scaffold(
        topBar = { GigTopBar(title = "Rate & Review", onBack = onDone) },
        bottomBar = {
            Surface(shadowElevation = 8.dp, color = GigSurface) {
                Column(modifier = Modifier.fillMaxWidth().padding(16.dp)) {
                    Button(
                        onClick = {
                            seekerViewModel.submitReview(b.id, rating, selectedTags.toList(), reviewText)
                            submitted = true
                            onDone()
                        },
                        modifier = Modifier.fillMaxWidth().height(52.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = GigPrimaryBlue),
                        shape = RoundedCornerShape(14.dp)
                    ) {
                        Text("Submit Review", fontWeight = FontWeight.Bold, color = Color.White)
                    }
                }
            }
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(GigBackground)
                .padding(padding)
                .verticalScroll(rememberScrollState())
                .padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            DemoDataBadge()

            Box(
                modifier = Modifier.size(70.dp).clip(CircleShape).background(GigPrimaryBlue),
                contentAlignment = Alignment.Center
            ) {
                Text(b.workerName.take(2).uppercase(), fontWeight = FontWeight.Bold, color = Color.White, fontSize = 22.sp)
            }

            Text("How was your service with ${b.workerName}?", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold, color = GigOnSurface)

            // Star row
            Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                for (i in 1..5) {
                    IconButton(onClick = { rating = i }) {
                        Icon(
                            if (i <= rating) Icons.Filled.Star else Icons.Filled.StarBorder,
                            contentDescription = "Star $i",
                            tint = if (i <= rating) GigStarYellow else GigOutline,
                            modifier = Modifier.size(36.dp)
                        )
                    }
                }
            }

            Text("What stood out?", fontWeight = FontWeight.SemiBold, style = MaterialTheme.typography.bodyMedium, color = GigOnSurface)

            // Quick tags
            Column(verticalArrangement = Arrangement.spacedBy(8.dp), horizontalAlignment = Alignment.CenterHorizontally) {
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    tags.take(3).forEach { tag ->
                        FilterChip(
                            selected = selectedTags.contains(tag),
                            onClick = { if (selectedTags.contains(tag)) selectedTags.remove(tag) else selectedTags.add(tag) },
                            label = { Text(tag) }
                        )
                    }
                }
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    tags.drop(3).forEach { tag ->
                        FilterChip(
                            selected = selectedTags.contains(tag),
                            onClick = { if (selectedTags.contains(tag)) selectedTags.remove(tag) else selectedTags.add(tag) },
                            label = { Text(tag) }
                        )
                    }
                }
            }

            OutlinedTextField(
                value = reviewText,
                onValueChange = { reviewText = it },
                label = { Text("Write additional feedback (optional)") },
                modifier = Modifier.fillMaxWidth().height(120.dp),
                shape = RoundedCornerShape(12.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = GigPrimaryBlue,
                    focusedContainerColor = GigSurface,
                    unfocusedContainerColor = GigSurface
                )
            )
        }
    }
}

// ─────────────────────────────────────────────────────────────────────────────
//  6. Bookings List Screen (With Action Buttons)
// ─────────────────────────────────────────────────────────────────────────────
@Composable
fun BookingsListScreen(
    seekerViewModel: SeekerViewModel,
    onTrackBooking: (Booking) -> Unit = {},
    onDisputeBooking: (Booking) -> Unit = {},
    onRateBooking: (Booking) -> Unit = {},
    onBack: () -> Unit
) {
    val bookings by seekerViewModel.bookings.collectAsState()
    var selectedTab by remember { mutableStateOf(0) }
    val tabs = listOf("All", "Active", "Completed", "Disputed")

    Scaffold(
        topBar = { GigTopBar(title = "My Bookings & History", onBack = onBack) }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(GigBackground)
                .padding(padding)
        ) {
            TabRow(
                selectedTabIndex = selectedTab,
                containerColor = GigSurface,
                contentColor = GigPrimaryBlue
            ) {
                tabs.forEachIndexed { index, title ->
                    Tab(
                        selected = selectedTab == index,
                        onClick = { selectedTab = index },
                        text = { Text(title, fontWeight = FontWeight.SemiBold) }
                    )
                }
            }

            val filtered = when (selectedTab) {
                1 -> bookings.filter { it.status == BookingStatus.CONFIRMED || it.status == BookingStatus.PENDING || it.status == BookingStatus.WORKER_ON_WAY || it.status == BookingStatus.SERVICE_STARTED }
                2 -> bookings.filter { it.status == BookingStatus.SERVICE_COMPLETED || it.status == BookingStatus.PAYMENT_SETTLED || it.status == BookingStatus.RATED }
                3 -> bookings.filter { it.status == BookingStatus.DISPUTED }
                else -> bookings
            }

            if (filtered.isEmpty()) {
                EmptyState("📋", "No bookings found in this section", modifier = Modifier.fillMaxSize())
            } else {
                LazyColumn(
                    contentPadding = PaddingValues(16.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    item { DemoDataBadge() }
                    items(filtered) { b ->
                        Card(
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(16.dp),
                            colors = CardDefaults.cardColors(containerColor = GigSurface),
                            border = BorderStroke(1.dp, GigOutlineVariant)
                        ) {
                            Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Text(b.serviceDetail, fontWeight = FontWeight.Bold, style = MaterialTheme.typography.titleMedium, color = GigOnSurface)
                                    StatusChip(status = b.status)
                                }
                                Text("Professional: ${b.workerName} • ${b.serviceCategory}", style = MaterialTheme.typography.bodySmall, color = GigSubtleText)
                                Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                                    Text("📅 ${b.date}", fontSize = 12.sp, color = GigSubtleText)
                                    Text("⏰ ${b.time}", fontSize = 12.sp, color = GigSubtleText)
                                    Text("₹${b.pricing.total}", fontSize = 12.sp, fontWeight = FontWeight.Bold, color = GigPrimaryBlue)
                                }
                                Text("📍 ${b.address}", fontSize = 12.sp, color = GigOnSurface.copy(alpha = 0.8f))

                                // Action Buttons Row
                                Row(
                                    modifier = Modifier.fillMaxWidth().padding(top = 4.dp),
                                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                                ) {
                                    OutlinedButton(
                                        onClick = { onDisputeBooking(b) },
                                        modifier = Modifier.weight(1f).height(38.dp),
                                        shape = RoundedCornerShape(8.dp),
                                        border = BorderStroke(1.dp, GigError)
                                    ) {
                                        Text("Raise Dispute", fontSize = 11.sp, color = GigError, fontWeight = FontWeight.SemiBold)
                                    }
                                    Button(
                                        onClick = { onTrackBooking(b) },
                                        modifier = Modifier.weight(1f).height(38.dp),
                                        colors = ButtonDefaults.buttonColors(containerColor = GigPrimaryBlue),
                                        shape = RoundedCornerShape(8.dp)
                                    ) {
                                        Text("View / Track", fontSize = 11.sp, color = Color.White, fontWeight = FontWeight.Bold)
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

// ─────────────────────────────────────────────────────────────────────────────
//  7. Seeker Profile Screen (Disputes, Language & Settings)
// ─────────────────────────────────────────────────────────────────────────────
@Composable
fun SeekerProfileScreen(
    onDisputeCenterClick: () -> Unit = {},
    onBack: () -> Unit
) {
    Scaffold(
        topBar = { GigTopBar(title = "Profile & Settings", onBack = onBack) }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(GigBackground)
                .padding(padding)
                .verticalScroll(rememberScrollState())
                .padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            DemoDataBadge()

            // Profile Header
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = GigSurface),
                border = BorderStroke(1.dp, GigOutlineVariant)
            ) {
                Row(
                    modifier = Modifier.padding(16.dp).fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Box(
                        modifier = Modifier.size(56.dp).clip(CircleShape).background(GigPrimaryBlue),
                        contentAlignment = Alignment.Center
                    ) {
                        Text("DS", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 20.sp)
                    }
                    Column {
                        Text("Demo Seeker", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold, color = GigOnSurface)
                        Text("+91 98765 43210", style = MaterialTheme.typography.bodySmall, color = GigSubtleText)
                        Text("Koregaon Park, Pune", style = MaterialTheme.typography.bodySmall, color = GigPrimaryBlue)
                    }
                }
            }

            // Impact badge
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = GigTealContainer)
            ) {
                Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(4.dp)) {
                    Text("🌟 Cooperative Community Supporter", fontWeight = FontWeight.Bold, color = OnGigTealContainer)
                    Text(
                        "Your bookings have generated ₹180 towards worker health insurance and digital equity funds.",
                        style = MaterialTheme.typography.bodySmall,
                        color = OnGigTealContainer
                    )
                }
            }

            // Settings sections
            ProfileSettingItem(icon = Icons.Filled.Gavel, title = "Grievance & Dispute Center", subtitle = "Report issues & track Digital Panchayat status", onClick = onDisputeCenterClick)
            ProfileSettingItem(icon = Icons.Filled.LocationOn, title = "Saved Addresses", subtitle = "Home, Office & Family")
            ProfileSettingItem(icon = Icons.Filled.Security, title = "Safety & Emergency Contacts", subtitle = "24/7 Cooperative Support line")
            ProfileSettingItem(icon = Icons.Filled.Language, title = "App Language", subtitle = "English (Regional available)")
            ProfileSettingItem(icon = Icons.Filled.Info, title = "About GigConnect", subtitle = "Pan-India Cooperative Gig-Services Platform v1.0")
        }
    }
}

@Composable
private fun ProfileSettingItem(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    title: String,
    subtitle: String,
    onClick: () -> Unit = {}
) {
    Card(
        modifier = Modifier.fillMaxWidth().clickable(onClick = onClick),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = GigSurface),
        border = BorderStroke(1.dp, GigOutlineVariant)
    ) {
        Row(
            modifier = Modifier.padding(14.dp).fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            Icon(icon, null, tint = GigPrimaryBlue)
            Column(modifier = Modifier.weight(1f)) {
                Text(title, fontWeight = FontWeight.SemiBold, style = MaterialTheme.typography.bodyMedium, color = GigOnSurface)
                Text(subtitle, fontSize = 12.sp, color = GigSubtleText)
            }
            Icon(Icons.Filled.ChevronRight, null, tint = GigSubtleText)
        }
    }
}
