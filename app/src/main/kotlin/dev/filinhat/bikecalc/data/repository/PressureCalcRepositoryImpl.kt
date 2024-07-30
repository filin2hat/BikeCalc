package dev.filinhat.bikecalc.data.repository

import dev.filinhat.bikecalc.common.enums.tire.TireSize
import dev.filinhat.bikecalc.common.enums.wheel.WheelSize
import dev.filinhat.bikecalc.data.model.PressureCoefficients
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class PressureCalcRepositoryImpl @Inject constructor() : PressureCalcRepository {
    override fun calcPressure(
        riderWeight: Double,
        bikeWeight: Double,
        wheelSize: WheelSize,
        tireSize: TireSize
    ): Flow<Pair<Double, Double>> = flow {
        val coefficients = pressureCoefficientsMap[wheelSize]
            ?: throw IllegalArgumentException("Unsupported wheel size: $wheelSize")

        val frontPressure = calculatePressure(
            riderWeight,
            bikeWeight,
            wheelSize.inchesSize,
            tireSize.tireWidthInMillimeters,
            coefficients,
            isFront = true
        )
        val rearPressure = calculatePressure(
            riderWeight,
            bikeWeight,
            wheelSize.inchesSize,
            tireSize.tireWidthInMillimeters,
            coefficients,
            isFront = false
        )

        emit(frontPressure to rearPressure)
    }

    @Suppress("LongParameterList")
    private fun calculatePressure(
        riderWeight: Double,
        bikeWeight: Double,
        wheelSize: Double,
        tireSize: Double,
        coefficients: PressureCoefficients,
        isFront: Boolean
    ): Double {
        val factor = if (isFront) coefficients.frontFactor else coefficients.rearFactor
        val empiricalCoefficient =
            if (isFront) coefficients.frontEmpiricalCoefficient else coefficients.rearEmpiricalCoefficient

        return ((riderWeight * factor + bikeWeight * factor) / (wheelSize * tireSize)) * empiricalCoefficient
    }

    @Suppress("MagicNumber")
    private val pressureCoefficientsMap = mapOf(
        // WheelSize.Inches24 to PressureCoefficients(0.50, 0.60, 70.0, 65.0),
        // WheelSize.Inches26 to PressureCoefficients(0.50, 0.60, 70.0, 65.0),
        WheelSize.Inches275 to PressureCoefficients(0.5, 0.6, 68.0, 62.5),
        // WheelSize.Inches28 to PressureCoefficients(0.42, 0.445, 120.0, 120.0),
        WheelSize.Inches29 to PressureCoefficients(0.52, 0.60, 71.0, 65.0)
    )
}
