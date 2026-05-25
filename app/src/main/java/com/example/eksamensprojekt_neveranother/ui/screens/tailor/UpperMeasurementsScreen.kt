package com.example.eksamensprojekt_neveranother.ui.screens.tailor

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.eksamensprojekt_neveranother.R
import com.example.eksamensprojekt_neveranother.ui.components.MeasurementTemplate

@Composable
fun UpperMeasurementsScreen(
    navController: NavController,
    initialValue: String,
    onValueSaved: (String) -> Unit
) {
    MeasurementTemplate(
        title = "Øvre omkreds",
        description = "Mål din omkreds om torso \nover brystet.",
        videoResId = R.raw.video_1,
        illustrationResId = R.drawable.uc,
        progressResId = R.drawable.progressindicatorstep1,
        initialValue = initialValue,
        onBackClick = { navController.popBackStack() },
        onNextClick = { value ->
            onValueSaved(value)
            navController.navigate("lower_measurements")
        }
    )
}
