package com.example.sensors_android.presentation.viewmodels

import androidx.lifecycle.ViewModel
import com.example.sensors_android.presentation.states.SignUpState
import com.example.sensors_android.presentation.viewmodels.utils.*
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

sealed interface SignUpSideEffect {
    object Success : SignUpSideEffect
}

class SignUpViewModel(
    private val passwordValidator: IPasswordValidator,
    private val credentialValidator: ICredentialValidator,
    private val emailValidator: IEmailValidator
) : ContainerHost<SignUpState, SignUpSideEffect>, ViewModel() {

    override val container = container<SignUpState, SignUpSideEffect>(SignUpState())

    fun emailChanged(email: String) = intent {
        reduce {
            state.copy(email = email)
        }
    }

    fun passwordChanged(password: String) = intent {
        reduce {
            state.copy(password = password)
        }
    }

    fun firstnameChanged(firstname: String) = intent {
        reduce {
            state.copy(firstName = firstname)
        }
    }

    fun lastnameChanged(lastname: String) = intent {
        reduce {
            state.copy(lastName = lastname)
        }
    }

    fun trySignUp() = intent {

        trySignUpDisabledState()

        val emailResult = emailValidator(state.email)
        val firstNameResult = credentialValidator(state.firstName)
        val lastNameResult = credentialValidator(state.lastName)
        val passwordResult = passwordValidator(state.password)

        val errorResult = listOf(
            emailResult,
            firstNameResult,
            lastNameResult,
            passwordResult
        ).any { !it.isSuccessful }

        reduce{
            state.copy(
                emailError = emailResult,
                firstNameError = firstNameResult,
                lastNameError = lastNameResult,
                passwordError = passwordResult
            )
        }

        when(errorResult){
            true -> trySignUpEnabledState()
            false -> signUp()
        }
    }

    private fun signUp() = intent {
        postSideEffect(SignUpSideEffect.Success)
    }

    private fun trySignUpDisabledState() = intent {
        reduce {
            state.copy(inProcessing = true, trySignUpEnabled = false)
        }
    }

    private fun trySignUpEnabledState() = intent {
        reduce {
            state.copy(inProcessing = false, trySignUpEnabled = true)
        }
    }
}