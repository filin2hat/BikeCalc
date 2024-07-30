package dev.filinhat.bikecalc.presentation

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import dev.filin2hat.bikecalc.BuildConfig
import dev.filin2hat.bikecalc.R
import dev.filinhat.bikecalc.presentation.screen.calcPressure.PressureCalculatorScreen
import dev.filinhat.bikecalc.presentation.ui.kit.InfoDialog

private const val CounterMax = 4

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun MainScreen() {
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current
    val context = LocalContext.current

    var openInfoDialog by remember { mutableStateOf(false) }
    var clickCounter by remember { mutableIntStateOf(0) }

    if (openInfoDialog) {
        InfoDialog(
            onCloseDialog = { openInfoDialog = false },
            dialogTitle = stringResource(R.string.dialog_title),
            dialogText = stringResource(R.string.dialog_text_chapter_one) + "\n\n" +
                stringResource(R.string.dialog_text_chapter_two) + "\n" +
                stringResource(R.string.dialog_text_chapter_three) + "\n" +
                stringResource(R.string.dialog_text_chapter_four) + "\n\n" +
                stringResource(R.string.dialog_text_end),
            icon = Icons.Default.Info
        )
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(R.string.app_bar_title),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        style = MaterialTheme.typography.titleLarge,
                        color = MaterialTheme.colorScheme.primary,
                        modifier = Modifier

                    )
                },
                actions = {
                    IconButton(
                        onClick = {
                            keyboardController?.hide()
                            focusManager.clearFocus()
                            openInfoDialog = true
                        },
                        colors = IconButtonDefaults.iconButtonColors(
                            contentColor = MaterialTheme.colorScheme.primary
                        ),
                        modifier = Modifier.size(48.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.Info,
                            contentDescription = null
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.background
                ),
                modifier = Modifier
                    .clickable {
                        keyboardController?.hide()
                        focusManager.clearFocus()
                        clickCounter++
                        if (clickCounter > CounterMax) {
                            clickCounter = 0
                            Toast.makeText(
                                context,
                                context.getString(
                                    R.string.app_version,
                                    BuildConfig.VERSION_NAME
                                ),
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
            )
        },
        containerColor = MaterialTheme.colorScheme.background,
        modifier = Modifier
            .fillMaxSize(),
    ) { innerPadding ->
        PressureCalculatorScreen(
            modifier = Modifier.padding(innerPadding),
        )
    }
}
