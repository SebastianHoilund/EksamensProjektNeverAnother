package com.example.eksamensprojekt_neveranother

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.compose.rememberNavController
import com.example.eksamensprojekt_neveranother.ui.screens.basket.BasketScreen
import com.example.eksamensprojekt_neveranother.viewmodel.CartViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.eksamensprojekt_neveranother.viewmodel.BasketItem


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            val navController = rememberNavController()

            val cartViewModel: CartViewModel = viewModel()

            // MIDLERTIDIG TEST — slet efter test
                LaunchedEffect(Unit) {
                    cartViewModel.addItem(
                        BasketItem(
                            navn = "OneBra™",
                            farve = "White",
                            pris = "799.00",
                            billedeRes = R.drawable.onebra_tm_white_bra_model
                        )
                    )
                }


            BasketScreen(
                navController = navController,
                viewModel = cartViewModel
            )

        }
    }
}
