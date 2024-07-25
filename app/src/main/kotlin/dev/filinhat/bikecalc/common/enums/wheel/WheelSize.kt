package dev.filinhat.bikecalc.common.enums.wheel

/**
 * Список диаметров колес велосипеда в дюймах.
 *
 * @param nameSize название размера в дюймах и диаметр по стандарту ISO
 * @param inchesSize диаметр в дюймах
 */
enum class WheelSize(
    val nameSize: String,
    val inchesSize: Double,
) {
    Inches24("24\" (ISO 507 - 540 mm)", 24.0),
    Inches26("26\" (ISO 559 - 590 mm)", 26.0),
    Inches275("27.5\" (ISO 584 mm / 650B)", 27.5),
    Inches28("28\" (ISO 622 / 700C mm)", 28.0),
    Inches29("29\" (ISO 622  mm)", 29.0),
}