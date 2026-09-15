package com.gigconnect.app.ui.seeker

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.*
import com.gigconnect.app.data.model.Worker
import com.gigconnect.app.ui.components.*
import com.gigconnect.app.ui.theme.*
import com.gigconnect.app.viewmodel.SeekerViewModel

@Composable
fun WorkerListScreen(
    serviceId: String,
    cityName: String,
    seekerViewModel: SeekerViewModel,
    onWorkerSelected: (Worker) -> Unit,
    onBack: () -> Unit
) {
    val workers by seekerViewModel.workers.collectAsState()
    val isLoading by seekerViewModel.isLoadingWorkers.collectAsState()
    val service by seekerViewModel.selectedService.collectAsState()

    LaunchedEffect(serviceId, cityName) {
        val svc = com.gigconnect.app.data.demo.DemoDataProvider.serviceCategories.find { it.id == serviceId }
        if (svc != null) seekerViewModel.selectService(svc, cityName)
    }

    Scaffold(
        topBar = {
            GigTopBar(
                title = service?.name ?: "Workers",
                subtitle = "Verified workers in $cityName",
                onBack = onBack
            )
        }
    ) { padding ->
        Column(modifier = Modifier.fillMaxSize().padding(padding)) {
            // Filter row
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                DemoDataBadge()
                Spacer(Modifier.weight(1f))
                Surface(
                    color = GigTealContainer,
                    shape = RoundedCornerShape(20.dp),
                    modifier = Modifier.clickable {}
                ) {
                    Row(
                        modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        Icon(Icons.Filled.FilterList, null, tint = GigTeal, modifier = Modifier.size(14.dp))
                        Text("Filter", style = MaterialTheme.typography.labelMedium, color = GigTeal)
                    }
                }
                Surface(
                    color = GigTealContainer,
                    shape = RoundedCornerShape(20.dp),
                    modifier = Modifier.clickable {}
                ) {
                    Row(
                        modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        Icon(Icons.Filled.Sort, null, tint = GigTeal, modifier = Modifier.size(14.dp))
                        Text("Sort", style = MaterialTheme.typography.labelMedium, color = GigTeal)
                    }
                }
            }

            if (isLoading) {
                LazyColumn(
                    contentPadding = PaddingValues(16.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    items(4) { WorkerCardSkeleton() }
                }
            } else if (workers.isEmpty()) {
                EmptyState(
                    icon = "🔍",
                    title = "No workers found",
                    subtitle = "Try a different service or location",
                    modifier = Modifier.fillMaxSize()
                )
            } else {
                LazyColumn(
                    contentPadding = PaddingValues(16.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    item {
                        Text(
                            "${workers.size} verified workers available",
                            style = MaterialTheme.typography.bodyMedium,
                            color = GigSubtleText,
                            fontWeight = FontWeight.Medium
                        )
                    }
                    items(workers) { worker ->
                        WorkerCard(worker = worker, onClick = { onWorkerSelected(worker) })
                    }
                }
            }
        }
    }
}
