package com.example.eksamensprojekt_neveranother

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.navigation.testing.TestNavHostController
import androidx.test.platform.app.InstrumentationRegistry
import com.example.eksamensprojekt_neveranother.ui.screens.home.HomeScreen
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

// ===== UI TEST: HOMESCREEN =====
// Denne testfil bruger Jetpack Compose Testing-biblioteket til at verificere, 
// at HomeScreen opfører sig korrekt under forskellige tilstande (isTailored true/false).
// UI-tests kører på en emulator eller en fysisk enhed, da de kræver Android-systemet.

class HomeScreenTest {

    // composeTestRule er hjertet i enhver Compose-test.
    // Den giver os mulighed for at "sætte" en Composable (setContent) og 
    // derefter søge efter elementer på skærmen (onNode).
    @get:Rule
    val composeTestRule = createComposeRule()

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

        // VERIFIKATION: Vi søger efter en node på skærmen, der indeholder teksten "Skræddersy BH".
        // Vi bruger assertIsDisplayed() for at sikre, at knappen rent faktisk er synlig for brugeren.
        composeTestRule.onNodeWithText("Skræddersy BH").assertIsDisplayed()
    }

    @Test
    fun homeScreen_displaysCorrectCta_whenTailored() {
        val navController = TestNavHostController(InstrumentationRegistry.getInstrumentation().targetContext)

        // Her tester vi det modsatte scenarie: Brugeren ER målt op.
        composeTestRule.setContent {
            HomeScreen(navController = navController, isTailored = true)
        }

        // VERIFIKATION: Knappen skal nu have ændret tekst til "Se din BH".
        // Dette tester, at vores if-else logik i HomeScreen.kt fungerer korrekt.
        composeTestRule.onNodeWithText("Se din BH").assertIsDisplayed()
    }

    @Test
    fun homeScreen_navigatesToTailorStart_whenNotTailored() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        // Vi bruger TestNavHostController specifikt til at teste navigation.
        val navController = TestNavHostController(context)
        
        composeTestRule.setContent {
            HomeScreen(navController = navController, isTailored = false)
        }

        // HANDLING: Vi finder knappen og simulerer et fysisk klik fra brugeren.
        composeTestRule.onNodeWithText("Skræddersy BH").performClick()
        
        // VERIFIKATION: Vi tjekker NavControllerens "backstack".
        // Vi verificerer, at den nuværende destination er "tailor_start", 
        // hvilket beviser, at klikket udløste den korrekte navigation.
        assertEquals("tailor_start", navController.currentBackStackEntry?.destination?.route)
    }
}
