package dev.filinhat.bikepressurecalc.presentation.screen

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.filinhat.bikepressurecalc.data.repository.PressureCalcRepository
import javax.inject.Inject

@HiltViewModel
class PressureCalculatorViewModel @Inject constructor(
    private val repository: PressureCalcRepository
) : ViewModel() {
}
