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
import com.example.eksamensprojekt_neveranother.viewmodel.ProfileViewModel
import com.example.eksamensprojekt_neveranother.viewmodel.TailorViewModel

// Astrid
// ===== APP NAVIGATION - Navigations-hjertet =====
// Denne fil definerer alle de "ruter" (skærme), som appen har.
// Den benytter Jetpack Compose Navigation til at skifte mellem skærme uden at genstarte appen.
@Composable
fun AppNavigation(
    navController: NavHostController, 
    modifier: Modifier = Modifier,
    
    // ===== MVVM & VIEWMODELS =====
    // Her instantierer vi vores ViewModels ved hjælp af viewModel().
    // Fordi de defineres her i toppen af navigationsstakken, kan de deles på tværs af flere skærme.
    // Det betyder, at data (f.eks. varer i kurven) ikke forsvinder, når man skifter skærm.
    cartViewModel: CartViewModel = viewModel(),
    productViewModel: ProductViewModel = viewModel(),
    measurementViewModel: MeasurementViewModel = viewModel(),
    profileViewModel: ProfileViewModel = viewModel(),
    tailorViewModel: TailorViewModel = viewModel()
) {
    // LaunchedEffect lytter på ændringer i measurementViewModel.isTailored.
    // Når brugeren har gennemført målingerne, opdaterer vi ProductViewModel,
    // så produktsiden ved, at den skal vise den skræddersyede version af BH'en.
    LaunchedEffect(measurementViewModel.isTailored) {
        if (measurementViewModel.isTailored) {
            productViewModel.isTailored = true
        }
    }

    // ===== NAVHOST =====
    // NavHost er containeren, hvor de enkelte skærme bliver vist.
    // navController holder styr på tilstanden (hvor er vi nu?), 
    // og startDestination angiver, hvilken skærm der skal vises først.
    NavHost(
        navController = navController,
        startDestination = "home", // Appen starter altid på forsiden.
        modifier = modifier
    ) {
        // Hver "composable" repræsenterer en rute i appen.
        
        composable("home") {
            HomeScreen(
                navController = navController,
                isTailored = productViewModel.isTailored
            )
        }
        
        composable("basket") {
            BasketScreen(
                navController = navController,
                viewModel = cartViewModel, // Vi sender den fælles cartViewModel med ned.
                isTailored = productViewModel.isTailored
            )
        }
        
        composable("profile") {
            ProfileScreen(
                viewModel = profileViewModel,
                goToHome = { navController.navigate("home") }
            )
        }
        
        composable("product") {
            ProductScreen(
                navController = navController,
                viewModel = productViewModel,
                cartViewModel = cartViewModel
            )
        }
        
        // Dette flow består af mange skærme, der alle deler measurementViewModel
        // for at opsamle brugerens input trin for trin.
        composable("tailor_start") {
            TailorStartScreen(
                navController = navController,
                viewModel = tailorViewModel
            )
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
            MidwayScreen(
                navController = navController,
                viewModel = tailorViewModel
            )
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
            ResultLoadingScreen(
                navController = navController,
                viewModel = tailorViewModel
            )
        }
        
        composable("result_screen") {
            ResultScreen(
                navController = navController,
                viewModel = measurementViewModel,
                onSeeProduct = {
                    // Når brugeren klikker "Se produkt", markerer vi processen som færdig.
                    measurementViewModel.completeMeasurement()
                    navController.navigate("product") {
                        // popUpTo("home") rydder navigations-historikken,
                        // så man ikke kan gå "tilbage" ind i skrædder-flowet efter færdiggørelse.
                        popUpTo("home")
                    }
                }
            )
        }
    }
}
