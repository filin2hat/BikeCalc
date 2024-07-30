package dev.filinhat.bikecalc.presentation.util

private const val MinPressure = 1.0
private const val MaxPressure = 200.0

/**
 * Валидация ввода значения веса.
 */
fun validateWeight(input: String): Boolean {
    return input.toDoubleOrNull()?.let { it in MinPressure..MaxPressure } == true
}
