package dev.filinhat.bikepressurecalc.presentation.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

private val LightColorScheme = lightColorScheme(
    primary = PrimaryColors.Primary,
    onPrimary = PrimaryColors.OnPrimary,
    primaryContainer = PrimaryColors.PrimaryContainer,
    onPrimaryContainer = PrimaryColors.OnPrimaryContainer,
    secondary = SecondaryColors.Secondary,
    onSecondary = SecondaryColors.OnSecondary,
    secondaryContainer = SecondaryColors.SecondaryContainer,
    onSecondaryContainer = SecondaryColors.OnSecondaryContainer,
    tertiary = TertiaryColors.Tertiary,
    onTertiary = TertiaryColors.OnTertiary,
    tertiaryContainer = TertiaryColors.TertiaryContainer,
    onTertiaryContainer = TertiaryColors.OnTertiaryContainer,
    error = ErrorColors.Error,
    onError = ErrorColors.OnError,
    errorContainer = ErrorColors.ErrorContainer,
    onErrorContainer = ErrorColors.OnErrorContainer,
    background = NeutralColors.Surface,
    onBackground = NeutralColors.OnSurface,
    surface = NeutralColors.Surface,
    onSurface = NeutralColors.OnSurface,
    surfaceVariant = NeutralColors.Surface,
    onSurfaceVariant = NeutralVariantColors.OnSurfaceVariant,
    outline = NeutralVariantColors.Outline,
    outlineVariant = NeutralVariantColors.OutlineVariant,
    scrim = NeutralColors.Scrim,
    inverseSurface = NeutralColors.InverseSurface,
    inverseOnSurface = NeutralColors.InverseOnSurface,
    inversePrimary = PrimaryColors.InversePrimary,
    surfaceDim = NeutralColors.SurfaceDim,
    surfaceBright = NeutralColors.SurfaceBright,
    surfaceContainerLowest = NeutralColors.SurfaceContainerLowest,
    surfaceContainerLow = NeutralColors.SurfaceContainerLow,
    surfaceContainer = NeutralColors.SurfaceContainer,
    surfaceContainerHigh = NeutralColors.SurfaceContainerHigh,
    surfaceContainerHighest = NeutralColors.SurfaceContainerHighest,
    surfaceTint = PrimaryColors.Primary,
)

private val DarkColorScheme = darkColorScheme(
    primary = PrimaryColors.InversePrimary,
    secondary = SecondaryColors.Secondary,
    tertiary = TertiaryColors.Tertiary
)

@Composable
fun BikePressureCalcTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}
