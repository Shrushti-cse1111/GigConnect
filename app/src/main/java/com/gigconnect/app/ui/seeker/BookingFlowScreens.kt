package com.gigconnect.app.ui.seeker

import androidx.compose.animation.*
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.shape.*
import androidx.compose.material.icons.Icons
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
                        colors = ButtonDefaults.buttonColors(containerColor = GigTeal),
                        shape = RoundedCornerShape(14.dp)
                    ) {
                        Icon(Icons.Filled.Navigation, null, modifier = Modifier.size(20.dp))
                        Spacer(Modifier.width(8.dp))
                        Text("Live Track Worker", fontWeight = FontWeight.Bold)
                    }

                    OutlinedButton(
                        onClick = onHome,
                        modifier = Modifier.fillMaxWidth().height(48.dp),
                        shape = RoundedCornerShape(14.dp)
                    ) {
                        Text("Back to Home")
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
                "Service Booked Successfully!",
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold,
                color = GigOnSurface
            )

            Text(
                "Booking ID: ${b.id}",
                style = MaterialTheme.typography.bodyMedium,
                color = GigSubtleText,
                fontWeight = FontWeight.SemiBold
            )

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
                                modifier = Modifier.size(44.dp).clip(CircleShape).background(GigTealContainer),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(b.workerName.take(2).uppercase(), fontWeight = FontWeight.Bold, color = GigTeal)
                            }
                            Column {
                                Text(b.workerName, fontWeight = FontWeight.Bold, style = MaterialTheme.typography.titleMedium)
                                Text(b.serviceCategory, style = MaterialTheme.typography.bodySmall, color = GigSubtleText)
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
                        Icon(Icons.Filled.Event, null, tint = GigTeal)
                        Column {
                            Text("Scheduled Slot", style = MaterialTheme.typography.labelSmall, color = GigSubtleText)
                            Text("${b.date} at ${b.time}", fontWeight = FontWeight.SemiBold)
                        }
                    }
                    Divider(color = GigOutlineVariant)
                    Row(horizontalArrangement = Arrangement.spacedBy(10.dp), verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Filled.Place, null, tint = GigTeal)
                        Column {
                            Text("Service Location", style = MaterialTheme.typography.labelSmall, color = GigSubtleText)
                            Text(b.address, fontWeight = FontWeight.SemiBold)
                        }
                    }
                }
            }

            PriceBreakdownCard(pricing = b.pricing)
        }
    }
}

// ─────────────────────────────────────────────────────────────────────────────
//  2. Live Tracking Screen (Simulated Map)
// ─────────────────────────────────────────────────────────────────────────────
@Composable
fun LiveTrackingScreen(
    seekerViewModel: SeekerViewModel,
    onPayment: () -> Unit,
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
                Column(modifier = Modifier.fillMaxWidth().padding(16.dp)) {
                    Button(
                        onClick = onPayment,
                        modifier = Modifier.fillMaxWidth().height(52.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = GigTeal),
                        shape = RoundedCornerShape(14.dp)
                    ) {
                        Text("Complete & Proceed to Pay (₹${b.pricing.servicePrice})", fontWeight = FontWeight.Bold)
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

            // Simulated Map Box
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(240.dp)
                    .background(Color(0xFFE8ECEF)),
                contentAlignment = Alignment.Center
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Box(
                        modifier = Modifier
                            .size(54.dp)
                            .clip(CircleShape)
                            .background(GigTeal),
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
                            Box(modifier = Modifier.size(8.dp).clip(CircleShape).background(GigSuccess))
                            Text(
                                if (eta > 0) "Worker arriving in $eta mins" else "Worker has arrived!",
                                fontWeight = FontWeight.Bold,
                                fontSize = 13.sp
                            )
                        }
                    }
                }
            }

            Column(
                modifier = Modifier.padding(20.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                // Worker contact bar
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = GigSurface)
                ) {
                    Row(
                        modifier = Modifier.padding(16.dp).fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(horizontalArrangement = Arrangement.spacedBy(12.dp), verticalAlignment = Alignment.CenterVertically) {
                            Box(modifier = Modifier.size(46.dp).clip(CircleShape).background(GigTealContainer), contentAlignment = Alignment.Center) {
                                Text(b.workerName.take(2).uppercase(), fontWeight = FontWeight.Bold, color = GigTeal)
                            }
                            Column {
                                Text(b.workerName, fontWeight = FontWeight.Bold, style = MaterialTheme.typography.titleMedium)
                                Text("⭐ 4.8 • Verified Worker", style = MaterialTheme.typography.bodySmall, color = GigSubtleText)
                            }
                        }

                        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                            FilledTonalIconButton(onClick = {}) {
                                Icon(Icons.Filled.Call, "Call", tint = GigTeal)
                            }
                            FilledTonalIconButton(onClick = {}) {
                                Icon(Icons.Filled.Message, "Chat", tint = GigTeal)
                            }
                        }
                    }
                }

                // Tracking Timeline Steps
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = GigSurface)
                ) {
                    Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(14.dp)) {
                        Text("Service Progress", fontWeight = FontWeight.Bold, style = MaterialTheme.typography.titleSmall)

                        TrackingStepItem("Booking Confirmed", "Request acknowledged by cooperative system", isDone = true)
                        TrackingStepItem("Worker Assigned", "${b.workerName} accepted your job", isDone = true)
                        TrackingStepItem("Worker En Route", if (eta > 0) "Estimated arrival in $eta mins" else "Arrived at location", isDone = eta <= 5, isCurrent = eta in 1..5)
                        TrackingStepItem("Service Execution", "Work in progress with safety compliance", isDone = status == BookingStatus.SERVICE_STARTED, isCurrent = status == BookingStatus.SERVICE_STARTED)
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
                .background(if (isDone) GigSuccess else if (isCurrent) GigTeal else GigOutlineVariant),
            contentAlignment = Alignment.Center
        ) {
            if (isDone) {
                Icon(Icons.Filled.Check, null, tint = Color.White, modifier = Modifier.size(16.dp))
            } else if (isCurrent) {
                Box(modifier = Modifier.size(8.dp).clip(CircleShape).background(Color.White))
            }
        }
        Column {
            Text(title, fontWeight = FontWeight.SemiBold, fontSize = 14.sp)
            Text(subtitle, fontSize = 12.sp, color = GigSubtleText)
        }
    }
}

// ─────────────────────────────────────────────────────────────────────────────
//  3. Payment Screen
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
        topBar = { GigTopBar(title = "Payment", onBack = onBack) },
        bottomBar = {
            Surface(shadowElevation = 8.dp, color = GigSurface) {
                Column(modifier = Modifier.fillMaxWidth().padding(16.dp)) {
                    Button(
                        onClick = onSuccess,
                        modifier = Modifier.fillMaxWidth().height(52.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = GigTeal),
                        shape = RoundedCornerShape(14.dp)
                    ) {
                        Text("Pay ₹${b.pricing.servicePrice} via $selectedMethod", fontWeight = FontWeight.Bold)
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
                    Text("Total Amount Due", style = MaterialTheme.typography.bodyMedium, color = GigTealDark)
                    Spacer(Modifier.height(4.dp))
                    Text("₹${b.pricing.servicePrice}", style = MaterialTheme.typography.headlineLarge, fontWeight = FontWeight.ExtraBold, color = GigTeal)
                    Spacer(Modifier.height(6.dp))
                    Text("Includes ₹${b.pricing.welfareContribution} worker welfare contribution", fontSize = 12.sp, color = GigTealDark)
                }
            }

            Text("Select Payment Method", fontWeight = FontWeight.Bold, style = MaterialTheme.typography.titleMedium)

            PaymentMethodOption("UPI", "Google Pay, PhonePe, Paytm, BHIM", "⚡", selectedMethod == "UPI") { selectedMethod = "UPI" }
            PaymentMethodOption("Debit / Credit Card", "Visa, Mastercard, RuPay", "💳", selectedMethod == "Card") { selectedMethod = "Card" }
            PaymentMethodOption("Net Banking", "All major Indian banks supported", "🏦", selectedMethod == "NetBanking") { selectedMethod = "NetBanking" }
            PaymentMethodOption("Cash on Delivery", "Pay directly after completion", "💵", selectedMethod == "Cash") { selectedMethod = "Cash" }

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
        border = BorderStroke(if (isSelected) 1.5.dp else 1.dp, if (isSelected) GigTeal else GigOutlineVariant)
    ) {
        Row(
            modifier = Modifier.padding(14.dp).fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(horizontalArrangement = Arrangement.spacedBy(12.dp), verticalAlignment = Alignment.CenterVertically) {
                Text(icon, fontSize = 24.sp)
                Column {
                    Text(title, fontWeight = FontWeight.Bold, style = MaterialTheme.typography.titleSmall)
                    Text(subtitle, fontSize = 11.sp, color = GigSubtleText)
                }
            }
            RadioButton(selected = isSelected, onClick = onSelect)
        }
    }
}

// ─────────────────────────────────────────────────────────────────────────────
//  4. Payment Success Screen
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
                        colors = ButtonDefaults.buttonColors(containerColor = GigTeal),
                        shape = RoundedCornerShape(14.dp)
                    ) {
                        Text("Rate ${b.workerName}", fontWeight = FontWeight.Bold)
                    }
                    OutlinedButton(
                        onClick = onHome,
                        modifier = Modifier.fillMaxWidth().height(48.dp),
                        shape = RoundedCornerShape(14.dp)
                    ) {
                        Text("Back to Home")
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
            Spacer(Modifier.height(20.dp))

            Box(
                modifier = Modifier.size(88.dp).clip(CircleShape).background(GigSuccessContainer),
                contentAlignment = Alignment.Center
            ) {
                Icon(Icons.Filled.Verified, null, tint = GigSuccess, modifier = Modifier.size(56.dp))
            }

            Text("Payment Successful!", style = MaterialTheme.typography.headlineMedium, fontWeight = FontWeight.ExtraBold)
            Text("₹${b.pricing.servicePrice} paid to GigConnect Cooperative", color = GigSubtleText, fontWeight = FontWeight.SemiBold)

            // Cooperative Impact Card
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = GigSaffronContainer)
            ) {
                Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(6.dp)) {
                    Text("🤝 Cooperative Social Impact", fontWeight = FontWeight.Bold, color = GigSaffronDark)
                    Text(
                        "₹${b.pricing.welfareContribution} of this payment directly funded ${b.workerName}'s cooperative health insurance and welfare fund. Thank you for supporting fair labor!",
                        style = MaterialTheme.typography.bodySmall,
                        color = GigOnSurface
                    )
                }
            }

            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = GigSurface),
                border = BorderStroke(1.dp, GigOutlineVariant)
            ) {
                Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
                    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                        Text("Txn ID", color = GigSubtleText, fontSize = 13.sp)
                        Text("TXN${(10000..99999).random()}", fontWeight = FontWeight.Bold, fontSize = 13.sp)
                    }
                    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                        Text("Service", color = GigSubtleText, fontSize = 13.sp)
                        Text(b.serviceDetail, fontWeight = FontWeight.Bold, fontSize = 13.sp)
                    }
                    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                        Text("Worker", color = GigSubtleText, fontSize = 13.sp)
                        Text(b.workerName, fontWeight = FontWeight.Bold, fontSize = 13.sp)
                    }
                    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                        Text("Date", color = GigSubtleText, fontSize = 13.sp)
                        Text(b.date, fontWeight = FontWeight.Bold, fontSize = 13.sp)
                    }
                }
            }
        }
    }
}

// ─────────────────────────────────────────────────────────────────────────────
//  5. Rating Screen
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

    Scaffold(
        topBar = { GigTopBar(title = "Rate & Review", onBack = onDone) },
        bottomBar = {
            Surface(shadowElevation = 8.dp, color = GigSurface) {
                Column(modifier = Modifier.fillMaxWidth().padding(16.dp)) {
                    Button(
                        onClick = onDone,
                        modifier = Modifier.fillMaxWidth().height(52.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = GigTeal),
                        shape = RoundedCornerShape(14.dp)
                    ) {
                        Text("Submit Review", fontWeight = FontWeight.Bold)
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
                modifier = Modifier.size(70.dp).clip(CircleShape).background(GigTeal),
                contentAlignment = Alignment.Center
            ) {
                Text(b.workerName.take(2).uppercase(), fontWeight = FontWeight.Bold, color = Color.White, fontSize = 22.sp)
            }

            Text("How was your service with ${b.workerName}?", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)

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

            Text("What stood out?", fontWeight = FontWeight.SemiBold, style = MaterialTheme.typography.bodyMedium)

            // Quick tags
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
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
            }

            OutlinedTextField(
                value = reviewText,
                onValueChange = { reviewText = it },
                label = { Text("Write additional feedback (optional)") },
                modifier = Modifier.fillMaxWidth().height(120.dp),
                shape = RoundedCornerShape(12.dp)
            )
        }
    }
}

// ─────────────────────────────────────────────────────────────────────────────
//  6. Bookings List Screen
// ─────────────────────────────────────────────────────────────────────────────
@Composable
fun BookingsListScreen(
    seekerViewModel: SeekerViewModel,
    onBack: () -> Unit
) {
    val bookings by seekerViewModel.bookings.collectAsState()
    var selectedTab by remember { mutableStateOf(0) }
    val tabs = listOf("All", "Active", "Completed")

    Scaffold(
        topBar = { GigTopBar(title = "My Bookings", onBack = onBack) }
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
                contentColor = GigTeal
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
                1 -> bookings.filter { it.status == BookingStatus.CONFIRMED || it.status == BookingStatus.PENDING }
                2 -> bookings.filter { it.status == BookingStatus.COMPLETED }
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
                            colors = CardDefaults.cardColors(containerColor = GigSurface)
                        ) {
                            Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Text(b.serviceDetail, fontWeight = FontWeight.Bold, style = MaterialTheme.typography.titleMedium)
                                    StatusChip(status = b.status)
                                }
                                Text("Professional: ${b.workerName} • ${b.serviceCategory}", style = MaterialTheme.typography.bodySmall, color = GigSubtleText)
                                Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                                    Text("📅 ${b.date}", fontSize = 12.sp, color = GigSubtleText)
                                    Text("⏰ ${b.time}", fontSize = 12.sp, color = GigSubtleText)
                                    Text("₹${b.pricing.servicePrice}", fontSize = 12.sp, fontWeight = FontWeight.Bold, color = GigTeal)
                                }
                                Text("📍 ${b.address}", fontSize = 12.sp, color = GigOnSurface.copy(alpha = 0.8f))
                            }
                        }
                    }
                }
            }
        }
    }
}

// ─────────────────────────────────────────────────────────────────────────────
//  7. Seeker Profile Screen
// ─────────────────────────────────────────────────────────────────────────────
@Composable
fun SeekerProfileScreen(
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
                colors = CardDefaults.cardColors(containerColor = GigSurface)
            ) {
                Row(
                    modifier = Modifier.padding(16.dp).fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Box(
                        modifier = Modifier.size(56.dp).clip(CircleShape).background(GigTeal),
                        contentAlignment = Alignment.Center
                    ) {
                        Text("DS", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 20.sp)
                    }
                    Column {
                        Text("Demo Seeker", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
                        Text("+91 98765 43210", style = MaterialTheme.typography.bodySmall, color = GigSubtleText)
                        Text("Koregaon Park, Pune", style = MaterialTheme.typography.bodySmall, color = GigTeal)
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
                    Text("🌟 Cooperative Community Supporter", fontWeight = FontWeight.Bold, color = GigTeal)
                    Text(
                        "Your bookings have contributed ₹180 to Pune worker health & pension welfare funds.",
                        style = MaterialTheme.typography.bodySmall,
                        color = GigTealDark
                    )
                }
            }

            // Settings sections
            ProfileSettingItem(icon = Icons.Filled.LocationOn, title = "Saved Addresses", subtitle = "Home, Office & Family")
            ProfileSettingItem(icon = Icons.Filled.Security, title = "Safety & Emergency Contacts", subtitle = "24/7 Cooperative Support line")
            ProfileSettingItem(icon = Icons.Filled.Language, title = "App Language", subtitle = "English (Regional available)")
            ProfileSettingItem(icon = Icons.Filled.Help, title = "Help & Support", subtitle = "Raise a grievance or talk to federation")
            ProfileSettingItem(icon = Icons.Filled.Info, title = "About GigConnect", subtitle = "Pan-India Cooperative Gig-Services Platform v1.0")
        }
    }
}

@Composable
private fun ProfileSettingItem(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    title: String,
    subtitle: String
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = GigSurface)
    ) {
        Row(
            modifier = Modifier.padding(14.dp).fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            Icon(icon, null, tint = GigTeal)
            Column(modifier = Modifier.weight(1f)) {
                Text(title, fontWeight = FontWeight.SemiBold, style = MaterialTheme.typography.bodyMedium)
                Text(subtitle, fontSize = 12.sp, color = GigSubtleText)
            }
            Icon(Icons.Filled.ChevronRight, null, tint = GigSubtleText)
        }
    }
}
