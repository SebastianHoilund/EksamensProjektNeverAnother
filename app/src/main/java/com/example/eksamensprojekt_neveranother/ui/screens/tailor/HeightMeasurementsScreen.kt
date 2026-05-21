package com.example.eksamensprojekt_neveranother.ui.screens.tailor

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.eksamensprojekt_neveranother.R
import com.example.eksamensprojekt_neveranother.ui.screens.components.MeasurementTemplate

@Composable
fun HeightMeasurementsScreen(navController: NavController) {
    MeasurementTemplate(
        title = "Brysthøjde",
        description = "Mål højden af dit bryst \nfra top til bund.",
        videoResId = R.raw.video_3,
        illustrationResId = R.drawable.bh,
        progressResId = R.drawable.progressindicatorstep3,
        onBackClick = {
            navController.popBackStack()
        },
        onNextClick = { value ->
            navController.navigate("width_measurements")
        }
    )
}
