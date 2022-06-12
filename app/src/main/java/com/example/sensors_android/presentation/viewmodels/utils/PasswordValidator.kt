package com.example.sensors_android.presentation.viewmodels.utils

import com.example.sensors_android.R

interface IPasswordValidator : Validator<String>

class PasswordValidator : IPasswordValidator {
    override fun invoke(form: String) =
        when {
            form.isEmpty() -> ValidateResult(
                isSuccessful = false,
                message = R.string.empty_field_error
            )
            form.length < 6 -> ValidateResult(
                isSuccessful = false,
                message = R.string.password_length_error
            )
            else -> ValidateResult()
        }
}