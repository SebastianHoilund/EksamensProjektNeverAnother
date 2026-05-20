package com.example.eksamensprojekt_neveranother

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.eksamensprojekt_neveranother.ui.theme.EksamensProjektNeverAnotherTheme
import com.example.eksamensprojekt_neveranother.ui.screens.navigation.BottomNavBar
import com.example.eksamensprojekt_neveranother.ui.theme.backgroundColor
import androidx.compose.runtime.setValue
import com.example.eksamensprojekt_neveranother.ui.screens.navigation.AppNavigation

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            val navController = rememberNavController() //remember husker hvor brugeren er i appen
            var currentScreen by remember { mutableStateOf("home-screen") }


            /*Popstack: Når du trykker på tilbage-knappen, går den igennem hvert stykke papir ét ad gangen.
                Så du skulle trykke tilbage mange gange for at komme ud af appen.
                launchSingleTop forhindrer kun dubletter hvis den skærm allerede ligger øverst.
                */
            //Genbruglig variabel til navigation
            val navigateTo = {rute: String ->
                navController.navigate(rute){
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

                    BottomNavBar(
                        currentScreen = currentScreen, onTabClick = { valgtRute ->
                            currentScreen = valgtRute //hvorfor denne???
                            navigateTo(valgtRute) }
                    )
                }
            }
        }
    }
}

