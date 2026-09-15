package com.gigconnect.app.ui.onboarding

import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
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
import com.gigconnect.app.ui.theme.*
import kotlinx.coroutines.delay

@Composable
fun WelcomeScreen(
    onGetStarted: () -> Unit,
    onLogin: () -> Unit
) {
    var visible by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) {
        delay(150)
        visible = true
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(GigBackground)
    ) {
        // Decorative ambient background glow using new brand colors
        Canvas(modifier = Modifier.fillMaxSize()) {
            drawCircle(
                color = GigSecondaryBlue.copy(alpha = 0.08f),
                radius = 350.dp.toPx(),
                center = Offset(size.width * 1.1f, size.height * -0.05f)
            )
            drawCircle(
                color = GigSoftTeal.copy(alpha = 0.4f),
                radius = 200.dp.toPx(),
                center = Offset(size.width * -0.2f, size.height * 0.4f)
            )
            drawCircle(
                color = GigPrimaryBlue.copy(alpha = 0.05f),
                radius = 280.dp.toPx(),
                center = Offset(size.width * 0.9f, size.height * 0.9f)
            )
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .statusBarsPadding()
                .navigationBarsPadding()
                .padding(horizontal = 24.dp, vertical = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            // Top Badge
            AnimatedVisibility(
                visible = visible,
                enter = fadeIn() + slideInVertically { -30 }
            ) {
                Surface(
                    color = GigSurface,
                    shape = RoundedCornerShape(24.dp),
                    border = BorderStroke(1.dp, GigOutlineVariant),
                    shadowElevation = 2.dp
                ) {
                    Row(
                        modifier = Modifier.padding(horizontal = 14.dp, vertical = 6.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        Text("🇮🇳", fontSize = 14.sp)
                        Text(
                            text = "Pan-India Cooperative Gig Federation",
                            style = MaterialTheme.typography.labelMedium,
                            color = GigOnSurface,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }

            // Center Branding Hero
            AnimatedVisibility(
                visible = visible,
                enter = fadeIn(tween(600)) + slideInVertically(tween(600)) { 40 }
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    // Modern App Icon
                    Box(
                        modifier = Modifier
                            .size(92.dp)
                            .clip(RoundedCornerShape(26.dp))
                            .background(GigPrimaryBlue)
                            .border(2.dp, GigSoftTeal, RoundedCornerShape(26.dp)),
                        contentAlignment = Alignment.Center
                    ) {
                        Text("🔗", fontSize = 46.sp)
                    }

                    Spacer(Modifier.height(24.dp))

                    Text(
                        text = "GigConnect",
                        style = MaterialTheme.typography.displayMedium,
                        color = GigOnBackground
                    )

                    Spacer(Modifier.height(8.dp))

                    Text(
                        text = "Connect Skills. Create Opportunities.",
                        style = MaterialTheme.typography.headlineSmall,
                        color = GigSecondaryBlue,
                        textAlign = TextAlign.Center
                    )

                    Spacer(Modifier.height(16.dp))

                    Text(
                        text = "Empowering India's skilled workforce with fair job allocation, transparent pricing, and democratic cooperative ownership.",
                        style = MaterialTheme.typography.bodyLarge,
                        color = GigSubtleText,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(horizontal = 12.dp)
                    )

                    Spacer(Modifier.height(32.dp))

                    // Feature highlights row
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        modifier = Modifier.padding(horizontal = 4.dp)
                    ) {
                        FeaturePill("🛡️ Verified")
                        FeaturePill("🤖 AI Matched")
                        FeaturePill("⚖️ Fair Pay")
                        FeaturePill("🏛️ Co-op Equity")
                    }
                }
            }

            // Bottom Actions
            AnimatedVisibility(
                visible = visible,
                enter = fadeIn(tween(800)) + slideInVertically(tween(800)) { 50 }
            ) {
                Column(
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Button(
                        onClick = onGetStarted,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(54.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = GigPrimaryBlue,
                            contentColor = Color.White
                        ),
                        shape = RoundedCornerShape(12.dp),
                        elevation = ButtonDefaults.buttonElevation(defaultElevation = 2.dp)
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Text(
                                text = "Get Started",
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.Bold
                            )
                            Icon(
                                Icons.AutoMirrored.Filled.ArrowForward,
                                contentDescription = null,
                                modifier = Modifier.size(20.dp)
                            )
                        }
                    }

                    OutlinedButton(
                        onClick = onLogin,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(52.dp),
                        colors = ButtonDefaults.outlinedButtonColors(
                            contentColor = GigPrimaryBlue
                        ),
                        border = BorderStroke(1.5.dp, GigPrimaryBlue),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Text(
                            text = "Login to Account",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold
                        )
                    }

                    Text(
                        text = "Active in Pune, Bengaluru, Mumbai, Delhi-NCR & 8+ hubs",
                        style = MaterialTheme.typography.bodySmall,
                        color = GigSubtleText,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
        }
    }
}

@Composable
private fun FeaturePill(text: String) {
    Surface(
        color = GigSurface,
        shape = RoundedCornerShape(12.dp),
        border = BorderStroke(1.dp, GigOutlineVariant)
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.labelSmall,
            color = GigOnSurface,
            modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp)
        )
    }
}
