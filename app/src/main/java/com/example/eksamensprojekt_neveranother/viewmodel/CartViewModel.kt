package com.example.eksamensprojekt_neveranother.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.example.eksamensprojekt_neveranother.data.BasketItem

// ===== CART VIEW MODEL =====
// Denne ViewModel fungerer som "sandheden" for brugerens indkøbskurv.
// Den lever i navigations-stakken (AppNavigation), så den bevarer sin data
// uanset om brugeren går til forsiden, profilen eller produktsiden.
class CartViewModel : ViewModel() {

    // mutableStateListOf er en speciel liste i Compose. 
    // Når man tilføjer eller fjerner elementer fra listen, opdaterer UI'en (f.eks. BasketScreen) 
    // sig selv automatisk uden at man skal kalde "refresh".
    // Vi bruger BasketItem-modellen, som nu ligger i 'data' pakken.
    val items = mutableStateListOf<BasketItem>()

    // Tilføjer en ny vare til kurven.
    fun addItem (item: BasketItem) {
        items.add(item)
    }

    // Fjerner en specifik vare fra kurven.
    fun deleteItem (item: BasketItem) {
        items.remove(item)
    }

    // ===== FORRETNINGSLOGIK (BUSINESS LOGIC) =====
    // En stor fordel ved ViewModels er, at vi kan flytte logik væk fra selve skærmene.
    // Denne funktion bestemmer, hvad teksten på knapperne skal være baseret på appens samlede tilstand.
    fun getBtnText(isTailored: Boolean): String {
        return when {
            items.isNotEmpty() -> "Check ud"       // Prioritet 1: Hvis der er varer, skal man betale.
            isTailored -> "Se din BH"              // Prioritet 2: Hvis man er målt op, skal man se sit produkt.
            else -> "Skræddersy BH"               // Default: Start onboarding.
        }
    }

    // Bestemmer hvor knappen skal navigere hen.
    fun getBtnNavigation(isTailored: Boolean): String {
        return when {
            items.isNotEmpty() -> "checkout"
            isTailored -> "product"
            else -> "tailor_start"
        }
    }
}
