package dev.filinhat.bikepressurecalc.presentation.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.filinhat.bikepressurecalc.common.enums.TireSize
import dev.filinhat.bikepressurecalc.common.enums.TireSize24Inches
import dev.filinhat.bikepressurecalc.common.enums.TireSize26Inches
import dev.filinhat.bikepressurecalc.common.enums.TireSize275Inches
import dev.filinhat.bikepressurecalc.common.enums.TireSize28Inches
import dev.filinhat.bikepressurecalc.common.enums.TireSize29Inches
import dev.filinhat.bikepressurecalc.common.enums.WheelSize
import dev.filinhat.bikepressurecalc.presentation.ui.kit.DropdownMenu
import dev.filinhat.bikepressurecalc.presentation.ui.theme.ApplicationTheme
import kotlinx.collections.immutable.toPersistentList

@Composable
fun PressureCalculatorScreen(modifier: Modifier) {
    var riderWeight by remember { mutableStateOf(TextFieldValue("")) }
    var bikeWeight by remember { mutableStateOf(TextFieldValue("")) }
    var wheelSize by remember { mutableStateOf(WheelSize.entries.first()) }
    var tireSize: TireSize? by remember { mutableStateOf(null) }
    var pressureFront by remember { mutableStateOf("") }
    var pressureRear by remember { mutableStateOf("") }
    var pressureFrontRoad by remember { mutableStateOf("") }
    var pressureRearRoad by remember { mutableStateOf("") }

    Column(
        modifier =
        modifier
            .padding(16.dp)
            .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        OutlinedTextField(
            value = riderWeight,
            onValueChange = { riderWeight = it },
            label = { Text("Вес велосипедиста (кг)") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.NumberPassword),
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = bikeWeight,
            onValueChange = { bikeWeight = it },
            label = { Text("Вес велосипеда (кг)") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.NumberPassword),
            modifier = Modifier.fillMaxWidth()
        )

        DropdownMenu(
            onItemSelected = {
                wheelSize = it
                tireSize = null
            },
            label = "Размер колеса (дюймы)",
            items = WheelSize.entries.toPersistentList(),
            value = wheelSize,
            modifier = Modifier.fillMaxWidth(),
            itemLabel = { it?.nameSize }
        )

        DropdownMenu(
            onItemSelected = { tireSize = it },
            label = "Размер покрышки",
            items =
            when (wheelSize) {
                WheelSize.Inches24 -> TireSize24Inches.entries.toPersistentList()
                WheelSize.Inches26 -> TireSize26Inches.entries.toPersistentList()
                WheelSize.Inches275 -> TireSize275Inches.entries.toPersistentList()
                WheelSize.Inches28 -> TireSize28Inches.entries.toPersistentList()
                WheelSize.Inches29 -> TireSize29Inches.entries.toPersistentList()
            },
            value = tireSize,
            modifier = Modifier.fillMaxWidth(),
            itemLabel = { it?.nameSize }
        )

        Button(
            onClick = {
//                pressureFront = String.format("%.1f", pressureFrontValue)
//                pressureRear = String.format("%.1f", pressureRearValue)
//                pressureFrontRoad = String.format("%.2f", pressureFrontRoadValue)
//                pressureRearRoad = String.format("%.2f", pressureRearRoadValue)
            },
            modifier = Modifier.fillMaxWidth()
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
            modifier = Modifier
        )
    }
}
