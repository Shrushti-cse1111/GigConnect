package com.gigconnect.app.ui.worker

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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.gigconnect.app.data.model.*
import com.gigconnect.app.ui.components.*
import com.gigconnect.app.ui.theme.*
import com.gigconnect.app.viewmodel.WorkerViewModel

@Composable
fun WorkerHomeScreen(
    workerViewModel: WorkerViewModel,
    onJobsClick: () -> Unit,
    onEarningsClick: () -> Unit,
    onSkillsClick: () -> Unit,
    onProfileClick: () -> Unit,
    onJobDetailClick: (Job) -> Unit,
    onRoleSwitchClick: () -> Unit,
    onEquityClick: () -> Unit,
    onSafetyClick: () -> Unit
) {
    val jobs by workerViewModel.jobs.collectAsState()
    val isAvailable by workerViewModel.isAvailable.collectAsState()
    val weeklyEarnings by workerViewModel.weeklyEarnings.collectAsState()
    val todayJobs by workerViewModel.todayJobs.collectAsState()
    val equityBalance by workerViewModel.equityBalance.collectAsState()
    val worker = workerViewModel.worker

    val newJobs = jobs.filter { it.status == JobStatus.NEW }

    Scaffold(
        bottomBar = {
            WorkerBottomNav(
                onHome = {},
                onJobs = onJobsClick,
                onEarnings = onEarningsClick,
                onProfile = onProfileClick,
                selected = 0
            )
        },
        topBar = {
            Surface(
                color = GigSurface,
                shadowElevation = 4.dp
            ) {
                Column(modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp, vertical = 12.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                            Box(
                                modifier = Modifier
                                    .size(46.dp)
                                    .clip(CircleShape)
                                    .background(GigPrimaryBlue),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    worker.name.take(2).uppercase(),
                                    color = Color.White,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 18.sp
                                )
                            }
                            Column {
                                Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                                    Text(
                                        worker.name,
                                        style = MaterialTheme.typography.titleMedium,
                                        fontWeight = FontWeight.Bold,
                                        color = GigOnSurface
                                    )
                                    VerifiedBadge(isVerified = true)
                                }
                                Text(
                                    "${worker.primarySkill} • Pune Co-op Member",
                                    style = MaterialTheme.typography.bodySmall,
                                    color = GigSubtleText
                                )
                            }
                        }

                        Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                            // Demo role switcher button
                            IconButton(onClick = onRoleSwitchClick) {
                                Icon(Icons.Filled.SwapHoriz, "Switch Role", tint = GigPrimaryBlue)
                            }

                            // Availability switch
                            FilterChip(
                                selected = isAvailable,
                                onClick = { workerViewModel.toggleAvailability() },
                                label = {
                                    Text(
                                        if (isAvailable) "Online" else "Offline",
                                        fontSize = 12.sp,
                                        fontWeight = FontWeight.SemiBold
                                    )
                                },
                                leadingIcon = {
                                    Box(
                                        modifier = Modifier
                                            .size(8.dp)
                                            .clip(CircleShape)
                                            .background(if (isAvailable) GigSuccess else GigSubtleText)
                                    )
                                },
                                colors = FilterChipDefaults.filterChipColors(
                                    selectedContainerColor = GigSuccessContainer,
                                    selectedLabelColor = GigSuccess
                                )
                            )
                        }
                    }
                }
            }
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(GigBackground)
                .padding(padding),
            contentPadding = PaddingValues(bottom = 32.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                Spacer(Modifier.height(4.dp))
                DemoDataBadge(modifier = Modifier.padding(horizontal = 20.dp))
            }

            // Stats grid
            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Card(
                        modifier = Modifier
                            .weight(1f)
                            .clickable { onEarningsClick() },
                        shape = RoundedCornerShape(16.dp),
                        colors = CardDefaults.cardColors(containerColor = GigSoftTeal)
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Text("Week Earnings", style = MaterialTheme.typography.labelSmall, color = GigOnBackground)
                            Spacer(Modifier.height(4.dp))
                            Text("₹$weeklyEarnings", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold, color = GigPrimaryBlue)
                            Spacer(Modifier.height(2.dp))
                            Text("+18% vs last week", fontSize = 11.sp, color = GigSuccess, fontWeight = FontWeight.SemiBold)
                        }
                    }

                    Card(
                        modifier = Modifier
                            .weight(1f)
                            .clickable { onEquityClick() },
                        shape = RoundedCornerShape(16.dp),
                        colors = CardDefaults.cardColors(containerColor = GigSecondaryBlue)
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Text("Co-op Equity", style = MaterialTheme.typography.labelSmall, color = Color.White)
                            Spacer(Modifier.height(4.dp))
                            Text("₹$equityBalance", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold, color = Color.White)
                            Spacer(Modifier.height(2.dp))
                            Text("Ownership stake", fontSize = 11.sp, color = Color.White.copy(alpha = 0.8f))
                        }
                    }
                }
            }

            // Secondary stats: Today's Jobs & Rating
            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Card(
                        modifier = Modifier.weight(1f),
                        shape = RoundedCornerShape(12.dp),
                        colors = CardDefaults.cardColors(containerColor = GigSurface)
                    ) {
                        Row(
                            modifier = Modifier.padding(12.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(10.dp)
                        ) {
                            Text("💼", fontSize = 24.sp)
                            Column {
                                Text("$todayJobs Jobs", fontWeight = FontWeight.Bold, style = MaterialTheme.typography.titleSmall, color = GigOnBackground)
                                Text("Completed today", fontSize = 11.sp, color = GigSubtleText)
                            }
                        }
                    }

                    Card(
                        modifier = Modifier.weight(1f),
                        shape = RoundedCornerShape(12.dp),
                        colors = CardDefaults.cardColors(containerColor = GigSurface)
                    ) {
                        Row(
                            modifier = Modifier.padding(12.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(10.dp)
                        ) {
                            Text("⭐", fontSize = 24.sp)
                            Column {
                                Text("${worker.rating} Rating", fontWeight = FontWeight.Bold, style = MaterialTheme.typography.titleSmall, color = GigOnBackground)
                                Text("${worker.totalReviews} reviews", fontSize = 11.sp, color = GigSubtleText)
                            }
                        }
                    }
                }
            }

            // Urgent / New Job Requests Section
            item {
                Column(modifier = Modifier.padding(horizontal = 20.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            "Available Jobs (${newJobs.size})",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                            color = GigOnBackground
                        )
                        TextButton(onClick = onJobsClick) {
                            Text("View All", color = GigPrimaryBlue, fontWeight = FontWeight.SemiBold)
                        }
                    }
                }
            }

            if (newJobs.isEmpty()) {
                item {
                    EmptyState(
                        icon = "⏳",
                        title = "No pending jobs right now",
                        subtitle = "FairMatch will alert you as soon as a new request arrives.",
                        modifier = Modifier.padding(horizontal = 20.dp)
                    )
                }
            } else {
                items(newJobs) { job ->
                    Box(modifier = Modifier.padding(horizontal = 20.dp)) {
                        JobRequestCard(
                            job = job,
                            onAccept = { workerViewModel.acceptJob(job.id) },
                            onViewDetails = { onJobDetailClick(job) }
                        )
                    }
                }
            }

            // Quick Actions
            item {
                Text(
                    "Worker Hub & Cooperative",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = GigOnBackground,
                    modifier = Modifier.padding(horizontal = 20.dp, vertical = 4.dp)
                )
            }

            item {
                Column(
                    modifier = Modifier.padding(horizontal = 20.dp),
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                        HubActionCard(
                            title = "Co-op Equity",
                            subtitle = "₹$equityBalance accumulated",
                            icon = "🏛️",
                            color = GigSoftTeal,
                            contentColor = GigOnBackground,
                            modifier = Modifier.weight(1f),
                            onClick = onEquityClick
                        )
                        HubActionCard(
                            title = "Skill-Up AI",
                            subtitle = "Earn 32% more with AC cert",
                            icon = "🎓",
                            color = GigPrimaryBlue,
                            contentColor = Color.White,
                            modifier = Modifier.weight(1f),
                            onClick = onSkillsClick
                        )
                    }

                    Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                        HubActionCard(
                            title = "Digital Passport",
                            subtitle = "Verified credentials & ID",
                            icon = "🪪",
                            color = GigSurfaceVariant,
                            contentColor = GigOnBackground,
                            modifier = Modifier.weight(1f),
                            onClick = onProfileClick
                        )
                        HubActionCard(
                            title = "Safety Center",
                            subtitle = "SOS & 24/7 worker helpline",
                            icon = "🛡️",
                            color = GigErrorContainer,
                            contentColor = GigError,
                            modifier = Modifier.weight(1f),
                            onClick = onSafetyClick
                        )
                    }
                }
            }

            // Fair cooperative governance notice
            item {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp),
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = GigSurface),
                    border = BorderStroke(1.dp, GigOutlineVariant)
                ) {
                    Row(
                        modifier = Modifier.padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(14.dp)
                    ) {
                        Text("⚖️", fontSize = 28.sp)
                        Column {
                            Text(
                                "FairMatch™ Algorithm Active",
                                fontWeight = FontWeight.Bold,
                                style = MaterialTheme.typography.titleSmall,
                                color = GigOnBackground
                            )
                            Text(
                                "You are guaranteed fair rotation. No commission gouging or shadow banning.",
                                style = MaterialTheme.typography.bodySmall,
                                color = GigSubtleText
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun WorkerBottomNav(
    onHome: () -> Unit,
    onJobs: () -> Unit,
    onEarnings: () -> Unit,
    onProfile: () -> Unit,
    selected: Int
) {
    NavigationBar(containerColor = GigSurface) {
        listOf(
            Triple(Icons.Filled.Home, "Home", onHome),
            Triple(Icons.Filled.Work, "Jobs", onJobs),
            Triple(Icons.Filled.AccountBalanceWallet, "Earnings", onEarnings),
            Triple(Icons.Filled.Person, "Profile", onProfile)
        ).forEachIndexed { index, (icon, label, action) ->
            NavigationBarItem(
                selected = selected == index,
                onClick = action,
                icon = { Icon(icon, contentDescription = label) },
                label = { Text(label, fontWeight = if (selected == index) FontWeight.Bold else FontWeight.Normal) },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = GigPrimaryBlue,
                    selectedTextColor = GigPrimaryBlue,
                    indicatorColor = GigTealContainer
                )
            )
        }
    }
}

@Composable
private fun HubActionCard(
    title: String,
    subtitle: String,
    icon: String,
    color: Color,
    contentColor: Color,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .clickable { onClick() },
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(containerColor = color)
    ) {
        Column(modifier = Modifier.padding(14.dp)) {
            Text(icon, fontSize = 24.sp)
            Spacer(Modifier.height(8.dp))
            Text(title, fontWeight = FontWeight.Bold, style = MaterialTheme.typography.titleSmall, color = contentColor)
            Spacer(Modifier.height(2.dp))
            Text(subtitle, fontSize = 11.sp, color = contentColor.copy(alpha = 0.8f), maxLines = 1)
        }
    }
}
