package com.example.sensors_android.presentation.views

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.sensors_android.ui.theme.Purple700

@Composable
fun PulsingCirclesAnimation(
    modifier: Modifier = Modifier,
    circleSize: Dp = 24.dp,
    circleColor: Color = Purple700
) {
    val infiniteTransition = rememberInfiniteTransition()

    val scale1 by rememberInfiniteState(
        transition = infiniteTransition,
        delay = 0
    )
    val scale2 by rememberInfiniteState(
        transition = infiniteTransition,
        delay = 500
    )
    val scale3 by rememberInfiniteState(
        transition = infiniteTransition,
        delay = 1000
    )

    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(5.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Circle(scale = scale1, color = circleColor)
        Circle(scale = scale2, color = circleColor)
        Circle(scale = scale3, color = circleColor)
    }
}

@Composable
fun Circle(
    circleSize: Dp = 24.dp,
    scale: Float,
    color: Color
) {
    Spacer(
        Modifier
            .size(circleSize)
            .scale(scale)
            .background(
                color = color,
                shape = CircleShape
            )
    )
}

@Composable
fun rememberInfiniteState(
    transition: InfiniteTransition,
    delay: Int
): State<Float> {
    return transition.animateFloat(
        initialValue = 0f,
        targetValue = 0f,
        animationSpec = infiniteRepeatable(
            animation = keyframes {
                durationMillis = 2000
                0f at delay with LinearEasing
                1f at delay + 500 with LinearEasing
                0f at delay + 1000
            }
        )
    )
}

@Preview
@Composable
fun AnimationTest() {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.White
    ) {
        PulsingCirclesAnimation()
    }
}