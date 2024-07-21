package dev.filinhat.bikepressurecalc.presentation.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

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

@Composable
fun ApplicationTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = LightColorScheme,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}
