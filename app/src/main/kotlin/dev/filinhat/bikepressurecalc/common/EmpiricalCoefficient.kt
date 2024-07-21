package dev.filinhat.bikepressurecalc.common

/**
 * K — эмпирический коэффициент, который можно определить на основании опыта и
 * различных тестов.
 * Для MTB велосипедов его значение находится в диапазоне от 50 до 100.
 * Для шоссейных велосипедов его значение находится в диапазоне от 100 до 150.
 */
const val mtbFrontEmpiricalCoefficient = 71
const val mtbRearEmpiricalCoefficient = 65
const val roadFrontEmpiricalCoefficient = 120
const val roadRearEmpiricalCoefficient = 120
