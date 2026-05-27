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

// Astrid
// ===== MAIN ACTIVITY - Appens indgangspunkt =====
// I en moderne Android-app (Single Activity Architecture) fungerer MainActivity som den primære "container".
// I stedet for at skifte mellem forskellige Activities, skifter vi mellem forskellige "Screens" (Composables) 
// inde i denne ene Activity ved hjælp af Jetpack Compose Navigation.
class MainActivity : ComponentActivity() {
    
    // onCreate er den første funktion, der kører, når appen starter.
    // Det er her, vi opsætter UI-konteksten.
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        // Gør det muligt for appen at tegne helt ud til kanten af skærmen (bag statusbar og navigationsbar).
        enableEdgeToEdge()
        
        // setContent definerer appens layout ved hjælp af Jetpack Compose.
        setContent {
            
            // ===== NAVIGATION SETUP =====
            // NavController er hjernen bag navigationen. Den holder styr på, hvilken skærm vi er på,
            // og gemmer historikken (backstack), så brugeren kan gå tilbage.
            val navController = rememberNavController()
            
            // Vi observerer den aktuelle rute (currentRoute), så vi ved, om vi skal vise bundmenuen (NavBar).
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentRoute = navBackStackEntry?.destination?.route

            // Liste over ruter, hvor BottomNavBar skal være synlig.
            // Vi skjuler den typisk på onboarding/skrædder-skærme for at holde fokus.
            val showNavBarScreens = listOf("home", "basket", "profile")

            // Appens overordnede tema (farver, skrifttyper osv.) defineret i ui/theme/Theme.kt.
            EksamensProjektNeverAnotherTheme {
                
                // Column fungerer som en vertikal container.
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(backgroundColor) // Baggrundsfarven fra vores tema.
                ) {
                    
                    // ===== APP NAVIGATION (NavHost) =====
                    // Her kalder vi vores hoved-navigationskomponent.
                    // weight(1f) gør, at navigationen tager alt den ledige plads, så NavBar skubbes til bunden.
                    AppNavigation(
                        navController = navController,
                        modifier = Modifier.weight(1f)
                    )

                    // Vi viser kun BottomNavBar, hvis den aktuelle rute er med i vores liste.
                    if (currentRoute in showNavBarScreens) {
                        BottomNavBar(
                            currentScreen = currentRoute ?: "home",
                            onTabClick = { route ->
                                // Når man klikker på et ikon i menuen, navigerer vi til den tilsvarende rute.
                                navController.navigate(route) {
                                    // popUpTo("home") sikrer, at vi ikke bygger en uendelig stak af skærme,
                                    // men altid vender tilbage til basen (Home).
                                    popUpTo("home") { saveState = true }
                                    // Undgår at åbne den samme skærm flere gange oven i hinanden.
                                    launchSingleTop = true
                                    // Gendanner skærmens tilstand (f.eks. scroll-position), når man går tilbage.
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
