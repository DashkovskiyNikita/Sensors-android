package com.example.sensors_android.presentation.viewmodels.utils

import androidx.annotation.StringRes

data class ValidateResult(
    val isSuccessful: Boolean = true,
    @StringRes val message: Int? = null
)

interface Validator<in T> {
    operator fun invoke(form: T): ValidateResult
}