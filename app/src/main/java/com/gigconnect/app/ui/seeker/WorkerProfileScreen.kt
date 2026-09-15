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
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.*
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.*
import com.gigconnect.app.data.model.Worker
import com.gigconnect.app.ui.components.*
import com.gigconnect.app.ui.theme.*

@Composable
fun WorkerProfileScreen(
    worker: Worker,
    onBook: () -> Unit,
    onBack: () -> Unit
) {
    Scaffold(
        topBar = { GigTopBar(title = "Worker Profile", onBack = onBack) },
        bottomBar = {
            Surface(shadowElevation = 8.dp) {
                Row(
                    modifier = Modifier.fillMaxWidth().padding(16.dp),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    OutlinedButton(
                        onClick = {},
                        modifier = Modifier.weight(1f).height(52.dp),
                        shape = RoundedCornerShape(14.dp),
                        colors = ButtonDefaults.outlinedButtonColors(contentColor = GigPrimaryBlue),
                        border = BorderStroke(1.dp, GigPrimaryBlue)
                    ) {
                        Icon(Icons.Filled.Phone, null, modifier = Modifier.size(18.dp))
                        Spacer(Modifier.width(6.dp))
                        Text("Call", fontWeight = FontWeight.SemiBold)
                    }
                    Button(
                        onClick = onBook,
                        modifier = Modifier.weight(2f).height(52.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = GigPrimaryBlue),
                        shape = RoundedCornerShape(14.dp)
                    ) {
                        Text("Book Worker", style = MaterialTheme.typography.titleSmall, color = Color.White, fontWeight = FontWeight.Bold)
                    }
                }
            }
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(GigBackground)
                .verticalScroll(rememberScrollState())
                .padding(padding)
        ) {
            // Profile Header
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Brush.verticalGradient(listOf(GigPrimaryBlue, GigBackground)))
                    .padding(24.dp)
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxWidth()) {
                    Box(
                        modifier = Modifier
                            .size(88.dp)
                            .clip(CircleShape)
                            .background(Brush.linearGradient(listOf(GigSecondaryBlue, GigPrimaryBlue))),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            worker.name.split(" ").take(2).joinToString("") { it.first().toString() },
                            style = MaterialTheme.typography.headlineMedium,
                            color = Color.White,
                            fontWeight = FontWeight.ExtraBold
                        )
                    }
                    Spacer(Modifier.height(12.dp))
                    Text(worker.name, style = MaterialTheme.typography.headlineSmall, fontWeight = FontWeight.Bold, color = GigOnBackground)
                    Text(worker.primarySkill, style = MaterialTheme.typography.bodyLarge, color = GigSubtleText)
                    Spacer(Modifier.height(8.dp))
                    if (worker.isVerified) VerifiedBadge()
                    Spacer(Modifier.height(12.dp))
                    Row(horizontalArrangement = Arrangement.spacedBy(20.dp)) {
                        StatBox("⭐ ${String.format("%.1f", worker.rating)}", "${worker.totalReviews} reviews")
                        StatBox("✅ ${worker.completedJobs}", "Jobs done")
                        StatBox("📍 ${worker.distanceKm} km", "Distance")
                    }
                }
            }

            Column(modifier = Modifier.padding(20.dp), verticalArrangement = Arrangement.spacedBy(20.dp)) {

                // Skills
                ProfileSection("🛠️ Skills") {
                    worker.skills.chunked(2).forEach { row ->
                        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                            row.forEach { skill ->
                                Surface(
                                    color = GigTealContainer,
                                    shape = RoundedCornerShape(8.dp)
                                ) {
                                    Text(skill, style = MaterialTheme.typography.bodySmall, color = OnGigTealContainer,
                                        fontWeight = FontWeight.Medium, modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp))
                                }
                            }
                        }
                    }
                }

                // Trust & Verification
                ProfileSection("🛡️ Trust & Verification") {
                    listOf(
                        "Identity Verified" to worker.isVerified,
                        "Cooperative Member" to worker.cooperativeMember,
                        "Skill Verified" to worker.isVerified,
                        "Community Verified" to true,
                        "Welfare Enrolled" to worker.welfareEnrolled
                    ).forEach { (label, checked) ->
                        TrustRow(label, checked)
                    }
                }

                // Experience & Area
                ProfileSection("📋 Experience") {
                    InfoRow("Experience", worker.experience)
                    InfoRow("Service Area", worker.serviceArea)
                    InfoRow("Languages", worker.languages.joinToString(", "))
                    InfoRow("Base Price", "₹${worker.basePrice}+")
                }

                // Transparent Pricing
                ProfileSection("💰 Transparent Pricing") {
                    val platform = (worker.basePrice * 0.10).toInt()
                    val welfare = (worker.basePrice * 0.04).toInt()
                    val workerEarning = worker.basePrice - welfare
                    PriceBreakdownCard(
                        com.gigconnect.app.data.model.PricingBreakdown(worker.basePrice, platform, workerEarning, welfare)
                    )
                }

                Spacer(Modifier.height(80.dp))
            }
        }
    }
}

@Composable
private fun StatBox(value: String, label: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Surface(color = GigSurface, shape = RoundedCornerShape(12.dp), border = BorderStroke(1.dp, GigOutlineVariant)) {
            Column(modifier = Modifier.padding(horizontal = 14.dp, vertical = 8.dp), horizontalAlignment = Alignment.CenterHorizontally) {
                Text(value, style = MaterialTheme.typography.labelLarge, fontWeight = FontWeight.Bold, color = GigOnSurface)
                Text(label, style = MaterialTheme.typography.labelSmall, color = GigSubtleText)
            }
        }
    }
}

@Composable
private fun ProfileSection(title: String, content: @Composable ColumnScope.() -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = GigSurface),
        elevation = CardDefaults.cardElevation(2.dp),
        border = BorderStroke(1.dp, GigOutlineVariant)
    ) {
        Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
            Text(title, style = MaterialTheme.typography.titleSmall, fontWeight = FontWeight.Bold, color = GigOnSurface)
            content()
        }
    }
}

@Composable
private fun TrustRow(label: String, verified: Boolean) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Icon(
            if (verified) Icons.Filled.CheckCircle else Icons.Filled.Cancel,
            null,
            tint = if (verified) GigSuccess else GigError,
            modifier = Modifier.size(18.dp)
        )
        Text(label, style = MaterialTheme.typography.bodyMedium, color = if (verified) GigOnSurface else GigSubtleText)
    }
}

@Composable
private fun InfoRow(label: String, value: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(label, style = MaterialTheme.typography.bodyMedium, color = GigSubtleText)
        Text(value, style = MaterialTheme.typography.bodyMedium, fontWeight = FontWeight.SemiBold, color = GigOnSurface)
    }
}
