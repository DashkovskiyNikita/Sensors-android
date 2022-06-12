package com.example.sensors_android.presentation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.sensors_android.presentation.views.SignInView
import com.example.sensors_android.presentation.views.SignUpView
import com.example.sensors_android.presentation.views.SplashView
import com.example.sensors_android.presentation.views.UserSensorsView

sealed class Navigation(val route: String) {
    object SplashScreen : Navigation("splash_screen")
    object SignInScreen : Navigation("sign_in_screen")
    object SignUpScreen : Navigation("sign_up_screen")
    object UserSensorsScreen : Navigation("user_sensors_screen")
    object UserSettingsScreen : Navigation("user_settings_screen")
}

@ExperimentalAnimationApi
@ExperimentalComposeUiApi
@Composable
fun SensorsAppNavigation(
    navHostController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navHostController,
        startDestination = Navigation.SplashScreen.route
    ) {
        composable(route = Navigation.SplashScreen.route) {
            SplashView {
                navHostController.navigate(Navigation.SignInScreen.route) {
                    popUpTo(Navigation.SplashScreen.route) {
                        inclusive = true
                    }
                }
            }
        }
        composable(route = Navigation.SignInScreen.route) {
            SignInView(navController = navHostController)
        }
        composable(route = Navigation.SignUpScreen.route) {
            SignUpView(navController = navHostController)
        }
        composable(route = Navigation.UserSensorsScreen.route) {
            UserSensorsView()
        }
    }
}