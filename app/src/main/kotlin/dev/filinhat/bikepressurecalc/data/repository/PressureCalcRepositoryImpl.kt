package dev.filinhat.bikepressurecalc.data.repository

import dev.filinhat.bikepressurecalc.common.enums.TireSize
import dev.filinhat.bikepressurecalc.common.enums.WheelSize
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class PressureCalcRepositoryImpl : PressureCalcRepository {
    override fun calcPressure(
        riderWeight: Double,
        bikeWeight: Double,
        wheelSize: WheelSize,
        tireSize: TireSize
    ): Flow<Pair<Double, Double>> = flow {
        val (frontPressure, rearPressure) =
            when (wheelSize) {
                WheelSize.Inches28 -> {
                    val front =
                        roadFrontPressure(
                            riderWeight,
                            bikeWeight,
                            wheelSize.inchesSize,
                            tireSize.tireWidthInMillimeters
                        )
                    val rear =
                        roadRearPressure(
                            riderWeight,
                            bikeWeight,
                            wheelSize.inchesSize,
                            tireSize.tireWidthInMillimeters
                        )
                    front to rear
                }

                WheelSize.Inches29,
                WheelSize.Inches24,
                WheelSize.Inches26,
                WheelSize.Inches275
                -> {
                    val front =
                        mtbFrontPressure(
                            riderWeight,
                            bikeWeight,
                            wheelSize.inchesSize,
                            tireSize.tireWidthInMillimeters
                        )
                    val rear =
                        mtbRearPressure(
                            riderWeight,
                            bikeWeight,
                            wheelSize.inchesSize,
                            tireSize.tireWidthInMillimeters
                        )
                    front to rear
                }
            }
        emit(frontPressure to rearPressure)
    }

    private fun mtbFrontPressure(
        riderWeight: Double,
        bikeWeight: Double,
        wheelSize: Double,
        tireSize: Double
    ): Double = ((riderWeight * 0.5 + bikeWeight * 0.5) / (wheelSize * tireSize)) *
            mtbFrontEmpiricalCoefficient

    private fun mtbRearPressure(
        riderWeight: Double,
        bikeWeight: Double,
        wheelSize: Double,
        tireSize: Double
    ): Double = ((riderWeight * 0.6 + bikeWeight * 0.6) / (wheelSize * tireSize)) *
            mtbRearEmpiricalCoefficient

    private fun roadFrontPressure(
        riderWeight: Double,
        bikeWeight: Double,
        wheelSize: Double,
        tireSize: Double
    ): Double = ((riderWeight * 0.45 + bikeWeight * 0.45) / (wheelSize * tireSize)) *
            roadFrontEmpiricalCoefficient

    private fun roadRearPressure(
        riderWeight: Double,
        bikeWeight: Double,
        wheelSize: Double,
        tireSize: Double
    ): Double = ((riderWeight * 0.55 + bikeWeight * 0.55) / (wheelSize * tireSize)) *
            roadRearEmpiricalCoefficient
}

/**
 * Эмпирический коэффициент, который можно определить на основании опыта и
 * различных тестов.
 *
 * Для MTB велосипедов его значение находится в диапазоне от 50 до 100.
 * Для шоссейных велосипедов его значение находится в диапазоне от 100 до 150.
 */
const val mtbFrontEmpiricalCoefficient = 71
const val mtbRearEmpiricalCoefficient = 65
const val roadFrontEmpiricalCoefficient = 120
const val roadRearEmpiricalCoefficient = 120
