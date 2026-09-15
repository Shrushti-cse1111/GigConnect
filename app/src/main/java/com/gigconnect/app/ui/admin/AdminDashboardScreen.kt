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
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.*
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.*
import com.gigconnect.app.data.model.*
import com.gigconnect.app.ui.components.*
import com.gigconnect.app.ui.theme.*
import com.gigconnect.app.viewmodel.AdminViewModel

@Composable
fun AdminDashboardScreen(
    adminViewModel: AdminViewModel,
    onDemandClick: () -> Unit,
    onFairMatchClick: () -> Unit,
    onWorkersClick: () -> Unit,
    onWelfareClick: () -> Unit,
    onPanchayatClick: () -> Unit,
    onArchitectureClick: () -> Unit,
    onRoleSwitchClick: () -> Unit
) {
    val stats = adminViewModel.adminStats

    Scaffold(
        bottomBar = {
            NavigationBar(containerColor = GigSurface) {
                listOf(
                    Triple(Icons.Filled.Dashboard, "Dashboard") {},
                    Triple(Icons.Filled.People, "Workers", onWorkersClick),
                    Triple(Icons.Filled.BarChart, "Demand", onDemandClick),
                    Triple(Icons.Filled.AccountBalance, "Welfare", onWelfareClick),
                    Triple(Icons.Filled.HowToVote, "Panchayat", onPanchayatClick)
                ).forEachIndexed { i, (icon, label, action) ->
                    NavigationBarItem(selected = i == 0, onClick = action, icon = { Icon(icon, label) }, label = { Text(label, style = MaterialTheme.typography.labelSmall) },
                        colors = NavigationBarItemDefaults.colors(selectedIconColor = GigTeal, selectedTextColor = GigTeal, indicatorColor = GigTealContainer))
                }
            }
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier.fillMaxSize().background(GigBackground).padding(padding),
            verticalArrangement = Arrangement.spacedBy(0.dp)
        ) {
            item {
                Box(modifier = Modifier.fillMaxWidth().background(Brush.verticalGradient(listOf(GigTealDark, GigTeal)))) {
                    Canvas(Modifier.fillMaxWidth().height(220.dp)) {
                        drawCircle(Color.White.copy(0.05f), 160.dp.toPx(), Offset(size.width, size.height * 0.4f))
                        drawCircle(GigSaffron.copy(0.12f), 80.dp.toPx(), Offset(0f, size.height))
                    }
                    Column(modifier = Modifier.padding(20.dp)) {
                        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
                            Column {
                                Text("🏛️ Federation Admin", style = MaterialTheme.typography.bodyMedium, color = Color.White.copy(0.8f))
                                Text("GigConnect Dashboard", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.ExtraBold, color = Color.White)
                                Text("Pune Cooperative Federation", style = MaterialTheme.typography.bodySmall, color = Color.White.copy(0.7f))
                            }
                            IconButton(onClick = onRoleSwitchClick) {
                                Icon(Icons.Filled.SwapHoriz, "Switch Role", tint = Color.White)
                            }
                        }
                        Spacer(Modifier.height(12.dp))
                        DemoDataBadge()
                        Spacer(Modifier.height(16.dp))
                        LazyRow(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                            items(listOf(
                                Triple("👷 ${stats.activeWorkers}", "Active Workers", GigSuccess),
                                Triple("📋 ${stats.pendingVerification}", "Pending", GigSaffron),
                                Triple("✅ ${stats.jobsToday}", "Jobs Today", Color.White),
                                Triple("💰 ₹${stats.totalPayout}", "Payouts", GigSaffron)
                            )) { (v, l, c) ->
                                AdminStatCard(v, l, c)
                            }
                        }
                    }
                }
            }

            item {
                Spacer(Modifier.height(16.dp))
                Text("Feature Modules", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold, modifier = Modifier.padding(horizontal = 20.dp))
                Spacer(Modifier.height(12.dp))
            }

            item {
                Column(modifier = Modifier.padding(horizontal = 20.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
                    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                        AdminModuleCard("🧠 Demand Intelligence", "AI-powered demand forecasting & resource planning", GigTealContainer, Modifier.weight(1f), onDemandClick)
                        AdminModuleCard("⚖️ FairMatch Engine", "Equitable job allocation beyond ratings", GigSaffronContainer, Modifier.weight(1f), onFairMatchClick)
                    }
                    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                        AdminModuleCard("🛡️ Worker Welfare", "Insurance, grievances, welfare stats", Color(0xFFE8F5E9), Modifier.weight(1f), onWelfareClick)
                        AdminModuleCard("🗳️ Digital Panchayat", "Cooperative governance & voting", Color(0xFFE3F2FD), Modifier.weight(1f), onPanchayatClick)
                    }
                    AdminModuleCard("🏗️ App Architecture", "MVVM, Clean Architecture, tech stack", GigSurfaceVariant, Modifier.fillMaxWidth(), onArchitectureClick)
                }
                Spacer(Modifier.height(16.dp))
            }

            item {
                Text("Pending Actions", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold, modifier = Modifier.padding(horizontal = 20.dp))
                Spacer(Modifier.height(10.dp))
                Column(modifier = Modifier.padding(horizontal = 20.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
                    PendingActionCard("⏳ ${stats.pendingVerification} Worker verifications pending", GigSaffron, onWorkersClick)
                    PendingActionCard("🗳️ 2 Active cooperative votes ongoing", GigTeal, onPanchayatClick)
                    PendingActionCard("⚠️ 1 Grievance requires admin review", GigError, onWelfareClick)
                }
                Spacer(Modifier.height(24.dp))
            }
        }
    }
}

@Composable
private fun AdminStatCard(value: String, label: String, textColor: Color) {
    Surface(color = Color.White.copy(0.12f), shape = RoundedCornerShape(12.dp)) {
        Column(modifier = Modifier.padding(horizontal = 14.dp, vertical = 10.dp).width(IntrinsicSize.Min)) {
            Text(value, fontWeight = FontWeight.ExtraBold, color = textColor, style = MaterialTheme.typography.titleSmall)
            Text(label, style = MaterialTheme.typography.labelSmall, color = Color.White.copy(0.75f))
        }
    }
}

@Composable
private fun AdminModuleCard(title: String, subtitle: String, bgColor: Color, modifier: Modifier, onClick: () -> Unit) {
    Card(modifier = modifier.clickable(onClick = onClick).height(110.dp), shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(bgColor), elevation = CardDefaults.cardElevation(2.dp)) {
        Column(modifier = Modifier.padding(14.dp).fillMaxSize(), verticalArrangement = Arrangement.SpaceBetween) {
            Text(title, fontWeight = FontWeight.Bold, style = MaterialTheme.typography.labelLarge, color = GigOnSurface)
            Text(subtitle, style = MaterialTheme.typography.bodySmall, color = GigSubtleText)
        }
    }
}

@Composable
private fun PendingActionCard(text: String, color: Color, onClick: () -> Unit) {
    Card(modifier = Modifier.fillMaxWidth().clickable(onClick = onClick), shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(color.copy(0.08f)), border = BorderStroke(1.dp, color.copy(0.2f))) {
        Row(modifier = Modifier.padding(14.dp), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            Box(modifier = Modifier.size(8.dp).clip(CircleShape).background(color))
            Text(text, style = MaterialTheme.typography.bodyMedium, modifier = Modifier.weight(1f))
            Icon(Icons.Filled.ChevronRight, null, tint = color)
        }
    }
}
