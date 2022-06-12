package com.example.sensors_android.presentation.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.sensors_android.R
import kotlinx.coroutines.delay

@Composable
fun SplashView(navigateToUserSensors: () -> Unit = {}) {
    LaunchedEffect(Unit) {
        delay(1500L)
        navigateToUserSensors()
    }
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(R.drawable.ic_logotype),
                contentDescription = "Splash screen logo"
            )
            PulsingCirclesAnimation()
        }
    }
}

@Composable
@Preview
fun SplashPreview() {
    Surface(color = Color.White) {
        SplashView()
    }
}