package com.example.assignment3


import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel

private const val TAG = "QuizViewModel"
const val CURRENT_STRING_KEY = "CURRENT_STRING_KEY"

class MyViewModel(private val savedStateHandle: SavedStateHandle) : ViewModel() {
    private var content: String
        get() = savedStateHandle.get(CURRENT_STRING_KEY) ?: "Hello"
        set(value) = savedStateHandle.set(CURRENT_STRING_KEY, value)

    fun findContent() : String {
        return content
    }
}