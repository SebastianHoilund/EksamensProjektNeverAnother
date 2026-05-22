package com.example.eksamensprojekt_neveranother

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.eksamensprojekt_neveranother.ui.screens.navigation.AppNavigation
import com.example.eksamensprojekt_neveranother.ui.screens.navigation.BottomNavBar
import com.example.eksamensprojekt_neveranother.ui.theme.EksamensProjektNeverAnotherTheme
import com.example.eksamensprojekt_neveranother.ui.theme.backgroundColor

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentRoute = navBackStackEntry?.destination?.route

            val showNavBarScreens = listOf("home", "basket", "profile")

            EksamensProjektNeverAnotherTheme {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(backgroundColor)
                ) {
                    AppNavigation(
                        navController = navController,
                        modifier = Modifier.weight(1f)
                    )

                    if (currentRoute in showNavBarScreens) {
                        BottomNavBar(
                            currentScreen = currentRoute ?: "home",
                            onTabClick = { route ->
                                navController.navigate(route) {
                                    popUpTo("home") { saveState = true }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            }
                        )
                    }
                }
            }
        }
    }
}
