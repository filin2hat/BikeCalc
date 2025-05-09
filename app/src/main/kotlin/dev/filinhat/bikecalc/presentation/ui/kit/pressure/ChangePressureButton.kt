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
import dev.filinhat.bikecalc.R
import dev.filinhat.bikecalc.domain.enums.units.PressureUnit
import dev.filinhat.bikecalc.presentation.ui.theme.ApplicationTheme

private const val BUTTON_WIDTH = 75

@Composable
fun ChangePressureButton(
    onPressureChange: (pressureUnit: PressureUnit) -> Unit,
    pressureUnit: PressureUnit,
    modifier: Modifier = Modifier,
) {
    ElevatedButton(
        onClick = {
            onPressureChange(pressureUnit)
        },
        border =
            BorderStroke(
                width = 2.dp,
                color = MaterialTheme.colorScheme.primary,
            ),
        shape = MaterialTheme.shapes.medium,
        modifier = modifier.width(BUTTON_WIDTH.dp),
        colors =
            ButtonDefaults.elevatedButtonColors(),
    ) {
        Text(
            text =
                when (pressureUnit) {
                    PressureUnit.KPa -> stringResource(id = R.string.kpa_btn)
                    PressureUnit.BAR -> stringResource(id = R.string.bar_btn)
                    PressureUnit.PSI -> stringResource(id = R.string.psi_btn)
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
            pressureUnit = PressureUnit.KPa,
            onPressureChange = {},
        )
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun ChangePressureButtonPreviewDark() {
    ApplicationTheme {
        ChangePressureButton(
            pressureUnit = PressureUnit.KPa,
            onPressureChange = {},
        )
    }
}
