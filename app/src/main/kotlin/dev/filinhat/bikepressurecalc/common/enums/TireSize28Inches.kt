package dev.filinhat.bikepressurecalc.common.enums

/**
 * Список размеров для 28 дюймовых колес велосипеда.
 */
enum class TireSize28Inches(
    override val tireSize: Double,
) : TireSize {
    Size28x075(0.75),
    Size28x090(0.90),
    Size28x095(0.95),
    Size28x100(1.00),
    Size28x105(1.05),
    Size28x107(1.07),
    Size28x110(1.10),
    Size28x120(1.20),
    Size28x125(1.25),
    Size28x130(1.30),
    Size28x135(1.35),
    Size28x140(1.40),
    Size28x150(1.50),
    Size28x1625(1.625),
    Size28x165(1.65),
    Size28x170(1.70),
    Size28x175(1.75),
    Size28x200(2.00),
    Size28x215(2.15),
    Size28x220(2.20),
    Size28x2350(2.350),
    Size28x240(2.40),
}
