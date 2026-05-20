package com.example.eksamensprojekt_neveranother.ui.screens.tailor

import androidx.compose.runtime.Composable
import com.example.eksamensprojekt_neveranother.R
import com.example.eksamensprojekt_neveranother.ui.screens.components.MeasurementTemplate

@Composable
fun UpperMeasurementsScreen() {
    MeasurementTemplate(
        title = "Øvre omkreds",
        description = "Mål din omkreds om torso \nover brystet.",
        videoResId = R.raw.video_1,
        illustrationResId = R.drawable.uc,
        progressResId = R.drawable.progressindicatorstep1,
        onBackClick = {
            // TODO: Navigate back
        },
        onNextClick = { value ->
            // TODO: Save 'value' and navigate to next step
        }
    )
}
