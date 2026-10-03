package com.gigconnect.app.ui.seeker

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.*
import com.gigconnect.app.data.model.PricingBreakdown
import com.gigconnect.app.ui.components.*
import com.gigconnect.app.ui.theme.*
import com.gigconnect.app.viewmodel.SeekerViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookingScreen(
    seekerViewModel: SeekerViewModel,
    onConfirm: () -> Unit,
    onBack: () -> Unit
) {
    val worker = seekerViewModel.recommendedWorker.collectAsState().value?.worker
        ?: seekerViewModel.selectedWorker.collectAsState().value
    val service = seekerViewModel.selectedService.collectAsState().value

    var address by remember { mutableStateOf("Flat 3B, Demo Tower, Koregaon Park, Pune") }
    var instructions by remember { mutableStateOf("") }
    var selectedDate by remember { mutableStateOf("Tomorrow, 17 Sep 2026") }
    var selectedTime by remember { mutableStateOf("10:00 AM") }
    var isInstant by remember { mutableStateOf(true) }

    val basePrice = service?.basePrice ?: 500
    val platform = (basePrice * 0.10).toInt()
    val welfare = (basePrice * 0.04).toInt()
    val workerEarning = basePrice - welfare
    val pricing = PricingBreakdown(basePrice, platform, workerEarning, welfare)

    Scaffold(
        topBar = { GigTopBar(title = "Confirm Booking", onBack = onBack) },
        bottomBar = {
            Surface(shadowElevation = 8.dp) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                        Text("Total Amount", style = MaterialTheme.typography.titleSmall, fontWeight = FontWeight.SemiBold)
                        Text("₹${pricing.total}", style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.ExtraBold, color = GigPrimaryBlue)
                    }
                    Spacer(Modifier.height(10.dp))
                    Button(
                        onClick = {
                            seekerViewModel.confirmBooking(address, selectedDate, selectedTime, instructions)
                            onConfirm()
                        },
                        modifier = Modifier.fillMaxWidth().height(54.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = GigPrimaryBlue),
                        shape = RoundedCornerShape(14.dp)
                    ) {
                        Icon(Icons.Filled.CheckCircle, null, modifier = Modifier.size(20.dp), tint = androidx.compose.ui.graphics.Color.White)
                        Spacer(Modifier.width(8.dp))
                        Text("Confirm Booking", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold, color = androidx.compose.ui.graphics.Color.White)
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
            verticalArrangement = Arrangement.spacedBy(18.dp)
        ) {
            // Worker Summary
            if (worker != null) {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = GigTealContainer)
                ) {
                    Row(
                        modifier = Modifier.padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        Box(
                            modifier = Modifier.size(48.dp).clip(CircleShape).background(GigPrimaryBlue),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(worker.name.first().toString(), color = androidx.compose.ui.graphics.Color.White,
                                fontWeight = FontWeight.Bold, style = MaterialTheme.typography.titleMedium)
                        }
                        Column(modifier = Modifier.weight(1f)) {
                            Text(worker.name, fontWeight = FontWeight.Bold, color = GigOnBackground)
                            Text(worker.primarySkill, style = MaterialTheme.typography.bodySmall, color = GigPrimaryBlue)
                        }
                        VerifiedBadge(small = true)
                    }
                }
            }

            // Booking Type
            Text("Booking Type", style = MaterialTheme.typography.titleSmall, fontWeight = FontWeight.Bold, color = GigOnBackground)
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                BookingTypeButton("⚡ Instant", isInstant, GigSecondaryBlue) { isInstant = true }
                BookingTypeButton("📅 Schedule", !isInstant, GigPrimaryBlue) { isInstant = false }
            }

            if (!isInstant) {
                // Date & Time
                Text("Date & Time", style = MaterialTheme.typography.titleSmall, fontWeight = FontWeight.Bold, color = GigOnBackground)
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                    OutlinedTextField(
                        value = selectedDate, onValueChange = { selectedDate = it },
                        textStyle = MaterialTheme.typography.bodyLarge.copy(color = GigOnBackground),
                        label = { Text("Date") },
                        leadingIcon = { Icon(Icons.Filled.CalendarToday, null) },
                        modifier = Modifier.weight(1f),
                        shape = RoundedCornerShape(12.dp),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedTextColor = GigOnBackground,
                            unfocusedTextColor = GigOnBackground,
                            focusedBorderColor = GigPrimaryBlue,
                            unfocusedBorderColor = GigOutlineVariant,
                            focusedContainerColor = GigSurface,
                            unfocusedContainerColor = GigSurface
                        )
                    )
                    OutlinedTextField(
                        value = selectedTime, onValueChange = { selectedTime = it },
                        textStyle = MaterialTheme.typography.bodyLarge.copy(color = GigOnBackground),
                        label = { Text("Time") },
                        leadingIcon = { Icon(Icons.Filled.Schedule, null) },
                        modifier = Modifier.weight(1f),
                        shape = RoundedCornerShape(12.dp),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedTextColor = GigOnBackground,
                            unfocusedTextColor = GigOnBackground,
                            focusedBorderColor = GigPrimaryBlue,
                            unfocusedBorderColor = GigOutlineVariant,
                            focusedContainerColor = GigSurface,
                            unfocusedContainerColor = GigSurface
                        )
                    )
                }
            }

            // Address
            Text("Service Address", style = MaterialTheme.typography.titleSmall, fontWeight = FontWeight.Bold, color = GigOnBackground)
            OutlinedTextField(
                value = address, onValueChange = { address = it },
                textStyle = MaterialTheme.typography.bodyLarge.copy(color = GigOnBackground),
                label = { Text("Full Address") },
                leadingIcon = { Icon(Icons.Filled.LocationOn, null) },
                minLines = 2,
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedTextColor = GigOnBackground,
                    unfocusedTextColor = GigOnBackground,
                    focusedBorderColor = GigPrimaryBlue,
                    unfocusedBorderColor = GigOutlineVariant,
                    focusedContainerColor = GigSurface,
                    unfocusedContainerColor = GigSurface
                )
            )

            // Instructions
            Text("Additional Instructions (Optional)", style = MaterialTheme.typography.titleSmall, fontWeight = FontWeight.Bold, color = GigOnBackground)
            OutlinedTextField(
                value = instructions, onValueChange = { instructions = it },
                textStyle = MaterialTheme.typography.bodyLarge.copy(color = GigOnBackground),
                label = { Text("Any specific details...") },
                leadingIcon = { Icon(Icons.Filled.Description, null) },
                minLines = 3,
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedTextColor = GigOnBackground,
                    unfocusedTextColor = GigOnBackground,
                    focusedBorderColor = GigPrimaryBlue,
                    unfocusedBorderColor = GigOutlineVariant,
                    focusedContainerColor = GigSurface,
                    unfocusedContainerColor = GigSurface
                )
            )

            // Pricing
            PriceBreakdownCard(pricing)

            // Cooperative message
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(containerColor = GigSuccessContainer)
            ) {
                Row(modifier = Modifier.padding(14.dp), horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                    Text("🤝", fontSize = 20.sp)
                    Text(
                        "₹${pricing.welfareContribution} of your payment supports worker welfare & cooperative operations.",
                        style = MaterialTheme.typography.bodySmall,
                        color = GigSuccess
                    )
                }
            }

            Spacer(Modifier.height(80.dp))
        }
    }
}

@Composable
private fun RowScope.BookingTypeButton(label: String, selected: Boolean, color: androidx.compose.ui.graphics.Color, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = Modifier.weight(1f).height(46.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = if (selected) color else GigSurfaceVariant,
            contentColor = if (selected) androidx.compose.ui.graphics.Color.White else GigSubtleText
        ),
        shape = RoundedCornerShape(12.dp),
        elevation = ButtonDefaults.buttonElevation(if (selected) 4.dp else 0.dp)
    ) {
        Text(label, fontWeight = if (selected) FontWeight.Bold else FontWeight.Normal)
    }
}
