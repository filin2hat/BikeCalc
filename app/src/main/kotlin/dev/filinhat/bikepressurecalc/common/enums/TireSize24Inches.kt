package dev.filinhat.bikepressurecalc.common.enums

/**
 * Список размеров для 24-х дюймовых колес велосипеда.
 */
enum class TireSize24Inches(
    override val tireSize: Double,
) : TireSize {
    Size24x075(0.75),
    Size24x090(0.90),
    Size24x100(1.00),
    Size24x120(1.20),
    Size24x1375(1.375),
    Size24x150(1.50),
    Size24x175(1.75),
    Size24x195(1.95),
    Size24x200(2.00),
    Size24x210(2.10),
    Size24x2125(2.125),
    Size24x215(2.15),
    Size24x220(2.20),
    Size24x225(2.25),
    Size24x230(2.30),
    Size24x235(2.35),
    Size24x240(2.40),
    Size24x250(2.50),
    Size24x300(3.00),
    Size24x350(3.50),
    Size24x400(4.00),
}
