package com.example.eksamensprojekt_neveranother.ui.screens.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.eksamensprojekt_neveranother.ui.screens.basket.BasketScreen
import com.example.eksamensprojekt_neveranother.ui.screens.home.HomeScreen
import com.example.eksamensprojekt_neveranother.ui.screens.profile.ProfilScreen

@Composable
fun AppNavigation(
    navController: NavHostController, 
    navigateTo: (String) -> Unit, 
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = "home-screen",
        modifier = modifier
    ) {
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
    }
}
