package com.example.sensors_android.presentation.viewmodels.utils

import android.util.Patterns
import com.example.sensors_android.R

interface IEmailValidator : Validator<String>

class EmailValidator : IEmailValidator {
    override operator fun invoke(form: String) =
        when (Patterns.EMAIL_ADDRESS.matcher(form).matches()) {
            true -> ValidateResult()
            false -> ValidateResult(
                isSuccessful = false,
                message = R.string.wrong_email_error
            )
        }
}