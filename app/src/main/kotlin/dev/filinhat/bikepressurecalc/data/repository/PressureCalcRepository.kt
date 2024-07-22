package dev.filinhat.bikepressurecalc.data.repository

import dev.filinhat.bikepressurecalc.common.enums.TireSize
import dev.filinhat.bikepressurecalc.common.enums.WheelSize
import kotlinx.coroutines.flow.Flow

interface PressureCalcRepository {
    fun calcPressure(
        riderWeight: Double,
        bikeWeight: Double,
        wheelSize: WheelSize,
        tireSize: TireSize
    ): Flow<Pair<Double, Double>>
}