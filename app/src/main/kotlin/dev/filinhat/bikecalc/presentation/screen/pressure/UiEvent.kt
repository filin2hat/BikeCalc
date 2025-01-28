package dev.filinhat.bikecalc.presentation.screen.pressure

import dev.filinhat.bikecalc.common.enums.tire.TireSize
import dev.filinhat.bikecalc.common.enums.wheel.WheelSize

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
        val tireSize: TireSize,
    ) : UiEvent
}
