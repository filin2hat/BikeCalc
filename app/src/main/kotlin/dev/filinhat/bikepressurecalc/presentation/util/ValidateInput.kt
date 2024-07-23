package dev.filinhat.bikepressurecalc.presentation.util


fun validateRiderWeight(input: String): Boolean {
    return input.toDoubleOrNull()?.let { it in 10.0..200.0 } == true
}

fun validateBikeWeight(input: String): Boolean {
    return input.toDoubleOrNull()?.let { it in 1.0..200.0 } == true
}