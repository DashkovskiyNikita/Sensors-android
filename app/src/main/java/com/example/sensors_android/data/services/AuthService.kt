package com.example.sensors_android.data.services

import com.example.sensors_android.data.models.SignInModel
import com.example.sensors_android.data.models.SignUpModel

interface AuthService {
    suspend fun signIn(body : SignInModel)
    suspend fun signUp(body : SignUpModel)
}