package com.example.sensors_android.presentation.states

import com.example.sensors_android.presentation.viewmodels.utils.ValidateResult

data class SignUpState(
    val email: String = "",
    val firstName: String = "",
    val lastName: String = "",
    val password: String = "",
    val emailError: ValidateResult = ValidateResult(),
    val firstNameError: ValidateResult = ValidateResult(),
    val lastNameError: ValidateResult = ValidateResult(),
    val passwordError: ValidateResult = ValidateResult(),
    val inProcessing: Boolean = false,
    val trySignUpEnabled: Boolean = true,
)
