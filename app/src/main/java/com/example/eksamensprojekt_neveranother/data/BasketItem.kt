package com.example.eksamensprojekt_neveranother.data

// ===== BASKET ITEM MODEL =====
// En simpel data-class, der definerer, hvad et produkt i kurven indeholder.
// Vi placerer den i 'data' pakken, da det er en ren data-model (POJO/DTO),
// som ikke indeholder logik, men kun beskriver strukturen af en vare.
data class BasketItem(
    val navn: String,      // Produktnavn (fx "OneBra™")
    val farve: String,     // Valgt farve
    val pris: String,      // Pris formateret som tekst
    val billedeRes: Int    // Ressource-ID til billedet (R.drawable...)
)
