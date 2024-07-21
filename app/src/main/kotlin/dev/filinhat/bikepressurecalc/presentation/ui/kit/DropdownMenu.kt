package dev.filinhat.bikepressurecalc.presentation.ui.kit

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.filinhat.bikepressurecalc.presentation.ui.theme.BikePressureCalcTheme
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

/**
 * Базовое поле с выбором для всего приложения
 *
 * @param onItemSelected callback на выбранный элемент
 * @param label Подсказка к полю
 * @param items Список элементов для выбора
 * @param modifier переопределить стиль элемента
 * @param itemLabel При передаче сложной модели, выбрать, что отображать в поле
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun <T> DropdownMenu(
    onItemSelected: (T) -> Unit,
    label: String,
    items: ImmutableList<T>,
    value: T?,
    modifier: Modifier = Modifier,
    itemLabel: (T?) -> String?
) where T : Any {
    var isMenuExpanded by remember { mutableStateOf(false) }
    var selectedItem by remember { mutableStateOf(value) }

    Box(
        modifier = modifier.fillMaxWidth()
    ) {
        ExposedDropdownMenuBox(
            expanded = isMenuExpanded,
            onExpandedChange = { isMenuExpanded = it },
            modifier = Modifier.fillMaxWidth()
        ) {
            OutlinedTextField(
                value = itemLabel(value) ?: itemLabel(selectedItem) ?: "",
                onValueChange = {},
                label = { Text(label) },
                readOnly = true,
                trailingIcon = {
                    ExposedDropdownMenuDefaults.TrailingIcon(expanded = isMenuExpanded)
                },
                colors = ExposedDropdownMenuDefaults.textFieldColors(
                    focusedContainerColor = MaterialTheme.colorScheme.onPrimary,
                    unfocusedContainerColor = MaterialTheme.colorScheme.onPrimary
                ),
                modifier = Modifier
                    .menuAnchor()
                    .fillMaxWidth()
            )
            ExposedDropdownMenu(
                expanded = isMenuExpanded,
                onDismissRequest = { isMenuExpanded = false }
            ) {
                items.forEach { item ->
                    DropdownMenuItem(
                        onClick = {
                            selectedItem = item
                            onItemSelected(item)
                            isMenuExpanded = false
                        },
                        text = { itemLabel(item)?.let { Text(it) } },
                    )
                }
            }
        }
    }

    LaunchedEffect(value) {
        if (value == null) {
            selectedItem = null
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewDropdownMenu() {
    BikePressureCalcTheme {
        DropdownMenu(
            items = persistentListOf(),
            onItemSelected = { },
            label = "Label",
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            value = "",
            itemLabel = { item -> item.toString() }
        )
    }
}