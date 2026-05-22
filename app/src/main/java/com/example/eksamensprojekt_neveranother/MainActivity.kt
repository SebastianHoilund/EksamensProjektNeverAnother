package com.example.eksamensprojekt_neveranother

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.example.eksamensprojekt_neveranother.ui.screens.basket.BasketScreen
import com.example.eksamensprojekt_neveranother.ui.screens.home.HomeScreen
import com.example.eksamensprojekt_neveranother.ui.screens.navigation.AppNavigation
import com.example.eksamensprojekt_neveranother.ui.screens.navigation.BottomNavBar
import com.example.eksamensprojekt_neveranother.ui.screens.product.ProductScreen
import com.example.eksamensprojekt_neveranother.ui.theme.EksamensProjektNeverAnotherTheme
import com.example.eksamensprojekt_neveranother.ui.theme.backgroundColor
import androidx.compose.runtime.setValue
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.eksamensprojekt_neveranother.ui.screens.navigation.AppNavigation
import com.example.eksamensprojekt_neveranother.ui.screens.tailor.MidwayScreen
import com.example.eksamensprojekt_neveranother.ui.screens.tailor.TailorStartScreen
import com.example.eksamensprojekt_neveranother.viewmodel.BasketItem
import com.example.eksamensprojekt_neveranother.viewmodel.CartViewModel
import com.example.eksamensprojekt_neveranother.viewmodel.ProductViewModel


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            val navController = rememberNavController() //remember husker hvor brugeren er i appen
            var currentScreen by remember { mutableStateOf("home-screen") }

            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentRoute = navBackStackEntry?.destination?.route

            val showNavBarScreens = listOf("home-screen", "basket-screen", "profil-screen")



            /*Popstack: Når du trykker på tilbage-knappen, går den igennem hvert stykke papir ét ad gangen.
                Så du skulle trykke tilbage mange gange for at komme ud af appen.
                launchSingleTop forhindrer kun dubletter hvis den skærm allerede ligger øverst.
                */
            //Genbruglig variabel til navigation - gør to ting: både skifter skærmen OG opdaterer din currentScreen state.
            val navigateTo = {rute: String -> //navigateTo: Funktionen der bruger begge dele.
                currentScreen = rute //currentScreen: Variablen der ved, hvor den orange streg skal være.
                navController.navigate(rute){ //navController: "Motoren" der skifter skærme.
                    launchSingleTop = true
                }
            }

            //forbindelse mellem Theme og Main Actitvity
            EksamensProjektNeverAnotherTheme{

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(backgroundColor)
                ) {

                    AppNavigation(
                        navController = navController,
                        navigateTo = navigateTo,
                        modifier = Modifier
                            .weight(1f) // Denne vægt SKUBBER navbaren ned i bunden
                    )

                    // NavHost styrer hvilken skærm der vises.

                    if (currentRoute in showNavBarScreens) {
                        BottomNavBar(
                            currentScreen = currentScreen, onTabClick = { valgtRute ->
                                currentScreen = valgtRute //hvorfor denne???
                                navigateTo(valgtRute)
                            }
                        )
                    }
                }
            }
        }
    }
}

