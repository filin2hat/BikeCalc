package dev.filinhat.bikecalc.presentation.screen.pressure

import android.content.res.Configuration
import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import dev.filin2hat.bikecalc.R
import dev.filinhat.bikecalc.common.enums.tire.TireSize
import dev.filinhat.bikecalc.common.enums.tire.TireSize24Inches
import dev.filinhat.bikecalc.common.enums.tire.TireSize26Inches
import dev.filinhat.bikecalc.common.enums.tire.TireSize275Inches
import dev.filinhat.bikecalc.common.enums.tire.TireSize28Inches
import dev.filinhat.bikecalc.common.enums.tire.TireSize29Inches
import dev.filinhat.bikecalc.common.enums.wheel.Wheel
import dev.filinhat.bikecalc.common.enums.wheel.WheelSize
import dev.filinhat.bikecalc.presentation.ui.kit.common.DropdownMenu
import dev.filinhat.bikecalc.presentation.ui.kit.common.InfoDialog
import dev.filinhat.bikecalc.presentation.ui.kit.pressure.PressureCard
import dev.filinhat.bikecalc.presentation.ui.theme.ApplicationTheme
import dev.filinhat.bikecalc.presentation.util.validateWeight
import kotlinx.collections.immutable.toPersistentList

/**
 * Экран для расчета давления велосипеда.
 *
 * @param viewModel [PressureCalculatorViewModel]
 */
@Composable
internal fun PressureCalculatorScreen(viewModel: PressureCalculatorViewModel = hiltViewModel()) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    PressureCalculatorScreen(
        uiState = uiState,
        onCalcPressure = { bikeWeight, riderWeight, wheelSize, tireSize ->
            viewModel.perform(
                PressureCalculatorViewModel.UiEvent.CalcPressure(
                    bikeWeight,
                    riderWeight,
                    wheelSize,
                    tireSize,
                ),
            )
        },
    )
}

@Composable
private fun PressureCalculatorScreen(
    uiState: PressureCalculatorViewModel.UiState,
    modifier: Modifier = Modifier,
    onCalcPressure: (
        bikeWeight: Double,
        riderWeight: Double,
        wheelSize: WheelSize,
        tireSize: TireSize,
    ) -> Unit = { _, _, _, _ -> },
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current
    val context = LocalContext.current

    var riderWeight: String by rememberSaveable { mutableStateOf("") }
    var bikeWeight: String by rememberSaveable { mutableStateOf("") }
    var wheelSize: WheelSize? by rememberSaveable { mutableStateOf(null) }
    var tireSize: TireSize? by rememberSaveable { mutableStateOf(null) }

    var wrongRiderWeight by rememberSaveable { mutableStateOf(false) }
    var wrongBikeWeight by rememberSaveable { mutableStateOf(false) }

    var expandedTireSize by rememberSaveable { mutableStateOf(false) }
    var expandedCalcResult by rememberSaveable { mutableStateOf(false) }
    var openInfoDialog by remember { mutableStateOf(false) }

    when (uiState) {
        PressureCalculatorViewModel.UiState.Loading -> {
            Box(
                contentAlignment = Alignment.Center,
                modifier =
                    modifier
                        .fillMaxSize()
                        .background(MaterialTheme.colorScheme.background),
            ) {
                CircularProgressIndicator()
            }
        }

        is PressureCalculatorViewModel.UiState.Error -> {
            Toast.makeText(context, uiState.message, Toast.LENGTH_SHORT).show()
        }

        is PressureCalculatorViewModel.UiState.Success -> {
            if (openInfoDialog) {
                InfoDialog(
                    onCloseDialog = { openInfoDialog = false },
                    dialogTitle = stringResource(R.string.dialog_title),
                    dialogText =
                        stringResource(R.string.dialog_text_chapter_one) + "\n\n" +
                            stringResource(R.string.dialog_text_chapter_two) + "\n" +
                            stringResource(R.string.dialog_text_chapter_three) + "\n" +
                            stringResource(R.string.dialog_text_chapter_four) + "\n\n" +
                            stringResource(R.string.dialog_text_end),
                    icon = Icons.Default.Info,
                )
            }
            Column(modifier = modifier.fillMaxSize()) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Text(
                        text = stringResource(R.string.app_bar_title),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        style = MaterialTheme.typography.titleLarge,
                        color = MaterialTheme.colorScheme.primary,
                    )

                    IconButton(
                        onClick = {
                            keyboardController?.hide()
                            focusManager.clearFocus()
                            openInfoDialog = true
                        },
                        colors =
                            IconButtonDefaults.iconButtonColors(
                                contentColor = MaterialTheme.colorScheme.primary,
                            ),
                        modifier = Modifier.size(48.dp),
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.Info,
                            contentDescription = null,
                        )
                    }
                }
                AnimatedVisibility(
                    visible = expandedCalcResult,
                    enter = expandVertically(),
                    exit = shrinkVertically(),
                    modifier = Modifier.padding(bottom = 18.dp),
                ) {
                    Column(
                        modifier =
                            Modifier
                                .padding(top = 4.dp)
                                .fillMaxWidth(),
                    ) {
                        PressureCard(
                            value = uiState.result.first,
                            wheel = Wheel.Front,
                        )
                        Spacer(modifier = Modifier.size(18.dp))

                        PressureCard(
                            value = uiState.result.second,
                            wheel = Wheel.Rear,
                        )
                    }
                }
                Row(
                    modifier = Modifier.fillMaxSize(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.Top,
                ) {
                    OutlinedTextField(
                        value = riderWeight,
                        onValueChange = {
                            riderWeight =
                                if (it.startsWith("0")) {
                                    it.trimStart('0')
                                } else {
                                    it
                                }
                            wrongRiderWeight = !validateWeight(riderWeight)
                            expandedCalcResult = false
                        },
                        label = {
                            Text(
                                text = stringResource(R.string.rider_weight_kg),
                                fontSize = 14.sp,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis,
                            )
                        },
                        modifier =
                            Modifier
                                .weight(0.5f)
                                .fillMaxWidth(),
                        isError = wrongRiderWeight,
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        textStyle = MaterialTheme.typography.displaySmall,
                        shape = MaterialTheme.shapes.medium,
                        colors =
                            OutlinedTextFieldDefaults.colors(
                                focusedContainerColor = MaterialTheme.colorScheme.background,
                                unfocusedContainerColor = MaterialTheme.colorScheme.background,
                                unfocusedLabelColor = MaterialTheme.colorScheme.onBackground,
                                focusedLabelColor = MaterialTheme.colorScheme.primary,
                                unfocusedTextColor = MaterialTheme.colorScheme.onBackground,
                                focusedTextColor = MaterialTheme.colorScheme.primary,
                                unfocusedBorderColor = MaterialTheme.colorScheme.inversePrimary,
                            ),
                        supportingText = {
                            if (wrongRiderWeight) {
                                Text(
                                    text = stringResource(R.string.weight_incorrect),
                                    color = MaterialTheme.colorScheme.error,
                                    fontSize = 10.sp,
                                )
                            }
                        },
                    )

                    Spacer(modifier = Modifier.size(22.dp))

                    OutlinedTextField(
                        value = bikeWeight,
                        onValueChange = {
                            bikeWeight =
                                if (it.startsWith("0")) {
                                    it.trimStart('0')
                                } else {
                                    it
                                }
                            wrongBikeWeight = !validateWeight(bikeWeight)
                            expandedCalcResult = false
                        },
                        label = {
                            Text(
                                text = stringResource(R.string.bike_weight_kg),
                                fontSize = 14.sp,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis,
                            )
                        },
                        modifier =
                            Modifier
                                .weight(0.5f)
                                .fillMaxWidth(),
                        isError = wrongBikeWeight,
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        textStyle = MaterialTheme.typography.displaySmall,
                        shape = MaterialTheme.shapes.medium,
                        colors =
                            OutlinedTextFieldDefaults.colors(
                                focusedContainerColor = MaterialTheme.colorScheme.background,
                                unfocusedContainerColor = MaterialTheme.colorScheme.background,
                                unfocusedLabelColor = MaterialTheme.colorScheme.onBackground,
                                focusedLabelColor = MaterialTheme.colorScheme.primary,
                                unfocusedTextColor = MaterialTheme.colorScheme.onBackground,
                                focusedTextColor = MaterialTheme.colorScheme.primary,
                                unfocusedBorderColor = MaterialTheme.colorScheme.inversePrimary,
                            ),
                        supportingText = {
                            if (wrongBikeWeight) {
                                Text(
                                    text = stringResource(R.string.weight_incorrect),
                                    color = MaterialTheme.colorScheme.error,
                                    fontSize = 10.sp,
                                )
                            }
                        },
                    )
                }

                DropdownMenu(
                    onItemSelected = {
                        if (it != wheelSize) {
                            tireSize = null
                            expandedTireSize = false
                            expandedCalcResult = false
                        }
                        wheelSize = it
                        expandedTireSize = true
                    },
                    label = stringResource(R.string.wheel_size),
                    items = WheelSize.entries.toPersistentList(),
                    value = wheelSize,
                    itemLabel = { it?.nameSize },
                    modifier = Modifier.fillMaxWidth(),
                )

                AnimatedVisibility(
                    visible = expandedTireSize,
                    enter = expandVertically(),
                    exit = shrinkVertically(),
                ) {
                    DropdownMenu(
                        onItemSelected = {
                            tireSize = it
                            expandedCalcResult = false
                        },
                        label = stringResource(R.string.tire_size),
                        items =
                            when (wheelSize) {
                                WheelSize.Inches24 -> TireSize24Inches.entries.toPersistentList()
                                WheelSize.Inches26 -> TireSize26Inches.entries.toPersistentList()
                                WheelSize.Inches275 -> TireSize275Inches.entries.toPersistentList()
                                WheelSize.Inches28 -> TireSize28Inches.entries.toPersistentList()
                                WheelSize.Inches29 -> TireSize29Inches.entries.toPersistentList()
                                else -> null
                            },
                        value = tireSize,
                        itemLabel = { it?.nameSize },
                        modifier =
                            Modifier
                                .padding(top = 18.dp)
                                .fillMaxWidth(),
                    )
                }

                Button(
                    shape = MaterialTheme.shapes.medium,
                    border =
                        BorderStroke(
                            width = 2.dp,
                            color = MaterialTheme.colorScheme.inversePrimary,
                        ),
                    onClick = {
                        onCalcPressure(
                            bikeWeight.toDouble(),
                            riderWeight.toDouble(),
                            wheelSize ?: return@Button,
                            tireSize ?: return@Button,
                        )
                        keyboardController?.hide()
                        focusManager.clearFocus()
                        expandedCalcResult = true
                    },
                    enabled =
                        validateIfEmpty(
                            wrongRiderWeight,
                            wrongBikeWeight,
                            wheelSize,
                            tireSize,
                            riderWeight,
                            bikeWeight,
                        ),
                    modifier =
                        Modifier
                            .fillMaxWidth()
                            .height(90.dp)
                            .padding(bottom = 18.dp, top = 24.dp),
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
        }
    }
}

@Composable
private fun validateIfEmpty(
    wrongRiderWeight: Boolean,
    wrongBikeWeight: Boolean,
    wheelSize: WheelSize?,
    tireSize: TireSize?,
    riderWeight: String,
    bikeWeight: String,
) = !wrongRiderWeight &&
    !wrongBikeWeight &&
    wheelSize != null &&
    tireSize != null &&
    riderWeight.isNotEmpty() &&
    bikeWeight.isNotEmpty()

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
private fun PressureCalculatorScreenPreview() {
    ApplicationTheme {
        PressureCalculatorScreen(
            uiState =
                PressureCalculatorViewModel.UiState.Success(
                    result = Pair(4.0, 4.2),
                ),
            onCalcPressure = { _, _, _, _ -> },
            modifier = Modifier,
        )
    }
}
