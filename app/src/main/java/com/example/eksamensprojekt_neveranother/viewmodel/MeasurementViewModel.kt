package com.example.eksamensprojekt_neveranother.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.eksamensprojekt_neveranother.data.TailorState

// ===== MEASUREMENT VIEW MODEL =====
// I MVVM-arkitekturen fungerer ViewModel som bindeleddet mellem data (Model) og brugerflade (View).
// Denne ViewModel holder styr på alle de målinger, som brugeren indtaster i "Skrædder-flowet".
class MeasurementViewModel : ViewModel() {
    
    // mutableStateOf er et Compose-værktøj, der gør variabler "intelligente".
    // Når værdien af tailorState ændrer sig, får alle UI-komponenter (Screens), der bruger denne variabel,
    // besked på automatisk at genoptegne sig selv (Recomposition).
    // 'private set' sikrer, at UI'en kun kan læse data, men ikke ændre dem direkte. 
    // Ændringer skal ske via funktionerne nedenfor (Encapsulation).
    var tailorState by mutableStateOf(TailorState())
        private set

    // Holder styr på, om brugeren har færdiggjort hele skrædder-processen.
    var isTailored by mutableStateOf(false)
        private set

    // Disse funktioner kaldes fra de forskellige skærme (f.eks. UpperMeasurementsScreen).
    // Vi bruger .copy() på vores data class for at skabe en ny version af tilstanden med den opdaterede værdi.
    // Dette er god praksis i Kotlin for at sikre "Immutability" (uforanderlighed).

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
