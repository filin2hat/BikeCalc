package dev.filinhat.bikepressurecalc.common

object Calculator {

    fun mtbFrontPressure(
        riderWeight: Double,
        bikeWeight: Double,
        wheelSize: Double,
        tireSize: Double
    ): Double {
        return ((riderWeight * 0.5 + bikeWeight * 0.5) / (wheelSize * tireSize)) * mtbFrontEmpiricalCoefficient
    }

    fun mtbRearPressure(
        riderWeight: Double,
        bikeWeight: Double,
        wheelSize: Double,
        tireSize: Double
    ): Double {
        return ((riderWeight * 0.6 + bikeWeight * 0.6) / (wheelSize * tireSize)) * mtbRearEmpiricalCoefficient
    }

    fun roadFrontPressure(
        riderWeight: Double,
        bikeWeight: Double,
        wheelSize: Double,
        tireSize: Double
    ): Double {
        return ((riderWeight * 0.45 + bikeWeight * 0.45) / (wheelSize * tireSize)) * roadFrontEmpiricalCoefficient
    }

    fun roadRearPressure(
        riderWeight: Double,
        bikeWeight: Double,
        wheelSize: Double,
        tireSize: Double
    ): Double {
        return ((riderWeight * 0.55 + bikeWeight * 0.55) / (wheelSize * tireSize)) * roadRearEmpiricalCoefficient
    }
}
