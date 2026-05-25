package com.example.eksamensprojekt_neveranother.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.eksamensprojekt_neveranother.R


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

    fun getBtnText(): String {
        return if (isTailored) "Føj til Kurv" else "Skræddersy BH"
    }

    fun onProductAction(cartViewModel: CartViewModel, navigateToBasket: () -> Unit, navigateToTailor: () -> Unit) {
        if (isTailored) {
            cartViewModel.addItem(
                BasketItem(
                    navn = "OneBra™",
                    farve = choseColor,
                    pris = "799,00",
                    billedeRes = R.drawable.productsitemodel1
                )
            )
            navigateToBasket()
        } else {
            navigateToTailor()
        }
    }
}
