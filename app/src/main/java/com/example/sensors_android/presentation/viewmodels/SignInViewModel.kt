package com.example.sensors_android.presentation.viewmodels

import androidx.lifecycle.ViewModel
import com.example.sensors_android.presentation.states.SignInState
import kotlinx.coroutines.delay
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

sealed interface SignInSideEffect{
    object Success : SignInSideEffect
}

class SignInViewModel : ContainerHost<SignInState, SignInSideEffect>, ViewModel() {

    override val container = container<SignInState, SignInSideEffect>(SignInState())

    fun loginChanged(login: String) = intent {
        reduce {
            state.copy(login = login)
        }
    }

    fun passwordChanged(password: String) = intent {
        reduce {
            state.copy(password = password)
        }
    }

    fun tryLogin() = intent {
        reduce {
            state.copy(inProcessing = true, tryLoginEnabled = false)
        }
        delay(1000L)
        //here we create a request to our api and get some result
        reduce {
            state.copy(inProcessing = false, tryLoginEnabled = true)
        }
    }
}