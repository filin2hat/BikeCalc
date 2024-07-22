package dev.filinhat.bikepressurecalc.common.enums

/**
 * Список размеров для 28 дюймовых колес велосипеда.
 */
enum class TireSize28Inches(
    override val nameSize: String,
    override val tireWidthInInches: Double,
    override val tireWidthInMillimeters: Double,
) : TireSize {
    Size28x075("28 x 0.75 (19 - 622)", 0.75, 19.0),
    Size28x090("28 x 0.90 (23 - 622)", 0.90, 23.0),
    Size28x095("28 x 0.95 (24 - 622)", 0.95, 24.0),
    Size28x100("28 x 1.00 (25 - 622)", 1.00, 25.0),
    Size28x105("28 x 1.05 (26 - 622)", 1.05, 26.0),
    Size28x107("28 x 1.07 (27 - 622)", 1.07, 27.0),
    Size28x110("28 x 1.10 (28 - 622)", 1.10, 28.0),
    Size28x120("28 x 1.20 (30 - 622)", 1.20, 30.0),
    Size28x125("28 x 1.25 (32 - 622)", 1.25, 32.0),
    Size28x130("28 x 1.30 (33 - 622)", 1.30, 33.0),
    Size28x135("28 x 1.35 (35 - 622)", 1.35, 35.0),
    Size28x140("28 x 1.40 (37 - 622)", 1.40, 37.0),
    Size28x150("28 x 1.50 (38 - 622)", 1.50, 38.0),
    Size28x1625("28 x 1.625 (42 - 622)", 1.625, 42.0),
    Size28x165("28 x 1.65 (43 - 622)", 1.65, 43.0),
    Size28x170("28 x 1.70 (45 - 622)", 1.70, 45.0),
    Size28x175("28 x 1.75 (47 - 622)", 1.75, 47.0),
    Size28x200("28 x 2.00 (50 - 622)", 2.00, 50.0),
    Size28x215("28 x 2.15 (55 - 622)", 2.15, 55.0),
    Size28x220("28 x 2.20 (56 - 622)", 2.20, 56.0),
    Size28x235("28 x 2.35 (60 - 622)", 2.35, 60.0),
    Size28x240("28 x 2.40 (61 - 622)", 2.40, 61.0),
}
