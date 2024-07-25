package dev.filinhat.bikecalc.presentation.util

/**
 * Валидация ввода значения веса.
 */
fun validateWeight(input: String): Boolean {
    return input.toDoubleOrNull()?.let { it in 1.0..200.0 } == true
}
