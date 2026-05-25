package com.example.eksamensprojekt_neveranother.ui.screens.tailor

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.eksamensprojekt_neveranother.R
import com.example.eksamensprojekt_neveranother.ui.components.MeasurementTemplate

import com.example.eksamensprojekt_neveranother.viewmodel.MeasurementViewModel

@Composable
fun LowerMeasurementsScreen(
    navController: NavController,
    viewModel: MeasurementViewModel
) {
    MeasurementTemplate(
        title = "Nedre omkreds",
        description = "Mål din omkreds om torso \nlige under brystet.",
        videoResId = R.raw.video_2,
        illustrationResId = R.drawable.lc,
        progressResId = R.drawable.progressindicatorstep2,
        initialValue = viewModel.tailorState.lowerCircumference,
        onBackClick = {
            navController.popBackStack()
        },
        onNextClick = { value ->
            viewModel.updateLower(value)
            navController.navigate("midway")
        }
    )
}
