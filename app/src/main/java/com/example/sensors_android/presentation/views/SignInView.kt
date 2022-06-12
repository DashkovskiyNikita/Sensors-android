package com.example.sensors_android.presentation.views

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.sensors_android.presentation.Navigation
import com.example.sensors_android.presentation.states.SignInState
import com.example.sensors_android.presentation.viewmodels.SignInViewModel
import com.example.sensors_android.presentation.viewmodels.utils.ValidateResult
import com.example.sensors_android.ui.theme.Purple700
import org.koin.androidx.compose.getViewModel

@ExperimentalComposeUiApi
@ExperimentalAnimationApi
@Composable
fun SignInView(
    modifier: Modifier = Modifier,
    navController: NavController = rememberNavController(),
    viewModel: SignInViewModel = getViewModel()
) {
    val state = viewModel.container.stateFlow.collectAsState().value

    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        SignInContent(
            state = state,
            loginChange = viewModel::loginChanged,
            passwordChange = viewModel::passwordChanged,
            signInClick = viewModel::tryLogin,
            navigateToSignUpScreen = {
                navController.navigate(Navigation.SignUpScreen.route)
            }
        )
    }
}

@ExperimentalComposeUiApi
@ExperimentalAnimationApi
@Composable
fun SignInContent(
    state: SignInState,
    loginChange: (String) -> Unit = {},
    passwordChange: (String) -> Unit = {},
    signInClick: () -> Unit = {},
    navigateToSignUpScreen: () -> Unit = {}
) {
    Column(
        modifier = Modifier
            .padding(start = 20.dp, end = 20.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = "Вход",
            color = Purple700,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.h4
        )
        SensorsAppTextField(
            value = state.login,
            label = { Text("Логин") },
            onValueChanged = loginChange
        )
        SensorsAppTextField(
            value = state.password,
            label = { Text("Пароль") },
            visualTransformation = PasswordVisualTransformation(),
            onValueChanged = passwordChange,
        )
        SensorsAppButton(
            text = "Войти",
            enabled = state.tryLoginEnabled,
            onClick = signInClick
        )
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { navigateToSignUpScreen() },
            text = "Регистрация",
            color = Purple700,
            textAlign = TextAlign.Center
        )
        SensorsAppProgressIndicator(visible = state.inProcessing)
    }
}


@ExperimentalAnimationApi
@ExperimentalComposeUiApi
@Composable
fun SensorsAppTextField(
    modifier: Modifier = Modifier,
    value: String,
    error: ValidateResult = ValidateResult(),
    onValueChanged: (String) -> Unit = {},
    label: @Composable (() -> Unit)? = null,
    visualTransformation: VisualTransformation = VisualTransformation.None
) {
    val keyboard = LocalSoftwareKeyboardController.current
    Column(modifier = modifier.fillMaxWidth()) {
        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = value,
            onValueChange = onValueChanged,
            label = label,
            isError = !error.isSuccessful,
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
            visualTransformation = visualTransformation,
            keyboardActions = KeyboardActions(
                onDone = {
                    keyboard?.hide()
                }
            ),
            colors = TextFieldDefaults.textFieldColors(
                textColor = Color.Black,
                cursorColor = Purple700,
                focusedIndicatorColor = Purple700,
                unfocusedIndicatorColor = Color.Gray,
                focusedLabelColor = Purple700,
                unfocusedLabelColor = Color.Gray,
                backgroundColor = Color.Transparent
            )
        )
        AnimatedVisibility(
            visible = !error.isSuccessful,
        ) {
            error.message?.let {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = stringResource(id = it),
                    color = MaterialTheme.colors.error,
                    textAlign = TextAlign.Start
                )
            }
        }
    }
}

@Composable
fun SensorsAppButton(
    modifier: Modifier = Modifier,
    text: String,
    enabled: Boolean = true,
    onClick: () -> Unit = {}
) {
    Button(
        modifier = modifier.fillMaxWidth(),
        enabled = enabled,
        shape = RoundedCornerShape(10.dp),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Purple700,
            contentColor = Color.White
        ),
        onClick = onClick
    ) {
        Text(text = text)
    }
}

@ExperimentalAnimationApi
@Composable
fun SensorsAppProgressIndicator(visible: Boolean) {
    AnimatedVisibility(
        visible = visible
    ) {
        CircularProgressIndicator(
            color = Purple700
        )
    }
}

@ExperimentalComposeUiApi
@ExperimentalAnimationApi
@Preview
@Composable
fun SignInPreview() {
    Surface(color = Color.White) {
        SignInContent(
            state = SignInState(
                login = "Nikita",
                password = "12345",
                inProcessing = true,
                tryLoginEnabled = false
            )
        )
    }
}