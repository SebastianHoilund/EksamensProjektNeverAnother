package com.example.eksamensprojekt_neveranother.ui.screens.tailor

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.eksamensprojekt_neveranother.R
import com.example.eksamensprojekt_neveranother.ui.components.MeasurementTemplate

import com.example.eksamensprojekt_neveranother.viewmodel.MeasurementViewModel

@Composable
fun WidthMeasurementsScreen(
    navController: NavController,
    viewModel: MeasurementViewModel
) {
    MeasurementTemplate(
        title = "Brystspænd",
        description = "Mål bredden af dit ene bryst \nfra side til side.",
        videoResId = R.raw.video_4,
        illustrationResId = R.drawable.bs,
        progressResId = R.drawable.progressindicatorstep4,
        initialValue = viewModel.measurement.width,
        viewModel = viewModel,
        onBackClick = {
            navController.popBackStack()
        },
        onNextClick = { value ->
            viewModel.updateWidth(value)
            navController.navigate("volume_selection")
        }
    )
}
