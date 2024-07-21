package dev.filinhat.bikepressurecalc.common.enums

/**
 * Список диаметров колес велосипеда в дюймах.
 *
 * @param inchesSize диаметр в дюймах
 */
enum class WheelSize(
    var inchesSize: Double,
) {
    Inches24(24.0),
    Inches26(26.0),
    Inches27dot5(27.5),
    Inches28(28.0),
    Inches29(29.0),
}
