package com.example.eksamensprojekt_neveranother.viewmodel

import androidx.lifecycle.ViewModel

// ===== TAILOR VIEW MODEL =====
// Denne ViewModel håndterer data og logik for de statiske skærme i skrædder-flowet.
// Selvom skærmene er simple, sikrer vi her at al tekst og navigation styres centralt.
class TailorViewModel : ViewModel() {

    // Data til TailorStartScreen
    val startTitle = "Lad os komme i gang!"
    val requirements = listOf("1. Et blødt målebånd", "2. Din bedste passende BH")
    
    // Ruter (Navigation)
    val routeToMeasurements = "upper_measurements"
    val routeToHeight = "height_measurements"
    val routeToResult = "result_screen"
    val routeHome = "home"

    // Tekst til MidwayScreen
    val midwayTitle = "Godt gået!"
    val midwayDescription = "Du er nu halvvejs. \nLad os fortsætte med de sidste mål."
    
    // Tekst til ResultLoadingScreen
    val loadingText = "Beregner din unikke profil..."
}
