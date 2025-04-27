package dev.filinhat.bikecalc.presentation.ui.kit.pressure

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.filinhat.bikecalc.R
import dev.filinhat.bikecalc.presentation.ui.theme.ApplicationTheme

@Composable
fun CalculatePressureButton(
    onClick: () -> Unit,
    enabled: Boolean,
    modifier: Modifier = Modifier,
) {
    Button(
        onClick = onClick,
        enabled = enabled,
        shape = MaterialTheme.shapes.medium,
        border =
            BorderStroke(
                width = 2.dp,
                color = MaterialTheme.colorScheme.inversePrimary,
            ),
        modifier =
            modifier
                .fillMaxWidth()
                .height(75.dp)
                .padding(bottom = 18.dp),
        colors =
            ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                contentColor = MaterialTheme.colorScheme.scrim,
            ),
    ) {
        Text(
            text = stringResource(R.string.calculate_pressure),
            style = MaterialTheme.typography.titleLarge,
        )
    }
}

@Preview
@Composable
private fun CalculatePressureButtonPreview() {
    ApplicationTheme {
        CalculatePressureButton(
            onClick = { /*TODO*/ },
            enabled = true,
        )
    }
}
