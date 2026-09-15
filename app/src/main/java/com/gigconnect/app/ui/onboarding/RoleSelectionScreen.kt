package com.gigconnect.app.ui.onboarding

import androidx.compose.animation.*
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.*
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.*
import com.gigconnect.app.data.model.UserRole
import com.gigconnect.app.ui.theme.*

@Composable
fun RoleSelectionScreen(onRoleSelected: (UserRole) -> Unit) {
    var selectedRole by remember { mutableStateOf<UserRole?>(null) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(GigBackground)
    ) {
        // Hero Header
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Brush.verticalGradient(listOf(GigTealDark, GigTeal)))
                .statusBarsPadding()
        ) {
            Canvas(modifier = Modifier.fillMaxWidth().height(180.dp)) {
                drawCircle(
                    color = Color.White.copy(0.05f),
                    radius = 160.dp.toPx(),
                    center = Offset(size.width * 0.9f, size.height * 0.3f)
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp, vertical = 24.dp)
            ) {
                Text("👋", fontSize = 32.sp)
                Spacer(Modifier.height(8.dp))
                Text(
                    text = "Choose Your Role",
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
                Spacer(Modifier.height(4.dp))
                Text(
                    text = "Personalized experience for service seekers & skilled cooperative workers.",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.White.copy(0.85f)
                )
            }
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                Text(
                    text = "Select how you would like to proceed:",
                    style = MaterialTheme.typography.labelLarge,
                    color = GigOnSurfaceVariant,
                    fontWeight = FontWeight.SemiBold
                )

                RoleSelectionCard(
                    emoji = "🔍",
                    title = "I need a service",
                    subtitle = "Book verified local professionals with guaranteed transparent pricing",
                    features = listOf(
                        "Verified & background-checked workers",
                        "AI SmartMatch based on skills & location",
                        "Live GPS tracking & transparent pricing",
                        "Direct cashless UPI or Cash payment"
                    ),
                    color = GigTeal,
                    isSelected = selectedRole == UserRole.SEEKER,
                    onClick = { selectedRole = UserRole.SEEKER }
                )

                RoleSelectionCard(
                    emoji = "🔧",
                    title = "I provide services",
                    subtitle = "Join the cooperative, earn fair income, and build ownership equity",
                    features = listOf(
                        "FairMatch algorithm (no commission gouging)",
                        "Co-op Equity Wallet & pension contribution",
                        "Skill-Up AI certification & demand radar",
                        "24/7 Safety Center & emergency SOS"
                    ),
                    color = GigSaffron,
                    isSelected = selectedRole == UserRole.WORKER,
                    onClick = { selectedRole = UserRole.WORKER }
                )
            }

            Column(
                verticalArrangement = Arrangement.spacedBy(10.dp),
                modifier = Modifier.fillMaxWidth().navigationBarsPadding()
            ) {
                Button(
                    onClick = { selectedRole?.let { onRoleSelected(it) } },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(54.dp),
                    enabled = selectedRole != null,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = GigTeal,
                        disabledContainerColor = GigOutlineVariant,
                        disabledContentColor = GigSubtleText
                    ),
                    shape = RoundedCornerShape(16.dp),
                    elevation = ButtonDefaults.buttonElevation(defaultElevation = 2.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Text(
                            text = "Continue",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold
                        )
                        Icon(
                            Icons.AutoMirrored.Filled.ArrowForward,
                            contentDescription = null,
                            modifier = Modifier.size(18.dp)
                        )
                    }
                }

                TextButton(
                    onClick = { onRoleSelected(UserRole.ADMIN) },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = "🏛️ Enter as Federation Admin (Demo)",
                        color = GigTeal,
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }
        }
    }
}

@Composable
private fun RoleSelectionCard(
    emoji: String,
    title: String,
    subtitle: String,
    features: List<String>,
    color: Color,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    val borderColor = if (isSelected) color else GigOutlineVariant
    val bgColor = if (isSelected) color.copy(alpha = 0.07f) else GigSurface

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = bgColor),
        border = BorderStroke(if (isSelected) 2.dp else 1.dp, borderColor),
        elevation = CardDefaults.cardElevation(if (isSelected) 4.dp else 1.dp)
    ) {
        Column(modifier = Modifier.padding(18.dp)) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(14.dp),
                verticalAlignment = Alignment.Top
            ) {
                Box(
                    modifier = Modifier
                        .size(50.dp)
                        .clip(RoundedCornerShape(14.dp))
                        .background(color.copy(0.14f)),
                    contentAlignment = Alignment.Center
                ) {
                    Text(emoji, fontSize = 24.sp)
                }
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = title,
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = GigOnSurface
                    )
                    Spacer(Modifier.height(3.dp))
                    Text(
                        text = subtitle,
                        style = MaterialTheme.typography.bodySmall,
                        color = GigSubtleText
                    )
                }
                if (isSelected) {
                    Box(
                        modifier = Modifier
                            .size(24.dp)
                            .clip(CircleShape)
                            .background(color),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            Icons.Filled.Check,
                            contentDescription = "Selected",
                            tint = Color.White,
                            modifier = Modifier.size(16.dp)
                        )
                    }
                }
            }

            Spacer(Modifier.height(14.dp))
            HorizontalDivider(color = GigOutlineVariant.copy(alpha = 0.6f))
            Spacer(Modifier.height(10.dp))

            Column(verticalArrangement = Arrangement.spacedBy(5.dp)) {
                features.forEach { feature ->
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Text(
                            text = "✓",
                            color = color,
                            fontWeight = FontWeight.ExtraBold,
                            fontSize = 12.sp
                        )
                        Text(
                            text = feature,
                            style = MaterialTheme.typography.bodySmall,
                            color = GigOnSurfaceVariant,
                            fontWeight = FontWeight.Medium
                        )
                    }
                }
            }
        }
    }
}
