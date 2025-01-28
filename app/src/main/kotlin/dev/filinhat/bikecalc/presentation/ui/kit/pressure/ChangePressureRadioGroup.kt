package dev.filinhat.bikecalc.presentation.ui.kit.pressure

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.filinhat.bikecalc.R
import dev.filinhat.bikecalc.common.enums.units.PressureUnits
import dev.filinhat.bikecalc.presentation.ui.theme.ApplicationTheme
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList

@Composable
fun ChangePressureRadioGroup(
    pressureUnits: ImmutableList<PressureUnits>,
    onPressureChange: (pressureUnits: PressureUnits) -> Unit,
    modifier: Modifier = Modifier,
) {
    val (selectedOption, onOptionSelected) = rememberSaveable { mutableStateOf(pressureUnits[0]) }

    Column(
        modifier
            .padding(vertical = 8.dp)
            .fillMaxHeight()
            .selectableGroup(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly,
    ) {
        pressureUnits.forEach { item ->
            Row(
                Modifier
                    .border(
                        1.dp,
                        MaterialTheme.colorScheme.inversePrimary,
                        MaterialTheme.shapes.medium,
                    ).clip(MaterialTheme.shapes.medium)
                    .background(MaterialTheme.colorScheme.background)
                    .height(32.dp)
                    .fillMaxWidth()
                    .selectable(
                        selected = (item == selectedOption),
                        onClick = {
                            onOptionSelected(item)
                            onPressureChange(item)
                        },
                        role = Role.RadioButton,
                    ),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                RadioButton(
                    selected = (item == selectedOption),
                    onClick = null,
                    modifier = Modifier.padding(start = 4.dp),
                    colors =
                        RadioButtonDefaults.colors(
                            selectedColor = MaterialTheme.colorScheme.primary,
                            unselectedColor = MaterialTheme.colorScheme.onBackground,
                        ),
                )
                Text(
                    text =
                        when (item) {
                            PressureUnits.KPa -> stringResource(R.string.kpa_btn)
                            PressureUnits.BAR -> stringResource(R.string.bar_btn)
                            PressureUnits.PSI -> stringResource(R.string.psi_btn)
                        },
                    style = MaterialTheme.typography.bodyLarge,
                    color = if (item == selectedOption) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onBackground,
                    modifier = Modifier.padding(start = 6.dp),
                )
            }
            if (pressureUnits.indexOf(item) < pressureUnits.lastIndex) {
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ChangePressureRadioGroupPreview() {
    ApplicationTheme {
        ChangePressureRadioGroup(
            pressureUnits =
                listOf(
                    PressureUnits.KPa,
                    PressureUnits.BAR,
                    PressureUnits.PSI,
                ).toImmutableList(),
            onPressureChange = {},
        )
    }
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun ChangePressureRadioGroupPreviewDark() {
    ApplicationTheme {
        ChangePressureRadioGroup(
            pressureUnits =
                listOf(
                    PressureUnits.KPa,
                    PressureUnits.BAR,
                    PressureUnits.PSI,
                ).toImmutableList(),
            onPressureChange = {},
        )
    }
}
