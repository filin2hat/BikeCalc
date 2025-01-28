package dev.filinhat.bikecalc.presentation.screen.pressure

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
    data class Error(
        val message: String,
    ) : UiState

    /**
     * Состояние успешного получения данных
     *
     * @param result Результат расчета давления
     */
    data class Success(
        val result: Pair<Double, Double>,
    ) : UiState
}
