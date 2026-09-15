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
//  Job Detail
// ─────────────────────────────────────────────────────────────────────────────
@Composable
fun JobDetailScreen(job: Job, onAccept: () -> Unit, onReject: () -> Unit, onBack: () -> Unit) {
    Scaffold(
        topBar = { GigTopBar(title = "Job Details", onBack = onBack) },
        bottomBar = {
            Surface(shadowElevation = 8.dp) {
                Row(modifier = Modifier.fillMaxWidth().padding(16.dp), horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                    OutlinedButton(
                        onClick = { onReject(); onBack() },
                        modifier = Modifier.weight(1f).height(52.dp),
                        shape = RoundedCornerShape(14.dp),
                        colors = ButtonDefaults.outlinedButtonColors(contentColor = GigError),
                        border = BorderStroke(1.dp, GigError)
                    ) { Text("Decline", fontWeight = FontWeight.SemiBold) }
                    Button(
                        onClick = { onAccept(); onBack() },
                        modifier = Modifier.weight(2f).height(52.dp),
                        colors = ButtonDefaults.buttonColors(GigPrimaryBlue),
                        shape = RoundedCornerShape(14.dp)
                    ) { Text("Accept Job", color = Color.White, fontWeight = FontWeight.Bold) }
                }
            }
        }
    ) { padding ->
        Column(modifier = Modifier.fillMaxSize().background(GigBackground).padding(padding).verticalScroll(rememberScrollState()).padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)) {

            Card(modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(GigSoftTeal)) {
                Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                        Text(job.serviceDetail, style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold, color = GigOnBackground)
                        Surface(color = GigSecondaryBlue, shape = RoundedCornerShape(8.dp)) {
                            Text("${job.aiMatchScore}% AI Match", color = Color.White, fontWeight = FontWeight.Bold,
                                modifier = Modifier.padding(horizontal = 10.dp, vertical = 5.dp))
                        }
                    }
                    Text(job.serviceCategory, color = GigPrimaryBlue)
                    HorizontalDivider(color = GigPrimaryBlue.copy(0.2f))
                    DetailRow("📍 Distance", "${job.distanceKm} km")
                    DetailRow("⏱️ Duration", job.estimatedDuration)
                    DetailRow("💰 Estimated Earnings", "₹${job.estimatedEarnings}")
                    DetailRow("📅 Date", job.scheduledDate)
                    DetailRow("🕐 Time", job.scheduledTime)
                    DetailRow("🏠 Address", job.address)
                }
            }

            // Customer info
            Card(modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(16.dp), colors = CardDefaults.cardColors(GigSurface), elevation = CardDefaults.cardElevation(2.dp)) {
                Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
                    Text("Customer Information", style = MaterialTheme.typography.titleSmall, fontWeight = FontWeight.Bold, color = GigOnSurface)
                    Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                        Box(modifier = Modifier.size(44.dp).clip(CircleShape).background(GigSecondaryBlue), contentAlignment = Alignment.Center) {
                            Text(job.customerName.first().toString(), fontWeight = FontWeight.Bold, color = Color.White)
                        }
                        Column {
                            Text(job.customerName, fontWeight = FontWeight.SemiBold, color = GigOnSurface)
                            if (job.customerVerified) StatusChip("VERIFIED")
                        }
                    }
                }
            }

            // Safety note
            Card(modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(14.dp), colors = CardDefaults.cardColors(GigSuccessContainer)) {
                Row(modifier = Modifier.padding(14.dp), horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                    Text("🛡️", fontSize = 20.sp)
                    Column {
                        Text("Safety First", fontWeight = FontWeight.Bold, color = GigSuccess)
                        Text("Your safety check-in is active. SOS is available from the Safety Center.",
                            style = MaterialTheme.typography.bodySmall, color = GigSuccess)
                    }
                }
            }

            // No-penalty rejection notice
            Card(modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(14.dp), colors = CardDefaults.cardColors(GigSoftTeal)) {
                Row(modifier = Modifier.padding(14.dp), horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                    Text("⚖️", fontSize = 20.sp)
                    Text("Declining this job will NOT affect your rating or opportunities. GigConnect supports fair worker agency.",
                        style = MaterialTheme.typography.bodySmall, color = GigOnBackground)
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
fun JobsListScreen(workerViewModel: WorkerViewModel, onJobDetail: (Job) -> Unit, onBack: () -> Unit) {
    val jobs by workerViewModel.jobs.collectAsState()
    var tab by remember { mutableStateOf(0) }
    val tabs = listOf("New", "Active", "Completed")

    Scaffold(topBar = { GigTopBar(title = "My Jobs", onBack = onBack) }) { padding ->
        Column(modifier = Modifier.fillMaxSize().background(GigBackground).padding(padding)) {
            TabRow(selectedTabIndex = tab, containerColor = GigSurface, contentColor = GigPrimaryBlue) {
                tabs.forEachIndexed { i, t -> Tab(selected = tab == i, onClick = { tab = i }, text = { Text(t) }) }
            }
            val filtered = when (tab) {
                0 -> jobs.filter { it.status == JobStatus.NEW }
                1 -> jobs.filter { it.status == JobStatus.ACCEPTED || it.status == JobStatus.IN_PROGRESS }
                else -> jobs.filter { it.status == JobStatus.COMPLETED }
            }
            if (filtered.isEmpty()) {
                EmptyState("📭", "No ${tabs[tab].lowercase()} jobs", modifier = Modifier.fillMaxSize())
            } else {
                LazyColumn(contentPadding = PaddingValues(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
                    items(filtered) { job ->
                        JobRequestCard(job = job, onAccept = { workerViewModel.acceptJob(job.id) }, onViewDetails = { onJobDetail(job) })
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
                }
            }
        }
    }
}

// ─────────────────────────────────────────────────────────────────────────────
//  Earnings Screen
// ─────────────────────────────────────────────────────────────────────────────
@Composable
fun EarningsScreen(workerViewModel: WorkerViewModel, onEquityClick: () -> Unit, onBack: () -> Unit) {
    val weekly by workerViewModel.weeklyEarnings.collectAsState()

    Scaffold(topBar = { GigTopBar(title = "Earnings", onBack = onBack) }) { padding ->
        Column(modifier = Modifier.fillMaxSize().background(GigBackground).padding(padding).verticalScroll(rememberScrollState())) {
            Box(modifier = Modifier.fillMaxWidth().background(Brush.verticalGradient(listOf(GigPrimaryBlue, GigPrimaryBlue))).padding(24.dp)) {
                Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxWidth()) {
                    DemoDataBadge()
                    Spacer(Modifier.height(10.dp))
                    Text("This Week", style = MaterialTheme.typography.bodyMedium, color = Color.White.copy(0.8f))
                    Text("₹$weekly", style = MaterialTheme.typography.displaySmall, fontWeight = FontWeight.ExtraBold, color = Color.White)
                    Spacer(Modifier.height(12.dp))
                    Row(horizontalArrangement = Arrangement.spacedBy(20.dp)) {
                        EarningStatChip("18 Jobs", "Completed")
                        EarningStatChip("⭐ 4.8", "Avg Rating")
                        EarningStatChip("₹469", "Per Job Avg")
                    }
                }
            }

            Column(modifier = Modifier.padding(20.dp), verticalArrangement = Arrangement.spacedBy(16.dp)) {
                // Bar chart (simplified canvas)
                Text("Daily Earnings", style = MaterialTheme.typography.titleSmall, fontWeight = FontWeight.Bold, color = GigOnBackground)
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

                Text("Earnings Breakdown", style = MaterialTheme.typography.titleSmall, fontWeight = FontWeight.Bold, color = GigOnBackground)
                Card(modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(GigSurface), elevation = CardDefaults.cardElevation(2.dp)) {
                    Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
                        BreakdownRow("Service Earnings", "₹7,900", GigOnSurface)
                        BreakdownRow("Welfare Contribution", "₹250", GigSubtleText)
                        BreakdownRow("Cooperative Equity Credit", "₹300", GigPrimaryBlue)
                        BreakdownRow("Platform Contribution", "₹0 / Transparent", GigSuccess)
                    }
                }

                Button(onClick = onEquityClick, modifier = Modifier.fillMaxWidth().height(52.dp),
                    colors = ButtonDefaults.buttonColors(GigPrimaryBlue), shape = RoundedCornerShape(14.dp)) {
                    Text("View Equity Wallet", color = Color.White, fontWeight = FontWeight.Bold)
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

    Scaffold(topBar = { GigTopBar(title = "Equity Wallet", onBack = onBack) }) { padding ->
        LazyColumn(modifier = Modifier.fillMaxSize().background(GigBackground).padding(padding)) {
            item {
                Box(modifier = Modifier.fillMaxWidth().background(Brush.verticalGradient(listOf(GigPrimaryBlue, GigPrimaryBlue))).padding(24.dp)) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxWidth()) {
                        DemoDataBadge()
                        Spacer(Modifier.height(10.dp))
                        Text("GigConnect Equity Wallet", style = MaterialTheme.typography.titleMedium, color = Color.White.copy(0.85f))
                        Text("Your work. Your share. Your cooperative.", style = MaterialTheme.typography.bodySmall, color = Color.White.copy(0.65f))
                        Spacer(Modifier.height(16.dp))
                        Text("₹$balance", style = MaterialTheme.typography.displaySmall, fontWeight = FontWeight.ExtraBold, color = Color.White)
                        Text("Current Cooperative Equity", style = MaterialTheme.typography.bodySmall, color = Color.White.copy(0.75f))
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
                        Text("A percentage of eligible transactions is credited toward your cooperative share, according to cooperative rules.",
                            style = MaterialTheme.typography.bodySmall, color = GigOnBackground)
                    }
                }
                Spacer(Modifier.height(16.dp))
                Text("Recent Transactions", style = MaterialTheme.typography.titleSmall, fontWeight = FontWeight.Bold,
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

    Scaffold(topBar = { GigTopBar(title = "Skill-Up AI", subtitle = "Based on local demand", onBack = onBack) }) { padding ->
        LazyColumn(modifier = Modifier.fillMaxSize().background(GigBackground).padding(padding),
            contentPadding = PaddingValues(20.dp), verticalArrangement = Arrangement.spacedBy(14.dp)) {
            item {
                Card(modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(GigSoftTeal)) {
                    Row(modifier = Modifier.padding(16.dp), horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                        Text("🤖", fontSize = 28.sp)
                        Column {
                            Text("Skill-Up AI", fontWeight = FontWeight.Bold, color = GigPrimaryBlue)
                            Text("Improve your skills based on local demand.", style = MaterialTheme.typography.bodySmall, color = GigPrimaryBlue)
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
                                        Text("⭐ AI Recommended", style = MaterialTheme.typography.labelSmall, color = Color.White,
                                            fontWeight = FontWeight.Bold, modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp))
                                    }
                                    Spacer(Modifier.height(4.dp))
                                }
                                Text(rec.skillName, style = MaterialTheme.typography.titleSmall, fontWeight = FontWeight.Bold, color = GigOnSurface)
                                Text("${rec.courseDuration} course", style = MaterialTheme.typography.bodySmall, color = GigSubtleText)
                            }
                            Surface(color = if (rec.demandTrend > 0) GigSuccessContainer else GigErrorContainer, shape = RoundedCornerShape(8.dp)) {
                                Text("${if (rec.demandTrend > 0) "↑" else "↓"} ${rec.demandTrend}% demand",
                                    style = MaterialTheme.typography.labelSmall, color = if (rec.demandTrend > 0) GigSuccess else GigError,
                                    fontWeight = FontWeight.Bold, modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp))
                            }
                        }
                        // Progress
                        Column {
                            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                                Text("Progress", style = MaterialTheme.typography.bodySmall, color = GigSubtleText)
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
                            Button(onClick = {}, modifier = Modifier.fillMaxWidth().height(42.dp),
                                colors = ButtonDefaults.buttonColors(if (rec.isRecommended) GigSecondaryBlue else GigPrimaryBlue),
                                shape = RoundedCornerShape(10.dp)) {
                                Text(if (rec.progressPercent == 0) "Start Learning" else "Continue Learning", color = Color.White, fontWeight = FontWeight.Bold)
                            }
                        } else {
                            Surface(modifier = Modifier.fillMaxWidth(), color = GigSuccessContainer, shape = RoundedCornerShape(10.dp)) {
                                Box(modifier = Modifier.padding(10.dp), contentAlignment = Alignment.Center) {
                                    Text("✅ Certified", fontWeight = FontWeight.Bold, color = GigSuccess)
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
