package com.example.eksamensprojekt_neveranother.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class ProductViewModel : ViewModel() {

    var choseColor by mutableStateOf("")

    var isTailored by mutableStateOf(false)

}