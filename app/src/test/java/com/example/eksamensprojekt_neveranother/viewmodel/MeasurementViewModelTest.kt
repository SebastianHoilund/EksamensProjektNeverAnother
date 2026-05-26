package com.example.eksamensprojekt_neveranother.viewmodel

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class MeasurementViewModelTest {

    private lateinit var viewModel: MeasurementViewModel

    @Before
    fun setup() {
        viewModel = MeasurementViewModel()
    }

    @Test
    fun `updateUpper updates state correctly`() {
        viewModel.updateUpper("75.5")
        assertEquals("75.5", viewModel.measurement.upperCircumference)
    }

    @Test
    fun `updateLower updates state correctly`() {
        viewModel.updateLower("80.0")
        assertEquals("80.0", viewModel.measurement.lowerCircumference)
    }

    @Test
    fun `completeMeasurement sets isTailored to true`() {
        viewModel.completeMeasurement()
        assertTrue(viewModel.isTailored)
    }
}
