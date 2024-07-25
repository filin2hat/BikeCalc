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
                WheelSize.Inches24 -> {
                    val front =
                        frontPressureSize24(
                            riderWeight,
                            bikeWeight,
                            wheelSize.inchesSize,
                            tireSize.tireWidthInMillimeters
                        )
                    val rear =
                        rearPressureSize24(
                            riderWeight,
                            bikeWeight,
                            wheelSize.inchesSize,
                            tireSize.tireWidthInMillimeters
                        )
                    front to rear
                }

                WheelSize.Inches26 -> {
                    val front =
                        frontPressureSize26(
                            riderWeight,
                            bikeWeight,
                            wheelSize.inchesSize,
                            tireSize.tireWidthInMillimeters
                        )
                    val rear =
                        rearPressureSize26(
                            riderWeight,
                            bikeWeight,
                            wheelSize.inchesSize,
                            tireSize.tireWidthInMillimeters
                        )
                    front to rear
                }

                WheelSize.Inches275 -> {
                    val front =
                        frontPressureSize275(
                            riderWeight,
                            bikeWeight,
                            wheelSize.inchesSize,
                            tireSize.tireWidthInMillimeters
                        )
                    val rear =
                        rearPressureSize275(
                            riderWeight,
                            bikeWeight,
                            wheelSize.inchesSize,
                            tireSize.tireWidthInMillimeters
                        )
                    front to rear
                }

                WheelSize.Inches28 -> {
                    val front =
                        frontPressureSize28(
                            riderWeight,
                            bikeWeight,
                            wheelSize.inchesSize,
                            tireSize.tireWidthInMillimeters
                        )
                    val rear =
                        rearPressureSize28(
                            riderWeight,
                            bikeWeight,
                            wheelSize.inchesSize,
                            tireSize.tireWidthInMillimeters
                        )
                    front to rear
                }

                WheelSize.Inches29 -> {
                    val front =
                        frontPressureSize29(
                            riderWeight,
                            bikeWeight,
                            wheelSize.inchesSize,
                            tireSize.tireWidthInMillimeters
                        )
                    val rear =
                        rearPressureSize29(
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

    private fun frontPressureSize29(
        riderWeight: Double,
        bikeWeight: Double,
        wheelSize: Double,
        tireSize: Double
    ): Double =
        ((riderWeight * FrontFactorSize29 + bikeWeight * FrontFactorSize29) / (wheelSize * tireSize)) *
                FrontEmpiricalCoefficientSize29

    private fun rearPressureSize29(
        riderWeight: Double,
        bikeWeight: Double,
        wheelSize: Double,
        tireSize: Double
    ): Double =
        ((riderWeight * RearFactorSize29 + bikeWeight * RearFactorSize29) / (wheelSize * tireSize)) *
                RearEmpiricalCoefficientSize29

    private fun frontPressureSize28(
        riderWeight: Double,
        bikeWeight: Double,
        wheelSize: Double,
        tireSize: Double
    ): Double =
        ((riderWeight * FrontFactorSize28 + bikeWeight * FrontFactorSize28) / (wheelSize * tireSize)) *
                FrontEmpiricalCoefficientSize28

    private fun rearPressureSize28(
        riderWeight: Double,
        bikeWeight: Double,
        wheelSize: Double,
        tireSize: Double
    ): Double =
        ((riderWeight * RearFactorSize28 + bikeWeight * RearFactorSize28) / (wheelSize * tireSize)) *
                RearEmpiricalCoefficientSize28

    private fun frontPressureSize275(
        riderWeight: Double,
        bikeWeight: Double,
        wheelSize: Double,
        tireSize: Double
    ): Double =
        ((riderWeight * FrontFactorSize275 + bikeWeight * FrontFactorSize275) / (wheelSize * tireSize)) *
                FrontEmpiricalCoefficientSize275

    private fun rearPressureSize275(
        riderWeight: Double,
        bikeWeight: Double,
        wheelSize: Double,
        tireSize: Double
    ): Double =
        ((riderWeight * RearFactorSize275 + bikeWeight * RearFactorSize275) / (wheelSize * tireSize)) *
                RearEmpiricalCoefficientSize275

    private fun frontPressureSize26(
        riderWeight: Double,
        bikeWeight: Double,
        wheelSize: Double,
        tireSize: Double
    ): Double =
        ((riderWeight * FrontFactorSize26 + bikeWeight * FrontFactorSize26) / (wheelSize * tireSize)) *
                FrontEmpiricalCoefficientSize26

    private fun rearPressureSize26(
        riderWeight: Double,
        bikeWeight: Double,
        wheelSize: Double,
        tireSize: Double
    ): Double =
        ((riderWeight * RearFactorSize26 + bikeWeight * RearFactorSize26) / (wheelSize * tireSize)) *
                RearEmpiricalCoefficientSize26

    private fun frontPressureSize24(
        riderWeight: Double,
        bikeWeight: Double,
        wheelSize: Double,
        tireSize: Double
    ): Double =
        ((riderWeight * FrontFactorSize24 + bikeWeight * FrontFactorSize24) / (wheelSize * tireSize)) *
                FrontEmpiricalCoefficientSize24

    private fun rearPressureSize24(
        riderWeight: Double,
        bikeWeight: Double,
        wheelSize: Double,
        tireSize: Double
    ): Double =
        ((riderWeight * RearFactorSize24 + bikeWeight * RearFactorSize24) / (wheelSize * tireSize)) *
                RearEmpiricalCoefficientSize24
}

/**
 * Эмпирический коэффициент, который можно определить на основании опыта и
 * различных тестов.
 *
 * Для MTB велосипедов его значение находится в диапазоне от 50 до 100.
 * Для шоссейных велосипедов его значение находится в диапазоне от 100 до 150.
 */
private const val FrontEmpiricalCoefficientSize29 = 71
private const val RearEmpiricalCoefficientSize29 = 65
private const val FrontFactorSize29 = 0.5
private const val RearFactorSize29 = 0.585

private const val FrontEmpiricalCoefficientSize28 = 120
private const val RearEmpiricalCoefficientSize28 = 120
private const val FrontFactorSize28 = 0.42
private const val RearFactorSize28 = 0.445

private const val FrontEmpiricalCoefficientSize275 = 70
private const val RearEmpiricalCoefficientSize275 = 65
private const val FrontFactorSize275 = 0.50
private const val RearFactorSize275 = 0.60

private const val FrontEmpiricalCoefficientSize26 = 70
private const val RearEmpiricalCoefficientSize26 = 65
private const val FrontFactorSize26 = 0.50
private const val RearFactorSize26 = 0.60

private const val FrontEmpiricalCoefficientSize24 = 70
private const val RearEmpiricalCoefficientSize24 = 60
private const val FrontFactorSize24 = 0.50
private const val RearFactorSize24 = 0.50
