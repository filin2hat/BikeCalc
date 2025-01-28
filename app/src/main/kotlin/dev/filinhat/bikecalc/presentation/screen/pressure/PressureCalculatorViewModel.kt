package dev.filinhat.bikecalc.presentation.screen.pressure

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.filinhat.bikecalc.common.enums.tire.TireSize
import dev.filinhat.bikecalc.common.enums.wheel.WheelSize
import dev.filinhat.bikecalc.data.repository.PressureCalcRepository
import dev.filinhat.bikecalc.presentation.util.BaseViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * ViewModel экрана [PressureCalculatorScreen]
 */
@HiltViewModel
class PressureCalculatorViewModel
    @Inject
    constructor(
        private val repository: PressureCalcRepository,
    ) : ViewModel(),
        BaseViewModel<UiState, UiEvent> {
        private val _uiState = MutableStateFlow<UiState>(UiState.Success(0.0 to 0.0))
        override val uiState =
            _uiState
                .asStateFlow()
                .stateIn(
                    scope = viewModelScope,
                    started = SharingStarted.Lazily,
                    initialValue = UiState.Loading,
                )

        override fun perform(event: UiEvent) =
            when (event) {
                is UiEvent.CalcPressure ->
                    onCalcPressure(
                        event.bikeWeight,
                        event.riderWeight,
                        event.wheelSize,
                        event.tireSize,
                    )
            }

        private fun onCalcPressure(
            riderWeight: Double,
            bikeWeight: Double,
            wheelSize: WheelSize,
            tireSize: TireSize,
        ) {
            viewModelScope.launch {
                _uiState.value = UiState.Loading
                repository
                    .calcPressure(riderWeight, bikeWeight, wheelSize, tireSize)
                    .catch { e ->
                        _uiState.value = UiState.Error("Указаны не корректные данные для расчета.")
                    }.collect { result ->
                        _uiState.value = UiState.Success(result)
                    }
            }
        }
    }
