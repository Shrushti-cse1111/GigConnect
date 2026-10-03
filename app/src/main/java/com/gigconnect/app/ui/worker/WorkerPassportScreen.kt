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
    val kycInfo by workerViewModel.kycInfo.collectAsState()
    val certifiedSkills by workerViewModel.certifiedSkills.collectAsState()
    val serviceRadius by workerViewModel.serviceRadiusKm.collectAsState()
    val workingHours by workerViewModel.workingHours.collectAsState()

    var showKycDialog by remember { mutableStateOf(false) }
    var showConfigDialog by remember { mutableStateOf(false) }
    var tempRadius by remember { mutableFloatStateOf(serviceRadius.toFloat()) }
    var tempHours by remember { mutableStateOf(workingHours) }

    // ── KYC Upload Dialog ────────────────────────────────────────────────────
    if (showKycDialog) {
        var selectedDoc by remember { mutableStateOf("Aadhaar Card (UIDAI)") }
        AlertDialog(
            onDismissRequest = { showKycDialog = false },
            title = { Text("Submit KYC / Identity Document", fontWeight = FontWeight.Bold, color = GigOnSurface) },
            text = {
                Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                    Text("Select verification document for cooperative accreditation. Private document numbers are encrypted and never shown publicly to customers.", fontSize = 12.sp, color = GigSubtleText)
                    listOf("Aadhaar Card (UIDAI)", "Labour Co-op Smart Card", "Voter ID Card", "Driving Licence").forEach { doc ->
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable { selectedDoc = doc }
                                .padding(vertical = 4.dp)
                        ) {
                            RadioButton(selected = selectedDoc == doc, onClick = { selectedDoc = doc }, colors = RadioButtonDefaults.colors(selectedColor = GigPrimaryBlue))
                            Spacer(Modifier.width(8.dp))
                            Text(doc, fontSize = 13.sp, color = GigOnSurface)
                        }
                    }
                }
            },
            confirmButton = {
                Button(
                    onClick = {
                        workerViewModel.submitKycDocument(selectedDoc)
                        showKycDialog = false
                    },
                    colors = ButtonDefaults.buttonColors(GigPrimaryBlue),
                    shape = RoundedCornerShape(10.dp)
                ) {
                    Text("Upload & Verify", color = Color.White, fontWeight = FontWeight.Bold)
                }
            },
            dismissButton = {
                TextButton(onClick = { showKycDialog = false }) { Text("Cancel") }
            }
        )
    }

    // ── Service Area & Availability Dialog ───────────────────────────────────
    if (showConfigDialog) {
        AlertDialog(
            onDismissRequest = { showConfigDialog = false },
            title = { Text("Service Area & Working Hours", fontWeight = FontWeight.Bold, color = GigOnSurface) },
            text = {
                Column(verticalArrangement = Arrangement.spacedBy(14.dp)) {
                    Text("Preferred Service Radius: ${tempRadius.toInt()} km", fontWeight = FontWeight.SemiBold, color = GigOnSurface, fontSize = 13.sp)
                    Slider(
                        value = tempRadius,
                        onValueChange = { tempRadius = it },
                        valueRange = 3f..30f,
                        steps = 8,
                        colors = SliderDefaults.colors(thumbColor = GigPrimaryBlue, activeTrackColor = GigPrimaryBlue)
                    )
                    OutlinedTextField(
                        value = tempHours,
                        onValueChange = { tempHours = it },
                        label = { Text("Daily Working Hours") },
                        modifier = Modifier.fillMaxWidth(),
                        colors = OutlinedTextFieldDefaults.colors(focusedTextColor = GigOnSurface, unfocusedTextColor = GigOnSurface)
                    )
                }
            },
            confirmButton = {
                Button(
                    onClick = {
                        workerViewModel.updateServiceRadius(tempRadius.toInt())
                        workerViewModel.updateWorkingHours(tempHours)
                        showConfigDialog = false
                    },
                    colors = ButtonDefaults.buttonColors(GigPrimaryBlue),
                    shape = RoundedCornerShape(10.dp)
                ) {
                    Text("Save Preferences", color = Color.White, fontWeight = FontWeight.Bold)
                }
            },
            dismissButton = {
                TextButton(onClick = { showConfigDialog = false }) { Text("Cancel") }
            }
        )
    }

    Scaffold(topBar = { GigTopBar(title = "Worker Passport", subtitle = "Your cooperative identity", onBack = onBack) }) { padding ->
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
                    VerifiedBadge(isVerified = true)
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
                                Text("Co-op Equity", style = MaterialTheme.typography.labelSmall, color = Color.White.copy(0.75f))
                            }
                        }
                    }
                }
            }

            Column(modifier = Modifier.padding(20.dp), verticalArrangement = Arrangement.spacedBy(16.dp)) {
                // KYC & Identity Status Card
                Card(modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(16.dp), colors = CardDefaults.cardColors(GigSurface), elevation = CardDefaults.cardElevation(2.dp)) {
                    Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
                        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
                            Text("🪪 Identity & KYC Status", fontWeight = FontWeight.Bold, color = GigOnSurface)
                            Surface(color = if (kycInfo.status == "VERIFIED") GigSuccessContainer else GigErrorContainer, shape = RoundedCornerShape(8.dp)) {
                                Text(
                                    kycInfo.status,
                                    color = if (kycInfo.status == "VERIFIED") GigSuccess else GigError,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 11.sp,
                                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                                )
                            }
                        }
                        PassportRow("Accredited Document", kycInfo.idType)
                        PassportRow("Verification Note", kycInfo.verifiedAt)
                        PassportRow("Cooperative Society", kycInfo.cooperativeSociety)
                        PassportRow("Member Reg ID", kycInfo.memberRegId)
                        OutlinedButton(
                            onClick = { showKycDialog = true },
                            modifier = Modifier.fillMaxWidth().height(42.dp),
                            shape = RoundedCornerShape(10.dp)
                        ) {
                            Text("Update / Resubmit KYC Document", fontSize = 13.sp)
                        }
                    }
                }

                // Service Area & Availability Settings Card
                Card(modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(16.dp), colors = CardDefaults.cardColors(GigSurface), elevation = CardDefaults.cardElevation(2.dp)) {
                    Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
                        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
                            Text("📍 Service Area & Availability", fontWeight = FontWeight.Bold, color = GigOnSurface)
                            IconButton(onClick = { showConfigDialog = true }) {
                                Icon(Icons.Filled.Edit, "Edit Service Preferences", tint = GigPrimaryBlue)
                            }
                        }
                        PassportRow("Preferred Service Radius", "$serviceRadius km from Pune HQ")
                        PassportRow("Active Working Hours", workingHours)
                        PassportRow("Base Locality", "Koregaon Park & Shivajinagar")
                    }
                }

                // Dynamic Certified Skills
                Card(modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(16.dp), colors = CardDefaults.cardColors(GigSurface), elevation = CardDefaults.cardElevation(2.dp)) {
                    Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
                        Text("🛠️ Cooperative Certified Skills", fontWeight = FontWeight.Bold, color = GigOnSurface)
                        certifiedSkills.forEach { skill ->
                            Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                                Icon(Icons.Filled.CheckCircle, null, tint = GigSuccess, modifier = Modifier.size(16.dp))
                                Text(skill, style = MaterialTheme.typography.bodyMedium, color = GigOnSurface)
                            }
                        }
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
