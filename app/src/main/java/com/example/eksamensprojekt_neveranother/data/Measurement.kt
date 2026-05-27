package com.example.eksamensprojekt_neveranother.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Measurement(
    @SerialName("upper_circumference")
    val upperCircumference: String = "",
    @SerialName("lower_circumference")
    val lowerCircumference: String = "",
    @SerialName("height")
    val height: String = "",
    @SerialName("width")
    val width: String = "",
    @SerialName("selected_volume")
    val selectedVolume: Int = -1
)
