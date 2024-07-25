package dev.filinhat.bikecalc.data.repository

import dev.filinhat.bikecalc.common.enums.tire.TireSize
import dev.filinhat.bikecalc.common.enums.wheel.WheelSize
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

/**
 * Реализация репозитория для расчета давления велосипеда.
 */
class PressureCalcRepositoryImpl @Inject constructor() : PressureCalcRepository {
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
    ): Double = ((riderWeight * mtbFrontFactor + bikeWeight * mtbFrontFactor) / (wheelSize * tireSize)) *
            mtbFrontEmpiricalCoefficient

    private fun mtbRearPressure(
        riderWeight: Double,
        bikeWeight: Double,
        wheelSize: Double,
        tireSize: Double
    ): Double = ((riderWeight * mtbRearFactor + bikeWeight * mtbRearFactor) / (wheelSize * tireSize)) *
            mtbRearEmpiricalCoefficient

    private fun roadFrontPressure(
        riderWeight: Double,
        bikeWeight: Double,
        wheelSize: Double,
        tireSize: Double
    ): Double = ((riderWeight * roadFrontFactor + bikeWeight * roadFrontFactor) / (wheelSize * tireSize)) *
            roadFrontEmpiricalCoefficient

    private fun roadRearPressure(
        riderWeight: Double,
        bikeWeight: Double,
        wheelSize: Double,
        tireSize: Double
    ): Double = ((riderWeight * roadRearFactor + bikeWeight * roadRearFactor) / (wheelSize * tireSize)) *
            roadRearEmpiricalCoefficient
}

/**
 * Эмпирический коэффициент, который можно определить на основании опыта и
 * различных тестов.
 *
 * Для MTB велосипедов его значение находится в диапазоне от 50 до 100.
 * Для шоссейных велосипедов его значение находится в диапазоне от 100 до 150.
 */
private const val mtbFrontEmpiricalCoefficient = 71
private const val mtbRearEmpiricalCoefficient = 65

private const val roadFrontEmpiricalCoefficient = 120
private const val roadRearEmpiricalCoefficient = 120


private const val mtbFrontFactor = 0.5
private const val mtbRearFactor = 0.585

private const val roadFrontFactor = 0.42
private const val roadRearFactor =  0.445
