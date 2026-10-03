package com.gigconnect.app.ui.seeker

import androidx.compose.animation.*
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
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
import androidx.compose.ui.window.Dialog
import com.gigconnect.app.data.demo.DemoDataProvider
import com.gigconnect.app.data.model.PricingBreakdown
import com.gigconnect.app.data.model.ServiceCategory
import com.gigconnect.app.ui.components.DemoDataBadge
import com.gigconnect.app.ui.components.GigTopBar
import com.gigconnect.app.ui.components.PriceBreakdownCard
import com.gigconnect.app.ui.theme.*
import com.gigconnect.app.viewmodel.SeekerViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ServiceRequestScreen(
    seekerViewModel: SeekerViewModel,
    cityName: String,
    onProceedToMatch: () -> Unit,
    onBack: () -> Unit
) {
    val draft by seekerViewModel.requestDraft.collectAsState()
    val selectedService by seekerViewModel.selectedService.collectAsState()
    val service = selectedService ?: DemoDataProvider.serviceCategories.first()

    var descriptionText by remember { mutableStateOf(draft.description) }
    var addressText by remember { mutableStateOf(draft.address) }
    var pincodeText by remember { mutableStateOf(draft.pincode) }
    var selectedUrgency by remember { mutableStateOf(draft.urgency) }
    var isInstant by remember { mutableStateOf(draft.isInstant) }
    var selectedDate by remember { mutableStateOf(draft.selectedDate.ifBlank { "Today, 18 Oct" }) }
    var selectedTime by remember { mutableStateOf(draft.selectedTime.ifBlank { "10:00 AM - 12:00 PM" }) }
    var errorMessage by remember { mutableStateOf<String?>(null) }

    var showDatePickerDialog by remember { mutableStateOf(false) }
    var showTimePickerDialog by remember { mutableStateOf(false) }

    // ─── Dynamic AI Price Engine based on Problem Description ────────────────
    val isDescriptionProvided = descriptionText.trim().length >= 8

    val aiDetectedComplexity = remember(descriptionText, service.id) {
        val text = descriptionText.lowercase()
        when {
            text.contains("install") || text.contains("wiring") || text.contains("heavy") || text.contains("deep") || text.contains("motor") || text.contains("ac") ->
                "Major Repair / Installation & Parts Sourcing"
            text.contains("repair") || text.contains("leak") || text.contains("fitting") || text.contains("switch") || text.contains("fan") || text.contains("pipe") ->
                "Standard Diagnostic & Fix (Moderate Complexity)"
            text.contains("check") || text.contains("minor") || text.contains("cleaning") || text.contains("inspect") ->
                "Light Maintenance / Preventive Inspection"
            else -> "Standard Cooperative Task Evaluation"
        }
    }

    val dynamicBasePrice = remember(descriptionText, selectedUrgency, service.basePrice) {
        val text = descriptionText.lowercase()
        var calculated = service.basePrice
        if (text.contains("install") || text.contains("wiring") || text.contains("deep") || text.contains("ac") || text.contains("heavy")) {
            calculated += 200
        } else if (text.contains("repair") || text.contains("leak") || text.contains("fan") || text.contains("pipe")) {
            calculated += 80
        }
        if (selectedUrgency == "Urgent") calculated += 100
        if (selectedUrgency == "Emergency") calculated += 200
        calculated
    }

    val dynamicMinPrice = (dynamicBasePrice * 0.9).toInt()
    val dynamicMaxPrice = (dynamicBasePrice * 1.25).toInt()

    val platformFee = (dynamicBasePrice * 0.10).toInt()
    val welfareFee = (dynamicBasePrice * 0.04).toInt()
    val workerEarnings = dynamicBasePrice - welfareFee
    val pricing = PricingBreakdown(dynamicBasePrice, platformFee, workerEarnings, welfareFee)

    val isServiceable = seekerViewModel.validateServiceArea(cityName, pincodeText)

    // ─── Interactive Calendar Picker Dialog ──────────────────────────────────
    if (showDatePickerDialog) {
        CalendarPickerDialog(
            initialDate = selectedDate,
            onDateSelected = { date ->
                selectedDate = date
                showDatePickerDialog = false
            },
            onDismiss = { showDatePickerDialog = false }
        )
    }

    // ─── Interactive Clock Time Picker Dialog ────────────────────────────────
    if (showTimePickerDialog) {
        ClockTimePickerDialog(
            initialTime = selectedTime,
            onTimeSelected = { time ->
                selectedTime = time
                showTimePickerDialog = false
            },
            onDismiss = { showTimePickerDialog = false }
        )
    }

    Scaffold(
        topBar = {
            GigTopBar(
                title = "Create Service Request",
                subtitle = "${service.name} in $cityName",
                onBack = onBack
            )
        },
        bottomBar = {
            Surface(shadowElevation = 10.dp, color = GigSurface) {
                Column(
                    modifier = Modifier
                        .padding(horizontal = 20.dp, vertical = 14.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column {
                            Text(
                                "Estimated Cost Range",
                                style = MaterialTheme.typography.labelSmall,
                                color = GigSubtleText
                            )
                            Spacer(Modifier.height(2.dp))
                            if (isDescriptionProvided) {
                                Text(
                                    "₹$dynamicMinPrice – ₹$dynamicMaxPrice",
                                    style = MaterialTheme.typography.titleMedium,
                                    fontWeight = FontWeight.ExtraBold,
                                    color = GigPrimaryBlue
                                )
                            } else {
                                Text(
                                    "Describe issue above",
                                    style = MaterialTheme.typography.titleSmall,
                                    fontWeight = FontWeight.SemiBold,
                                    color = GigSecondaryBlue
                                )
                            }
                        }
                        if (isDescriptionProvided) {
                            Surface(
                                color = GigTealContainer,
                                shape = RoundedCornerShape(8.dp)
                            ) {
                                Text(
                                    "4% Welfare Included",
                                    style = MaterialTheme.typography.labelSmall,
                                    color = OnGigTealContainer,
                                    fontWeight = FontWeight.Bold,
                                    modifier = Modifier.padding(horizontal = 10.dp, vertical = 5.dp)
                                )
                            }
                        }
                    }

                    Button(
                        onClick = {
                            if (descriptionText.trim().length < 8) {
                                errorMessage = "Please describe the problem (at least 8 characters) for AI FairPrice analysis."
                                return@Button
                            }
                            if (addressText.trim().isBlank()) {
                                errorMessage = "Please provide your service address."
                                return@Button
                            }
                            if (!isServiceable) {
                                errorMessage = "Services are currently unavailable in this area."
                                return@Button
                            }

                            // Save draft details
                            seekerViewModel.updateDraftDescription(descriptionText)
                            seekerViewModel.updateDraftUrgency(selectedUrgency)
                            seekerViewModel.updateDraftLocation(addressText, "Local Area", cityName, pincodeText)
                            seekerViewModel.updateDraftSchedule(selectedDate, selectedTime, isInstant)
                            seekerViewModel.runSmartMatch()
                            onProceedToMatch()
                        },
                        enabled = isServiceable,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(54.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = GigPrimaryBlue),
                        shape = RoundedCornerShape(14.dp)
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Text(
                                "Find FairMatch Verified Workers",
                                style = MaterialTheme.typography.titleSmall,
                                fontWeight = FontWeight.Bold,
                                color = Color.White
                            )
                            Icon(Icons.AutoMirrored.Filled.ArrowForward, null, tint = Color.White)
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
                .padding(horizontal = 20.dp, vertical = 20.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            DemoDataBadge()

            // 1. Service Category Selector
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = GigSurface),
                border = BorderStroke(1.dp, GigOutlineVariant)
            ) {
                Column(modifier = Modifier.padding(18.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            "1. Service Category",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                            color = GigOnBackground
                        )
                        Surface(
                            color = GigTealContainer,
                            shape = RoundedCornerShape(8.dp)
                        ) {
                            Text(
                                service.name,
                                style = MaterialTheme.typography.labelSmall,
                                fontWeight = FontWeight.Bold,
                                color = OnGigTealContainer,
                                modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp)
                            )
                        }
                    }

                    LazyRow(
                        horizontalArrangement = Arrangement.spacedBy(10.dp),
                        contentPadding = PaddingValues(vertical = 4.dp)
                    ) {
                        items(DemoDataProvider.serviceCategories) { cat ->
                            val isSelected = cat.id == service.id
                            Surface(
                                color = if (isSelected) GigPrimaryBlue else GigSurface,
                                shape = RoundedCornerShape(12.dp),
                                border = BorderStroke(1.dp, if (isSelected) GigPrimaryBlue else GigOutlineVariant),
                                modifier = Modifier.clickable {
                                    seekerViewModel.initServiceRequest(cat, cityName)
                                }
                            ) {
                                Row(
                                    modifier = Modifier.padding(horizontal = 12.dp, vertical = 10.dp),
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                                ) {
                                    Text(cat.icon, fontSize = 16.sp)
                                    Text(
                                        cat.name,
                                        style = MaterialTheme.typography.labelMedium,
                                        fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
                                        color = if (isSelected) Color.White else GigOnBackground
                                    )
                                }
                            }
                        }
                    }
                }
            }

            // 2. Problem Description Input
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = GigSurface),
                border = BorderStroke(1.dp, GigOutlineVariant)
            ) {
                Column(modifier = Modifier.padding(18.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            "2. Problem Description *",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                            color = GigOnBackground
                        )
                        Text(
                            "${descriptionText.length}/150",
                            style = MaterialTheme.typography.labelSmall,
                            color = GigSubtleText
                        )
                    }

                    Text(
                        "Describe what needs to be fixed. Our AI analyzes the task scope to calculate transparent pricing.",
                        style = MaterialTheme.typography.bodySmall,
                        color = GigSubtleText
                    )

                    OutlinedTextField(
                        value = descriptionText,
                        onValueChange = {
                            if (it.length <= 150) {
                                descriptionText = it
                                errorMessage = null
                            }
                        },
                        textStyle = MaterialTheme.typography.bodyLarge.copy(color = GigOnBackground),
                        placeholder = {
                            Text(
                                "e.g. Kitchen tap is leaking heavily and needs washer/pipe replacement.",
                                color = GigPlaceholderText
                            )
                        },
                        minLines = 3,
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

                    // Quick prompt chips
                    Text(
                        "Quick Examples:",
                        style = MaterialTheme.typography.labelSmall,
                        fontWeight = FontWeight.SemiBold,
                        color = GigSubtleText
                    )
                    LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        val prompts = listOf(
                            "Ceiling fan making noise & wobbling",
                            "Kitchen sink tap water pipe leakage",
                            "AC cooling issue & filter deep cleaning",
                            "Electrical main switch sparking"
                        )
                        items(prompts) { p ->
                            Surface(
                                color = GigSurfaceVariant,
                                shape = RoundedCornerShape(8.dp),
                                border = BorderStroke(0.5.dp, GigOutlineVariant),
                                modifier = Modifier.clickable {
                                    descriptionText = p
                                    errorMessage = null
                                }
                            ) {
                                Text(
                                    p,
                                    style = MaterialTheme.typography.labelSmall,
                                    color = GigOnSurface,
                                    modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp)
                                )
                            }
                        }
                    }
                }
            }

            // 3. Upload Photos / Video
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = GigSurface),
                border = BorderStroke(1.dp, GigOutlineVariant)
            ) {
                Column(modifier = Modifier.padding(18.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
                    Text(
                        "3. Attach Photos / Video (Optional)",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = GigOnBackground
                    )
                    Text(
                        "Helps worker prepare exact tools and accurate replacement parts beforehand.",
                        style = MaterialTheme.typography.bodySmall,
                        color = GigSubtleText
                    )

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        OutlinedButton(
                            onClick = {
                                seekerViewModel.addDraftMedia("photo_${System.currentTimeMillis() % 1000}.jpg", isVideo = false)
                            },
                            modifier = Modifier.weight(1f).height(46.dp),
                            shape = RoundedCornerShape(10.dp),
                            border = BorderStroke(1.dp, GigPrimaryBlue)
                        ) {
                            Icon(Icons.Filled.AddAPhoto, null, modifier = Modifier.size(18.dp), tint = GigPrimaryBlue)
                            Spacer(Modifier.width(6.dp))
                            Text("Add Photo", fontSize = 13.sp, color = GigPrimaryBlue, fontWeight = FontWeight.Bold)
                        }

                        OutlinedButton(
                            onClick = {
                                seekerViewModel.addDraftMedia("video_${System.currentTimeMillis() % 1000}.mp4", isVideo = true)
                            },
                            modifier = Modifier.weight(1f).height(46.dp),
                            shape = RoundedCornerShape(10.dp),
                            border = BorderStroke(1.dp, GigSecondaryBlue)
                        ) {
                            Icon(Icons.Filled.Videocam, null, modifier = Modifier.size(18.dp), tint = GigSecondaryBlue)
                            Spacer(Modifier.width(6.dp))
                            Text("Add Video", fontSize = 13.sp, color = GigSecondaryBlue, fontWeight = FontWeight.Bold)
                        }
                    }

                    // Uploaded Media Previews
                    if (draft.mediaItems.isNotEmpty()) {
                        LazyRow(
                            horizontalArrangement = Arrangement.spacedBy(10.dp),
                            modifier = Modifier.padding(top = 4.dp)
                        ) {
                            items(draft.mediaItems) { item ->
                                Surface(
                                    color = GigSurfaceVariant,
                                    shape = RoundedCornerShape(10.dp),
                                    border = BorderStroke(1.dp, GigOutlineVariant)
                                ) {
                                    Row(
                                        modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp),
                                        verticalAlignment = Alignment.CenterVertically,
                                        horizontalArrangement = Arrangement.spacedBy(6.dp)
                                    ) {
                                        Text(if (item.isVideo) "🎥" else "📷", fontSize = 14.sp)
                                        Text(item.name, style = MaterialTheme.typography.labelSmall, color = GigOnSurface)
                                        IconButton(
                                            onClick = { seekerViewModel.removeDraftMedia(item.id) },
                                            modifier = Modifier.size(20.dp)
                                        ) {
                                            Icon(Icons.Filled.Close, "Remove", tint = GigError, modifier = Modifier.size(14.dp))
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }

            // 4. Urgency Tier
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = GigSurface),
                border = BorderStroke(1.dp, GigOutlineVariant)
            ) {
                Column(modifier = Modifier.padding(18.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
                    Text(
                        "4. Service Urgency",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = GigOnBackground
                    )

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        UrgencyOptionChip("Standard", "Regular rate", selectedUrgency == "Standard", Modifier.weight(1f)) {
                            selectedUrgency = "Standard"
                            seekerViewModel.updateDraftUrgency("Standard")
                        }
                        UrgencyOptionChip("Urgent", "+₹100", selectedUrgency == "Urgent", Modifier.weight(1f)) {
                            selectedUrgency = "Urgent"
                            seekerViewModel.updateDraftUrgency("Urgent")
                        }
                        UrgencyOptionChip("Emergency", "+₹200", selectedUrgency == "Emergency", Modifier.weight(1f)) {
                            selectedUrgency = "Emergency"
                            seekerViewModel.updateDraftUrgency("Emergency")
                        }
                    }
                }
            }

            // 5. Service Location & Service Area Validation
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = GigSurface),
                border = BorderStroke(1.dp, GigOutlineVariant)
            ) {
                Column(modifier = Modifier.padding(18.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            "5. Service Location",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                            color = GigOnBackground
                        )
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(6.dp)
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(8.dp)
                                    .clip(CircleShape)
                                    .background(if (isServiceable) GigSuccess else GigError)
                            )
                            Text(
                                if (isServiceable) "Service Available" else "Area Not Served",
                                style = MaterialTheme.typography.labelSmall,
                                fontWeight = FontWeight.Bold,
                                color = if (isServiceable) GigSuccess else GigError
                            )
                        }
                    }

                    OutlinedTextField(
                        value = addressText,
                        onValueChange = {
                            addressText = it
                            errorMessage = null
                        },
                        textStyle = MaterialTheme.typography.bodyLarge.copy(color = GigOnBackground),
                        label = { Text("Service Address (Flat, Street, Area)") },
                        leadingIcon = { Icon(Icons.Filled.LocationOn, null, tint = GigPrimaryBlue) },
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

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        OutlinedTextField(
                            value = pincodeText,
                            onValueChange = {
                                if (it.length <= 6 && it.all { c -> c.isDigit() }) {
                                    pincodeText = it
                                }
                            },
                            textStyle = MaterialTheme.typography.bodyLarge.copy(color = GigOnBackground),
                            label = { Text("PIN Code") },
                            modifier = Modifier.weight(1f),
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
                        OutlinedTextField(
                            value = cityName,
                            onValueChange = {},
                            readOnly = true,
                            textStyle = MaterialTheme.typography.bodyLarge.copy(color = GigOnBackground),
                            label = { Text("City Hub") },
                            modifier = Modifier.weight(1f),
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
                    }

                    if (!isServiceable) {
                        Surface(
                            color = GigErrorContainer,
                            shape = RoundedCornerShape(10.dp),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Row(
                                modifier = Modifier.padding(12.dp),
                                horizontalArrangement = Arrangement.spacedBy(8.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Icon(Icons.Filled.LocationOff, null, tint = GigError, modifier = Modifier.size(20.dp))
                                Text(
                                    "GigConnect cooperative services are not currently active in this pincode. Please select a supported city hub.",
                                    style = MaterialTheme.typography.bodySmall,
                                    color = GigError
                                )
                            }
                        }
                    }
                }
            }

            // 6. Schedule (Interactive Calendar & Clock Pickers)
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = GigSurface),
                border = BorderStroke(1.dp, GigOutlineVariant)
            ) {
                Column(modifier = Modifier.padding(18.dp), verticalArrangement = Arrangement.spacedBy(14.dp)) {
                    Text(
                        "6. Preferred Timing",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = GigOnBackground
                    )

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        FilterChip(
                            selected = isInstant,
                            onClick = { isInstant = true },
                            label = { Text("⚡ Immediate / Instant") },
                            modifier = Modifier.weight(1f),
                            colors = FilterChipDefaults.filterChipColors(
                                selectedContainerColor = GigTealContainer,
                                selectedLabelColor = GigPrimaryBlue
                            )
                        )
                        FilterChip(
                            selected = !isInstant,
                            onClick = { isInstant = false },
                            label = { Text("📅 Scheduled") },
                            modifier = Modifier.weight(1f),
                            colors = FilterChipDefaults.filterChipColors(
                                selectedContainerColor = GigTealContainer,
                                selectedLabelColor = GigPrimaryBlue
                            )
                        )
                    }

                    if (!isInstant) {
                        Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                            // Date Selection with Calendar trigger
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clip(RoundedCornerShape(12.dp))
                                    .background(GigSurfaceVariant)
                                    .border(1.dp, GigOutlineVariant, RoundedCornerShape(12.dp))
                                    .clickable { showDatePickerDialog = true }
                                    .padding(horizontal = 14.dp, vertical = 12.dp),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                                ) {
                                    Icon(Icons.Filled.CalendarMonth, "Calendar", tint = GigPrimaryBlue, modifier = Modifier.size(22.dp))
                                    Column {
                                        Text("Selected Date", style = MaterialTheme.typography.labelSmall, color = GigSubtleText)
                                        Text(selectedDate, style = MaterialTheme.typography.bodyMedium, fontWeight = FontWeight.Bold, color = GigOnBackground)
                                    }
                                }
                                OutlinedButton(
                                    onClick = { showDatePickerDialog = true },
                                    shape = RoundedCornerShape(8.dp),
                                    contentPadding = PaddingValues(horizontal = 10.dp, vertical = 4.dp),
                                    border = BorderStroke(1.dp, GigPrimaryBlue)
                                ) {
                                    Text("Open Calendar", fontSize = 11.sp, color = GigPrimaryBlue, fontWeight = FontWeight.Bold)
                                }
                            }

                            // Time Selection with Clock trigger
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clip(RoundedCornerShape(12.dp))
                                    .background(GigSurfaceVariant)
                                    .border(1.dp, GigOutlineVariant, RoundedCornerShape(12.dp))
                                    .clickable { showTimePickerDialog = true }
                                    .padding(horizontal = 14.dp, vertical = 12.dp),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                                ) {
                                    Icon(Icons.Filled.AccessTime, "Clock", tint = GigPrimaryBlue, modifier = Modifier.size(22.dp))
                                    Column {
                                        Text("Selected Time Slot", style = MaterialTheme.typography.labelSmall, color = GigSubtleText)
                                        Text(selectedTime, style = MaterialTheme.typography.bodyMedium, fontWeight = FontWeight.Bold, color = GigOnBackground)
                                    }
                                }
                                OutlinedButton(
                                    onClick = { showTimePickerDialog = true },
                                    shape = RoundedCornerShape(8.dp),
                                    contentPadding = PaddingValues(horizontal = 10.dp, vertical = 4.dp),
                                    border = BorderStroke(1.dp, GigPrimaryBlue)
                                ) {
                                    Text("Pick Clock Time", fontSize = 11.sp, color = GigPrimaryBlue, fontWeight = FontWeight.Bold)
                                }
                            }
                        }
                    }
                }
            }

            // 7. Dynamic AI Price Estimate / Range Breakdown
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = GigSurface),
                border = BorderStroke(1.dp, GigOutlineVariant)
            ) {
                Column(modifier = Modifier.padding(18.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            "7. FairMatch AI Price Estimate",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                            color = GigOnBackground
                        )
                        Surface(
                            color = if (isDescriptionProvided) GigSuccessContainer else GigSurfaceVariant,
                            shape = RoundedCornerShape(8.dp)
                        ) {
                            Text(
                                if (isDescriptionProvided) "🤖 AI Analyzed" else "Pending Input",
                                style = MaterialTheme.typography.labelSmall,
                                fontWeight = FontWeight.Bold,
                                color = if (isDescriptionProvided) GigSuccess else GigSubtleText,
                                modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                            )
                        }
                    }

                    if (isDescriptionProvided) {
                        // AI Complexity & Scope Card
                        Surface(
                            color = GigTealContainer.copy(alpha = 0.6f),
                            shape = RoundedCornerShape(12.dp),
                            border = BorderStroke(1.dp, GigSoftTeal)
                        ) {
                            Column(modifier = Modifier.padding(12.dp), verticalArrangement = Arrangement.spacedBy(4.dp)) {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                                ) {
                                    Text("✨", fontSize = 14.sp)
                                    Text("AI Task Scope:", style = MaterialTheme.typography.labelSmall, fontWeight = FontWeight.Bold, color = GigPrimaryBlue)
                                    Text(aiDetectedComplexity, style = MaterialTheme.typography.labelSmall, color = GigOnSurface)
                                }
                                Text(
                                    "Dynamic FairPrice based on detected task keywords, regional cooperative tariffs, and urgency tier.",
                                    style = MaterialTheme.typography.bodySmall,
                                    color = GigSubtleText
                                )
                            }
                        }

                        // Full Transparent Price Breakdown
                        PriceBreakdownCard(pricing = pricing)
                    } else {
                        // Placeholder prompt when description is not yet entered
                        Surface(
                            color = GigSurfaceVariant,
                            shape = RoundedCornerShape(12.dp),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Row(
                                modifier = Modifier.padding(16.dp),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(12.dp)
                            ) {
                                Text("💡", fontSize = 24.sp)
                                Column(verticalArrangement = Arrangement.spacedBy(2.dp)) {
                                    Text(
                                        "Enter Problem Description Above",
                                        style = MaterialTheme.typography.titleSmall,
                                        fontWeight = FontWeight.Bold,
                                        color = GigOnSurface
                                    )
                                    Text(
                                        "FairMatch AI will evaluate the required work and calculate an itemized price range.",
                                        style = MaterialTheme.typography.bodySmall,
                                        color = GigSubtleText
                                    )
                                }
                            }
                        }
                    }
                }
            }

            // Error Display
            if (errorMessage != null) {
                Card(
                    colors = CardDefaults.cardColors(containerColor = GigErrorContainer),
                    shape = RoundedCornerShape(12.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Row(
                        modifier = Modifier.padding(14.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        Icon(Icons.Filled.Warning, null, tint = GigError, modifier = Modifier.size(20.dp))
                        Text(
                            errorMessage!!,
                            style = MaterialTheme.typography.bodyMedium,
                            color = GigError,
                            fontWeight = FontWeight.Medium
                        )
                    }
                }
            }

            Spacer(Modifier.height(40.dp))
        }
    }
}

// ─────────────────────────────────────────────────────────────────────────────
//  Interactive Calendar Picker Dialog
// ─────────────────────────────────────────────────────────────────────────────

@Composable
private fun CalendarPickerDialog(
    initialDate: String,
    onDateSelected: (String) -> Unit,
    onDismiss: () -> Unit
) {
    val quickDates = listOf(
        "Today, 18 Oct" to "Today",
        "Tomorrow, 19 Oct" to "Tomorrow",
        "Mon, 20 Oct" to "Next Mon",
        "Tue, 21 Oct" to "Next Tue",
        "Wed, 22 Oct" to "Next Wed",
        "Thu, 23 Oct" to "Next Thu"
    )

    var currentPicked by remember { mutableStateOf(initialDate) }

    Dialog(onDismissRequest = onDismiss) {
        Surface(
            shape = RoundedCornerShape(20.dp),
            color = GigSurface,
            border = BorderStroke(1.dp, GigOutlineVariant),
            shadowElevation = 16.dp,
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier.padding(22.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Icon(Icons.Filled.CalendarMonth, null, tint = GigPrimaryBlue, modifier = Modifier.size(24.dp))
                        Text("Select Service Date", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold, color = GigOnBackground)
                    }
                    IconButton(onClick = onDismiss, modifier = Modifier.size(28.dp)) {
                        Icon(Icons.Filled.Close, "Close", tint = GigSubtleText)
                    }
                }

                HorizontalDivider(color = GigOutlineVariant)

                Text("Available Schedule Slots (October 2026):", style = MaterialTheme.typography.labelMedium, fontWeight = FontWeight.SemiBold, color = GigSubtleText)

                Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    quickDates.chunked(2).forEach { row ->
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            row.forEach { (fullDate, label) ->
                                val isSelected = currentPicked == fullDate
                                Surface(
                                    color = if (isSelected) GigPrimaryBlue else GigSurfaceVariant,
                                    shape = RoundedCornerShape(12.dp),
                                    border = BorderStroke(1.dp, if (isSelected) GigPrimaryBlue else GigOutlineVariant),
                                    modifier = Modifier
                                        .weight(1f)
                                        .clickable { currentPicked = fullDate }
                                ) {
                                    Column(
                                        modifier = Modifier.padding(vertical = 10.dp, horizontal = 12.dp),
                                        horizontalAlignment = Alignment.CenterHorizontally
                                    ) {
                                        Text(
                                            label,
                                            style = MaterialTheme.typography.labelSmall,
                                            color = if (isSelected) Color.White.copy(0.8f) else GigSubtleText
                                        )
                                        Text(
                                            fullDate.substringAfter(", "),
                                            style = MaterialTheme.typography.bodyMedium,
                                            fontWeight = FontWeight.Bold,
                                            color = if (isSelected) Color.White else GigOnSurface
                                        )
                                    }
                                }
                            }
                        }
                    }
                }

                Spacer(Modifier.height(4.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    OutlinedButton(
                        onClick = onDismiss,
                        modifier = Modifier.weight(1f).height(46.dp),
                        shape = RoundedCornerShape(10.dp)
                    ) {
                        Text("Cancel", color = GigSubtleText)
                    }
                    Button(
                        onClick = { onDateSelected(currentPicked) },
                        modifier = Modifier.weight(1f).height(46.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = GigPrimaryBlue),
                        shape = RoundedCornerShape(10.dp)
                    ) {
                        Text("Confirm Date", fontWeight = FontWeight.Bold, color = Color.White)
                    }
                }
            }
        }
    }
}

// ─────────────────────────────────────────────────────────────────────────────
//  Interactive Clock Time Picker Dialog
// ─────────────────────────────────────────────────────────────────────────────

@Composable
private fun ClockTimePickerDialog(
    initialTime: String,
    onTimeSelected: (String) -> Unit,
    onDismiss: () -> Unit
) {
    val timePresets = listOf(
        "09:00 AM - 11:00 AM" to "🌅 Morning Slot",
        "11:00 AM - 01:00 PM" to "☀️ Mid-Day Slot",
        "02:00 PM - 04:00 PM" to "🌤️ Afternoon Slot",
        "04:00 PM - 06:00 PM" to "🌇 Evening Slot",
        "06:00 PM - 08:00 PM" to "🌙 Night Slot"
    )

    var currentPicked by remember { mutableStateOf(initialTime) }

    Dialog(onDismissRequest = onDismiss) {
        Surface(
            shape = RoundedCornerShape(20.dp),
            color = GigSurface,
            border = BorderStroke(1.dp, GigOutlineVariant),
            shadowElevation = 16.dp,
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier.padding(22.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Icon(Icons.Filled.AccessTime, null, tint = GigPrimaryBlue, modifier = Modifier.size(24.dp))
                        Text("Pick Service Clock Time", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold, color = GigOnBackground)
                    }
                    IconButton(onClick = onDismiss, modifier = Modifier.size(28.dp)) {
                        Icon(Icons.Filled.Close, "Close", tint = GigSubtleText)
                    }
                }

                HorizontalDivider(color = GigOutlineVariant)

                Text("Standard Working Hours:", style = MaterialTheme.typography.labelMedium, fontWeight = FontWeight.SemiBold, color = GigSubtleText)

                Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    timePresets.forEach { (slot, label) ->
                        val isSelected = currentPicked == slot
                        Surface(
                            color = if (isSelected) GigPrimaryBlue else GigSurfaceVariant,
                            shape = RoundedCornerShape(12.dp),
                            border = BorderStroke(1.dp, if (isSelected) GigPrimaryBlue else GigOutlineVariant),
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable { currentPicked = slot }
                        ) {
                            Row(
                                modifier = Modifier
                                    .padding(horizontal = 14.dp, vertical = 10.dp)
                                    .fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text(
                                    label,
                                    style = MaterialTheme.typography.bodyMedium,
                                    fontWeight = FontWeight.Medium,
                                    color = if (isSelected) Color.White else GigOnSurface
                                )
                                Text(
                                    slot,
                                    style = MaterialTheme.typography.bodyMedium,
                                    fontWeight = FontWeight.Bold,
                                    color = if (isSelected) Color.White else GigPrimaryBlue
                                )
                            }
                        }
                    }
                }

                Spacer(Modifier.height(4.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    OutlinedButton(
                        onClick = onDismiss,
                        modifier = Modifier.weight(1f).height(46.dp),
                        shape = RoundedCornerShape(10.dp)
                    ) {
                        Text("Cancel", color = GigSubtleText)
                    }
                    Button(
                        onClick = { onTimeSelected(currentPicked) },
                        modifier = Modifier.weight(1f).height(46.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = GigPrimaryBlue),
                        shape = RoundedCornerShape(10.dp)
                    ) {
                        Text("Set Time Slot", fontWeight = FontWeight.Bold, color = Color.White)
                    }
                }
            }
        }
    }
}

@Composable
private fun UrgencyOptionChip(
    title: String,
    subtitle: String,
    isSelected: Boolean,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Surface(
        color = if (isSelected) GigTealContainer else GigSurface,
        shape = RoundedCornerShape(12.dp),
        border = BorderStroke(if (isSelected) 1.5.dp else 1.dp, if (isSelected) GigPrimaryBlue else GigOutlineVariant),
        modifier = modifier.clickable(onClick = onClick)
    ) {
        Column(
            modifier = Modifier.padding(vertical = 12.dp, horizontal = 10.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(2.dp)
        ) {
            Text(
                title,
                fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
                color = if (isSelected) GigPrimaryBlue else GigOnSurface,
                style = MaterialTheme.typography.labelMedium
            )
            Text(
                subtitle,
                style = MaterialTheme.typography.labelSmall,
                color = if (isSelected) GigPrimaryBlue else GigSubtleText,
                fontSize = 10.sp
            )
        }
    }
}
