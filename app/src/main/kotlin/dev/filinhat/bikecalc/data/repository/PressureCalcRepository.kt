package dev.filinhat.bikecalc.data.repository

import dev.filinhat.bikecalc.common.enums.tire.TireSize
import dev.filinhat.bikecalc.common.enums.wheel.WheelSize
import kotlinx.coroutines.flow.Flow

/**
 * Репозиторий для расчета давления велосипеда.
 */
interface PressureCalcRepository {
    fun calcPressure(
        riderWeight: Double,
        bikeWeight: Double,
        wheelSize: WheelSize,
        tireSize: TireSize,
    ): Flow<Pair<Double, Double>>
}
