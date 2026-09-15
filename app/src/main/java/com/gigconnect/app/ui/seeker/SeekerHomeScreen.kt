package com.gigconnect.app.ui.seeker

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
import com.gigconnect.app.data.demo.DemoDataProvider
import com.gigconnect.app.data.model.*
import com.gigconnect.app.ui.components.*
import com.gigconnect.app.ui.theme.*
import com.gigconnect.app.viewmodel.SeekerViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SeekerHomeScreen(
    cityName: String,
    seekerViewModel: SeekerViewModel,
    onServiceSelected: (ServiceCategory) -> Unit,
    onWorkerTapped: (Worker) -> Unit,
    onBookingsClick: () -> Unit,
    onMessagesClick: () -> Unit,
    onProfileClick: () -> Unit,
    onRoleSwitchClick: () -> Unit,
    onLocationClick: () -> Unit,
    isOffline: Boolean
) {
    val workers by seekerViewModel.workers.collectAsState()

    // Load featured workers on home
    LaunchedEffect(cityName) {
        seekerViewModel.selectService(DemoDataProvider.serviceCategories.first(), cityName)
    }

    Scaffold(
        bottomBar = {
            SeekerBottomNav(
                onHome = {},
                onBookings = onBookingsClick,
                onMessages = onMessagesClick,
                onProfile = onProfileClick,
                selected = 0
            )
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(GigBackground)
                .padding(padding),
            verticalArrangement = Arrangement.spacedBy(0.dp)
        ) {
            // Header
            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Brush.verticalGradient(listOf(GigPrimaryBlue, GigPrimaryBlue)))
                ) {
                    Canvas(Modifier.fillMaxWidth().height(180.dp)) {
                        drawCircle(Color.White.copy(0.04f), 160.dp.toPx(), Offset(size.width * 0.85f, 0f))
                    }
                    Column(modifier = Modifier.padding(start = 20.dp, end = 20.dp, top = 20.dp, bottom = 24.dp)) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column {
                                Text("Good morning 👋", style = MaterialTheme.typography.bodyMedium, color = Color.White.copy(0.8f))
                                Text("Demo User", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold, color = Color.White)
                            }
                            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                                IconButton(onClick = onRoleSwitchClick) {
                                    Icon(Icons.Filled.SwapHoriz, "Switch Role", tint = Color.White)
                                }
                                IconButton(onClick = {}) {
                                    Icon(Icons.Filled.Notifications, "Notifications", tint = Color.White)
                                }
                            }
                        }
                        Spacer(Modifier.height(12.dp))
                        // Location chip
                        Surface(
                            color = Color.White.copy(0.15f),
                            shape = RoundedCornerShape(20.dp),
                            modifier = Modifier.clickable(onClick = onLocationClick)
                        ) {
                            Row(
                                modifier = Modifier.padding(horizontal = 12.dp, vertical = 7.dp),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(6.dp)
                            ) {
                                Icon(Icons.Filled.LocationOn, null, tint = GigSoftTeal, modifier = Modifier.size(16.dp))
                                Text(cityName, style = MaterialTheme.typography.bodyMedium, color = Color.White, fontWeight = FontWeight.SemiBold)
                                Icon(Icons.Filled.KeyboardArrowDown, null, tint = Color.White, modifier = Modifier.size(16.dp))
                            }
                        }
                        Spacer(Modifier.height(16.dp))
                        // Search bar
                        Surface(
                            color = Color.White,
                            shape = RoundedCornerShape(14.dp),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Row(
                                modifier = Modifier.padding(horizontal = 16.dp, vertical = 14.dp),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(10.dp)
                            ) {
                                Icon(Icons.Filled.Search, null, tint = GigPlaceholderText, modifier = Modifier.size(20.dp))
                                Text("Search plumber, electrician...", style = MaterialTheme.typography.bodyMedium, color = GigPlaceholderText)
                            }
                        }
                    }
                }
            }

            if (isOffline) item { OfflineBanner() }

            // Emergency Card
            item {
                Spacer(Modifier.height(16.dp))
                EmergencyCard(onClick = { onServiceSelected(DemoDataProvider.serviceCategories.first()) })
                Spacer(Modifier.height(4.dp))
            }

            // Service Categories
            item {
                Row(
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text("Services", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold, color = GigOnBackground)
                    Text("See all →", style = MaterialTheme.typography.bodySmall, color = GigPrimaryBlue)
                }
                Spacer(Modifier.height(12.dp))
            }

            item {
                LazyRow(
                    contentPadding = PaddingValues(horizontal = 20.dp),
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    items(DemoDataProvider.serviceCategories) { service ->
                        ServiceCategoryCard(service = service, onClick = { onServiceSelected(service) })
                    }
                }
                Spacer(Modifier.height(20.dp))
            }

            // Top Rated Workers
            item {
                Row(
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text("Top Rated Workers", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold, color = GigOnBackground)
                    DemoDataBadge()
                }
                Spacer(Modifier.height(12.dp))
            }

            val displayWorkers = DemoDataProvider.getWorkersForCity(cityName)
            if (displayWorkers.isEmpty()) {
                item {
                    repeat(3) { WorkerCardSkeleton() }
                }
            } else {
                items(displayWorkers) { worker ->
                    Box(modifier = Modifier.padding(horizontal = 20.dp, vertical = 6.dp)) {
                        WorkerCard(worker = worker, onClick = { onWorkerTapped(worker) })
                    }
                }
            }

            item { Spacer(Modifier.height(24.dp)) }
        }
    }
}

@Composable
private fun EmergencyCard(onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = GigErrorContainer),
        border = BorderStroke(1.dp, GigError.copy(0.2f)),
        elevation = CardDefaults.cardElevation(2.dp)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(52.dp)
                    .clip(RoundedCornerShape(14.dp))
                    .background(GigError.copy(0.12f)),
                contentAlignment = Alignment.Center
            ) {
                Text("🚨", fontSize = 26.sp)
            }
            Column(modifier = Modifier.weight(1f)) {
                Text("Need help urgently?", style = MaterialTheme.typography.titleSmall, fontWeight = FontWeight.Bold, color = GigError)
                Text("Get a verified worker nearby", style = MaterialTheme.typography.bodySmall, color = GigSubtleText)
            }
            Button(
                onClick = onClick,
                colors = ButtonDefaults.buttonColors(containerColor = GigError),
                shape = RoundedCornerShape(10.dp),
                contentPadding = PaddingValues(horizontal = 14.dp, vertical = 8.dp)
            ) {
                Text("Book Now", style = MaterialTheme.typography.labelLarge, color = Color.White, fontWeight = FontWeight.Bold)
            }
        }
    }
}

@Composable
fun SeekerBottomNav(
    onHome: () -> Unit,
    onBookings: () -> Unit,
    onMessages: () -> Unit,
    onProfile: () -> Unit,
    selected: Int
) {
    NavigationBar(containerColor = GigSurface) {
        listOf(
            Triple(Icons.Filled.Home, "Home", onHome),
            Triple(Icons.Filled.BookOnline, "Bookings", onBookings),
            Triple(Icons.Filled.Chat, "Messages", onMessages),
            Triple(Icons.Filled.Person, "Profile", onProfile)
        ).forEachIndexed { index, (icon, label, action) ->
            NavigationBarItem(
                selected = selected == index,
                onClick = action,
                icon = { Icon(icon, label) },
                label = { Text(label) },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = GigPrimaryBlue,
                    selectedTextColor = GigPrimaryBlue,
                    indicatorColor = GigTealContainer
                )
            )
        }
    }
}
