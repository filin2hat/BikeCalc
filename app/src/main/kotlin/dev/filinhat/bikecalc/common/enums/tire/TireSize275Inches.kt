package dev.filinhat.bikecalc.common.enums.tire

/**
 * Список размеров для 27.5-х дюймовых колес велосипеда.
 */
@Suppress("MagicNumber")
enum class TireSize275Inches(
    override val nameSize: String,
    override val tireWidthInInches: Double,
    override val tireWidthInMillimeters: Double,
) : TireSize {
    Size650x25b("27.5 x 1.00 (25 - 584)", 1.00, 19.2),
    Size650x28B("27.5 x 1.10 (28 - 584)", 1.10, 25.0),
    Size275x120("27.5 x 1.20 (30 - 584)", 1.20, 30.2),
    Size275x130("27.5 x 1.30 (33 - 584)", 1.30, 34.0),
    Size275x135("27.5 x 1.35 (33 - 584)", 1.35, 37.8),
    Size275x140("27.5 x 1.40 (35 - 584)", 1.40, 38.70),
    Size275x150("27.5 x 1.50 (38 - 584)", 1.50, 41.1),
    Size275x160("27.5 x 1.60 (41 - 584)", 1.60, 42.64),
    Size275x165("27.5 x 1.65 (44 - 584)", 1.65, 43.40),
    Size275x170("27.5 x 1.70 (43 - 584)", 1.70, 45.2),
    Size275x175("27.5 x 1.75 (47 - 584)", 1.75, 47.0),
    Size275x180("27.5 x 1.80 (45 - 584)", 1.80, 47.00),
    Size275x185("27.5 x 1.85 (47 - 584)", 1.85, 48.00),
    Size275x190("27.5 x 1.90 (48 - 584)", 1.90, 48.90),
    Size275x195("27.5 x 1.95 (49 - 584)", 1.95, 49.90),
    Size275x200("27.5 x 2.00 (50 - 584)", 2.00, 50.8),
    Size275x205("27.5 x 2.05 (52 - 584)", 2.05, 52.07),
    Size275x210("27.5 x 2.10 (54 - 584)", 2.10, 53.34),
    Size275x220("27.5 x 2.20 (56 - 584)", 2.20, 55.88),
    Size275x225("27.5 x 2.25 (57 - 584)", 2.25, 57.15),
    Size275x230("27.5 x 2.30 (58 - 584)", 2.30, 58.42),
    Size275x235("27.5 x 2.35 (60 - 584)", 2.35, 59.69),
    Size275x240("27.5 x 2.40 (61 - 584)", 2.40, 60.96),
    Size275x250("27.5 x 2.50 (64 - 584)", 2.50, 63.5),
    Size275x260("27.5 x 2.60 (65 - 584)", 2.60, 66.04),
    Size275x270("27.5 x 2.70 (68 - 584)", 2.70, 67.58),
    Size275x275("27.5 x 2.75 (70 - 584)", 2.75, 68.35),
    Size275x280("27.5 x 2.80 (72 - 584)", 2.80, 72.05),
    Size275x290("27.5 x 2.90 (73 - 584)", 2.90, 73.96),
    Size275x300("27.5 x 3.00 (76 - 584)", 3.00, 76.20),
    Size275x380("27.5 x 3.80 (96 - 584)", 3.80, 96.52),

}
