package dev.filinhat.bikepressurecalc.presentation.screen

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
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
internal fun PressureCalculatorScreen(
    viewModel: PressureCalculatorViewModel = hiltViewModel(),
    modifier: Modifier
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    PressureCalculatorScreen(
        uiState = uiState,
        modifier = modifier,
        onCalcPressure = { bikeWeight, riderWeight, wheelSize, tireSize ->
            viewModel.perform(
                PressureCalculatorViewModel.UiEvent.CalcPressure(
                    bikeWeight,
                    riderWeight,
                    wheelSize,
                    tireSize
                )
            )
        }
    )
}

@Composable
private fun PressureCalculatorScreen(
    uiState: PressureCalculatorViewModel.UiState,
    modifier: Modifier,
    onCalcPressure: (
        bikeWeight: Double,
        riderWeight: Double,
        wheelSize: WheelSize,
        tireSize: TireSize
    ) -> Unit = { _, _, _, _ -> }
) {
    val context = LocalContext.current
    var riderWeight by rememberSaveable { mutableStateOf("0.0") }
    var bikeWeight by rememberSaveable { mutableStateOf("0.0") }
    var wheelSize by rememberSaveable { mutableStateOf(WheelSize.entries.first()) }
    var tireSize: TireSize? by rememberSaveable { mutableStateOf(null) }

    when (uiState) {
        PressureCalculatorViewModel.UiState.Loading -> {
            Box(
                contentAlignment = Alignment.Center,
                modifier = modifier.fillMaxSize()
            ) {
                CircularProgressIndicator()
            }
        }

        is PressureCalculatorViewModel.UiState.Error -> {
            Toast.makeText(context, uiState.message, Toast.LENGTH_SHORT).show()
        }

        is PressureCalculatorViewModel.UiState.Success -> {
            Column(
                modifier = modifier
                    .verticalScroll(rememberScrollState())
                    .padding(16.dp)
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                OutlinedTextField(
                    value = riderWeight,
                    onValueChange = { riderWeight = it },
                    label = { Text("Вес велосипедиста (кг)") },
                    modifier = Modifier.fillMaxWidth()
                )

                OutlinedTextField(
                    value = bikeWeight,
                    onValueChange = { bikeWeight = it },
                    label = { Text("Вес велосипеда (кг)") },
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
                        onCalcPressure(
                            bikeWeight.toDouble(),
                            riderWeight.toDouble(),
                            wheelSize,
                            tireSize ?: return@Button
                        )
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Рассчитать давление")
                }

                Text(
                    text = "Давление переднего колеса: ${uiState.result.first} бар",
                    fontSize = 18.sp
                )
                Text(
                    text = "Давление заднего колеса: ${uiState.result.second} бар",
                    fontSize = 18.sp
                )
            }
        }
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
