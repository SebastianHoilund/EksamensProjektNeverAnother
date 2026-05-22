package com.example.eksamensprojekt_neveranother.ui.screens.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.eksamensprojekt_neveranother.ui.screens.basket.BasketScreen
import com.example.eksamensprojekt_neveranother.ui.screens.home.HomeScreen
import com.example.eksamensprojekt_neveranother.ui.screens.profile.ProfileScreen
import com.example.eksamensprojekt_neveranother.data.TailorState
import com.example.eksamensprojekt_neveranother.ui.screens.product.ProductScreen
import com.example.eksamensprojekt_neveranother.ui.screens.tailor.HeightMeasurementsScreen
import com.example.eksamensprojekt_neveranother.ui.screens.tailor.LowerMeasurementsScreen
import com.example.eksamensprojekt_neveranother.ui.screens.tailor.MidwayScreen
import com.example.eksamensprojekt_neveranother.ui.screens.tailor.ResultLoadingScreen
import com.example.eksamensprojekt_neveranother.ui.screens.tailor.ResultScreen
import com.example.eksamensprojekt_neveranother.ui.screens.tailor.TailorStartScreen
import com.example.eksamensprojekt_neveranother.ui.screens.tailor.UpperMeasurementsScreen
import com.example.eksamensprojekt_neveranother.ui.screens.tailor.VolumeSelectionScreen
import com.example.eksamensprojekt_neveranother.ui.screens.tailor.WidthMeasurementsScreen
import com.example.eksamensprojekt_neveranother.viewmodel.CartViewModel
import com.example.eksamensprojekt_neveranother.viewmodel.ProductViewModel

@Composable
fun AppNavigation(
    navController: NavHostController, 
    modifier: Modifier = Modifier,
    cartViewModel: CartViewModel = viewModel(),
    productViewModel: ProductViewModel = viewModel()
) {
    // Temporary state management without a ViewModel
    var tailorState by remember { mutableStateOf(TailorState()) }

    NavHost(
        navController = navController,
        startDestination = "home",
        modifier = modifier
    ) {
        composable("home") {
            HomeScreen(
                navController = navController,
                isTailored = productViewModel.isTailored
            )
        }
        composable("basket") {
            BasketScreen(
                navController = navController,
                viewModel = cartViewModel,
                isTailored = productViewModel.isTailored
            )
        }
        composable("profile") {
            ProfileScreen(
                goToHome = { navController.navigate("home") },
                goToBasket = { navController.navigate("basket") }
            )
        }
        composable("product") {
            ProductScreen(
                navController = navController,
                viewModel = productViewModel,
                cartViewModel = cartViewModel
            )
        }
        composable("tailor_start") {
            TailorStartScreen(navController = navController)
        }
        composable("upper_measurements") {
            UpperMeasurementsScreen(
                navController = navController,
                initialValue = tailorState.upperCircumference,
                onValueSaved = { tailorState = tailorState.copy(upperCircumference = it) }
            )
        }
        composable("lower_measurements") {
            LowerMeasurementsScreen(
                navController = navController,
                initialValue = tailorState.lowerCircumference,
                onValueSaved = { tailorState = tailorState.copy(lowerCircumference = it) }
            )
        }
        composable("midway") {
            MidwayScreen(navController = navController)
        }
        composable("height_measurements") {
            HeightMeasurementsScreen(
                navController = navController,
                initialValue = tailorState.height,
                onValueSaved = { tailorState = tailorState.copy(height = it) }
            )
        }
        composable("width_measurements") {
            WidthMeasurementsScreen(
                navController = navController,
                initialValue = tailorState.width,
                onValueSaved = { tailorState = tailorState.copy(width = it) }
            )
        }
        composable("volume_selection") {
            VolumeSelectionScreen(
                navController = navController,
                initialOption = tailorState.selectedVolume,
                onOptionSaved = { tailorState = tailorState.copy(selectedVolume = it) }
            )
        }
        composable("result_loading") {
            ResultLoadingScreen(navController = navController)
        }
        composable("result_screen") {
            ResultScreen(
                navController = navController,
                state = tailorState,
                onSeeProduct = {
                    productViewModel.isTailored = true
                    navController.navigate("product") {
                        popUpTo("home")
                    }
                }
            )
        }
    }
}
