package com.example.eksamensprojekt_neveranother.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.eksamensprojekt_neveranother.data.Measurement

// ===== MEASUREMENT VIEW MODEL =====
// I MVVM-arkitekturen fungerer ViewModel som bindeleddet mellem data (Model) og brugerflade (View).
// Denne ViewModel holder styr på alle de målinger, som brugeren indtaster i "Skrædder-flowet".
class MeasurementViewModel : ViewModel() {
    
    // mutableStateOf er et Compose-værktøj, der gør variabler "intelligente".
    // Når værdien af tailorState ændrer sig, får alle UI-komponenter (Screens), der bruger denne variabel,
    // besked på automatisk at genoptegne sig selv (Recomposition).
    // 'private set' sikrer, at UI'en kun kan læse data, men ikke ændre dem direkte. 
    // Ændringer skal ske via funktionerne nedenfor (Encapsulation).
    var measurement by mutableStateOf(Measurement())
        private set

    // Holder styr på, om brugeren har færdiggjort hele skrædder-processen.
    var isTailored by mutableStateOf(false)
        private set

    // Tilstand for om vi viser illustration eller video i vores MeasurementTemplate.
    // Ved at gemme dette i ViewModel, bevares brugerens valg selvom skærmen genopbygges.
    var showIllustration by mutableStateOf(true)
        private set

    fun toggleIllustration() {
        showIllustration = !showIllustration
    }

    // ===== LOGIC FOR RESULT SCREEN =====

    // Mapper det valgte volumen-indeks til en læselig tekst.
    // Dette valg er truffet for at adskille den rå data (et heltal/indeks) fra præsentationslaget (tekst).
    // Ved at definere listen her, sikrer vi en "Single Source of Truth", så UI'en ikke selv skal gætte,
    // hvad f.eks. indeks 2 betyder.
    fun getFormattedVolume(): String {
        // En lokal liste over strenge der korresponderer med de valgmuligheder, brugeren har i UI'en.
        val options = listOf(
            "fast fylde i toppen",
            "blød fylde i toppen",
            "fast fylde i bunden",
            "blød fylde i bunden"
        )
        // Validering: Vi tjekker om det gemte indeks faktisk findes i listen (indgår i indices-range).
        // Dette forhindrer "IndexOutOfBoundsException", hvis dataen mod forventning skulle være korrupt.
        return if (measurement.selectedVolume in options.indices) {
            // Returnerer den menneskeligt læselige streng baseret på indekset.
            options[measurement.selectedVolume]
        } else {
            "-"
        }
    }

    // Disse funktioner kaldes fra de forskellige skærme (f.eks. UpperMeasurementsScreen).
    // Vi bruger .copy() på vores data class for at skabe en ny version af tilstanden med den opdaterede værdi.
    // Dette er god praksis i Kotlin for at sikre "Immutability" (uforanderlighed).

    fun updateUpper(value: String) {
        measurement = measurement.copy(upperCircumference = value)
    }

    fun updateLower(value: String) {
        measurement = measurement.copy(lowerCircumference = value)
    }

    fun updateHeight(value: String) {
        measurement = measurement.copy(height = value)
    }

    fun updateWidth(value: String) {
        measurement = measurement.copy(width = value)
    }

    fun updateVolume(value: Int) {
        measurement = measurement.copy(selectedVolume = value)
    }

    fun completeMeasurement() {
        isTailored = true
    }
}
