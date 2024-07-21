package dev.filinhat.bikepressurecalc.presentation.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.filinhat.bikepressurecalc.common.PressureCalculator.mtbFrontPressure
import dev.filinhat.bikepressurecalc.common.PressureCalculator.mtbRearPressure
import dev.filinhat.bikepressurecalc.common.PressureCalculator.roadFrontPressure
import dev.filinhat.bikepressurecalc.common.PressureCalculator.roadRearPressure
import dev.filinhat.bikepressurecalc.common.enums.WheelSize
import dev.filinhat.bikepressurecalc.common.utils.toStringName
import dev.filinhat.bikepressurecalc.common.utils.toWheelSize
import dev.filinhat.bikepressurecalc.presentation.ui.kit.DropdownMenu
import dev.filinhat.bikepressurecalc.presentation.ui.theme.ApplicationTheme
import kotlinx.collections.immutable.toPersistentList

@Composable
fun PressureCalculatorScreen(modifier: Modifier) {
    var riderWeight by remember { mutableStateOf(TextFieldValue("")) }
    var bikeWeight by remember { mutableStateOf(TextFieldValue("")) }
    var wheelSize by remember { mutableStateOf(WheelSize.entries.first()) }
    var tireSize by remember { mutableStateOf(TextFieldValue("")) }
    var pressureFront by remember { mutableStateOf("") }
    var pressureRear by remember { mutableStateOf("") }
    var pressureFrontRoad by remember { mutableStateOf("") }
    var pressureRearRoad by remember { mutableStateOf("") }

    Column(
        modifier =
            modifier
                .padding(16.dp)
                .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        OutlinedTextField(
            value = riderWeight,
            onValueChange = { riderWeight = it },
            label = { Text("Вес велосипедиста (кг)") },
            modifier = Modifier.fillMaxWidth(),
        )

        OutlinedTextField(
            value = bikeWeight,
            onValueChange = { bikeWeight = it },
            label = { Text("Вес велосипеда (кг)") },
            modifier = Modifier.fillMaxWidth(),
        )

        DropdownMenu(
            onItemSelected = { item ->
                wheelSize = item.toWheelSize()
            },
            label = "Размер колеса (дюймы)",
            items = WheelSize.entries.map { it.toStringName() }.toPersistentList(),
            value = wheelSize.toStringName(),
            modifier = Modifier.fillMaxWidth(),
            itemLabel = { it.toString() },
        )

        OutlinedTextField(
            value = tireSize,
            onValueChange = {
                tireSize = it
            },
            label = { Text("Размер покрышки (мм)") },
            modifier = Modifier.fillMaxWidth(),
        )

        Button(
            onClick = {
                val riderWeightValue = riderWeight.text.toDoubleOrNull() ?: 0.0
                val bikeWeightValue = bikeWeight.text.toDoubleOrNull() ?: 0.0
                val tireSizeValue = tireSize.text.toDoubleOrNull() ?: 0.0

                val pressureFrontValue =
                    mtbFrontPressure(
                        riderWeightValue,
                        bikeWeightValue,
                        wheelSize.inchesSize,
                        tireSizeValue,
                    )
                val pressureRearValue =
                    mtbRearPressure(
                        riderWeightValue,
                        bikeWeightValue,
                        wheelSize.inchesSize,
                        tireSizeValue,
                    )
                val pressureFrontRoadValue =
                    roadFrontPressure(
                        riderWeightValue,
                        bikeWeightValue,
                        wheelSize.inchesSize,
                        tireSizeValue,
                    )
                val pressureRearRoadValue =
                    roadRearPressure(
                        riderWeightValue,
                        bikeWeightValue,
                        wheelSize.inchesSize,
                        tireSizeValue,
                    )

                pressureFront = String.format("%.1f", pressureFrontValue)
                pressureRear = String.format("%.1f", pressureRearValue)
                pressureFrontRoad = String.format("%.2f", pressureFrontRoadValue)
                pressureRearRoad = String.format("%.2f", pressureRearRoadValue)
            },
            modifier = Modifier.fillMaxWidth(),
        ) {
            Text("Рассчитать давление")
        }

        Text(text = "Давление переднего колеса: $pressureFront бар", fontSize = 18.sp)
        Text(text = "Давление заднего колеса: $pressureRear бар", fontSize = 18.sp)
        Text(text = "Шоссейное Давление переднего колеса: $pressureFrontRoad бар", fontSize = 18.sp)
        Text(text = "Шоссейное Давление заднего колеса: $pressureRearRoad бар", fontSize = 18.sp)
    }
}

@Preview(showBackground = true)
@Composable
private fun PressureCalculatorScreenPreview() {
    ApplicationTheme {
        PressureCalculatorScreen(
            modifier = Modifier,
        )
    }
}
