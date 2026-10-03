package com.gigconnect.app.ui.components

import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.*
import com.gigconnect.app.data.model.*
import com.gigconnect.app.ui.theme.*

// ─────────────────────────────────────────────────────────────────────────────
//  1. Demo Data Badge
// ─────────────────────────────────────────────────────────────────────────────

@Composable
fun DemoDataBadge(modifier: Modifier = Modifier) {
    Surface(
        modifier = modifier,
        color = GigWarningContainer,
        shape = RoundedCornerShape(8.dp),
        border = BorderStroke(1.dp, GigWarning.copy(alpha = 0.3f))
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Text("⚡", fontSize = 11.sp)
            Text(
                text = "DEMO DATA",
                style = MaterialTheme.typography.labelSmall,
                color = OnGigWarningContainer,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

// ─────────────────────────────────────────────────────────────────────────────
//  2. Verified Badge
// ─────────────────────────────────────────────────────────────────────────────

@Composable
fun VerifiedBadge(
    modifier: Modifier = Modifier,
    small: Boolean = false,
    isVerified: Boolean = true
) {
    if (!isVerified) return
    Surface(
        modifier = modifier.wrapContentWidth(),
        color = GigVerifiedContainer,
        shape = RoundedCornerShape(if (small) 6.dp else 8.dp),
        border = BorderStroke(0.5.dp, GigVerifiedBlue.copy(alpha = 0.2f))
    ) {
        Row(
            modifier = Modifier.padding(
                horizontal = if (small) 8.dp else 10.dp,
                vertical = if (small) 3.dp else 4.dp
            ),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Icon(
                Icons.Filled.Verified,
                contentDescription = "Verified",
                tint = GigVerifiedBlue,
                modifier = Modifier.size(if (small) 13.dp else 15.dp)
            )
            Text(
                text = if (small) "Verified" else "Verified Profile",
                style = if (small) MaterialTheme.typography.labelSmall else MaterialTheme.typography.labelMedium,
                color = OnGigVerifiedContainer,
                fontWeight = FontWeight.Bold,
                maxLines = 1,
                softWrap = false
            )
        }
    }
}

// ─────────────────────────────────────────────────────────────────────────────
//  3. Star Rating Bar
// ─────────────────────────────────────────────────────────────────────────────

@Composable
fun RatingBar(
    rating: Float,
    reviewCount: Int = 0,
    modifier: Modifier = Modifier,
    showCount: Boolean = true
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Icon(
            Icons.Filled.Star,
            contentDescription = "Rating",
            tint = GigStarYellow,
            modifier = Modifier.size(16.dp)
        )
        Text(
            text = String.format("%.1f", rating),
            style = MaterialTheme.typography.labelLarge,
            fontWeight = FontWeight.Bold,
            color = GigOnSurface
        )
        if (showCount && reviewCount > 0) {
            Text(
                text = "($reviewCount)",
                style = MaterialTheme.typography.bodySmall,
                color = GigSubtleText
            )
        }
    }
}

// ─────────────────────────────────────────────────────────────────────────────
//  4. Worker Card
// ─────────────────────────────────────────────────────────────────────────────

@Composable
fun WorkerCard(
    worker: Worker,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = GigSurface),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp, pressedElevation = 4.dp),
        border = BorderStroke(1.dp, GigOutlineVariant)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(14.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Avatar
                Box(
                    modifier = Modifier
                        .size(54.dp)
                        .clip(CircleShape)
                        .background(GigPrimaryBlue),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = worker.name.split(" ").take(2).joinToString("") { it.first().toString() },
                        style = MaterialTheme.typography.titleMedium,
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                }

                Column(modifier = Modifier.weight(1f)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        Text(
                            text = worker.name,
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                            color = GigOnSurface,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            modifier = Modifier.weight(1f, fill = false)
                        )
                        if (worker.isVerified) {
                            VerifiedBadge(small = true)
                        }
                    }
                    Spacer(Modifier.height(2.dp))
                    Text(
                        text = worker.primarySkill,
                        style = MaterialTheme.typography.bodyMedium,
                        color = GigSubtleText,
                        fontWeight = FontWeight.Medium
                    )
                }

                // Price Badge
                Surface(
                    color = GigTealContainer,
                    shape = RoundedCornerShape(10.dp)
                ) {
                    Column(
                        modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp),
                        horizontalAlignment = Alignment.End
                    ) {
                        Text(
                            text = "₹${worker.basePrice}+",
                            style = MaterialTheme.typography.titleSmall,
                            fontWeight = FontWeight.ExtraBold,
                            color = OnGigTealContainer
                        )
                        Text(
                            text = "per job",
                            style = MaterialTheme.typography.labelSmall,
                            color = OnGigTealContainer.copy(alpha = 0.8f),
                            fontSize = 10.sp
                        )
                    }
                }
            }

            Spacer(Modifier.height(14.dp))
            HorizontalDivider(color = GigOutlineVariant)
            Spacer(Modifier.height(12.dp))

            // Stats row
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                RatingBar(worker.rating, worker.totalReviews, showCount = true)
                InfoPill(icon = "📍", text = "${worker.distanceKm} km away")
                InfoPill(icon = "✅", text = "${worker.completedJobs} jobs")
            }

            Spacer(Modifier.height(14.dp))

            Button(
                onClick = onClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = GigPrimaryBlue,
                    contentColor = Color.White
                ),
                shape = RoundedCornerShape(12.dp),
                elevation = ButtonDefaults.buttonElevation(defaultElevation = 0.dp)
            ) {
                Text(
                    text = "View Profile & Book",
                    style = MaterialTheme.typography.labelLarge,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

@Composable
private fun InfoPill(icon: String, text: String) {
    Surface(
        color = GigTealContainer,
        shape = RoundedCornerShape(8.dp)
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            Text(icon, fontSize = 11.sp)
            Text(
                text = text,
                style = MaterialTheme.typography.labelSmall,
                color = OnGigTealContainer,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

// ─────────────────────────────────────────────────────────────────────────────
//  5. Service Category Card
// ─────────────────────────────────────────────────────────────────────────────

@Composable
fun ServiceCategoryCard(
    service: ServiceCategory,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .width(96.dp)
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = GigSurface),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        border = BorderStroke(1.dp, GigOutlineVariant)
    ) {
        Column(
            modifier = Modifier.padding(vertical = 14.dp, horizontal = 8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .clip(CircleShape)
                    .background(GigTealContainer),
                contentAlignment = Alignment.Center
            ) {
                Text(service.icon, fontSize = 24.sp)
            }
            Spacer(Modifier.height(8.dp))
            Text(
                text = service.name,
                style = MaterialTheme.typography.labelMedium,
                color = GigOnSurface,
                fontWeight = FontWeight.SemiBold,
                textAlign = TextAlign.Center,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

// ─────────────────────────────────────────────────────────────────────────────
//  6. Price Breakdown Card
// ─────────────────────────────────────────────────────────────────────────────

@Composable
fun PriceBreakdownCard(pricing: PricingBreakdown, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = GigSurface),
        border = BorderStroke(1.dp, GigOutlineVariant),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
    ) {
        Column(modifier = Modifier.padding(18.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    "Transparent Pricing",
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.Bold,
                    color = GigOnSurface
                )
                Surface(
                    color = GigTealContainer,
                    shape = RoundedCornerShape(6.dp)
                ) {
                    Text(
                        "100% FAIR",
                        style = MaterialTheme.typography.labelSmall,
                        color = OnGigTealContainer,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                    )
                }
            }
            Spacer(Modifier.height(12.dp))
            PriceLine("Service Base Price", "₹${pricing.servicePrice}")
            PriceLine("Platform Operations (10%)", "₹${pricing.platformContribution}", isSubtle = true)
            PriceLine("Worker Direct Payout", "₹${pricing.workerEarnings}", highlight = true)
            PriceLine("Cooperative Welfare Fund (4%)", "₹${pricing.welfareContribution}", isSubtle = true)
            HorizontalDivider(modifier = Modifier.padding(vertical = 12.dp), color = GigOutlineVariant)
            PriceLine("Total Amount", "₹${pricing.total}", isBold = true)
        }
    }
}

@Composable
private fun PriceLine(
    label: String,
    value: String,
    isSubtle: Boolean = false,
    highlight: Boolean = false,
    isBold: Boolean = false
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = label,
            style = if (isBold) MaterialTheme.typography.titleMedium else MaterialTheme.typography.bodyMedium,
            color = if (isBold) GigOnSurface else if (isSubtle) GigSubtleText else GigOnSurfaceVariant,
            fontWeight = if (isBold) FontWeight.Bold else FontWeight.Medium
        )
        Text(
            text = value,
            style = if (isBold) MaterialTheme.typography.titleMedium else MaterialTheme.typography.bodyMedium,
            fontWeight = if (isBold || highlight) FontWeight.Bold else FontWeight.SemiBold,
            color = if (highlight) GigPrimaryBlue else if (isBold) GigOnSurface else GigOnSurface
        )
    }
}

// ─────────────────────────────────────────────────────────────────────────────
//  7. Skeleton Shimmer Loaders
// ─────────────────────────────────────────────────────────────────────────────

@Composable
fun SkeletonBox(modifier: Modifier = Modifier, shape: Shape = RoundedCornerShape(8.dp)) {
    val infiniteTransition = rememberInfiniteTransition(label = "skeleton")
    val alpha by infiniteTransition.animateFloat(
        initialValue = 0.4f,
        targetValue = 0.8f,
        animationSpec = infiniteRepeatable(
            animation = tween(800, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "alpha"
    )
    Box(
        modifier = modifier
            .clip(shape)
            .background(GigOutlineVariant.copy(alpha = alpha))
    )
}

@Composable
fun WorkerCardSkeleton() {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(2.dp),
        colors = CardDefaults.cardColors(containerColor = GigSurface),
        border = BorderStroke(1.dp, GigOutlineVariant)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(horizontalArrangement = Arrangement.spacedBy(14.dp)) {
                SkeletonBox(modifier = Modifier.size(54.dp), shape = CircleShape)
                Column(modifier = Modifier.weight(1f)) {
                    SkeletonBox(modifier = Modifier.fillMaxWidth(0.6f).height(18.dp))
                    Spacer(Modifier.height(8.dp))
                    SkeletonBox(modifier = Modifier.fillMaxWidth(0.4f).height(14.dp))
                }
            }
            Spacer(Modifier.height(14.dp))
            SkeletonBox(modifier = Modifier.fillMaxWidth().height(48.dp), shape = RoundedCornerShape(12.dp))
        }
    }
}

// ─────────────────────────────────────────────────────────────────────────────
//  8. Empty State & Error State
// ─────────────────────────────────────────────────────────────────────────────

@Composable
fun EmptyState(
    icon: String = "📭",
    title: String,
    subtitle: String = "",
    actionLabel: String? = null,
    onAction: (() -> Unit)? = null,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Box(
            modifier = Modifier
                .size(80.dp)
                .clip(CircleShape)
                .background(GigTealContainer),
            contentAlignment = Alignment.Center
        ) {
            Text(icon, fontSize = 36.sp)
        }
        Spacer(Modifier.height(16.dp))
        Text(
            text = title,
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
            color = GigOnSurface,
            textAlign = TextAlign.Center
        )
        if (subtitle.isNotEmpty()) {
            Spacer(Modifier.height(8.dp))
            Text(
                text = subtitle,
                style = MaterialTheme.typography.bodyMedium,
                color = GigSubtleText,
                textAlign = TextAlign.Center
            )
        }
        if (actionLabel != null && onAction != null) {
            Spacer(Modifier.height(24.dp))
            Button(
                onClick = onAction,
                colors = ButtonDefaults.buttonColors(containerColor = GigPrimaryBlue),
                shape = RoundedCornerShape(12.dp),
                contentPadding = PaddingValues(horizontal = 24.dp, vertical = 12.dp)
            ) {
                Text(actionLabel, color = Color.White, fontWeight = FontWeight.Bold)
            }
        }
    }
}

@Composable
fun ErrorState(onRetry: () -> Unit, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Box(
            modifier = Modifier
                .size(80.dp)
                .clip(CircleShape)
                .background(GigErrorContainer),
            contentAlignment = Alignment.Center
        ) {
            Text("⚠️", fontSize = 36.sp)
        }
        Spacer(Modifier.height(16.dp))
        Text(
            "Connection Interrupted",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
            color = GigOnSurface
        )
        Spacer(Modifier.height(8.dp))
        Text(
            "Please check your internet connection and try again.",
            style = MaterialTheme.typography.bodyMedium,
            color = GigSubtleText,
            textAlign = TextAlign.Center
        )
        Spacer(Modifier.height(24.dp))
        Button(
            onClick = onRetry,
            colors = ButtonDefaults.buttonColors(containerColor = GigPrimaryBlue),
            shape = RoundedCornerShape(12.dp)
        ) {
            Icon(Icons.Filled.Refresh, contentDescription = null, tint = Color.White, modifier = Modifier.size(18.dp))
            Spacer(Modifier.width(8.dp))
            Text("Try Again", color = Color.White, fontWeight = FontWeight.Bold)
        }
    }
}

// ─────────────────────────────────────────────────────────────────────────────
//  9. Offline Banner
// ─────────────────────────────────────────────────────────────────────────────

@Composable
fun OfflineBanner(modifier: Modifier = Modifier) {
    Surface(
        modifier = modifier.fillMaxWidth(),
        color = GigWarningContainer,
        shadowElevation = 2.dp
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Icon(Icons.Filled.WifiOff, contentDescription = null, tint = OnGigWarningContainer, modifier = Modifier.size(18.dp))
            Text(
                "You're offline — showing cached data",
                style = MaterialTheme.typography.labelMedium,
                color = OnGigWarningContainer,
                fontWeight = FontWeight.SemiBold
            )
        }
    }
}

// ─────────────────────────────────────────────────────────────────────────────
//  10. Progress Ring
// ─────────────────────────────────────────────────────────────────────────────

@Composable
fun ProgressRing(
    progress: Float,
    size: Dp = 80.dp,
    strokeWidth: Dp = 8.dp,
    color: Color = GigPrimaryBlue,
    label: String = "",
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier.size(size), contentAlignment = Alignment.Center) {
        Canvas(modifier = Modifier.size(size)) {
            drawArc(
                color = color.copy(alpha = 0.15f),
                startAngle = -90f,
                sweepAngle = 360f,
                useCenter = false,
                style = Stroke(width = strokeWidth.toPx(), cap = StrokeCap.Round)
            )
            drawArc(
                color = color,
                startAngle = -90f,
                sweepAngle = 360f * progress.coerceIn(0f, 1f),
                useCenter = false,
                style = Stroke(width = strokeWidth.toPx(), cap = StrokeCap.Round)
            )
        }
        if (label.isNotEmpty()) {
            Text(
                text = label,
                style = MaterialTheme.typography.labelMedium,
                fontWeight = FontWeight.ExtraBold,
                color = color
            )
        }
    }
}

// ─────────────────────────────────────────────────────────────────────────────
//  11. Top App Bar
// ─────────────────────────────────────────────────────────────────────────────

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GigTopBar(
    title: String,
    subtitle: String = "",
    onBack: (() -> Unit)? = null,
    actions: @Composable RowScope.() -> Unit = {}
) {
    TopAppBar(
        title = {
            Column {
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                if (subtitle.isNotEmpty()) {
                    Text(
                        text = subtitle,
                        style = MaterialTheme.typography.bodySmall,
                        color = Color.White.copy(alpha = 0.9f),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }
        },
        navigationIcon = {
            if (onBack != null) {
                IconButton(onClick = onBack) {
                    Icon(
                        Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Back",
                        tint = Color.White
                    )
                }
            }
        },
        actions = actions,
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = GigPrimaryBlue,
            titleContentColor = Color.White,
            navigationIconContentColor = Color.White,
            actionIconContentColor = Color.White
        )
    )
}

// ─────────────────────────────────────────────────────────────────────────────
//  12. Accessible Status Chip
// ─────────────────────────────────────────────────────────────────────────────

@Composable
fun StatusChip(status: String, modifier: Modifier = Modifier) {
    val (bg, fg) = when (status.uppercase().replace(" ", "_")) {
        "VERIFIED", "COMPLETED", "RESOLVED", "PASSED" -> GigSuccessContainer to OnGigSuccessContainer
        "PENDING", "UNDER_REVIEW", "SCHEDULED" -> GigWarningContainer to OnGigWarningContainer
        "NEEDS_REVIEW", "CANCELLED", "HIGH", "CRITICAL" -> GigErrorContainer to OnGigErrorContainer
        "ACTIVE", "IN_PROGRESS", "ACCEPTED", "CONFIRMED" -> GigTealContainer to OnGigTealContainer
        "MEDIUM" -> GigWarningContainer to OnGigWarningContainer
        "LOW" -> GigSuccessContainer to OnGigSuccessContainer
        else -> GigOutlineVariant to GigOnSurface
    }

    Surface(
        modifier = modifier,
        color = bg,
        shape = RoundedCornerShape(20.dp),
        border = BorderStroke(0.5.dp, fg.copy(alpha = 0.2f))
    ) {
        Text(
            text = status,
            style = MaterialTheme.typography.labelSmall,
            fontWeight = FontWeight.Bold,
            color = fg,
            maxLines = 1,
            softWrap = false,
            modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp)
        )
    }
}

@Composable
fun StatusChip(status: BookingStatus, modifier: Modifier = Modifier) {
    StatusChip(status = status.name, modifier = modifier)
}
