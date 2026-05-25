package com.example.eksamensprojekt_neveranother.ui.screens.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.eksamensprojekt_neveranother.ui.screens.basket.BasketScreen
import com.example.eksamensprojekt_neveranother.ui.screens.home.HomeScreen
import com.example.eksamensprojekt_neveranother.ui.screens.profile.ProfileScreen
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
import com.example.eksamensprojekt_neveranother.viewmodel.MeasurementViewModel

@Composable
fun AppNavigation(
    navController: NavHostController, 
    modifier: Modifier = Modifier,
    cartViewModel: CartViewModel = viewModel(),
    productViewModel: ProductViewModel = viewModel(),
    measurementViewModel: MeasurementViewModel = viewModel()
) {
    // Sync isTailored from MeasurementViewModel to ProductViewModel
    LaunchedEffect(measurementViewModel.isTailored) {
        if (measurementViewModel.isTailored) {
            productViewModel.isTailored = true
        }
    }

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
                viewModel = measurementViewModel
            )
        }
        composable("lower_measurements") {
            LowerMeasurementsScreen(
                navController = navController,
                viewModel = measurementViewModel
            )
        }
        composable("midway") {
            MidwayScreen(navController = navController)
        }
        composable("height_measurements") {
            HeightMeasurementsScreen(
                navController = navController,
                viewModel = measurementViewModel
            )
        }
        composable("width_measurements") {
            WidthMeasurementsScreen(
                navController = navController,
                viewModel = measurementViewModel
            )
        }
        composable("volume_selection") {
            VolumeSelectionScreen(
                navController = navController,
                viewModel = measurementViewModel
            )
        }
        composable("result_loading") {
            ResultLoadingScreen(navController = navController)
        }
        composable("result_screen") {
            ResultScreen(
                navController = navController,
                viewModel = measurementViewModel,
                onSeeProduct = {
                    measurementViewModel.completeMeasurement()
                    navController.navigate("product") {
                        popUpTo("home")
                    }
                }
            )
        }
    }
}
