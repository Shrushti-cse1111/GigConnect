package com.gigconnect.app.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val GigLightColorScheme = lightColorScheme(
    primary = GigPrimaryBlue,
    onPrimary = GigSurface,
    primaryContainer = GigPrimaryContainer,
    onPrimaryContainer = OnGigPrimaryContainer,
    secondary = GigSecondaryBlue,
    onSecondary = GigOnBackground,
    secondaryContainer = GigTealContainer,
    onSecondaryContainer = OnGigTealContainer,
    tertiary = GigSoftTeal,
    background = GigBackground,
    onBackground = GigOnBackground,
    surface = GigSurface,
    onSurface = GigOnSurface,
    surfaceVariant = GigSurfaceVariant,
    onSurfaceVariant = GigOnSurfaceVariant,
    outline = GigOutline,
    outlineVariant = GigOutlineVariant,
    error = GigError,
    onError = GigSurface,
    errorContainer = GigErrorContainer,
    onErrorContainer = OnGigErrorContainer
)

private val GigDarkColorScheme = darkColorScheme(
    primary = GigPrimaryBlueDark,
    onPrimary = OnGigPrimaryContainer,
    primaryContainer = GigPrimaryBlue,
    onPrimaryContainer = OnGigPrimaryContainer,
    secondary = GigSecondaryBlue,
    onSecondary = GigOnBackgroundDark,
    secondaryContainer = GigSoftTeal,
    onSecondaryContainer = GigOnBackground,
    background = GigBackgroundDark,
    onBackground = GigOnBackgroundDark,
    surface = GigSurfaceDark,
    onSurface = GigOnSurfaceDark,
    surfaceVariant = GigSurfaceVariantDark,
    onSurfaceVariant = GigOnSurfaceVariantDark,
    outline = GigOutline,
    outlineVariant = GigOutlineVariant,
    error = GigError,
    onError = GigSurface,
    errorContainer = GigErrorContainer,
    onErrorContainer = OnGigErrorContainer
)

@Composable
fun GigConnectTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }
        darkTheme -> GigDarkColorScheme
        else -> GigLightColorScheme
    }

    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.primary.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = false
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = GigTypography,
        content = content
    )
}
