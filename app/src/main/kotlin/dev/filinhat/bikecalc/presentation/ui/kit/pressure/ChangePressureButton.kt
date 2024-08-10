package dev.filinhat.bikecalc.presentation.ui.kit.pressure

import android.content.res.Configuration
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.filin2hat.bikecalc.R
import dev.filinhat.bikecalc.common.enums.units.PressureUnits
import dev.filinhat.bikecalc.presentation.ui.theme.ApplicationTheme

@Suppress("ktlint:standard:property-naming")
private const val ButtonWidth = 75

@Composable
fun ChangePressureButton(
    pressureUnits: PressureUnits,
    modifier: Modifier = Modifier,
    onPressureChanged: (pressureUnits: PressureUnits) -> Unit,
) {
    ElevatedButton(
        onClick = {
            onPressureChanged(pressureUnits)
        },
        border =
            BorderStroke(
                width = 2.dp,
                color = MaterialTheme.colorScheme.primary,
            ),
        shape = MaterialTheme.shapes.medium,
        modifier = modifier.width(ButtonWidth.dp),
        colors =
            ButtonDefaults.elevatedButtonColors(),
    ) {
        Text(
            text =
                when (pressureUnits) {
                    PressureUnits.KPa -> stringResource(id = R.string.kpa_btn)
                    PressureUnits.BAR -> stringResource(id = R.string.bar_btn)
                    PressureUnits.PSI -> stringResource(id = R.string.psi_btn)
                },
            style = MaterialTheme.typography.labelLarge,
        )
    }
}

@Preview
@Composable
private fun ChangePressureButtonPreview() {
    ApplicationTheme {
        ChangePressureButton(
            pressureUnits = PressureUnits.KPa,
            onPressureChanged = {},
        )
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun ChangePressureButtonPreviewDark() {
    ApplicationTheme {
        ChangePressureButton(
            pressureUnits = PressureUnits.KPa,
            onPressureChanged = {},
        )
    }
}
