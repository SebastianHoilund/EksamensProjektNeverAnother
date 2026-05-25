package com.example.eksamensprojekt_neveranother.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.eksamensprojekt_neveranother.data.TailorState

class MeasurementViewModel : ViewModel() {
    var tailorState by mutableStateOf(TailorState())
        private set

    var isTailored by mutableStateOf(false)
        private set

    fun updateUpper(value: String) {
        tailorState = tailorState.copy(upperCircumference = value)
    }

    fun updateLower(value: String) {
        tailorState = tailorState.copy(lowerCircumference = value)
    }

    fun updateHeight(value: String) {
        tailorState = tailorState.copy(height = value)
    }

    fun updateWidth(value: String) {
        tailorState = tailorState.copy(width = value)
    }

    fun updateVolume(value: Int) {
        tailorState = tailorState.copy(selectedVolume = value)
    }

    fun completeMeasurement() {
        isTailored = true
    }
}
