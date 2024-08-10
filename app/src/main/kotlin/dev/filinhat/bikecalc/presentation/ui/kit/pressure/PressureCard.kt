package dev.filinhat.bikecalc.presentation.ui.kit.pressure

import android.content.res.Configuration
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.filin2hat.bikecalc.R
import dev.filinhat.bikecalc.common.enums.units.PressureUnits
import dev.filinhat.bikecalc.common.enums.wheel.Wheel
import dev.filinhat.bikecalc.presentation.ui.theme.ApplicationTheme
import kotlinx.collections.immutable.toImmutableList
import kotlin.math.ceil
import kotlin.math.pow

@Suppress("ktlint:standard:property-naming")
const val CardHeight = 130

/**
 * Карточка для расчета и просмотра давления велосипеда.
 *
 * @param value давление велосипеда
 * @param wheel тип велосипеда
 * @param modifier модификатор карточки
 */
@Composable
fun PressureCard(
    value: Double,
    wheel: Wheel,
    modifier: Modifier = Modifier,
) {
    var pressureUnits by rememberSaveable { mutableStateOf(PressureUnits.BAR) }

    Card(
        colors =
            CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer,
            ),
        modifier =
            modifier
                .fillMaxWidth()
                .height(CardHeight.dp),
        border = BorderStroke(width = 2.dp, color = MaterialTheme.colorScheme.primary),
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier =
                Modifier
                    .padding(horizontal = 16.dp)
                    .fillMaxWidth(),
        ) {
            Column(
                modifier =
                    Modifier.weight(0.6f),
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.Start,
            ) {
                Text(
                    text =
                        when (wheel) {
                            Wheel.Front -> stringResource(R.string.front_wheel_pressure)
                            Wheel.Rear -> stringResource(R.string.rear_wheel_pressure)
                        },
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.padding(bottom = 8.dp),
                    color = MaterialTheme.colorScheme.scrim,
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    verticalAlignment = Alignment.Bottom,
                ) {
                    Text(
                        style = MaterialTheme.typography.displayMedium,
                        color = MaterialTheme.colorScheme.scrim,
                        text =
                            when (pressureUnits) {
                                PressureUnits.BAR -> formatValue(value)
                                PressureUnits.KPa -> formatValue(value.barToKPa(), 0)
                                PressureUnits.PSI -> formatValue(value.barToPsi(), 0)
                            },
                    )

                    Text(
                        style = MaterialTheme.typography.displaySmall,
                        color = MaterialTheme.colorScheme.scrim,
                        text =
                            when (pressureUnits) {
                                PressureUnits.BAR ->
                                    stringResource(R.string.bar)

                                PressureUnits.KPa ->
                                    stringResource(R.string.kpa)

                                PressureUnits.PSI ->
                                    stringResource(R.string.psi)
                            },
                    )
                }
            }

            ChangePressureRadioGroup(
                pressureUnits =
                    listOf(
                        PressureUnits.BAR,
                        PressureUnits.PSI,
                        PressureUnits.KPa,
                    ).toImmutableList(),
                onPressureChanged = { unit ->
                    pressureUnits = unit
                },
                modifier =
                    Modifier
                        .weight(0.4f)
                        .padding(start = 16.dp),
            )
        }
    }
}

private fun formatValue(
    value: Double,
    decimalPlaces: Int = 1,
): String =
    String.format(
        "%.${decimalPlaces}f",
        ceil(value * 10.0.pow(decimalPlaces)) / 10.0.pow(decimalPlaces),
    )

// Conversion functions
private fun Double.barToKPa(): Double = this * 100

private fun Double.barToAtm(): Double = this * 0.986923

private fun Double.barToPsi(): Double = this * 14.5038

@Preview(showBackground = true)
@Composable
private fun PressureCardPreview() {
    ApplicationTheme {
        PressureCard(
            wheel = Wheel.Front,
            value = 10.54654564,
        )
    }
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun PressureCardDarkPreview() {
    ApplicationTheme {
        PressureCard(
            wheel = Wheel.Front,
            value = 10.54654564,
        )
    }
}
