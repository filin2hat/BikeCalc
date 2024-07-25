package dev.filinhat.bikecalc.presentation.screen.calcPressure

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.filinhat.bikecalc.common.enums.tire.TireSize
import dev.filinhat.bikecalc.common.enums.wheel.WheelSize
import dev.filinhat.bikecalc.data.repository.PressureCalcRepository
import dev.filinhat.bikecalc.presentation.screen.calcPressure.PressureCalculatorViewModel.UiEvent
import dev.filinhat.bikecalc.presentation.screen.calcPressure.PressureCalculatorViewModel.UiState
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
class PressureCalculatorViewModel @Inject constructor(
    private val repository: PressureCalcRepository
) : BaseViewModel<UiState, UiEvent>, ViewModel() {

    private val _uiState = MutableStateFlow<UiState>(UiState.Success(0.0 to 0.0))
    override val uiState = _uiState.asStateFlow()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.Lazily,
            initialValue = UiState.Loading,
        )

    override fun perform(event: UiEvent) = when (event) {
        is UiEvent.CalcPressure -> onCalcPressure(
            event.bikeWeight,
            event.riderWeight,
            event.wheelSize,
            event.tireSize
        )
    }

    private fun onCalcPressure(
        riderWeight: Double,
        bikeWeight: Double,
        wheelSize: WheelSize,
        tireSize: TireSize
    ) {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            repository.calcPressure(riderWeight, bikeWeight, wheelSize, tireSize)
                .catch { e ->
                    _uiState.value = UiState.Error("Указаны не корректные данные для расчета.")
                }
                .collect { result ->
                    _uiState.value = UiState.Success(result)
                }
        }
    }

    /**
     * Определяет состояния UI, которые может принимать ViewModel.
     */
    sealed interface UiState {

        /**
         * Состояние загрузки данных
         */
        data object Loading : UiState

        /**
         * Состояние ошибки
         *
         * @param message Сообщение об ошибке
         */
        data class Error(val message: String) : UiState

        /**
         * Состояние успешного получения данных
         *
         * @param result Результат расчета давления
         */
        data class Success(val result: Pair<Double, Double>) : UiState
    }

    /**
     * Определяет события UI, инициированные пользователем,
     * на которые ViewModel должен отреагировать.
     */
    sealed interface UiEvent {

        /**
         * Событие "Рассчитать давление"
         *
         * @param bikeWeight Вес велосипеда
         * @param riderWeight Вес велосипедиста
         * @param wheelSize Размер колеса
         * @param tireSize Размер покрышки
         */
        data class CalcPressure(
            val riderWeight: Double,
            val bikeWeight: Double,
            val wheelSize: WheelSize,
            val tireSize: TireSize
        ) : UiEvent
    }
}
