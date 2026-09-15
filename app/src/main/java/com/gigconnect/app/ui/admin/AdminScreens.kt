package com.gigconnect.app.ui.admin

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
import com.gigconnect.app.data.model.*
import com.gigconnect.app.ui.components.*
import com.gigconnect.app.ui.theme.*
import com.gigconnect.app.viewmodel.AdminViewModel

// ─────────────────────────────────────────────────────────────────────────────
//  Demand Intelligence
// ─────────────────────────────────────────────────────────────────────────────
@Composable
fun DemandIntelligenceScreen(adminViewModel: AdminViewModel, onBack: () -> Unit) {
    val forecasts = adminViewModel.demandForecasts

    Scaffold(topBar = { GigTopBar(title = "Demand Intelligence", subtitle = "AI-powered forecasting", onBack = onBack) }) { padding ->
        LazyColumn(modifier = Modifier.fillMaxSize().background(GigBackground).padding(padding),
            contentPadding = PaddingValues(20.dp), verticalArrangement = Arrangement.spacedBy(14.dp)) {
            item {
                Card(modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(16.dp), colors = CardDefaults.cardColors(GigSoftTeal)) {
                    Row(modifier = Modifier.padding(16.dp), horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                        Text("🧠", fontSize = 28.sp)
                        Column {
                            Text("AI Demand Intelligence", fontWeight = FontWeight.Bold, color = GigPrimaryBlue)
                            Text("Forecasting worker demand for the next 30 days based on historical data, weather, and events.",
                                style = MaterialTheme.typography.bodySmall, color = GigPrimaryBlue)
                            Spacer(Modifier.height(6.dp))
                            DemoDataBadge()
                        }
                    }
                }
            }

            items(forecasts) { forecast ->
                Card(modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(GigSurface), elevation = CardDefaults.cardElevation(2.dp)) {
                    Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
                        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
                            Text(forecast.serviceCategory, fontWeight = FontWeight.Bold, color = GigOnSurface)
                            StatusChip(forecast.urgencyLevel)
                        }
                        Text(forecast.area, style = MaterialTheme.typography.bodySmall, color = GigPrimaryBlue)
                        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                            DemandStat("Predicted Demand", "${forecast.predictedDemand} jobs")
                            DemandStat("Available Workers", "${forecast.availableWorkers}")
                            DemandStat("Gap", "${forecast.gap} workers needed")
                        }
                        // Demand bar
                        val util = (forecast.availableWorkers.toFloat() / (forecast.predictedDemand * 0.6f)).coerceIn(0f, 1f)
                        Column {
                            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                                Text("Worker Utilization", style = MaterialTheme.typography.bodySmall, color = GigSubtleText)
                                Text("${(util * 100).toInt()}%", fontWeight = FontWeight.Bold, color = if (util < 0.7f) GigError else GigSuccess)
                            }
                            Spacer(Modifier.height(4.dp))
                            LinearProgressIndicator(
                                progress = { util }, modifier = Modifier.fillMaxWidth().height(8.dp).clip(RoundedCornerShape(4.dp)),
                                color = if (util < 0.7f) GigError else GigPrimaryBlue, trackColor = GigSoftTeal
                            )
                        }
                        Text("AI Insight: ${forecast.aiInsight}", style = MaterialTheme.typography.bodySmall, color = GigSubtleText)
                        if (forecast.gap > 0) {
                            Button(onClick = {}, modifier = Modifier.fillMaxWidth().height(40.dp),
                                colors = ButtonDefaults.buttonColors(GigSecondaryBlue), shape = RoundedCornerShape(10.dp)) {
                                Text("Notify Workers in ${forecast.area}", style = MaterialTheme.typography.labelLarge, color = Color.White, fontWeight = FontWeight.Bold)
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable private fun DemandStat(label: String, value: String) {
    Column {
        Text(value, fontWeight = FontWeight.Bold, style = MaterialTheme.typography.titleSmall, color = GigOnSurface)
        Text(label, style = MaterialTheme.typography.labelSmall, color = GigSubtleText)
    }
}

// ─────────────────────────────────────────────────────────────────────────────
//  FairMatch Engine
// ─────────────────────────────────────────────────────────────────────────────
@Composable
fun FairMatchScreen(adminViewModel: AdminViewModel, onBack: () -> Unit) {
    val candidates by adminViewModel.fairMatchCandidates.collectAsState()

    Scaffold(topBar = { GigTopBar(title = "FairMatch Engine", subtitle = "Equitable job allocation", onBack = onBack) }) { padding ->
        LazyColumn(modifier = Modifier.fillMaxSize().background(GigBackground).padding(padding),
            contentPadding = PaddingValues(20.dp), verticalArrangement = Arrangement.spacedBy(14.dp)) {
            item {
                Card(modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(16.dp), colors = CardDefaults.cardColors(GigSoftTeal)) {
                    Row(modifier = Modifier.padding(16.dp), horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                        Text("⚖️", fontSize = 28.sp)
                        Column {
                            Text("FairMatch Engine", fontWeight = FontWeight.Bold, color = GigPrimaryBlue)
                            Text("Distributes jobs equitably — not just to the highest-rated worker, but considering last-job time, workload, skills, and welfare.",
                                style = MaterialTheme.typography.bodySmall, color = GigOnBackground)
                            Spacer(Modifier.height(6.dp))
                            DemoDataBadge()
                        }
                    }
                }
            }

            item {
                Text("Candidate Ranking", style = MaterialTheme.typography.titleSmall, fontWeight = FontWeight.Bold, color = GigOnBackground)
                Text("For: Plumbing job — Koregaon Park, Pune", style = MaterialTheme.typography.bodySmall, color = GigSubtleText)
            }

            itemsIndexed(candidates) { index, candidate ->
                Card(modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(if (index == 0) GigSoftTeal else GigSurface),
                    elevation = CardDefaults.cardElevation(if (index == 0) 6.dp else 2.dp),
                    border = if (index == 0) BorderStroke(2.dp, GigPrimaryBlue) else null) {
                    Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
                        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
                            Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                                Box(modifier = Modifier.size(36.dp).clip(CircleShape).background(if (index == 0) GigPrimaryBlue else GigSurfaceVariant),
                                    contentAlignment = Alignment.Center) {
                                    Text("#${index + 1}", fontWeight = FontWeight.ExtraBold, color = if (index == 0) Color.White else GigSubtleText)
                                }
                                Column {
                                    Text(candidate.workerName, fontWeight = FontWeight.Bold, color = GigOnSurface)
                                    Text("⭐ ${candidate.workerRating}", style = MaterialTheme.typography.bodySmall, color = GigSubtleText)
                                }
                            }
                            Column(horizontalAlignment = Alignment.End) {
                                ProgressRing(candidate.fairMatchScore / 100f, 50.dp, 5.dp, if (index == 0) GigPrimaryBlue else GigSecondaryBlue, "${candidate.fairMatchScore}%")
                                Text("Fair Score", style = MaterialTheme.typography.labelSmall, color = GigSubtleText)
                            }
                        }
                        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                            FairMatchStat("Workload", "${candidate.currentWorkload} active")
                            FairMatchStat("Idle Since", candidate.idleSince)
                            FairMatchStat("Distance", "${candidate.distanceKm} km")
                        }
                        if (index == 0) {
                            Surface(color = GigPrimaryBlue, shape = RoundedCornerShape(8.dp), modifier = Modifier.fillMaxWidth()) {
                                Box(contentAlignment = Alignment.Center, modifier = Modifier.padding(8.dp)) {
                                    Text("⭐ FairMatch Recommended", color = Color.White, fontWeight = FontWeight.Bold)
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable private fun FairMatchStat(label: String, value: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(value, fontWeight = FontWeight.Bold, style = MaterialTheme.typography.bodySmall, color = GigOnSurface)
        Text(label, style = MaterialTheme.typography.labelSmall, color = GigSubtleText)
    }
}

// ─────────────────────────────────────────────────────────────────────────────
//  Worker Management
// ─────────────────────────────────────────────────────────────────────────────
@Composable
fun WorkerManagementScreen(adminViewModel: AdminViewModel, onBack: () -> Unit) {
    val filter by adminViewModel.workerFilter.collectAsState()
    val filteredWorkers by adminViewModel.filteredWorkers.collectAsState()

    Scaffold(topBar = { GigTopBar(title = "Worker Management", subtitle = "Verification & profiles", onBack = onBack) }) { padding ->
        Column(modifier = Modifier.fillMaxSize().background(GigBackground).padding(padding)) {
            Row(modifier = Modifier.fillMaxWidth().padding(16.dp), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                listOf(VerificationStatus.VERIFIED, VerificationStatus.PENDING, VerificationStatus.NEEDS_REVIEW).forEach { s ->
                    FilterChip(selected = filter == s, onClick = { adminViewModel.setWorkerFilter(s) }, label = { Text(s.name) },
                        colors = FilterChipDefaults.filterChipColors(selectedContainerColor = GigPrimaryBlue, selectedLabelColor = Color.White))
                }
            }
            LazyColumn(contentPadding = PaddingValues(horizontal = 16.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
                item { DemoDataBadge(); Spacer(Modifier.height(4.dp)) }
                items(filteredWorkers) { worker ->
                    Card(modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(14.dp),
                        colors = CardDefaults.cardColors(GigSurface), elevation = CardDefaults.cardElevation(2.dp)) {
                        Row(modifier = Modifier.padding(14.dp), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                            Box(modifier = Modifier.size(44.dp).clip(CircleShape).background(GigPrimaryBlue), contentAlignment = Alignment.Center) {
                                Text(worker.name.split(" ").take(2).joinToString("") { it.first().toString() }, color = Color.White, fontWeight = FontWeight.Bold)
                            }
                            Column(modifier = Modifier.weight(1f)) {
                                Text(worker.name, fontWeight = FontWeight.SemiBold, color = GigOnSurface)
                                Text(worker.primarySkill, style = MaterialTheme.typography.bodySmall, color = GigPrimaryBlue)
                                Text("⭐ ${worker.rating} • ${worker.completedJobs} jobs", style = MaterialTheme.typography.bodySmall, color = GigSubtleText)
                            }
                            StatusChip(worker.verificationStatus.name)
                        }
                    }
                }
                item { Spacer(Modifier.height(24.dp)) }
            }
        }
    }
}

// ─────────────────────────────────────────────────────────────────────────────
//  Welfare Screen
// ─────────────────────────────────────────────────────────────────────────────
@Composable
fun WelfareScreen(adminViewModel: AdminViewModel, onBack: () -> Unit) {
    val welfare = adminViewModel.welfareStats
    val grievances by adminViewModel.grievances.collectAsState()

    Scaffold(topBar = { GigTopBar(title = "Worker Welfare", subtitle = "Insurance & grievances", onBack = onBack) }) { padding ->
        LazyColumn(modifier = Modifier.fillMaxSize().background(GigBackground).padding(padding),
            contentPadding = PaddingValues(20.dp), verticalArrangement = Arrangement.spacedBy(14.dp)) {
            item {
                Card(modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(20.dp),
                    colors = CardDefaults.cardColors(Color(0xFFE8F5E9))) {
                    Column(modifier = Modifier.padding(20.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
                        Text("🛡️ Welfare Overview", fontWeight = FontWeight.Bold, style = MaterialTheme.typography.titleMedium, color = GigOnSurface)
                        DemoDataBadge()
                        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                            WelfareStat("${welfare.insuredWorkers}", "Insured Workers")
                            WelfareStat("₹${welfare.totalFundCollected}", "Fund Collected")
                            WelfareStat("${welfare.claimsThisMonth}", "Claims This Month")
                        }
                    }
                }
            }

            item {
                Text("Grievances", style = MaterialTheme.typography.titleSmall, fontWeight = FontWeight.Bold, color = GigOnBackground)
            }

            items(grievances) { g ->
                Card(modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(14.dp),
                    colors = CardDefaults.cardColors(GigSurface), elevation = CardDefaults.cardElevation(2.dp)) {
                    Column(modifier = Modifier.padding(14.dp), verticalArrangement = Arrangement.spacedBy(6.dp)) {
                        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                            Text("#${g.id} • ${g.category}", fontWeight = FontWeight.SemiBold, style = MaterialTheme.typography.bodyMedium, color = GigOnSurface)
                            StatusChip(g.status)
                        }
                        Text(g.description, style = MaterialTheme.typography.bodySmall, color = GigSubtleText)
                        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                            Text("By ${g.raisedBy}", style = MaterialTheme.typography.bodySmall, color = GigSubtleText)
                            Text(g.raisedDate, style = MaterialTheme.typography.bodySmall, color = GigSubtleText)
                        }
                        if (g.status == "Pending") {
                            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                                OutlinedButton(onClick = {}, modifier = Modifier.height(36.dp), shape = RoundedCornerShape(8.dp), colors = ButtonDefaults.outlinedButtonColors(contentColor = GigPrimaryBlue), border = BorderStroke(1.dp, GigPrimaryBlue)) { Text("Review", style = MaterialTheme.typography.labelMedium) }
                                Button(onClick = {}, modifier = Modifier.height(36.dp), colors = ButtonDefaults.buttonColors(GigSuccess), shape = RoundedCornerShape(8.dp)) { Text("Resolve", style = MaterialTheme.typography.labelMedium, color = Color.White) }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable private fun WelfareStat(value: String, label: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(value, fontWeight = FontWeight.ExtraBold, style = MaterialTheme.typography.titleMedium, color = GigSuccess)
        Text(label, style = MaterialTheme.typography.labelSmall, color = GigSubtleText)
    }
}

// ─────────────────────────────────────────────────────────────────────────────
//  Digital Panchayat (Cooperative Governance + Voting)
// ─────────────────────────────────────────────────────────────────────────────
@Composable
fun DigitalPanchayatScreen(adminViewModel: AdminViewModel, onBack: () -> Unit, onVotingClick: () -> Unit) {
    val proposals by adminViewModel.proposals.collectAsState()

    Scaffold(topBar = { GigTopBar(title = "Digital Panchayat", subtitle = "Democratic cooperative governance", onBack = onBack) }) { padding ->
        LazyColumn(modifier = Modifier.fillMaxSize().background(GigBackground).padding(padding),
            contentPadding = PaddingValues(20.dp), verticalArrangement = Arrangement.spacedBy(14.dp)) {
            item {
                Card(modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(16.dp), colors = CardDefaults.cardColors(GigSoftTeal)) {
                    Row(modifier = Modifier.padding(16.dp), horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                        Text("🏛️", fontSize = 28.sp)
                        Column {
                            Text("Digital Panchayat", fontWeight = FontWeight.Bold, color = GigPrimaryBlue)
                            Text("Every worker is a member. Every voice counts. Cooperative decisions made democratically.",
                                style = MaterialTheme.typography.bodySmall, color = GigOnBackground)
                            Spacer(Modifier.height(6.dp))
                            DemoDataBadge()
                        }
                    }
                }
            }

            item { Text("Active Proposals", style = MaterialTheme.typography.titleSmall, fontWeight = FontWeight.Bold, color = GigOnBackground) }

            items(proposals) { proposal ->
                val total = (proposal.approveVotes + proposal.rejectVotes).coerceAtLeast(1)
                val approveRatio = proposal.approveVotes.toFloat() / total

                Card(modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(GigSurface), elevation = CardDefaults.cardElevation(3.dp),
                    border = if (proposal.userVote != null) BorderStroke(2.dp, GigPrimaryBlue) else null) {
                    Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
                        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                            Text(proposal.title, fontWeight = FontWeight.Bold, style = MaterialTheme.typography.titleSmall, modifier = Modifier.weight(1f), color = GigOnSurface)
                            StatusChip(proposal.status)
                        }
                        Text(proposal.description, style = MaterialTheme.typography.bodySmall, color = GigSubtleText)
                        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                            Column(modifier = Modifier.weight(1f)) {
                                Text("Approve: ${proposal.approveVotes}", style = MaterialTheme.typography.bodySmall, color = GigSuccess, fontWeight = FontWeight.Bold)
                                LinearProgressIndicator(progress = { approveRatio }, modifier = Modifier.fillMaxWidth().height(8.dp).clip(RoundedCornerShape(4.dp)), color = GigSuccess, trackColor = GigSuccessContainer)
                            }
                            Column(modifier = Modifier.weight(1f)) {
                                Text("Reject: ${proposal.rejectVotes}", style = MaterialTheme.typography.bodySmall, color = GigError, fontWeight = FontWeight.Bold)
                                LinearProgressIndicator(progress = { 1f - approveRatio }, modifier = Modifier.fillMaxWidth().height(8.dp).clip(RoundedCornerShape(4.dp)), color = GigError, trackColor = GigErrorContainer)
                            }
                        }
                        if (proposal.userVote == null && proposal.status == "Active") {
                            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                                OutlinedButton(onClick = { adminViewModel.vote(proposal.id, VoteChoice.REJECT) }, modifier = Modifier.weight(1f).height(42.dp), shape = RoundedCornerShape(10.dp), colors = ButtonDefaults.outlinedButtonColors(contentColor = GigError), border = BorderStroke(1.dp, GigError)) {
                                    Text("Reject", fontWeight = FontWeight.Bold)
                                }
                                Button(onClick = { adminViewModel.vote(proposal.id, VoteChoice.APPROVE) }, modifier = Modifier.weight(1f).height(42.dp), colors = ButtonDefaults.buttonColors(GigSuccess), shape = RoundedCornerShape(10.dp)) {
                                    Text("Approve", color = Color.White, fontWeight = FontWeight.Bold)
                                }
                            }
                        } else if (proposal.userVote != null) {
                            Surface(modifier = Modifier.fillMaxWidth(), color = GigSoftTeal, shape = RoundedCornerShape(8.dp)) {
                                Box(modifier = Modifier.padding(8.dp), contentAlignment = Alignment.Center) {
                                    Text("✅ You voted: ${proposal.userVote?.name}", fontWeight = FontWeight.Bold, color = GigPrimaryBlue)
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
//  Architecture Screen
// ─────────────────────────────────────────────────────────────────────────────
@Composable
fun ArchitectureScreen(onBack: () -> Unit) {
    Scaffold(topBar = { GigTopBar(title = "App Architecture", subtitle = "Tech stack overview", onBack = onBack) }) { padding ->
        LazyColumn(modifier = Modifier.fillMaxSize().background(GigBackground).padding(padding),
            contentPadding = PaddingValues(20.dp), verticalArrangement = Arrangement.spacedBy(14.dp)) {
            item {
                Card(modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(16.dp), colors = CardDefaults.cardColors(GigSoftTeal)) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text("🏗️ GigConnect Architecture", fontWeight = FontWeight.Bold, color = GigPrimaryBlue)
                        Text("Built for scalability, maintainability & performance", style = MaterialTheme.typography.bodySmall, color = GigPrimaryBlue)
                    }
                }
            }

            val sections = listOf(
                "📐 Architecture Pattern" to listOf("MVVM (Model-View-ViewModel)", "Clean Architecture (3 layers)", "Single Activity + Navigation Graph", "Repository Pattern"),
                "🎨 UI Layer" to listOf("Jetpack Compose (100% declarative)", "Material Design 3", "Compose Navigation", "Reusable Component Library"),
                "⚙️ Business Logic" to listOf("ViewModel + StateFlow", "Coroutines for async ops", "Compose State management", "Demo Data via DemoDataProvider"),
                "📦 Data Layer" to listOf("Repository Pattern", "Room Database (local)", "Retrofit (API ready)", "Coil (image loading)"),
                "🔧 Tech Stack" to listOf("Kotlin 100%", "Android SDK 35", "Gradle Version Catalog", "Kotlin DSL build scripts"),
                "🤖 AI Features" to listOf("SmartMatch Algorithm (skill + distance + workload)", "FairMatch Engine (equity allocation)", "Skill-Up AI (demand-based recommendations)", "Demand Intelligence (supply-demand forecasting)", "Voice Assistant (10+ Indian languages)")
            )

            items(sections) { (title, items) ->
                Card(modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(GigSurface), elevation = CardDefaults.cardElevation(2.dp)) {
                    Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                        Text(title, fontWeight = FontWeight.Bold, style = MaterialTheme.typography.titleSmall, color = GigOnSurface)
                        items.forEach { item ->
                            Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                                Box(modifier = Modifier.size(6.dp).clip(CircleShape).background(GigPrimaryBlue))
                                Text(item, style = MaterialTheme.typography.bodySmall, color = GigOnSurface)
                            }
                        }
                    }
                }
            }
        }
    }
}
