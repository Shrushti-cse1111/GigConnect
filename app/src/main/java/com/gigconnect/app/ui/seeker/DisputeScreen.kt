package com.gigconnect.app.ui.seeker

import androidx.compose.animation.*
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
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
import com.gigconnect.app.data.model.DisputeCategory
import com.gigconnect.app.data.model.DisputeRecord
import com.gigconnect.app.data.model.DisputeStatus
import com.gigconnect.app.ui.components.DemoDataBadge
import com.gigconnect.app.ui.components.GigTopBar
import com.gigconnect.app.ui.theme.*
import com.gigconnect.app.viewmodel.SeekerViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DisputeScreen(
    seekerViewModel: SeekerViewModel,
    bookingId: String? = null,
    onBack: () -> Unit
) {
    val currentBooking by seekerViewModel.currentBooking.collectAsState()
    val allBookings by seekerViewModel.bookings.collectAsState()
    val disputes by seekerViewModel.disputes.collectAsState()

    val targetBooking = allBookings.find { it.id == bookingId } ?: currentBooking ?: allBookings.firstOrNull()

    var selectedCategory by remember { mutableStateOf(DisputeCategory.INCOMPLETE_WORK) }
    var descriptionText by remember { mutableStateOf("") }
    var evidenceList by remember { mutableStateOf(listOf("damage_proof_1.jpg")) }
    var isSubmitted by remember { mutableStateOf(false) }
    var createdTicketId by remember { mutableStateOf<String?>(null) }
    var errorMessage by remember { mutableStateOf<String?>(null) }

    Scaffold(
        topBar = {
            GigTopBar(
                title = "Grievance & Dispute Center",
                subtitle = "Digital Panchayat Mediation",
                onBack = onBack
            )
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
            DemoDataBadge()

            if (isSubmitted) {
                // Success / Confirmation Card
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(20.dp),
                    colors = CardDefaults.cardColors(containerColor = GigSurface),
                    border = BorderStroke(1.5.dp, GigSuccess)
                ) {
                    Column(
                        modifier = Modifier.padding(24.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .size(64.dp)
                                .clip(CircleShape)
                                .background(GigSuccessContainer),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(Icons.Filled.VerifiedUser, null, tint = GigSuccess, modifier = Modifier.size(36.dp))
                        }

                        Text(
                            "Dispute Registered Successfully",
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.Bold,
                            color = GigOnSurface
                        )

                        Text(
                            "Ticket Number: ${createdTicketId ?: "DISP-4892"}",
                            style = MaterialTheme.typography.titleSmall,
                            fontWeight = FontWeight.Bold,
                            color = GigPrimaryBlue
                        )

                        Surface(
                            color = GigWarningContainer,
                            shape = RoundedCornerShape(10.dp)
                        ) {
                            Text(
                                "🔒 Escrow Payment Frozen: No funds will be released to the worker until the Digital Panchayat mediator investigates.",
                                style = MaterialTheme.typography.bodySmall,
                                color = GigWarning,
                                modifier = Modifier.padding(12.dp)
                            )
                        }

                        Text(
                            "A representative from the Pune Cooperative Federation will contact you within 4 hours.",
                            style = MaterialTheme.typography.bodySmall,
                            color = GigSubtleText,
                            modifier = Modifier.padding(top = 4.dp)
                        )

                        Button(
                            onClick = onBack,
                            modifier = Modifier.fillMaxWidth().height(48.dp),
                            colors = ButtonDefaults.buttonColors(containerColor = GigPrimaryBlue),
                            shape = RoundedCornerShape(12.dp)
                        ) {
                            Text("Back to Bookings", fontWeight = FontWeight.Bold, color = Color.White)
                        }
                    }
                }
            } else {
                // Mediation Guarantee Banner
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(14.dp),
                    colors = CardDefaults.cardColors(containerColor = GigTealContainer)
                ) {
                    Row(
                        modifier = Modifier.padding(14.dp),
                        horizontalArrangement = Arrangement.spacedBy(10.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text("⚖️", fontSize = 22.sp)
                        Column {
                            Text(
                                "Fair Democratic Resolution",
                                fontWeight = FontWeight.Bold,
                                style = MaterialTheme.typography.titleSmall,
                                color = OnGigTealContainer
                            )
                            Text(
                                "GigConnect is governed cooperatively. Disputes are resolved transparently by our peer grievance panel.",
                                style = MaterialTheme.typography.bodySmall,
                                color = OnGigTealContainer
                            )
                        }
                    }
                }

                // Booking Reference Info
                if (targetBooking != null) {
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(14.dp),
                        colors = CardDefaults.cardColors(containerColor = GigSurface),
                        border = BorderStroke(1.dp, GigOutlineVariant)
                    ) {
                        Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(6.dp)) {
                            Text("Referenced Booking", style = MaterialTheme.typography.labelSmall, color = GigSubtleText)
                            Text(
                                "${targetBooking.serviceCategory}: ${targetBooking.serviceDetail}",
                                fontWeight = FontWeight.Bold,
                                style = MaterialTheme.typography.titleMedium,
                                color = GigOnSurface
                            )
                            Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                                Text("Worker: ${targetBooking.workerName}", style = MaterialTheme.typography.bodySmall, color = GigSubtleText)
                                Text("Booking ID: ${targetBooking.id}", style = MaterialTheme.typography.bodySmall, color = GigPrimaryBlue, fontWeight = FontWeight.SemiBold)
                            }
                        }
                    }
                }

                // Dispute Category Selection
                Text("Select Grievance Category", style = MaterialTheme.typography.titleSmall, fontWeight = FontWeight.Bold, color = GigOnBackground)
                Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    DisputeCategory.values().forEach { category ->
                        val isSelected = selectedCategory == category
                        Surface(
                            color = if (isSelected) GigTealContainer else GigSurface,
                            shape = RoundedCornerShape(12.dp),
                            border = BorderStroke(if (isSelected) 1.5.dp else 1.dp, if (isSelected) GigPrimaryBlue else GigOutlineVariant),
                            modifier = Modifier.fillMaxWidth().clickable { selectedCategory = category }
                        ) {
                            Row(
                                modifier = Modifier.padding(14.dp).fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(12.dp)
                            ) {
                                Text(category.icon, fontSize = 20.sp)
                                Column(modifier = Modifier.weight(1f)) {
                                    Text(
                                        category.label,
                                        fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
                                        color = if (isSelected) GigPrimaryBlue else GigOnSurface,
                                        style = MaterialTheme.typography.bodyMedium
                                    )
                                }
                                RadioButton(
                                    selected = isSelected,
                                    onClick = { selectedCategory = category },
                                    colors = RadioButtonDefaults.colors(selectedColor = GigPrimaryBlue)
                                )
                            }
                        }
                    }
                }

                // Description Input
                Text("Describe the Issue", style = MaterialTheme.typography.titleSmall, fontWeight = FontWeight.Bold, color = GigOnBackground)
                OutlinedTextField(
                    value = descriptionText,
                    onValueChange = {
                        descriptionText = it
                        errorMessage = null
                    },
                    textStyle = MaterialTheme.typography.bodyLarge.copy(color = GigOnBackground),
                    label = { Text("Detailed description of what occurred...") },
                    minLines = 4,
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedTextColor = GigOnBackground,
                        unfocusedTextColor = GigOnBackground,
                        focusedBorderColor = GigPrimaryBlue,
                        unfocusedBorderColor = GigOutlineVariant,
                        focusedContainerColor = GigSurface,
                        unfocusedContainerColor = GigSurface,
                        cursorColor = GigPrimaryBlue
                    )
                )

                // Evidence Attachments
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(14.dp),
                    colors = CardDefaults.cardColors(containerColor = GigSurface),
                    border = BorderStroke(1.dp, GigOutlineVariant)
                ) {
                    Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
                        Text("Attach Photos / Proof", fontWeight = FontWeight.Bold, style = MaterialTheme.typography.titleSmall)
                        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                            OutlinedButton(
                                onClick = {
                                    evidenceList = evidenceList + "evidence_${System.currentTimeMillis() % 1000}.jpg"
                                },
                                shape = RoundedCornerShape(10.dp),
                                border = BorderStroke(1.dp, GigPrimaryBlue)
                            ) {
                                Icon(Icons.Filled.AddPhotoAlternate, null, modifier = Modifier.size(16.dp), tint = GigPrimaryBlue)
                                Spacer(Modifier.width(6.dp))
                                Text("Add Photo", fontSize = 12.sp, color = GigPrimaryBlue)
                            }
                        }

                        if (evidenceList.isNotEmpty()) {
                            LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                                items(evidenceList) { file ->
                                    Surface(
                                        color = GigTealContainer,
                                        shape = RoundedCornerShape(8.dp),
                                        border = BorderStroke(1.dp, GigPrimaryBlue)
                                    ) {
                                        Row(
                                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                                            verticalAlignment = Alignment.CenterVertically,
                                            horizontalArrangement = Arrangement.spacedBy(4.dp)
                                        ) {
                                            Text("📸 $file", style = MaterialTheme.typography.labelSmall, color = OnGigTealContainer)
                                            IconButton(
                                                onClick = { evidenceList = evidenceList.filterNot { it == file } },
                                                modifier = Modifier.size(16.dp)
                                            ) {
                                                Icon(Icons.Filled.Close, null, tint = GigError, modifier = Modifier.size(12.dp))
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }

                // Error Banner
                if (errorMessage != null) {
                    Card(
                        colors = CardDefaults.cardColors(containerColor = GigErrorContainer),
                        shape = RoundedCornerShape(10.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Row(
                            modifier = Modifier.padding(12.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Icon(Icons.Filled.Warning, null, tint = GigError, modifier = Modifier.size(20.dp))
                            Text(errorMessage!!, style = MaterialTheme.typography.bodySmall, color = GigError, fontWeight = FontWeight.Medium)
                        }
                    }
                }

                // Submit Button
                Button(
                    onClick = {
                        if (descriptionText.trim().length < 5) {
                            errorMessage = "Please enter at least 5 characters describing the issue."
                            return@Button
                        }
                        val bId = targetBooking?.id ?: "BK1024"
                        seekerViewModel.raiseDispute(bId, selectedCategory, descriptionText, evidenceList)
                        createdTicketId = "DISP-${(1000..9999).random()}"
                        isSubmitted = true
                    },
                    modifier = Modifier.fillMaxWidth().height(52.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = GigError),
                    shape = RoundedCornerShape(14.dp)
                ) {
                    Icon(Icons.Filled.Gavel, null, modifier = Modifier.size(18.dp), tint = Color.White)
                    Spacer(Modifier.width(8.dp))
                    Text("Submit Dispute & Freeze Escrow", style = MaterialTheme.typography.titleSmall, fontWeight = FontWeight.Bold, color = Color.White)
                }

                Spacer(Modifier.height(30.dp))
            }
        }
    }
}
