package dev.filinhat.bikepressurecalc

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
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
import dev.filinhat.bikepressurecalc.ui.theme.BikePressureCalcTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BikePressureCalcTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    BikePressureCalculator(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@SuppressLint("DefaultLocale")
@Composable
fun BikePressureCalculator(
    modifier: Modifier
) {
    var riderWeight by remember { mutableStateOf(TextFieldValue("")) }
    var bikeWeight by remember { mutableStateOf(TextFieldValue("")) }
    var wheelSize by remember { mutableStateOf(TextFieldValue("")) }
    var tireSize by remember { mutableStateOf(TextFieldValue("")) }
    var pressureFront by remember { mutableStateOf("") }
    var pressureRear by remember { mutableStateOf("") }

    Column(
        modifier = modifier
            .padding(16.dp)
            .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(text = "Калькулятор давления в шинах для MTB", fontSize = 24.sp)

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

        OutlinedTextField(
            value = wheelSize,
            onValueChange = { wheelSize = it },
            label = { Text("Размер колеса (дюймы)") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = tireSize,
            onValueChange = { tireSize = it
            },
            label = { Text("Размер покрышки (мм)") },
            modifier = Modifier.fillMaxWidth()
        )

        Button(
            onClick = {
                val riderWeightValue = riderWeight.text.toDoubleOrNull() ?: 0.0
                val bikeWeightValue = bikeWeight.text.toDoubleOrNull() ?: 0.0
                val wheelSizeValue = wheelSize.text.toDoubleOrNull() ?: 0.0
                val tireSizeValue = tireSize.text.toDoubleOrNull() ?: 0.0

                val pressureFrontValue = calculateFrontPressure(riderWeightValue, bikeWeightValue, wheelSizeValue, tireSizeValue)
                val pressureRearValue = calculateRearPressure(riderWeightValue, bikeWeightValue, wheelSizeValue, tireSizeValue)

                pressureFront = String.format("%.2f", pressureFrontValue)
                pressureRear = String.format("%.2f", pressureRearValue)
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Рассчитать давление")
        }

        Text(text = "Давление переднего колеса: $pressureFront бар", fontSize = 18.sp)
        Text(text = "Давление заднего колеса: $pressureRear бар", fontSize = 18.sp)
    }
}

fun calculateFrontPressure(riderWeight: Double, bikeWeight: Double, wheelSize: Double, tireSize: Double): Double {
    val K = 71
    return ((riderWeight * 0.5 + bikeWeight * 0.5) / (wheelSize * tireSize)) * K
}

fun calculateRearPressure(riderWeight: Double, bikeWeight: Double, wheelSize: Double, tireSize: Double): Double {
    val K = 65
    return ((riderWeight * 0.6 + bikeWeight * 0.6) / (wheelSize * tireSize)) * K
}
