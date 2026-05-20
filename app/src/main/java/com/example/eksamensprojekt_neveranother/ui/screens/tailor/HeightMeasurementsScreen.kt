package com.example.eksamensprojekt_neveranother.ui.screens.tailor

import androidx.compose.runtime.Composable
import com.example.eksamensprojekt_neveranother.R
import com.example.eksamensprojekt_neveranother.ui.screens.components.MeasurementTemplate

@Composable
fun HeightMeasurementsScreen() {
    MeasurementTemplate(
        title = "Brysthøjde",
        description = "Mål højden af dit bryst \nfra top til bund.",
        videoResId = R.raw.video_3,
        illustrationResId = R.drawable.bh,
        progressResId = R.drawable.progressindicatorstep3,
        onBackClick = {
            // TODO: Navigate back
        },
        onNextClick = { value ->
            // TODO: Save 'value' and navigate to next step
        }
    )
}
