package com.example.eksamensprojekt_neveranother.ui.screens.tailor

import androidx.compose.runtime.Composable
import com.example.eksamensprojekt_neveranother.R
import com.example.eksamensprojekt_neveranother.ui.screens.components.MeasurementTemplate

@Composable
fun LowerMeasurementsScreen() {
    MeasurementTemplate(
        title = "Nedre omkreds",
        description = "Mål din omkreds om torso \nlige under brystet.",
        videoResId = R.raw.video_2,
        illustrationResId = R.drawable.lc,
        progressResId = R.drawable.progressindicatorstep2,
        onBackClick = {
            // TODO: Navigate back
        },
        onNextClick = { value ->
            // TODO: Save 'value' and navigate to next step
        }
    )
}
