package dev.filinhat.bikecalc.domain.repository

import dev.filinhat.bikecalc.domain.enums.tire.TireSize
import dev.filinhat.bikecalc.domain.enums.units.WeightUnit
import dev.filinhat.bikecalc.domain.enums.wheel.WheelSize
import dev.filinhat.bikecalc.domain.model.PressureCalcResult
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
        weightUnit: WeightUnit,
    ): Flow<PressureCalcResult>
}
