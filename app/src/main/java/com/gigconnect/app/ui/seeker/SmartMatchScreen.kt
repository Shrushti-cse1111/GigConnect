package com.gigconnect.app.ui.seeker

import androidx.compose.animation.*
import androidx.compose.animation.core.*
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.*
import com.gigconnect.app.data.model.SmartMatchResult
import com.gigconnect.app.ui.components.*
import com.gigconnect.app.ui.theme.*
import com.gigconnect.app.viewmodel.SeekerViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SmartMatchScreen(
    seekerViewModel: SeekerViewModel,
    onWorkerSelected: () -> Unit,
    onBack: () -> Unit
) {
    val isRunning by seekerViewModel.isSmartMatchRunning.collectAsState()
    val progress by seekerViewModel.smartMatchProgress.collectAsState()
    val steps by seekerViewModel.smartMatchSteps.collectAsState()
    val recommended by seekerViewModel.recommendedWorker.collectAsState()
    var showWhySheet by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        if (recommended == null) seekerViewModel.runSmartMatch()
    }

    if (showWhySheet && recommended != null) {
        WhyThisWorkerSheet(result = recommended!!, onDismiss = { showWhySheet = false })
    }

    Scaffold(
        topBar = { GigTopBar(title = "GigConnect SmartMatch", subtitle = "AI-powered matching", onBack = onBack) }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(GigBackground)
                .padding(padding)
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            DemoDataBadge()
            Spacer(Modifier.height(24.dp))

            // Animated ring
            val animProgress by animateFloatAsState(
                targetValue = progress,
                animationSpec = tween(400),
                label = "progress"
            )

            Box(contentAlignment = Alignment.Center) {
                ProgressRing(
                    progress = if (isRunning) animProgress else 1f,
                    size = 140.dp,
                    strokeWidth = 12.dp,
                    color = GigTeal,
                    modifier = Modifier
                )
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    if (isRunning) {
                        Text("🤖", fontSize = 32.sp)
                        Text("${(animProgress * 100).toInt()}%", style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.Bold, color = GigTeal)
                    } else {
                        Text("✅", fontSize = 32.sp)
                        Text("Match\nFound!", style = MaterialTheme.typography.titleSmall,
                            fontWeight = FontWeight.Bold, color = GigSuccess, textAlign = TextAlign.Center)
                    }
                }
            }

            Spacer(Modifier.height(16.dp))

            Text(
                if (isRunning) "Finding the best verified worker for you..." else "AI Recommended Worker",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.SemiBold,
                color = GigOnSurface
            )

            Spacer(Modifier.height(20.dp))

            // Steps
            if (steps.isNotEmpty()) {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = GigSurface),
                    elevation = CardDefaults.cardElevation(2.dp)
                ) {
                    Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
                        Text("AI Analysis", style = MaterialTheme.typography.titleSmall, fontWeight = FontWeight.Bold)
                        steps.forEach { (label, done) ->
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(10.dp)
                            ) {
                                AnimatedContent(targetState = done, label = "check") { isDone ->
                                    Icon(
                                        if (isDone) Icons.Filled.CheckCircle else Icons.Filled.RadioButtonUnchecked,
                                        null,
                                        tint = if (isDone) GigSuccess else GigOutline,
                                        modifier = Modifier.size(20.dp)
                                    )
                                }
                                Text(
                                    label,
                                    style = MaterialTheme.typography.bodyMedium,
                                    color = if (done) GigOnSurface else GigSubtleText,
                                    fontWeight = if (done) FontWeight.Medium else FontWeight.Normal
                                )
                            }
                        }
                    }
                }
            }

            // Recommended worker card
            AnimatedVisibility(visible = recommended != null && !isRunning) {
                val r = recommended ?: return@AnimatedVisibility
                Spacer(Modifier.height(16.dp))
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(20.dp),
                    colors = CardDefaults.cardColors(containerColor = GigSurface),
                    elevation = CardDefaults.cardElevation(6.dp),
                    border = BorderStroke(2.dp, GigTeal)
                ) {
                    Column(modifier = Modifier.padding(20.dp)) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(14.dp)
                        ) {
                            Box(
                                modifier = Modifier.size(60.dp).clip(CircleShape)
                                    .background(Brush.linearGradient(listOf(GigTeal, GigSaffron))),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    r.worker.name.split(" ").take(2).joinToString("") { it.first().toString() },
                                    style = MaterialTheme.typography.titleMedium, color = Color.White, fontWeight = FontWeight.Bold
                                )
                            }
                            Column(modifier = Modifier.weight(1f)) {
                                Text(r.worker.name, style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
                                Text(r.worker.primarySkill, color = GigTeal)
                                if (r.worker.isVerified) VerifiedBadge(small = true)
                            }
                            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                ProgressRing(
                                    progress = r.overallScore / 100f,
                                    size = 56.dp,
                                    strokeWidth = 6.dp,
                                    color = GigSaffron,
                                    label = "${r.overallScore}%"
                                )
                                Text("Match", style = MaterialTheme.typography.labelSmall, color = GigSubtleText)
                            }
                        }

                        Spacer(Modifier.height(14.dp))
                        TextButton(
                            onClick = { showWhySheet = true },
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Icon(Icons.Filled.Info, null, tint = GigTeal, modifier = Modifier.size(16.dp))
                            Spacer(Modifier.width(6.dp))
                            Text("Why this worker?", color = GigTeal)
                        }
                        Button(
                            onClick = onWorkerSelected,
                            modifier = Modifier.fillMaxWidth().height(50.dp),
                            colors = ButtonDefaults.buttonColors(containerColor = GigTeal),
                            shape = RoundedCornerShape(12.dp)
                        ) {
                            Text("Confirm & Book", fontWeight = FontWeight.Bold)
                        }
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun WhyThisWorkerSheet(result: SmartMatchResult, onDismiss: () -> Unit) {
    ModalBottomSheet(onDismissRequest = onDismiss, shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp)) {
        Column(modifier = Modifier.padding(start = 24.dp, end = 24.dp, bottom = 40.dp)) {
            Text("Why this match?", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
            Spacer(Modifier.height(16.dp))
            MatchRow("🎯 Skill Match", "${result.skillScore}%", result.skillScore / 100f)
            MatchRow("📍 Distance", "${result.worker.distanceKm} km", result.distanceScore / 100f)
            MatchRow("🟢 Availability", "Available now", result.availabilityScore / 100f)
            MatchRow("⭐ Rating", "${result.worker.rating}/5", result.ratingScore / 100f)
            MatchRow("📋 Workload", if (result.worker.currentWorkload == 0) "Low" else "Moderate", result.workloadScore / 100f)
            MatchRow("⚖️ Fair Rotation Score", "High", result.fairnessScore / 100f)
            Spacer(Modifier.height(16.dp))
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = GigTealContainer),
                shape = RoundedCornerShape(12.dp)
            ) {
                Row(modifier = Modifier.padding(14.dp), horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                    Text("🤖", fontSize = 20.sp)
                    Text(
                        "FairMatch ensures that jobs are distributed fairly — not just to the highest-rated worker.",
                        style = MaterialTheme.typography.bodySmall,
                        color = GigTealDark
                    )
                }
            }
        }
    }
}

@Composable
private fun MatchRow(label: String, value: String, progress: Float) {
    Column(modifier = Modifier.fillMaxWidth().padding(vertical = 6.dp)) {
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Text(label, style = MaterialTheme.typography.bodyMedium)
            Text(value, style = MaterialTheme.typography.bodyMedium, fontWeight = FontWeight.Bold, color = GigTeal)
        }
        Spacer(Modifier.height(4.dp))
        LinearProgressIndicator(
            progress = { progress },
            modifier = Modifier.fillMaxWidth().height(6.dp).clip(RoundedCornerShape(3.dp)),
            color = GigTeal,
            trackColor = GigTealContainer
        )
    }
}
