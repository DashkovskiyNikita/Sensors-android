package com.example.sensors_android.presentation.views

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.sensors_android.presentation.Navigation
import com.example.sensors_android.presentation.states.SignUpState
import com.example.sensors_android.presentation.viewmodels.SignUpSideEffect
import com.example.sensors_android.presentation.viewmodels.SignUpViewModel
import com.example.sensors_android.ui.theme.Purple700
import org.koin.androidx.compose.getViewModel

@ExperimentalComposeUiApi
@ExperimentalAnimationApi
@Composable
fun SignUpView(
    modifier: Modifier = Modifier,
    navController: NavController = rememberNavController(),
    viewModel: SignUpViewModel = getViewModel()
) {
    val state = viewModel.container.stateFlow.collectAsState().value

    LaunchedEffect(viewModel) {
        viewModel.container.sideEffectFlow.collect {
            when (it) {
                is SignUpSideEffect.Success ->
                    navController.navigate(Navigation.UserSensorsScreen.route)
            }
        }
    }

    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        SignUpContent(
            state = state,
            emailChanged = viewModel::emailChanged,
            passwordChanged = viewModel::passwordChanged,
            firstnameChanged = viewModel::firstnameChanged,
            lastnameChanged = viewModel::lastnameChanged,
            trySignUp = viewModel::trySignUp
        )
    }
}

@ExperimentalComposeUiApi
@ExperimentalAnimationApi
@Composable
fun SignUpContent(
    state: SignUpState,
    emailChanged: (String) -> Unit = {},
    passwordChanged: (String) -> Unit = {},
    firstnameChanged: (String) -> Unit = {},
    lastnameChanged: (String) -> Unit = {},
    trySignUp: () -> Unit = {}
) {
    Column(
        modifier = Modifier
            .padding(start = 20.dp, end = 20.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = "Регистрация",
            color = Purple700,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.h4
        )
        SensorsAppTextField(
            value = state.email,
            label = { Text("Почта") },
            error = state.emailError,
            onValueChanged = emailChanged
        )
        SensorsAppTextField(
            value = state.firstName,
            label = { Text("Имя") },
            error = state.firstNameError,
            onValueChanged = firstnameChanged
        )
        SensorsAppTextField(
            value = state.lastName,
            label = { Text("Фамилия") },
            error = state.lastNameError,
            onValueChanged = lastnameChanged
        )
        SensorsAppTextField(
            value = state.password,
            label = { Text("Пароль") },
            visualTransformation = PasswordVisualTransformation(),
            error = state.passwordError,
            onValueChanged = passwordChanged
        )
        SensorsAppButton(
            text = "Зарегистрироваться",
            enabled = state.trySignUpEnabled,
            onClick = trySignUp
        )
        SensorsAppProgressIndicator(
            visible = state.inProcessing
        )
    }
}

@ExperimentalComposeUiApi
@ExperimentalAnimationApi
@Preview
@Composable
fun SignUpPreview() {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.White
    ) {
        SignUpContent(state = SignUpState())
    }
}