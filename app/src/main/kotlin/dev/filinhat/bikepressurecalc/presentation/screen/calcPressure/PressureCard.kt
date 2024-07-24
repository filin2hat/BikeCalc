package dev.filinhat.bikepressurecalc.presentation.screen.calcPressure

import android.annotation.SuppressLint
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.filin2hat.bikepressurecalc.R
import dev.filinhat.bikepressurecalc.common.enums.Wheel
import dev.filinhat.bikepressurecalc.presentation.ui.theme.ApplicationTheme
import kotlin.math.ceil

@Composable
fun PressureCard(
    value: Double,
    wheel: Wheel,
    modifier: Modifier = Modifier
) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        ),
        modifier = modifier
            .fillMaxWidth()
            .height(140.dp)
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
                    text = "${formatValue(value)} bar",
                    style = MaterialTheme.typography.displayLarge
                )
            }

            Column(
                verticalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(8.dp)
            ) {
                ElevatedButton(
                    onClick = { },
                    modifier = Modifier.width(80.dp),
                    shape = MaterialTheme.shapes.medium
                ) { Text("atm") }
                ElevatedButton(
                    onClick = { },
                    modifier = Modifier.width(80.dp),
                    shape = MaterialTheme.shapes.medium
                ) { Text("psi") }
            }
        }
    }
}

@SuppressLint("DefaultLocale")
@Composable
private fun formatValue(value: Double) = String.format("%.1f", ceil(value * 10) / 10)

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