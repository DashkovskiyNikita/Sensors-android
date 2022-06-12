package com.example.sensors_android.presentation.viewmodels

import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val viewModelsModule = module {
    singleOf(::SignInViewModel)
    singleOf(::SignUpViewModel)
    singleOf(::UserSensorsViewModel)
    singleOf(::UserSettingsViewModel)
}