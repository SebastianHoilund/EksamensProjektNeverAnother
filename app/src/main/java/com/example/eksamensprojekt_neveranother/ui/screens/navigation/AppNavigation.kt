package com.example.eksamensprojekt_neveranother.ui.screens.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
// import com.example.eksamensprojekt_neveranother.ui.screens.basket.BasketScreen
// import com.example.eksamensprojekt_neveranother.ui.screens.home.HomeScreen
// import com.example.eksamensprojekt_neveranother.ui.screens.profile.ProfilScreen
import com.example.eksamensprojekt_neveranother.ui.screens.tailor.HeightMeasurementsScreen
import com.example.eksamensprojekt_neveranother.ui.screens.tailor.LowerMeasurementsScreen
import com.example.eksamensprojekt_neveranother.ui.screens.tailor.TailorStartScreen
import com.example.eksamensprojekt_neveranother.ui.screens.tailor.UpperMeasurementsScreen
import com.example.eksamensprojekt_neveranother.ui.screens.tailor.WidthMeasurementsScreen

@Composable
fun AppNavigation(
    navController: NavHostController, 
    navigateTo: (String) -> Unit, 
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        //startDestination = "home-screen",
        startDestination = "tailor_start",
        modifier = modifier
    ) {
        /*
        composable("home-screen") {
            HomeScreen(
                goToBasket = { navigateTo("basket-screen") },
                goToProfil = { navigateTo("profil-screen") }
            )
        }
        composable("basket-screen") {
            BasketScreen(
                goToHome = { navigateTo("home-screen") },
                goToProfil = { navigateTo("profil-screen") }
            )
        }
        composable("profil-screen") {
            ProfilScreen(
                goToHome = { navigateTo("home-screen") },
                goToBasket = { navigateTo("basket-screen") }
            )
        }
        */
        composable("tailor_start") {
            TailorStartScreen(navController = navController)
        }
        composable("upper_measurements") {
            UpperMeasurementsScreen(navController = navController)
        }
        composable("lower_measurements") {
            LowerMeasurementsScreen(navController = navController)
        }
        composable("height_measurements") {
            HeightMeasurementsScreen(navController = navController)
        }
        composable("width_measurements") {
            WidthMeasurementsScreen(navController = navController)
        }
    }
}
