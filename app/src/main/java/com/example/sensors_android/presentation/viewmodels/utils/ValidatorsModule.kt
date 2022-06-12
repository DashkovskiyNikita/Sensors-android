package com.example.sensors_android.presentation.viewmodels.utils

import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val validatorsModule = module {
    singleOf(::EmailValidator) {
        bind<IEmailValidator>()
    }
    singleOf(::CredentialValidator) {
        bind<ICredentialValidator>()
    }
    singleOf(::PasswordValidator) {
        bind<IPasswordValidator>()
    }
}