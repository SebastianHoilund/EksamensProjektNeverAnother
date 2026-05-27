package com.example.eksamensprojekt_neveranother.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

// ===== PROFILE VIEW MODEL =====
// Denne ViewModel holder styr på brugerens data på profil-siden.
// Ved at flytte data herover adskiller vi ProfileScreen data fra UI.
class ProfileViewModel : ViewModel() {

    // Brugerdata som mutableStateOf, så UI'en opdateres automatisk ved ændringer.
    var userName by mutableStateOf("Lisbeth")
    
    // Liste over menupunkter. I en rigtig app kunne disse hentes fra en database.
    val menuItems = listOf("Mine mål", "Ordre", "Gavekort")

    // Funktioner til at håndtere klik på knapperne i bunden.
    fun onLanguageSelect() {
        println("Sprog valgt")
    }

    fun onNewsletterSignup() {
        println("Tilmeldt nyhedsbrev")
    }

    fun onContactUs() {
        println("Kontakt os klikket")
    }
}
