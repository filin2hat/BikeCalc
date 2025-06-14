package dev.filinhat.bikecalc.presentation.ui.kit.pressure

import android.content.res.Configuration
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SegmentedButton
import androidx.compose.material3.SegmentedButtonDefaults
import androidx.compose.material3.SingleChoiceSegmentedButtonRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.filinhat.bikecalc.R
import dev.filinhat.bikecalc.domain.enums.tube.TubeType
import dev.filinhat.bikecalc.presentation.ui.theme.ApplicationTheme

@Composable
fun TubeTypeChangeButton(
    onClick: () -> Unit,
    onTypeChange: (TubeType) -> Unit,
    enabled: Boolean,
    selectedType: TubeType,
    modifier: Modifier = Modifier,
) {
    var selectType by remember { mutableStateOf(selectedType) }
    var selectedIndex by remember { mutableIntStateOf(0) }
    val items =
        listOf(
            stringResource(R.string.tubes),
            stringResource(R.string.tubeless),
        )

    SingleChoiceSegmentedButtonRow(
        modifier = modifier.fillMaxWidth(),
    ) {
        items.forEachIndexed { index, label ->
            SegmentedButton(
                enabled = enabled,
                shape = MaterialTheme.shapes.medium,
                border =
                    BorderStroke(
                        width = 2.dp,
                        color = MaterialTheme.colorScheme.inversePrimary,
                    ),
                colors =
                    SegmentedButtonDefaults.colors(
                        activeContainerColor = MaterialTheme.colorScheme.primaryContainer,
                        activeContentColor = MaterialTheme.colorScheme.scrim,
                        inactiveContainerColor = MaterialTheme.colorScheme.background,
                        inactiveContentColor = MaterialTheme.colorScheme.primary,
                        disabledActiveContainerColor =
                            MaterialTheme.colorScheme.primaryContainer.copy(
                                alpha = 0.3f,
                            ),
                        disabledInactiveContainerColor =
                            MaterialTheme.colorScheme.background.copy(
                                alpha = 0.4f,
                            ),
                        disabledActiveContentColor = MaterialTheme.colorScheme.scrim.copy(alpha = 0.3f),
                        disabledInactiveContentColor =
                            MaterialTheme.colorScheme.inversePrimary.copy(
                                alpha = 0.8f,
                            ),
                    ),
                onClick = {
                    onClick()
                    selectedIndex = index
                    selectType =
                        when (index) {
                            0 -> TubeType.TUBES
                            else -> TubeType.TUBELESS
                        }
                    onTypeChange(selectType)
                },
                selected = index == selectedIndex,
                label = {
                    Text(
                        text = label,
                        style = MaterialTheme.typography.titleMedium,
                    )
                },
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .height(75.dp)
                        .padding(bottom = 22.dp),
            )
            if (index != items.lastIndex) {
                Spacer(modifier = Modifier.width(12.dp))
            }
        }
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun CalcSegmentedButtonEnabledDarkPreview() {
    ApplicationTheme {
        TubeTypeChangeButton(
            onClick = { /*TODO*/ },
            onTypeChange = { /*TODO*/ },
            enabled = true,
            selectedType = TubeType.TUBES,
        )
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun CalcSegmentedButtonDisabledDarkPreview() {
    ApplicationTheme {
        TubeTypeChangeButton(
            onClick = { /*TODO*/ },
            onTypeChange = { /*TODO*/ },
            enabled = false,
            selectedType = TubeType.TUBES,
        )
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
private fun CalcSegmentedButtonEnabledLightPreview() {
    ApplicationTheme {
        TubeTypeChangeButton(
            onClick = { /*TODO*/ },
            onTypeChange = { /*TODO*/ },
            enabled = true,
            selectedType = TubeType.TUBES,
        )
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
private fun CalcSegmentedButtonDisabledLightPreview() {
    ApplicationTheme {
        TubeTypeChangeButton(
            onClick = { /*TODO*/ },
            onTypeChange = { /*TODO*/ },
            enabled = false,
            selectedType = TubeType.TUBES,
        )
    }
}
