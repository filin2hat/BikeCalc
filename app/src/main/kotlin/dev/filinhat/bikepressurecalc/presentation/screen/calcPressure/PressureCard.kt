package dev.filinhat.bikepressurecalc.presentation.screen.calcPressure

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedButton
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
import dev.filin2hat.bikepressurecalc.R
import dev.filinhat.bikepressurecalc.common.enums.PressureUnits
import dev.filinhat.bikepressurecalc.common.enums.Wheel
import dev.filinhat.bikepressurecalc.presentation.ui.theme.ApplicationTheme
import kotlin.math.ceil
import kotlin.math.pow
import kotlin.math.roundToInt

const val ButtonWidth = 75
const val CardHeight = 135

@Composable
fun PressureCard(
    value: Double,
    wheel: Wheel,
    modifier: Modifier = Modifier
) {
    var pressureUnits by rememberSaveable { mutableStateOf(PressureUnits.BAR) }

    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        ),
        modifier = modifier
            .fillMaxWidth()
            .height(CardHeight.dp),
        border = BorderStroke(width = 2.dp, color = MaterialTheme.colorScheme.primary),
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
        ) {
            Column {
                Text(
                    text = when (wheel) {
                        Wheel.Front -> stringResource(R.string.front_wheel_pressure)
                        Wheel.Rear -> stringResource(R.string.rear_wheel_pressure)
                    },
                    style = MaterialTheme.typography.titleSmall,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                Text(
                    style = MaterialTheme.typography.displayLarge,
                    text =
                    when (pressureUnits) {
                        PressureUnits.BAR -> stringResource(
                            R.string.bar,
                            formatValue(value)
                        )

                        PressureUnits.ATM -> stringResource(
                            R.string.atm,
                            formatValue(value.barToAtm())
                        )

                        PressureUnits.PSI -> stringResource(
                            R.string.psi,
                            formatValue(value.barToPsi(), 0)
                        )
                    }
                )
            }

            Column(
                verticalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(8.dp)
            ) {
                when (pressureUnits) {
                    PressureUnits.BAR -> {
                        ElevatedButton(
                            onClick = {
                                pressureUnits = PressureUnits.ATM
                            },
                            modifier = Modifier.width(ButtonWidth.dp),
                            border = BorderStroke(
                                width = 2.dp,
                                color = MaterialTheme.colorScheme.primary
                            ),
                            shape = MaterialTheme.shapes.medium
                        ) {
                            Text(
                                text = stringResource(id = R.string.atm_btn),
                                style = MaterialTheme.typography.labelLarge
                            )
                        }
                        ElevatedButton(
                            onClick = {
                                pressureUnits = PressureUnits.PSI
                            },
                            modifier = Modifier.width(ButtonWidth.dp),
                            border = BorderStroke(
                                width = 2.dp,
                                color = MaterialTheme.colorScheme.primary
                            ),
                            shape = MaterialTheme.shapes.medium,
                        ) {
                            Text(
                                text = stringResource(id = R.string.psi_btn),
                                style = MaterialTheme.typography.labelLarge
                            )
                        }
                    }

                    PressureUnits.ATM -> {
                        ElevatedButton(
                            onClick = {
                                pressureUnits = PressureUnits.BAR
                            },
                            modifier = Modifier.width(ButtonWidth.dp),
                            border = BorderStroke(
                                width = 2.dp,
                                color = MaterialTheme.colorScheme.primary
                            ),
                            shape = MaterialTheme.shapes.medium
                        ) {
                            Text(
                                text = stringResource(id = R.string.bar_btn),
                                style = MaterialTheme.typography.labelLarge
                            )
                        }
                        ElevatedButton(
                            onClick = {
                                pressureUnits = PressureUnits.PSI
                            },
                            modifier = Modifier.width(ButtonWidth.dp),
                            border = BorderStroke(
                                width = 2.dp,
                                color = MaterialTheme.colorScheme.primary
                            ),
                            shape = MaterialTheme.shapes.medium,
                        ) {
                            Text(
                                text = stringResource(id = R.string.psi_btn),
                                style = MaterialTheme.typography.labelLarge
                            )
                        }
                    }

                    PressureUnits.PSI -> {
                        ElevatedButton(
                            onClick = {
                                pressureUnits = PressureUnits.ATM
                            },
                            modifier = Modifier.width(ButtonWidth.dp),
                            border = BorderStroke(
                                width = 2.dp,
                                color = MaterialTheme.colorScheme.primary
                            ),
                            shape = MaterialTheme.shapes.medium
                        ) {
                            Text(
                                text = stringResource(id = R.string.atm_btn),
                                style = MaterialTheme.typography.labelLarge
                            )
                        }
                        ElevatedButton(
                            onClick = {
                                pressureUnits = PressureUnits.BAR
                            },
                            modifier = Modifier.width(ButtonWidth.dp),
                            border = BorderStroke(
                                width = 2.dp,
                                color = MaterialTheme.colorScheme.primary
                            ),
                            shape = MaterialTheme.shapes.medium,
                        ) {
                            Text(
                                text = stringResource(id = R.string.bar_btn),
                                style = MaterialTheme.typography.labelLarge
                            )
                        }
                    }
                }
            }
        }
    }
}

private fun formatValue(value: Double, decimalPlaces: Int = 1): String {
    return String.format(
        "%.${decimalPlaces}f",
        ceil(value * 10.0.pow(decimalPlaces)) / 10.0.pow(decimalPlaces)
    )
}

// Conversion functions
private fun Double.barToAtm(): Double {
    return this / 1.01325
}

private fun Double.barToPsi(): Double {
    return (this * 14.5038).roundToInt().toDouble()
}

@Preview(showBackground = true)
@Composable
private fun PressureCardPreview() {
    ApplicationTheme {
        PressureCard(
            wheel = Wheel.Front,
            value = 10.54654564
        )
    }
}