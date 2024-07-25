package dev.filinhat.bikepressurecalc.presentation.util


fun validateWeight(input: String): Boolean {
    return input.toDoubleOrNull()?.let { it in 1.0..200.0 } == true
}
