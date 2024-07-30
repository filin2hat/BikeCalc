package dev.filinhat.bikecalc.presentation.ui.kit

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.filin2hat.bikecalc.R
import dev.filinhat.bikecalc.presentation.ui.theme.ApplicationTheme

private const val Rotation = 180f

/**
 * Информационное окно.
 *
 * @param onCloseDialog функция закрытия диалога
 * @param dialogTitle заголовок диалога
 * @param dialogText текст диалога
 * @param icon иконка
 */
@Composable
fun InfoDialog(
    onCloseDialog: () -> Unit,
    dialogTitle: String,
    dialogText: String,
    icon: ImageVector,
) {
    AlertDialog(
        icon = {
            Icon(
                icon,
                contentDescription = "",
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier
                    .rotate(Rotation)
                    .size(36.dp)
            )
        },
        title = {
            Text(
                text = dialogTitle,
                style = MaterialTheme.typography.titleLarge
            )
        },
        text = {
            Column(
                modifier = Modifier
                    .verticalScroll(rememberScrollState())
            ) {
                Text(
                    text = dialogText,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        },
        confirmButton = {
            TextButton(
                onClick = {
                    onCloseDialog()
                }
            ) {
                Text(stringResource(R.string.i_understand_this))
            }
        },
        onDismissRequest = { },
    )
}

@Suppress("UnusedPrivateMember")
@Preview
@Composable
private fun InfoDialogPreview() {
    ApplicationTheme {
        InfoDialog(
            onCloseDialog = {},
            dialogTitle = "Header example",
            dialogText = "Any text with information or warnings for the user.",
            icon = Icons.Default.Info
        )
    }
}

@Suppress("UnusedPrivateMember")
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun InfoDialogDarkPreview() {
    ApplicationTheme {
        InfoDialog(
            onCloseDialog = {},
            dialogTitle = "Header example",
            dialogText = "Any text with information or warnings for the user.",
            icon = Icons.Default.Info
        )
    }
}
