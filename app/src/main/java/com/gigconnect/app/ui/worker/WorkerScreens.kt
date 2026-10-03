package com.gigconnect.app.ui.worker

import androidx.compose.animation.core.*
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
import androidx.compose.ui.graphics.*
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.*
import com.gigconnect.app.data.demo.DemoDataProvider
import com.gigconnect.app.data.model.*
import com.gigconnect.app.ui.components.*
import com.gigconnect.app.ui.theme.*
import com.gigconnect.app.viewmodel.WorkerViewModel

// ─────────────────────────────────────────────────────────────────────────────
//  Job Detail & Execution Lifecycle
// ─────────────────────────────────────────────────────────────────────────────
@Composable
fun JobDetailScreen(
    jobId: String,
    workerViewModel: WorkerViewModel,
    onBack: () -> Unit
) {
    val jobs by workerViewModel.jobs.collectAsState()
    val job = jobs.find { it.id == jobId } ?: workerViewModel.getJobById(jobId)

    var showDeclineDialog by remember { mutableStateOf(false) }
    var selectedDeclineReason by remember { mutableStateOf("Too far from current location") }
    var showCompletionDialog by remember { mutableStateOf(false) }
    var completionNotes by remember { mutableStateOf("Work completed cleanly according to cooperative safety guidelines.") }

    val declineReasons = listOf(
        "Too far from current location",
        "Schedule conflict with existing job",
        "Requires specialized tools not available",
        "Estimated earnings too low for scope",
        "Personal emergency / Off-duty",
        "Other reason"
    )

    if (job == null) {
        Scaffold(topBar = { GigTopBar(title = "Job Details", onBack = onBack) }) { padding ->
            EmptyState("❌", "Job not found", modifier = Modifier.padding(padding).fillMaxSize())
        }
        return
    }

    // ── Non-Punitive Decline Dialog ──────────────────────────────────────────
    if (showDeclineDialog) {
        AlertDialog(
            onDismissRequest = { showDeclineDialog = false },
            title = {
                Column {
                    Text("Decline Job Request", fontWeight = FontWeight.Bold, color = GigOnSurface)
                    Spacer(Modifier.height(4.dp))
                    Text("Declining will NEVER affect your rating or FairMatch priority.", fontSize = 12.sp, color = GigSuccess, fontWeight = FontWeight.SemiBold)
                }
            },
            text = {
                Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    Text("Please select a reason to help the cooperative optimize job routing:", fontSize = 13.sp, color = GigSubtleText)
                    declineReasons.forEach { reason ->
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable { selectedDeclineReason = reason }
                                .padding(vertical = 4.dp)
                        ) {
                            RadioButton(
                                selected = selectedDeclineReason == reason,
                                onClick = { selectedDeclineReason = reason },
                                colors = RadioButtonDefaults.colors(selectedColor = GigPrimaryBlue)
                            )
                            Spacer(Modifier.width(8.dp))
                            Text(reason, fontSize = 13.sp, color = GigOnSurface)
                        }
                    }
                }
            },
            confirmButton = {
                Button(
                    onClick = {
                        workerViewModel.rejectJobWithReason(job.id, selectedDeclineReason)
                        showDeclineDialog = false
                    },
                    colors = ButtonDefaults.buttonColors(GigError),
                    shape = RoundedCornerShape(10.dp)
                ) {
                    Text("Confirm Decline", color = Color.White, fontWeight = FontWeight.Bold)
                }
            },
            dismissButton = {
                TextButton(onClick = { showDeclineDialog = false }) {
                    Text("Cancel", color = GigPrimaryBlue)
                }
            }
        )
    }

    // ── Completion Confirmation Dialog ───────────────────────────────────────
    if (showCompletionDialog) {
        AlertDialog(
            onDismissRequest = { showCompletionDialog = false },
            title = { Text("Complete & Settle Job", fontWeight = FontWeight.Bold, color = GigOnSurface) },
            text = {
                Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                    Text("Confirming completion will immediately credit ₹${job.estimatedEarnings} to your earnings and ₹${job.equityContribution} to your cooperative equity wallet.", fontSize = 13.sp, color = GigSubtleText)
                    OutlinedTextField(
                        value = completionNotes,
                        onValueChange = { completionNotes = it },
                        label = { Text("Completion Notes") },
                        modifier = Modifier.fillMaxWidth(),
                        maxLines = 3,
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedTextColor = GigOnSurface,
                            unfocusedTextColor = GigOnSurface,
                            focusedBorderColor = GigPrimaryBlue
                        )
                    )
                }
            },
            confirmButton = {
                Button(
                    onClick = {
                        workerViewModel.completeJob(job.id, completionNotes)
                        showCompletionDialog = false
                    },
                    colors = ButtonDefaults.buttonColors(GigSuccess),
                    shape = RoundedCornerShape(10.dp)
                ) {
                    Text("Confirm Completion", color = Color.White, fontWeight = FontWeight.Bold)
                }
            },
            dismissButton = {
                TextButton(onClick = { showCompletionDialog = false }) {
                    Text("Cancel")
                }
            }
        )
    }

    Scaffold(
        topBar = { GigTopBar(title = "Job Lifecycle & Settlement", subtitle = "Job #${job.id}", onBack = onBack) },
        bottomBar = {
            Surface(shadowElevation = 8.dp, color = GigSurface) {
                Column(modifier = Modifier.fillMaxWidth().padding(16.dp)) {
                    when (job.status) {
                        JobStatus.NEW -> {
                            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                                OutlinedButton(
                                    onClick = { showDeclineDialog = true },
                                    modifier = Modifier.weight(1f).height(52.dp),
                                    shape = RoundedCornerShape(14.dp),
                                    colors = ButtonDefaults.outlinedButtonColors(contentColor = GigError),
                                    border = BorderStroke(1.dp, GigError)
                                ) { Text("Decline", fontWeight = FontWeight.SemiBold) }
                                Button(
                                    onClick = { workerViewModel.acceptJob(job.id) },
                                    modifier = Modifier.weight(2f).height(52.dp),
                                    colors = ButtonDefaults.buttonColors(GigPrimaryBlue),
                                    shape = RoundedCornerShape(14.dp)
                                ) { Text("Accept Job", color = Color.White, fontWeight = FontWeight.Bold) }
                            }
                        }
                        JobStatus.ACCEPTED -> {
                            Button(
                                onClick = { workerViewModel.startTransit(job.id) },
                                modifier = Modifier.fillMaxWidth().height(52.dp),
                                colors = ButtonDefaults.buttonColors(GigPrimaryBlue),
                                shape = RoundedCornerShape(14.dp)
                            ) {
                                Icon(Icons.Filled.Navigation, null, tint = Color.White)
                                Spacer(Modifier.width(8.dp))
                                Text("Start Transit (Navigate to Customer)", color = Color.White, fontWeight = FontWeight.Bold)
                            }
                        }
                        JobStatus.ON_THE_WAY -> {
                            Button(
                                onClick = { workerViewModel.markArrived(job.id) },
                                modifier = Modifier.fillMaxWidth().height(52.dp),
                                colors = ButtonDefaults.buttonColors(GigSecondaryBlue),
                                shape = RoundedCornerShape(14.dp)
                            ) {
                                Icon(Icons.Filled.Place, null, tint = Color.White)
                                Spacer(Modifier.width(8.dp))
                                Text("Mark Arrived at Location", color = Color.White, fontWeight = FontWeight.Bold)
                            }
                        }
                        JobStatus.ARRIVED -> {
                            Button(
                                onClick = { workerViewModel.startJob(job.id) },
                                modifier = Modifier.fillMaxWidth().height(52.dp),
                                colors = ButtonDefaults.buttonColors(GigSuccess),
                                shape = RoundedCornerShape(14.dp)
                            ) {
                                Icon(Icons.Filled.PlayArrow, null, tint = Color.White)
                                Spacer(Modifier.width(8.dp))
                                Text("Start Service Work", color = Color.White, fontWeight = FontWeight.Bold)
                            }
                        }
                        JobStatus.IN_PROGRESS -> {
                            Button(
                                onClick = { showCompletionDialog = true },
                                modifier = Modifier.fillMaxWidth().height(52.dp),
                                colors = ButtonDefaults.buttonColors(GigSuccess),
                                shape = RoundedCornerShape(14.dp)
                            ) {
                                Icon(Icons.Filled.CheckCircle, null, tint = Color.White)
                                Spacer(Modifier.width(8.dp))
                                Text("Complete Service & Settle", color = Color.White, fontWeight = FontWeight.Bold)
                            }
                        }
                        JobStatus.COMPLETED -> {
                            Surface(
                                color = GigSuccessContainer,
                                shape = RoundedCornerShape(12.dp),
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Row(
                                    modifier = Modifier.padding(14.dp),
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.Center
                                ) {
                                    Icon(Icons.Filled.CheckCircle, null, tint = GigSuccess)
                                    Spacer(Modifier.width(8.dp))
                                    Text("Service Completed & ₹${job.estimatedEarnings} Settled", color = GigSuccess, fontWeight = FontWeight.Bold)
                                }
                            }
                        }
                        JobStatus.REJECTED -> {
                            Surface(
                                color = GigErrorContainer,
                                shape = RoundedCornerShape(12.dp),
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Text(
                                    "Job Declined (${job.rejectionReason ?: "By worker"}) • Zero rating penalty",
                                    color = GigError,
                                    fontWeight = FontWeight.Bold,
                                    modifier = Modifier.padding(14.dp),
                                    textAlign = androidx.compose.ui.text.style.TextAlign.Center
                                )
                            }
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
                .padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Live Status Banner
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(
                    when (job.status) {
                        JobStatus.NEW -> GigSoftTeal
                        JobStatus.ACCEPTED, JobStatus.ON_THE_WAY, JobStatus.ARRIVED, JobStatus.IN_PROGRESS -> GigTealContainer
                        JobStatus.COMPLETED -> GigSuccessContainer
                        JobStatus.REJECTED -> GigErrorContainer
                    }
                )
            ) {
                Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
                        Text(job.serviceDetail, style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold, color = GigOnBackground)
                        Surface(
                            color = when (job.status) {
                                JobStatus.COMPLETED -> GigSuccess
                                JobStatus.REJECTED -> GigError
                                else -> GigPrimaryBlue
                            },
                            shape = RoundedCornerShape(8.dp)
                        ) {
                            Text(
                                job.status.name.replace("_", " "),
                                color = Color.White,
                                fontWeight = FontWeight.Bold,
                                fontSize = 11.sp,
                                modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                            )
                        }
                    }
                    Text(job.serviceCategory, color = GigPrimaryBlue, fontWeight = FontWeight.SemiBold)
                    HorizontalDivider(color = GigPrimaryBlue.copy(0.15f))
                    DetailRow("📍 Distance", "${job.distanceKm} km")
                    DetailRow("⏱️ Est. Duration", job.estimatedDuration)
                    DetailRow("💰 Net Worker Earnings", "₹${job.estimatedEarnings}")
                    DetailRow("📅 Scheduled Date", job.scheduledDate)
                    DetailRow("🕐 Scheduled Time", job.scheduledTime)
                    DetailRow("🏠 Service Location", job.address)
                    if (job.startedAt != null) DetailRow("⚡ Started At", job.startedAt)
                    if (job.completedAt != null) DetailRow("✅ Completed At", job.completedAt)
                }
            }

            // Transparent FairMatch Explanation Card
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(GigSurface),
                elevation = CardDefaults.cardElevation(2.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        Text("⚖️", fontSize = 20.sp)
                        Text("FairMatch™ Transparent Allocation", style = MaterialTheme.typography.titleSmall, fontWeight = FontWeight.Bold, color = GigOnSurface)
                    }
                    Surface(color = GigSoftTeal, shape = RoundedCornerShape(8.dp)) {
                        Text(
                            job.matchExplanation,
                            style = MaterialTheme.typography.bodySmall,
                            color = GigPrimaryBlue,
                            fontWeight = FontWeight.Medium,
                            modifier = Modifier.padding(10.dp)
                        )
                    }
                    Text("GigConnect guarantees algorithmic neutrality. Workers receive assignments based on verified skills, geographical proximity, and rotation equity.", fontSize = 11.sp, color = GigSubtleText)
                }
            }

            // Transparent Cooperative Pricing & Earning Breakdown
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(GigSurface),
                elevation = CardDefaults.cardElevation(2.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
                    Text("💰 Transparent Financial Breakdown", style = MaterialTheme.typography.titleSmall, fontWeight = FontWeight.Bold, color = GigOnSurface)
                    HorizontalDivider(color = GigOutlineVariant)
                    BreakdownRow("Customer Total Payment", "₹${job.customerPayment}", GigOnSurface)
                    BreakdownRow("Platform Extraction Fee", "₹0 (0%)", GigSuccess)
                    BreakdownRow("Labour Co-op Admin Share", "₹${job.coopAllocation}", GigSubtleText)
                    BreakdownRow("Welfare Fund Contribution", "₹${job.welfareContribution}", GigSubtleText)
                    BreakdownRow("Patronage Equity Credit", "+₹${job.equityContribution}", GigPrimaryBlue)
                    HorizontalDivider(color = GigOutlineVariant)
                    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                        Text("Net Worker Take-Home", style = MaterialTheme.typography.titleSmall, fontWeight = FontWeight.Bold, color = GigOnSurface)
                        Text("₹${job.estimatedEarnings}", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.ExtraBold, color = GigPrimaryBlue)
                    }
                }
            }

            // Customer info & Contact
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(GigSurface),
                elevation = CardDefaults.cardElevation(2.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
                    Text("Customer Information", style = MaterialTheme.typography.titleSmall, fontWeight = FontWeight.Bold, color = GigOnSurface)
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                            Box(modifier = Modifier.size(44.dp).clip(CircleShape).background(GigSecondaryBlue), contentAlignment = Alignment.Center) {
                                Text(job.customerName.first().toString(), fontWeight = FontWeight.Bold, color = Color.White)
                            }
                            Column {
                                Text(job.customerName, fontWeight = FontWeight.SemiBold, color = GigOnSurface)
                                if (job.customerVerified) StatusChip("VERIFIED SEEKER")
                            }
                        }

                        if (job.status == JobStatus.ACCEPTED || job.status == JobStatus.ON_THE_WAY || job.status == JobStatus.ARRIVED || job.status == JobStatus.IN_PROGRESS) {
                            FilledTonalIconButton(onClick = {}) {
                                Icon(Icons.Filled.Call, "Call Customer", tint = GigPrimaryBlue)
                            }
                        }
                    }
                }
            }

            // Safety check-in
            Card(modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(14.dp), colors = CardDefaults.cardColors(GigSuccessContainer)) {
                Row(modifier = Modifier.padding(14.dp), horizontalArrangement = Arrangement.spacedBy(10.dp), verticalAlignment = Alignment.CenterVertically) {
                    Text("🛡️", fontSize = 20.sp)
                    Column {
                        Text("Cooperative Safety Shield Active", fontWeight = FontWeight.Bold, color = GigSuccess)
                        Text("Your location is shared only during active transit. 24/7 SOS helpline is one tap away.",
                            style = MaterialTheme.typography.bodySmall, color = GigSuccess)
                    }
                }
            }
        }
    }
}

@Composable private fun DetailRow(label: String, value: String) {
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
        Text(label, style = MaterialTheme.typography.bodySmall, color = GigSubtleText)
        Text(value, style = MaterialTheme.typography.bodySmall, fontWeight = FontWeight.SemiBold, color = GigOnSurface)
    }
}

// ─────────────────────────────────────────────────────────────────────────────
//  Jobs List
// ─────────────────────────────────────────────────────────────────────────────
@Composable
fun JobsListScreen(
    workerViewModel: WorkerViewModel,
    onJobDetail: (Job) -> Unit,
    onBack: () -> Unit,
    onHomeClick: () -> Unit = onBack,
    onEarningsClick: () -> Unit = {},
    onProfileClick: () -> Unit = {}
) {
    val jobs by workerViewModel.jobs.collectAsState()
    var tab by remember { mutableStateOf(0) }
    val tabs = listOf("Available", "Active / In Progress", "Completed", "All Jobs")

    Scaffold(
        topBar = { GigTopBar(title = "Worker Jobs Hub", subtitle = "Available, Pending & Completed", onBack = onBack) },
        bottomBar = {
            WorkerBottomNav(
                onHome = onHomeClick,
                onJobs = {},
                onEarnings = onEarningsClick,
                onProfile = onProfileClick,
                selected = 1
            )
        }
    ) { padding ->
        Column(modifier = Modifier.fillMaxSize().background(GigBackground).padding(padding)) {
            ScrollableTabRow(
                selectedTabIndex = tab,
                containerColor = GigSurface,
                contentColor = GigPrimaryBlue,
                edgePadding = 16.dp
            ) {
                tabs.forEachIndexed { i, t ->
                    val count = when (i) {
                        0 -> jobs.count { it.status == JobStatus.NEW }
                        1 -> jobs.count { it.status == JobStatus.ACCEPTED || it.status == JobStatus.ON_THE_WAY || it.status == JobStatus.ARRIVED || it.status == JobStatus.IN_PROGRESS }
                        2 -> jobs.count { it.status == JobStatus.COMPLETED }
                        else -> jobs.size
                    }
                    Tab(
                        selected = tab == i,
                        onClick = { tab = i },
                        text = {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(6.dp)
                            ) {
                                Text(t, fontWeight = if (tab == i) FontWeight.Bold else FontWeight.Medium)
                                Surface(
                                    color = if (tab == i) GigPrimaryBlue.copy(alpha = 0.12f) else GigSurfaceVariant,
                                    shape = RoundedCornerShape(10.dp)
                                ) {
                                    Text(
                                        "$count",
                                        style = MaterialTheme.typography.labelSmall,
                                        fontWeight = FontWeight.Bold,
                                        color = if (tab == i) GigPrimaryBlue else GigSubtleText,
                                        modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                                    )
                                }
                            }
                        }
                    )
                }
            }

            val filtered = when (tab) {
                0 -> jobs.filter { it.status == JobStatus.NEW }
                1 -> jobs.filter { it.status == JobStatus.ACCEPTED || it.status == JobStatus.ON_THE_WAY || it.status == JobStatus.ARRIVED || it.status == JobStatus.IN_PROGRESS }
                2 -> jobs.filter { it.status == JobStatus.COMPLETED }
                else -> jobs
            }

            if (filtered.isEmpty()) {
                EmptyState("📭", "No ${tabs[tab].lowercase()} jobs found", modifier = Modifier.fillMaxSize())
            } else {
                LazyColumn(contentPadding = PaddingValues(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
                    item { DemoDataBadge() }
                    items(filtered) { job ->
                        JobRequestCard(
                            job = job,
                            onAccept = { workerViewModel.acceptJob(job.id) },
                            onViewDetails = { onJobDetail(job) }
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun JobRequestCard(
    job: Job,
    onAccept: () -> Unit,
    onViewDetails: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.fillMaxWidth().clickable { onViewDetails() },
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = GigSurface),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
                Text(job.serviceDetail, style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold, color = GigOnSurface)
                Surface(color = GigSoftTeal, shape = RoundedCornerShape(6.dp)) {
                    Text("₹${job.estimatedEarnings}", color = GigPrimaryBlue, fontWeight = FontWeight.ExtraBold, modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp))
                }
            }
            Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                Text("📍 ${job.distanceKm} km", style = MaterialTheme.typography.bodySmall, color = GigSubtleText)
                Text("⏱ ${job.estimatedDuration}", style = MaterialTheme.typography.bodySmall, color = GigSubtleText)
                Text("🎯 ${job.aiMatchScore}% Match", style = MaterialTheme.typography.bodySmall, color = GigPrimaryBlue, fontWeight = FontWeight.SemiBold)
            }
            Text(job.address, style = MaterialTheme.typography.bodySmall, color = GigOnSurface.copy(alpha = 0.8f))
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                OutlinedButton(
                    onClick = onViewDetails,
                    modifier = Modifier.weight(1f).height(42.dp),
                    shape = RoundedCornerShape(10.dp)
                ) {
                    Text("Details", fontSize = 13.sp)
                }
                if (job.status == JobStatus.NEW) {
                    Button(
                        onClick = onAccept,
                        modifier = Modifier.weight(1f).height(42.dp),
                        colors = ButtonDefaults.buttonColors(GigPrimaryBlue),
                        shape = RoundedCornerShape(10.dp)
                    ) {
                        Text("Accept", fontSize = 13.sp, color = Color.White, fontWeight = FontWeight.Bold)
                    }
                } else {
                    Surface(
                        color = GigTealContainer,
                        shape = RoundedCornerShape(10.dp),
                        modifier = Modifier.weight(1f).height(42.dp)
                    ) {
                        Box(contentAlignment = Alignment.Center) {
                            Text(job.status.name.replace("_", " "), fontSize = 12.sp, color = GigPrimaryBlue, fontWeight = FontWeight.Bold)
                        }
                    }
                }
            }
        }
    }
}

// ─────────────────────────────────────────────────────────────────────────────
//  Earnings & Welfare Screen
// ─────────────────────────────────────────────────────────────────────────────
@Composable
fun EarningsScreen(
    workerViewModel: WorkerViewModel,
    onEquityClick: () -> Unit,
    onBack: () -> Unit
) {
    val weekly by workerViewModel.weeklyEarnings.collectAsState()
    val welfareBalance by workerViewModel.welfareBalance.collectAsState()
    val welfareClaims by workerViewModel.welfareClaims.collectAsState()
    var tabIndex by remember { mutableStateOf(0) }

    Scaffold(topBar = { GigTopBar(title = "Earnings & Welfare", onBack = onBack) }) { padding ->
        Column(modifier = Modifier.fillMaxSize().background(GigBackground).padding(padding).verticalScroll(rememberScrollState())) {
            Box(modifier = Modifier.fillMaxWidth().background(Brush.verticalGradient(listOf(GigPrimaryBlue, GigPrimaryBlue))).padding(24.dp)) {
                Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxWidth()) {
                    DemoDataBadge()
                    Spacer(Modifier.height(10.dp))
                    Text("This Week Total Earnings", style = MaterialTheme.typography.bodyMedium, color = Color.White.copy(0.8f))
                    Text("₹$weekly", style = MaterialTheme.typography.displaySmall, fontWeight = FontWeight.ExtraBold, color = Color.White)
                    Spacer(Modifier.height(12.dp))
                    Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                        EarningStatChip("18 Jobs", "Completed")
                        EarningStatChip("⭐ 4.8", "Avg Rating")
                        EarningStatChip("₹$welfareBalance", "Welfare Fund")
                    }
                }
            }

            TabRow(
                selectedTabIndex = tabIndex,
                containerColor = GigSurface,
                contentColor = GigPrimaryBlue
            ) {
                Tab(selected = tabIndex == 0, onClick = { tabIndex = 0 }, text = { Text("Earnings Ledger", fontWeight = FontWeight.Bold) })
                Tab(selected = tabIndex == 1, onClick = { tabIndex = 1 }, text = { Text("Welfare & Insurance", fontWeight = FontWeight.Bold) })
            }

            if (tabIndex == 0) {
                Column(modifier = Modifier.padding(20.dp), verticalArrangement = Arrangement.spacedBy(16.dp)) {
                    // Daily earnings chart
                    Text("Daily Earnings (Pune Urban Co-op)", style = MaterialTheme.typography.titleSmall, fontWeight = FontWeight.Bold, color = GigOnBackground)
                    Card(modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(16.dp),
                        colors = CardDefaults.cardColors(GigSurface), elevation = CardDefaults.cardElevation(2.dp)) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            val days = listOf("Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun")
                            val values = listOf(1200, 1800, 900, 2100, 1400, 1650, 750)
                            val max = values.max().toFloat()
                            Row(modifier = Modifier.fillMaxWidth().height(120.dp), verticalAlignment = Alignment.Bottom, horizontalArrangement = Arrangement.SpaceBetween) {
                                days.forEachIndexed { i, day ->
                                    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.weight(1f)) {
                                        val h = (values[i] / max * 100).dp
                                        Box(modifier = Modifier.width(20.dp).height(h).clip(RoundedCornerShape(topStart = 4.dp, topEnd = 4.dp)).background(GigPrimaryBlue))
                                        Spacer(Modifier.height(4.dp))
                                        Text(day, style = MaterialTheme.typography.labelSmall, color = GigSubtleText)
                                    }
                                }
                            }
                        }
                    }

                    Text("Transparent Weekly Allocation", style = MaterialTheme.typography.titleSmall, fontWeight = FontWeight.Bold, color = GigOnBackground)
                    Card(modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(16.dp),
                        colors = CardDefaults.cardColors(GigSurface), elevation = CardDefaults.cardElevation(2.dp)) {
                        Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
                            BreakdownRow("Service Earnings Take-Home", "₹7,900", GigOnSurface)
                            BreakdownRow("Welfare Reserve Fund", "₹250", GigSubtleText)
                            BreakdownRow("Cooperative Equity Share", "₹300", GigPrimaryBlue)
                            BreakdownRow("Platform Extraction Fee", "₹0 / Transparent", GigSuccess)
                        }
                    }

                    Button(
                        onClick = onEquityClick,
                        modifier = Modifier.fillMaxWidth().height(52.dp),
                        colors = ButtonDefaults.buttonColors(GigPrimaryBlue),
                        shape = RoundedCornerShape(14.dp)
                    ) {
                        Text("View Cooperative Equity Wallet", color = Color.White, fontWeight = FontWeight.Bold)
                    }
                }
            } else {
                Column(modifier = Modifier.padding(20.dp), verticalArrangement = Arrangement.spacedBy(16.dp)) {
                    Card(modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(16.dp), colors = CardDefaults.cardColors(GigSoftTeal)) {
                        Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(6.dp)) {
                            Text("🛡️ Worker Welfare & Social Security", fontWeight = FontWeight.Bold, color = GigPrimaryBlue)
                            Text("Your cooperative automatically accumulates health insurance, emergency assistance, and pension contributions for every job delivered.", fontSize = 12.sp, color = GigOnBackground)
                            Text("Current Welfare Reserve: ₹$welfareBalance", fontWeight = FontWeight.Bold, color = GigPrimaryBlue)
                        }
                    }

                    Text("Welfare Coverage & Records", style = MaterialTheme.typography.titleSmall, fontWeight = FontWeight.Bold, color = GigOnBackground)
                    welfareClaims.forEach { claim ->
                        Card(
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(12.dp),
                            colors = CardDefaults.cardColors(GigSurface),
                            elevation = CardDefaults.cardElevation(1.dp)
                        ) {
                            Row(modifier = Modifier.padding(14.dp), verticalAlignment = Alignment.CenterVertically) {
                                Box(modifier = Modifier.size(40.dp).clip(CircleShape).background(GigSuccessContainer), contentAlignment = Alignment.Center) {
                                    Text("🛡️", fontSize = 18.sp)
                                }
                                Spacer(Modifier.width(12.dp))
                                Column(modifier = Modifier.weight(1f)) {
                                    Text(claim.type, fontWeight = FontWeight.SemiBold, style = MaterialTheme.typography.bodyMedium, color = GigOnSurface)
                                    Text(claim.description, style = MaterialTheme.typography.bodySmall, color = GigSubtleText)
                                    Text(claim.date, style = MaterialTheme.typography.bodySmall, color = GigSubtleText)
                                }
                                Text("₹${claim.amount}", fontWeight = FontWeight.Bold, color = GigSuccess)
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable private fun EarningStatChip(value: String, label: String) {
    Surface(color = Color.White.copy(0.15f), shape = RoundedCornerShape(10.dp)) {
        Column(modifier = Modifier.padding(horizontal = 14.dp, vertical = 8.dp), horizontalAlignment = Alignment.CenterHorizontally) {
            Text(value, fontWeight = FontWeight.Bold, color = Color.White)
            Text(label, style = MaterialTheme.typography.labelSmall, color = Color.White.copy(0.75f))
        }
    }
}

@Composable private fun BreakdownRow(label: String, value: String, valueColor: Color) {
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
        Text(label, style = MaterialTheme.typography.bodyMedium, color = GigSubtleText)
        Text(value, fontWeight = FontWeight.Bold, color = valueColor)
    }
}

// ─────────────────────────────────────────────────────────────────────────────
//  Equity Wallet Screen
// ─────────────────────────────────────────────────────────────────────────────
@Composable
fun EquityWalletScreen(workerViewModel: WorkerViewModel, onBack: () -> Unit) {
    val balance by workerViewModel.equityBalance.collectAsState()
    val monthGain by workerViewModel.thisMonthEquity.collectAsState()
    val entries by workerViewModel.equityEntries.collectAsState()

    Scaffold(topBar = { GigTopBar(title = "Patronage Equity Wallet", subtitle = "Cooperative Ownership", onBack = onBack) }) { padding ->
        LazyColumn(modifier = Modifier.fillMaxSize().background(GigBackground).padding(padding)) {
            item {
                Box(modifier = Modifier.fillMaxWidth().background(Brush.verticalGradient(listOf(GigPrimaryBlue, GigPrimaryBlue))).padding(24.dp)) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxWidth()) {
                        DemoDataBadge()
                        Spacer(Modifier.height(10.dp))
                        Text("GigConnect Patronage Equity", style = MaterialTheme.typography.titleMedium, color = Color.White.copy(0.85f))
                        Text("Your work. Your share. Your cooperative.", style = MaterialTheme.typography.bodySmall, color = Color.White.copy(0.65f))
                        Spacer(Modifier.height(16.dp))
                        Text("₹$balance", style = MaterialTheme.typography.displaySmall, fontWeight = FontWeight.ExtraBold, color = Color.White)
                        Text("Accumulated Cooperative Ownership", style = MaterialTheme.typography.bodySmall, color = Color.White.copy(0.75f))
                        Spacer(Modifier.height(8.dp))
                        Surface(color = GigSuccessContainer, shape = RoundedCornerShape(12.dp)) {
                            Text("+₹$monthGain this month", color = GigSuccess, fontWeight = FontWeight.Bold,
                                modifier = Modifier.padding(horizontal = 14.dp, vertical = 6.dp))
                        }
                    }
                }
                Spacer(Modifier.height(16.dp))
            }

            item {
                Card(modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp), shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(GigSoftTeal)) {
                    Row(modifier = Modifier.padding(14.dp), horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                        Text("🤝", fontSize = 20.sp)
                        Text("Unlike private platforms that extract profits, GigConnect credits eligible transaction margins back into your cooperative ownership stake.",
                            style = MaterialTheme.typography.bodySmall, color = GigOnBackground)
                    }
                }
                Spacer(Modifier.height(16.dp))
                Text("Patronage Dividend & Job Credits", style = MaterialTheme.typography.titleSmall, fontWeight = FontWeight.Bold,
                    color = GigOnBackground, modifier = Modifier.padding(horizontal = 20.dp))
                Spacer(Modifier.height(10.dp))
            }

            items(entries) { entry ->
                Card(modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp, vertical = 4.dp),
                    shape = RoundedCornerShape(12.dp), colors = CardDefaults.cardColors(GigSurface), elevation = CardDefaults.cardElevation(1.dp)) {
                    Row(modifier = Modifier.padding(14.dp), verticalAlignment = Alignment.CenterVertically) {
                        Box(modifier = Modifier.size(36.dp).clip(CircleShape).background(GigSuccessContainer), contentAlignment = Alignment.Center) {
                            Text("₹", fontWeight = FontWeight.Bold, color = GigSuccess)
                        }
                        Spacer(Modifier.width(12.dp))
                        Column(modifier = Modifier.weight(1f)) {
                            Text("Job #${entry.jobId}", fontWeight = FontWeight.SemiBold, style = MaterialTheme.typography.bodyMedium, color = GigOnSurface)
                            Text(entry.description, style = MaterialTheme.typography.bodySmall, color = GigSubtleText)
                            Text(entry.date, style = MaterialTheme.typography.bodySmall, color = GigSubtleText)
                        }
                        Text("+₹${entry.amount}", fontWeight = FontWeight.Bold, color = GigSuccess)
                    }
                }
            }
            item { Spacer(Modifier.height(24.dp)) }
        }
    }
}

// ─────────────────────────────────────────────────────────────────────────────
//  Skill-Up AI Screen
// ─────────────────────────────────────────────────────────────────────────────
@Composable
fun SkillUpScreen(workerViewModel: WorkerViewModel, onBack: () -> Unit) {
    val recs by workerViewModel.skillRecommendations.collectAsState()

    Scaffold(topBar = { GigTopBar(title = "Skill-Up AI Coach", subtitle = "Connected to Pune market demand", onBack = onBack) }) { padding ->
        LazyColumn(modifier = Modifier.fillMaxSize().background(GigBackground).padding(padding),
            contentPadding = PaddingValues(20.dp), verticalArrangement = Arrangement.spacedBy(14.dp)) {
            item {
                Card(modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(GigSoftTeal)) {
                    Row(modifier = Modifier.padding(16.dp), horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                        Text("🎓", fontSize = 28.sp)
                        Column {
                            Text("Demand-Aware Skill Coach", fontWeight = FontWeight.Bold, color = GigPrimaryBlue)
                            Text("Recommendations are synchronized with real-time customer request trends across Pune.", style = MaterialTheme.typography.bodySmall, color = GigPrimaryBlue)
                            Spacer(Modifier.height(4.dp))
                            DemoDataBadge()
                        }
                    }
                }
            }
            items(recs) { rec ->
                Card(modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(GigSurface), elevation = CardDefaults.cardElevation(2.dp),
                    border = if (rec.isRecommended) BorderStroke(2.dp, GigSecondaryBlue) else null) {
                    Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
                        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                            Column(modifier = Modifier.weight(1f)) {
                                if (rec.isRecommended) {
                                    Surface(color = GigSecondaryBlue, shape = RoundedCornerShape(6.dp)) {
                                        Text("⭐ AI High-Demand Recommendation", style = MaterialTheme.typography.labelSmall, color = Color.White,
                                            fontWeight = FontWeight.Bold, modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp))
                                    }
                                    Spacer(Modifier.height(4.dp))
                                }
                                Text(rec.skillName, style = MaterialTheme.typography.titleSmall, fontWeight = FontWeight.Bold, color = GigOnSurface)
                                Text("${rec.courseDuration} practical module", style = MaterialTheme.typography.bodySmall, color = GigSubtleText)
                            }
                            Surface(color = if (rec.demandTrend > 0) GigSuccessContainer else GigErrorContainer, shape = RoundedCornerShape(8.dp)) {
                                Text("${if (rec.demandTrend > 0) "↑" else "↓"} ${rec.demandTrend}% Pune demand",
                                    style = MaterialTheme.typography.labelSmall, color = if (rec.demandTrend > 0) GigSuccess else GigError,
                                    fontWeight = FontWeight.Bold, modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp))
                            }
                        }
                        // Progress
                        Column {
                            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                                Text("Module Progress", style = MaterialTheme.typography.bodySmall, color = GigSubtleText)
                                Text("${rec.progressPercent}%", style = MaterialTheme.typography.bodySmall, fontWeight = FontWeight.Bold, color = GigOnSurface)
                            }
                            Spacer(Modifier.height(4.dp))
                            LinearProgressIndicator(
                                progress = { rec.progressPercent / 100f },
                                modifier = Modifier.fillMaxWidth().height(8.dp).clip(RoundedCornerShape(4.dp)),
                                color = if (rec.progressPercent == 100) GigSuccess else GigPrimaryBlue,
                                trackColor = GigSoftTeal
                            )
                        }
                        Text(rec.aiReason, style = MaterialTheme.typography.bodySmall, color = GigSubtleText)
                        if (rec.progressPercent < 100) {
                            Button(
                                onClick = {
                                    workerViewModel.updateCourseProgress(rec.skillName, rec.progressPercent + 30)
                                },
                                modifier = Modifier.fillMaxWidth().height(42.dp),
                                colors = ButtonDefaults.buttonColors(if (rec.isRecommended) GigSecondaryBlue else GigPrimaryBlue),
                                shape = RoundedCornerShape(10.dp)
                            ) {
                                Text(if (rec.progressPercent == 0) "Start Learning Module" else "Continue Learning (+30%)", color = Color.White, fontWeight = FontWeight.Bold)
                            }
                        } else {
                            Surface(modifier = Modifier.fillMaxWidth(), color = GigSuccessContainer, shape = RoundedCornerShape(10.dp)) {
                                Row(
                                    modifier = Modifier.padding(10.dp),
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.Center
                                ) {
                                    Icon(Icons.Filled.CheckCircle, null, tint = GigSuccess)
                                    Spacer(Modifier.width(6.dp))
                                    Text("Cooperative Certified", fontWeight = FontWeight.Bold, color = GigSuccess)
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
//  Voice Assistant Screen
// ─────────────────────────────────────────────────────────────────────────────
@Composable
fun VoiceAssistantScreen(workerViewModel: WorkerViewModel, onBack: () -> Unit) {
    val isListening by workerViewModel.isVoiceListening.collectAsState()
    val result by workerViewModel.voiceResult.collectAsState()
    val language by workerViewModel.detectedLanguage.collectAsState()
    var selectedLang by remember { mutableStateOf("English") }
    val languages = listOf("English", "Hindi", "Marathi", "Tamil", "Telugu", "Kannada", "Malayalam", "Bengali", "Gujarati", "Punjabi")

    val pulse by rememberInfiniteTransition(label = "pulse").animateFloat(0.85f, 1.15f,
        infiniteRepeatable(tween(700, easing = FastOutSlowInEasing), RepeatMode.Reverse), label = "p")

    Scaffold(topBar = { GigTopBar(title = "GigConnect Voice", subtitle = "Regional language support", onBack = onBack) }) { padding ->
        Column(modifier = Modifier.fillMaxSize().background(GigBackground).padding(padding).verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally) {
            Spacer(Modifier.height(24.dp))
            DemoDataBadge()
            Spacer(Modifier.height(24.dp))

            // Language selector
            LazyRow(contentPadding = PaddingValues(horizontal = 20.dp), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                items(languages) { lang ->
                    FilterChip(selected = selectedLang == lang, onClick = { selectedLang = lang },
                        label = { Text(lang) },
                        colors = FilterChipDefaults.filterChipColors(selectedContainerColor = GigPrimaryBlue, selectedLabelColor = Color.White))
                }
            }

            Spacer(Modifier.height(40.dp))

            // Mic button
            Box(contentAlignment = Alignment.Center) {
                if (isListening) {
                    Box(modifier = Modifier.size((120 * pulse).toInt().dp).clip(CircleShape).background(GigPrimaryBlue.copy(0.15f)))
                }
                Box(
                    modifier = Modifier
                        .size(100.dp)
                        .clip(CircleShape)
                        .background(if (isListening) GigError else GigPrimaryBlue)
                        .clickable { workerViewModel.simulateVoiceInput(selectedLang) },
                    contentAlignment = Alignment.Center
                ) {
                    Icon(if (isListening) Icons.Filled.Stop else Icons.Filled.Mic, null,
                        tint = Color.White, modifier = Modifier.size(48.dp))
                }
            }

            Spacer(Modifier.height(16.dp))
            Text(if (isListening) "Listening..." else "Tap to speak",
                style = MaterialTheme.typography.titleSmall, color = if (isListening) GigError else GigSubtleText,
                fontWeight = if (isListening) FontWeight.Bold else FontWeight.Normal)

            if (result.isNotEmpty()) {
                Spacer(Modifier.height(24.dp))
                Card(modifier = Modifier.fillMaxWidth().padding(horizontal = 24.dp),
                    shape = RoundedCornerShape(16.dp), colors = CardDefaults.cardColors(GigSurface), elevation = CardDefaults.cardElevation(4.dp)) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Row(horizontalArrangement = Arrangement.spacedBy(8.dp), verticalAlignment = Alignment.CenterVertically) {
                            Surface(color = GigSoftTeal, shape = RoundedCornerShape(6.dp)) {
                                Text(language, style = MaterialTheme.typography.labelSmall, color = GigPrimaryBlue, modifier = Modifier.padding(4.dp, 2.dp))
                            }
                            Text("Recognized", style = MaterialTheme.typography.labelSmall, color = GigSubtleText)
                        }
                        Spacer(Modifier.height(8.dp))
                        Text(result, style = MaterialTheme.typography.bodyMedium, color = GigOnSurface)
                    }
                }
            }

            Spacer(Modifier.height(24.dp))
            Text("Example commands:", style = MaterialTheme.typography.labelMedium, color = GigSubtleText)
            Spacer(Modifier.height(8.dp))
            listOf(
                "\"Show my jobs for today.\"",
                "\"आज मेरे लिए कौनसे काम हैं?\"",
                "\"माझे आजचे काम दाखव.\""
            ).forEach { cmd ->
                Text(cmd, style = MaterialTheme.typography.bodySmall, color = GigPrimaryBlue, modifier = Modifier.padding(vertical = 2.dp))
            }
        }
    }
}

// ─────────────────────────────────────────────────────────────────────────────
//  Safety Center Screen
// ─────────────────────────────────────────────────────────────────────────────
@Composable
fun SafetyCenterScreen(onBack: () -> Unit) {
    var showSosDialog by remember { mutableStateOf(false) }

    if (showSosDialog) {
        AlertDialog(
            onDismissRequest = { showSosDialog = false },
            title = { Text("🚨 SOS Alert", fontWeight = FontWeight.Bold, color = GigOnSurface) },
            text = { Text("This will notify your emergency contact and GigConnect safety team.\n\nFor demo purposes, this is simulated.", color = GigSubtleText) },
            confirmButton = {
                Button(onClick = { showSosDialog = false }, colors = ButtonDefaults.buttonColors(GigError)) {
                    Text("Trigger SOS (Demo)")
                }
            },
            dismissButton = {
                TextButton(onClick = { showSosDialog = false }) { Text("Cancel") }
            }
        )
    }

    Scaffold(topBar = { GigTopBar(title = "Safety Center", onBack = onBack) }) { padding ->
        Column(modifier = Modifier.fillMaxSize().background(GigBackground).padding(padding)
            .verticalScroll(rememberScrollState()).padding(20.dp), verticalArrangement = Arrangement.spacedBy(16.dp)) {
            // SOS button
            Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Box(
                        modifier = Modifier.size(120.dp).clip(CircleShape).background(GigError).clickable { showSosDialog = true },
                        contentAlignment = Alignment.Center
                    ) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Text("🚨", fontSize = 32.sp)
                            Text("SOS", color = Color.White, fontWeight = FontWeight.ExtraBold, style = MaterialTheme.typography.titleLarge)
                        }
                    }
                    Spacer(Modifier.height(8.dp))
                    Text("Press for emergency help", style = MaterialTheme.typography.bodySmall, color = GigSubtleText)
                }
            }

            listOf(
                Triple("📞", "Emergency Contact", "Call your pre-set emergency contact"),
                Triple("✅", "Safety Check-In", "Let your contacts know you're safe"),
                Triple("👤", "Customer Details", "View verified customer information"),
                Triple("📍", "Job Location", "View job address on map"),
                Triple("⚠️", "Report Issue", "Report safety concerns anonymously")
            ).forEach { (emoji, title, sub) ->
                Card(modifier = Modifier.fillMaxWidth().clickable {}, shape = RoundedCornerShape(14.dp),
                    colors = CardDefaults.cardColors(GigSurface), elevation = CardDefaults.cardElevation(2.dp)) {
                    Row(modifier = Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(14.dp)) {
                        Box(modifier = Modifier.size(44.dp).clip(CircleShape).background(GigErrorContainer), contentAlignment = Alignment.Center) {
                            Text(emoji, fontSize = 22.sp)
                        }
                        Column(modifier = Modifier.weight(1f)) {
                            Text(title, fontWeight = FontWeight.SemiBold, color = GigOnSurface)
                            Text(sub, style = MaterialTheme.typography.bodySmall, color = GigSubtleText)
                        }
                        Icon(Icons.Filled.ChevronRight, null, tint = GigSubtleText)
                    }
                }
            }
        }
    }
}
