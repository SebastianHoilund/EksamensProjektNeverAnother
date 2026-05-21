package com.example.eksamensprojekt_neveranother.ui.screens.tailor

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.eksamensprojekt_neveranother.R
import com.example.eksamensprojekt_neveranother.ui.screens.components.MeasurementTemplate

@Composable
fun WidthMeasurementsScreen(navController: NavController) {
    MeasurementTemplate(
        title = "Brystspænd",
        description = "Mål bredden af dit ene bryst \nfra side til side.",
        videoResId = R.raw.video_4,
        illustrationResId = R.drawable.bs,
        progressResId = R.drawable.progressindicatorstep4,
        onBackClick = {
            navController.popBackStack()
        },
        onNextClick = { value ->
            // TODO: Navigate to the next planned screen
        }
    )
}
