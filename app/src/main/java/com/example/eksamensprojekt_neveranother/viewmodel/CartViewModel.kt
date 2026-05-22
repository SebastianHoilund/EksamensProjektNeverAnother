 package com.example.eksamensprojekt_neveranother.viewmodel

    import androidx.compose.runtime.mutableStateListOf
    import androidx.lifecycle.ViewModel



    data class BasketItem(
        val navn: String,
        val farve: String,
        val pris: String,
        val billedeRes: Int
    )


    class CartViewModel : ViewModel() {

        val items = mutableStateListOf<BasketItem>()

        fun addItem (item: BasketItem) {
            items.add(item)
        }

        fun deleteItem (item: BasketItem) {
            items.remove(item)
        }

    }
