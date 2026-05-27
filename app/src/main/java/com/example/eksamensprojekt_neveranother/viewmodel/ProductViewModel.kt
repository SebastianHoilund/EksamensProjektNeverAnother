package com.example.eksamensprojekt_neveranother.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.eksamensprojekt_neveranother.R
import com.example.eksamensprojekt_neveranother.data.BasketItem

// ===== PRODUCT VIEW MODEL =====
// Holder styr på brugerens valg på produktsiden.
class ProductViewModel : ViewModel() {

    // chosecolor gemmer den valgte farve ("White" eller "Black").
    // Når denne ændres, opdateres UI'en automatisk (recomposition),
    // så den orange ring flytter sig til den rigtige cirkel.
    var chosenColor by mutableStateOf("White")


    // isTailored: true når brugeren har gennemført measurement/måling
    // Styrer om knappen viser "Skræddersy BH" eller "Føj til kurv"
    // Styrer om "Se dine mål" knappen er synlig
    var isTailored by mutableStateOf(false)

    // Bestemmer dynamisk teksten på knappen i ProductScreen.
    fun getBtnText(): String {
        return if (isTailored) "Føj til Kurv" else "Skræddersy BH"
    }

    // Håndterer hvad der sker, når brugeren klikker på hovedknappen.
    fun onProductAction(
        cartViewModel: CartViewModel, 
        navigateToBasket: () -> Unit, 
        navigateToTailor: () -> Unit
    ) {
        if (isTailored) {
            // Hvis man er målt op, opretter vi et BasketItem (Model) og tilføjer det til kurven.
            cartViewModel.addItem(
                BasketItem(
                    navn = "OneBra™",
                    farve = chosenColor,
                    pris = "799,00",
                    billedeRes = R.drawable.productsitemodel1
                )
            )
            // Efter tilføjelse navigerer vi brugeren direkte til kurven.
            navigateToBasket()
        } else {
                // Hvis man ikke er målt op, sendes man til starten af skrædder-flowet.
            navigateToTailor()
        }
    }
}
