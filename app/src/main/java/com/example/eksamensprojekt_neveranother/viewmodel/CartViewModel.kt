package com.example.eksamensprojekt_neveranother.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel


// ===== CART VIEW MODEL =====
// Holder styr på varerne i kurven
data class BasketItem( // BasketItem: data class der repræsenterer én vare
    val navn: String,// navn: produktnavn (fx "OneBra™")
    val farve: String,// farve: valgt farve (fx "White")
    val pris: String,// pris: pris som tekst (fx "799,00")
    val billedeRes: Int// billedeRes: ressource-ID til billedet af produktet
)


class CartViewModel : ViewModel() {

    val items = mutableStateListOf<BasketItem>()// items: liste af varer der automatisk opdaterer UI

    fun addItem (item: BasketItem) {// addItem(): tilføjer en ny vare til kurven
        items.add(item)
    }

    fun deleteItem (item: BasketItem) {// deleteItem(): fjerner en vare fra kurven
        items.remove(item)
    }

}