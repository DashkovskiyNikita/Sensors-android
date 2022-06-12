package com.example.sensors_android.presentation.states

data class UserSensorsState(
    val sensors : List<String> = emptyList(),
    val isLoading : Boolean = true
)
