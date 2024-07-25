package dev.filinhat.bikecalc.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import dagger.hilt.android.AndroidEntryPoint
import dev.filin2hat.bikecalc.R
import dev.filinhat.bikecalc.presentation.screen.calcPressure.PressureCalculatorScreen
import dev.filinhat.bikecalc.presentation.ui.kit.InfoDialog
import dev.filinhat.bikecalc.presentation.ui.theme.ApplicationTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val keyboardController = LocalSoftwareKeyboardController.current
            val focusManager = LocalFocusManager.current

            val openInfoDialog = remember { mutableStateOf(false) }
            ApplicationTheme {
                if (openInfoDialog.value) {
                    InfoDialog(
                        onCloseDialog = { openInfoDialog.value = false },
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
                                )
                            },
                            actions = {
                                IconButton(
                                    onClick = {
                                        keyboardController?.hide()
                                        focusManager.clearFocus()
                                        openInfoDialog.value = true
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
        }
    }
}
