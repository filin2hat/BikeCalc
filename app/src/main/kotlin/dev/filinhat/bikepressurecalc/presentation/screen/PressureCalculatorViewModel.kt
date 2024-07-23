package dev.filinhat.bikepressurecalc.presentation.screen

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.filinhat.bikepressurecalc.data.repository.PressureCalcRepository
import dev.filinhat.bikepressurecalc.presentation.screen.PressureCalculatorViewModel.UiEvent
import dev.filinhat.bikepressurecalc.presentation.screen.PressureCalculatorViewModel.UiState
import dev.filinhat.bikepressurecalc.presentation.util.BaseViewModel
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class PressureCalculatorViewModel @Inject constructor(
    private val repository: PressureCalcRepository
) : BaseViewModel<UiState, UiEvent> ,ViewModel() {

    sealed interface UiState{}

    sealed interface UiEvent{}

    override val uiState: StateFlow<UiState>
        get() = TODO("Not yet implemented")

    override fun perform(event: UiEvent) {
        TODO("Not yet implemented")
    }
}
