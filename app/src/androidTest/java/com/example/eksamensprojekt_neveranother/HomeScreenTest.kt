package com.example.eksamensprojekt_neveranother

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.createGraph
import androidx.navigation.testing.TestNavHostController
import androidx.test.platform.app.InstrumentationRegistry
import com.example.eksamensprojekt_neveranother.ui.screens.home.HomeScreen
import androidx.navigation.compose.composable
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import androidx.compose.ui.test.junit4.v2.createComposeRule as createComposeRuleV2

// ===== UI TEST: HOMESCREEN =====
// Denne testfil bruger Jetpack Compose Testing-biblioteket til at verificere, 
// at HomeScreen opfører sig korrekt under forskellige tilstande (isTailored true/false).
// UI-tests kører på en emulator eller en fysisk enhed, da de kræver Android-systemet.

class HomeScreenTest {

    // composeTestRule er hjertet i enhver Compose-test.
    // Den giver os mulighed for at "sætte" en Composable (setContent) og
    // derefter søge efter elementer på skærmen (onNode).
    // Vi bruger v2 versionen da den gamle version var deprecated,
    // det nu er denne man bruger ifølge de nyeste StandardTestDispatcher standarder.

    // @get:Rule er en JUnit 4 annotation, der fortæller test-systemet,
    // at denne variabel er en "Test Rule".
    // En Rule fungerer som en slags "hjælper", der automatisk sætter test-miljøet op
    // før hver test kører (f.eks. starter Compose) og rydder op bagefter.
    @get:Rule
    val composeTestRule = createComposeRuleV2()

    @Test
    fun homeScreen_displaysCorrectCta_whenNotTailored() {
        // Vi opretter en "falsk" NavController til testen.
        // Dette er nødvendigt, da HomeScreen kræver en NavController som parameter.
        val navController = TestNavHostController(InstrumentationRegistry.getInstrumentation().targetContext)
        
        // setContent starter den UI, vi gerne vil teste.
        // Her tester vi scenariet, hvor brugeren IKKE er målt op endnu.
        composeTestRule.setContent {
            HomeScreen(navController = navController, isTailored = false)
        }

        // Vi venter på at Compose er færdig med at tegne skærmen
        composeTestRule.waitForIdle()

        // VERIFIKATION: Vi søger efter en node på skærmen, der indeholder teksten "Skræddersy BH".
        // Vi bruger assertIsDisplayed() for at sikre, at knappen rent faktisk er synlig for brugeren.
        composeTestRule.onNodeWithText("Skræddersy BH").assertIsDisplayed()
        
        // Valgfrit: En lille pause så vi kan nå at se det (kun til demo-brug)
        Thread.sleep(1000)
    }

    @Test
    fun homeScreen_displaysCorrectCta_whenTailored() {
        val navController = TestNavHostController(InstrumentationRegistry.getInstrumentation().targetContext)

        // Her tester vi det modsatte scenarie: Brugeren ER målt op.
        composeTestRule.setContent {
            HomeScreen(navController = navController, isTailored = true)
        }

        composeTestRule.waitForIdle()

        // VERIFIKATION: Knappen skal nu have ændret tekst til "Se din BH".
        // Dette tester, at vores if-else logik i HomeScreen.kt fungerer korrekt.
        composeTestRule.onNodeWithText("Se din BH").assertIsDisplayed()
        
        Thread.sleep(1000)
    }

    @Test
    fun homeScreen_navigatesToTailorStart_whenNotTailored() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        val navController = TestNavHostController(context)
        
        // VIKTIGT: Vi skal konfigurere controlleren med de ruter, den skal kende til,
        // ellers ved den ikke hvad "tailor_start" betyder når vi trykker på knappen.
        composeTestRule.runOnUiThread {
            navController.navigatorProvider.addNavigator(ComposeNavigator())
            navController.graph = navController.createGraph(startDestination = "home") {
                composable("home") { }
                composable("tailor_start") { }
                composable("product") { }
            }
        }
        
        composeTestRule.setContent {
            HomeScreen(navController = navController, isTailored = false)
        }

        composeTestRule.waitForIdle()

        // HANDLING: Vi finder knappen og simulerer et fysisk klik fra brugeren.
        composeTestRule.onNodeWithText("Skræddersy BH").performClick()
        
        // Vi venter på at navigationen bliver behandlet
        composeTestRule.waitForIdle()
        
        // VERIFIKATION: Vi tjekker NavControllerens "backstack".
        // Vi verificerer, at den nuværende destination er "tailor_start", 
        // hvilket beviser, at klikket udløste den korrekte navigation.
        assertEquals("tailor_start", navController.currentBackStackEntry?.destination?.route)
        
        Thread.sleep(1000)
    }
}
