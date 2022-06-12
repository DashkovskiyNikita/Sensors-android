package com.example.sensors_android.presentation.states

import androidx.annotation.StringRes

data class SignInState(
    val login: String = "",
    val password: String = "",
    val inProcessing: Boolean = false,
    val tryLoginEnabled: Boolean = true,
)

