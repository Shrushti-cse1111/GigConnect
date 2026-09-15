package com.gigconnect.app.ui.worker

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
import androidx.compose.ui.graphics.*
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.*
import com.gigconnect.app.ui.components.*
import com.gigconnect.app.ui.theme.*
import com.gigconnect.app.viewmodel.WorkerViewModel

@Composable
fun WorkerPassportScreen(
    workerViewModel: WorkerViewModel,
    onVoiceClick: () -> Unit,
    onBack: () -> Unit
) {
    val worker = workerViewModel.worker
    val equityBalance by workerViewModel.equityBalance.collectAsState()

    Scaffold(topBar = { GigTopBar(title = "Worker Passport", subtitle = "Your digital identity", onBack = onBack) }) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(GigBackground)
                .padding(padding)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Gradient header
            Box(
                modifier = Modifier.fillMaxWidth().background(Brush.verticalGradient(listOf(GigPrimaryBlue, GigPrimaryBlue))).padding(24.dp),
                contentAlignment = Alignment.Center
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Box(
                        modifier = Modifier.size(90.dp).clip(CircleShape).background(Brush.linearGradient(listOf(GigSecondaryBlue, GigPrimaryBlue))),
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
                    Text(worker.name, style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold, color = Color.White)
                    Text(worker.primarySkill, style = MaterialTheme.typography.bodyMedium, color = Color.White.copy(0.85f))
                    Spacer(Modifier.height(8.dp))
                    if (worker.isVerified) VerifiedBadge()
                    Spacer(Modifier.height(12.dp))
                    Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                        Surface(color = Color.White.copy(0.15f), shape = RoundedCornerShape(10.dp)) {
                            Column(modifier = Modifier.padding(12.dp, 8.dp), horizontalAlignment = Alignment.CenterHorizontally) {
                                Text("⭐ ${worker.rating}", fontWeight = FontWeight.ExtraBold, color = Color.White)
                                Text("Rating", style = MaterialTheme.typography.labelSmall, color = Color.White.copy(0.75f))
                            }
                        }
                        Surface(color = Color.White.copy(0.15f), shape = RoundedCornerShape(10.dp)) {
                            Column(modifier = Modifier.padding(12.dp, 8.dp), horizontalAlignment = Alignment.CenterHorizontally) {
                                Text("${worker.completedJobs}", fontWeight = FontWeight.ExtraBold, color = Color.White)
                                Text("Jobs Done", style = MaterialTheme.typography.labelSmall, color = Color.White.copy(0.75f))
                            }
                        }
                        Surface(color = Color.White.copy(0.15f), shape = RoundedCornerShape(10.dp)) {
                            Column(modifier = Modifier.padding(12.dp, 8.dp), horizontalAlignment = Alignment.CenterHorizontally) {
                                Text("₹$equityBalance", fontWeight = FontWeight.ExtraBold, color = Color.White)
                                Text("Equity", style = MaterialTheme.typography.labelSmall, color = Color.White.copy(0.75f))
                            }
                        }
                    }
                }
            }

            Column(modifier = Modifier.padding(20.dp), verticalArrangement = Arrangement.spacedBy(16.dp)) {
                // Trust Badges
                Card(modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(16.dp), colors = CardDefaults.cardColors(GigSurface), elevation = CardDefaults.cardElevation(2.dp)) {
                    Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                        Text("🛡️ Trust Badges", fontWeight = FontWeight.Bold, color = GigOnSurface)
                        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                            listOf("✓ Identity", "✓ Aadhaar", "✓ Skill", "✓ Cooperative").forEach { badge ->
                                Surface(color = GigSuccessContainer, shape = RoundedCornerShape(8.dp)) {
                                    Text(badge, style = MaterialTheme.typography.labelSmall, color = GigSuccess,
                                        fontWeight = FontWeight.Bold, modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp))
                                }
                            }
                        }
                    }
                }

                // Skills
                Card(modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(16.dp), colors = CardDefaults.cardColors(GigSurface), elevation = CardDefaults.cardElevation(2.dp)) {
                    Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
                        Text("🛠️ Certified Skills", fontWeight = FontWeight.Bold, color = GigOnSurface)
                        worker.skills.forEach { skill ->
                            Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                                Icon(Icons.Filled.CheckCircle, null, tint = GigSuccess, modifier = Modifier.size(16.dp))
                                Text(skill, style = MaterialTheme.typography.bodyMedium, color = GigOnSurface)
                            }
                        }
                    }
                }

                // Details
                Card(modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(16.dp), colors = CardDefaults.cardColors(GigSurface), elevation = CardDefaults.cardElevation(2.dp)) {
                    Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                        Text("📋 Details", fontWeight = FontWeight.Bold, color = GigOnSurface)
                        PassportRow("Experience", worker.experience)
                        PassportRow("Service Area", worker.serviceArea)
                        PassportRow("Languages", worker.languages.joinToString(", "))
                        PassportRow("Member Since", "January 2023")
                        PassportRow("Cooperative", "Pune Gig Workers Federation")
                    }
                }

                // Voice assistant shortcut
                Button(onClick = onVoiceClick, modifier = Modifier.fillMaxWidth().height(52.dp),
                    colors = ButtonDefaults.buttonColors(GigPrimaryBlue), shape = RoundedCornerShape(14.dp)) {
                    Icon(Icons.Filled.Mic, null, modifier = Modifier.size(20.dp), tint = Color.White)
                    Spacer(Modifier.width(8.dp))
                    Text("Open Voice Assistant", fontWeight = FontWeight.Bold, color = Color.White)
                }

                Spacer(Modifier.height(24.dp))
            }
        }
    }
}

@Composable
private fun PassportRow(label: String, value: String) {
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
        Text(label, style = MaterialTheme.typography.bodySmall, color = GigSubtleText)
        Text(value, style = MaterialTheme.typography.bodySmall, fontWeight = FontWeight.SemiBold, color = GigOnSurface)
    }
}
