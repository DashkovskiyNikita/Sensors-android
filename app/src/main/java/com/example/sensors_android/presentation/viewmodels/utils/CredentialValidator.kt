package com.example.sensors_android.presentation.viewmodels.utils

import com.example.sensors_android.R

interface ICredentialValidator : Validator<String>

class CredentialValidator : ICredentialValidator {
    override fun invoke(form: String) =
        when (form.isNotEmpty()) {
            true -> ValidateResult()
            false -> ValidateResult(
                isSuccessful = false,
                message = R.string.empty_field_error
            )
        }
}