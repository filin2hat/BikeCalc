package dev.filinhat.bikecalc.presentation.ui.kit.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MenuAnchorType
import androidx.compose.material3.MenuDefaults
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
import androidx.compose.ui.unit.sp
import dev.filinhat.bikecalc.presentation.ui.theme.ApplicationTheme
import kotlinx.collections.immutable.PersistentList
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
    items: PersistentList<T>?,
    value: T?,
    label: String,
    itemLabel: (T?) -> String?,
    modifier: Modifier = Modifier,
) where T : Any {
    var isMenuExpanded by remember { mutableStateOf(false) }
    var selectedItem by remember { mutableStateOf(value) }

    Box(
        modifier = modifier.fillMaxWidth(),
    ) {
        ExposedDropdownMenuBox(
            expanded = isMenuExpanded,
            onExpandedChange = { isMenuExpanded = it },
            modifier = Modifier.fillMaxWidth(),
        ) {
            OutlinedTextField(
                value = itemLabel(value) ?: itemLabel(selectedItem) ?: "",
                onValueChange = {},
                label = {
                    Text(
                        text = label,
                        fontSize = 14.sp,
                    )
                },
                readOnly = true,
                trailingIcon = {
                    ExposedDropdownMenuDefaults.TrailingIcon(expanded = isMenuExpanded)
                },
                textStyle = MaterialTheme.typography.bodyLarge,
                colors =
                ExposedDropdownMenuDefaults.outlinedTextFieldColors(
                    focusedContainerColor = MaterialTheme.colorScheme.background,
                    unfocusedContainerColor = MaterialTheme.colorScheme.background,
                    focusedTextColor = MaterialTheme.colorScheme.primary,
                    unfocusedTextColor = MaterialTheme.colorScheme.onBackground,
                    unfocusedBorderColor = MaterialTheme.colorScheme.inversePrimary,
                ),
                shape = MaterialTheme.shapes.medium,
                modifier =
                Modifier
                    .fillMaxWidth()
                    .menuAnchor(
                        type = MenuAnchorType.PrimaryNotEditable,
                        enabled = true
                    )
            )
            ExposedDropdownMenu(
                expanded = isMenuExpanded,
                onDismissRequest = { isMenuExpanded = false },
                modifier = Modifier.background(MaterialTheme.colorScheme.primaryContainer),
            ) {
                items?.forEach { item ->
                    DropdownMenuItem(
                        onClick = {
                            selectedItem = item
                            onItemSelected(item)
                            isMenuExpanded = false
                        },
                        text = {
                            itemLabel(item)?.let {
                                Text(
                                    text = it,
                                    style = MaterialTheme.typography.bodyLarge,
                                )
                            }
                        },
                        colors =
                        MenuDefaults.itemColors(
                            textColor = MaterialTheme.colorScheme.onPrimaryContainer,
                        ),
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
    ApplicationTheme {
        DropdownMenu(
            items = persistentListOf("One", "Two", "Three"),
            onItemSelected = { },
            label = "Label",
            modifier =
            Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            value = "One",
            itemLabel = { item -> item.toString() },
        )
    }
}
