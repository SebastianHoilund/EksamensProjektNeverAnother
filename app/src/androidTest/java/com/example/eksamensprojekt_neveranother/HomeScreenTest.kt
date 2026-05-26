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

class HomeScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun homeScreen_displaysCorrectCta_whenNotTailored() {
        val navController = TestNavHostController(InstrumentationRegistry.getInstrumentation().targetContext)
        
        composeTestRule.setContent {
            HomeScreen(navController = navController, isTailored = false)
        }

        // Check if "Skræddersy BH" text is displayed on the button
        composeTestRule.onNodeWithText("Skræddersy BH").assertIsDisplayed()
    }

    @Test
    fun homeScreen_displaysCorrectCta_whenTailored() {
        val navController = TestNavHostController(InstrumentationRegistry.getInstrumentation().targetContext)

        composeTestRule.setContent {
            HomeScreen(navController = navController, isTailored = true)
        }

        // Check if "Se din BH" text is displayed on the button
        composeTestRule.onNodeWithText("Se din BH").assertIsDisplayed()
    }

    @Test
    fun homeScreen_navigatesToTailorStart_whenNotTailored() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        val navController = TestNavHostController(context)
        
        composeTestRule.setContent {
            HomeScreen(navController = navController, isTailored = false)
        }

        composeTestRule.onNodeWithText("Skræddersy BH").performClick()
        
        assertEquals("tailor_start", navController.currentBackStackEntry?.destination?.route)
    }
}
