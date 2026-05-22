package com.example.eksamensprojekt_neveranother.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel


// ===== PRODUCT VIEW MODEL =====
// Holder styr på brugerens valg på produktsiden
class ProductViewModel : ViewModel() {

    // choseColor: gemmer den valgte farve ("White" eller "Black")
    // Bruges til at vise orange ring om den valgte cirkel
    // Sendes med som BasketItem når man trykker "Føj til kurv"
    var choseColor by mutableStateOf("")


    // isTailored: true når brugeren har gennemført measurement/måling
    // Styrer om knappen viser "Skræddersy BH" eller "Føj til kurv"
    // Styrer om "Se dine mål" knappen er synlig
    var isTailored by mutableStateOf(false)

}