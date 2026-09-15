package com.gigconnect.app.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
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
import com.gigconnect.app.ui.theme.*

// ─────────────────────────────────────────────────────────────────────────────
//  Demo Role Switcher Bottom Sheet
// ─────────────────────────────────────────────────────────────────────────────

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DemoRoleSwitcher(
    currentRole: UserRole,
    onRoleSelected: (UserRole) -> Unit,
    onDismiss: () -> Unit
) {
    ModalBottomSheet(
        onDismissRequest = onDismiss,
        shape = RoundedCornerShape(topStart = 28.dp, topEnd = 28.dp),
        containerColor = GigSurface,
        dragHandle = { BottomSheetDefaults.DragHandle(color = GigOutline) }
    ) {
        Column(
            modifier = Modifier
                .padding(horizontal = 24.dp)
                .padding(bottom = 36.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Demo Role Switcher",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    color = GigOnSurface
                )
                DemoDataBadge()
            }
            Text(
                text = "Switch between personas to explore the complete GigConnect cooperative ecosystem.",
                style = MaterialTheme.typography.bodyMedium,
                color = GigSubtleText
            )
            Spacer(Modifier.height(20.dp))

            Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                RoleSwitcherCard(
                    emoji = "👤",
                    title = "Service Seeker",
                    subtitle = "Book verified workers, track real-time ETA, and review transparent pricing",
                    isSelected = currentRole == UserRole.SEEKER,
                    color = GigTeal
                ) {
                    onRoleSelected(UserRole.SEEKER)
                    onDismiss()
                }

                RoleSwitcherCard(
                    emoji = "🔧",
                    title = "Skilled Worker",
                    subtitle = "Accept fair-allocation jobs, track equity wallet, access welfare & AI skill-up",
                    isSelected = currentRole == UserRole.WORKER,
                    color = GigSaffron
                ) {
                    onRoleSelected(UserRole.WORKER)
                    onDismiss()
                }

                RoleSwitcherCard(
                    emoji = "🏛️",
                    title = "Federation Admin",
                    subtitle = "Manage cooperative governance, AI demand forecasting & digital panchayat",
                    isSelected = currentRole == UserRole.ADMIN,
                    color = GigTealDark
                ) {
                    onRoleSelected(UserRole.ADMIN)
                    onDismiss()
                }
            }
        }
    }
}

@Composable
private fun RoleSwitcherCard(
    emoji: String,
    title: String,
    subtitle: String,
    isSelected: Boolean,
    color: Color,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = if (isSelected) GigTealContainer.copy(alpha = 0.6f) else GigSurfaceVariant
        ),
        border = BorderStroke(
            width = if (isSelected) 1.5.dp else 1.dp,
            color = if (isSelected) color else GigOutlineVariant
        )
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(46.dp)
                    .clip(CircleShape)
                    .background(color.copy(alpha = 0.15f)),
                contentAlignment = Alignment.Center
            ) {
                Text(emoji, fontSize = 22.sp)
            }
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.Bold,
                    color = GigOnSurface
                )
                Spacer(Modifier.height(2.dp))
                Text(
                    text = subtitle,
                    style = MaterialTheme.typography.bodySmall,
                    color = GigSubtleText
                )
            }
            if (isSelected) {
                Icon(
                    Icons.Filled.CheckCircle,
                    contentDescription = "Selected",
                    tint = color,
                    modifier = Modifier.size(24.dp)
                )
            }
        }
    }
}

// ─────────────────────────────────────────────────────────────────────────────
//  Demo Location Switcher Bottom Sheet
// ─────────────────────────────────────────────────────────────────────────────

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DemoLocationSwitcher(
    cities: List<DemoCity>,
    selectedCity: DemoCity,
    onCitySelected: (DemoCity) -> Unit,
    onDismiss: () -> Unit
) {
    ModalBottomSheet(
        onDismissRequest = onDismiss,
        shape = RoundedCornerShape(topStart = 28.dp, topEnd = 28.dp),
        containerColor = GigSurface,
        dragHandle = { BottomSheetDefaults.DragHandle(color = GigOutline) }
    ) {
        Column(
            modifier = Modifier
                .padding(horizontal = 24.dp)
                .padding(bottom = 36.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Select Service City",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    color = GigOnSurface
                )
                DemoDataBadge()
            }
            Text(
                text = "Switching city updates localized workers, demand trends, and cooperative networks.",
                style = MaterialTheme.typography.bodyMedium,
                color = GigSubtleText
            )
            Spacer(Modifier.height(16.dp))

            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                items(cities) { city ->
                    val isSelected = city.name == selectedCity.name
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                onCitySelected(city)
                                onDismiss()
                            },
                        shape = RoundedCornerShape(14.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = if (isSelected) GigTealContainer else GigSurfaceVariant
                        ),
                        border = BorderStroke(
                            width = if (isSelected) 1.5.dp else 1.dp,
                            color = if (isSelected) GigTeal else GigOutlineVariant
                        )
                    ) {
                        Row(
                            modifier = Modifier.padding(14.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(12.dp)
                        ) {
                            Text("🏙️", fontSize = 24.sp)
                            Column(modifier = Modifier.weight(1f)) {
                                Text(
                                    text = city.name,
                                    style = MaterialTheme.typography.titleSmall,
                                    fontWeight = FontWeight.Bold,
                                    color = if (isSelected) GigTealDark else GigOnSurface
                                )
                                Text(
                                    text = "${city.locality}, ${city.state} • ${city.primaryLanguages.take(2).joinToString(", ")}",
                                    style = MaterialTheme.typography.bodySmall,
                                    color = if (isSelected) OnGigTealContainer.copy(alpha = 0.8f) else GigSubtleText
                                )
                            }
                            if (isSelected) {
                                Icon(
                                    Icons.Filled.CheckCircle,
                                    contentDescription = "Selected",
                                    tint = GigTeal,
                                    modifier = Modifier.size(22.dp)
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
