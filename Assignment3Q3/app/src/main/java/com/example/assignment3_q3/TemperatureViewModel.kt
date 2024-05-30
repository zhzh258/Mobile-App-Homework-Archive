package com.example.assignment3_q3

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel

const val CURRENT_CELSIUS_KEY = "CURRENT_CELSIUS_KEY"
const val CURRENT_FAHRENHEIT_KEY = "CURRENT_FAHRENHEIT_KEY"

class TemperatureViewModel(private val savedStateHandle: SavedStateHandle) : ViewModel() {
    var celsius: Int
        get() = savedStateHandle.get(CURRENT_CELSIUS_KEY) ?: 0
        set(value) = savedStateHandle.set(CURRENT_CELSIUS_KEY, value)
}