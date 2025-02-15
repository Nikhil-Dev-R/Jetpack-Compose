package com.rudraksha.practice.animations.brushes

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp

@Composable
fun SunsetHorizon() {
    val infiniteTransition = rememberInfiniteTransition()
    val sunsetTransition = rememberInfiniteTransition()
    val offset by sunsetTransition.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(tween(3000, easing = FastOutSlowInEasing))
    )

    val brush = Brush.horizontalGradient(
        colors = listOf(Color.Blue, Color.Cyan, Color(0xFFEEAA00), Color.Red),
        startX = 0f,
        endX = 500f * offset
    )

    Box(modifier = Modifier
        .size(200.dp)
        .background(brush))
}

@Composable
fun SunsetScene() {
    val infiniteTransition = rememberInfiniteTransition("")

    // Animation for the sun’s position and gradient shift
    val sunPositionY by infiniteTransition.animateFloat(
        initialValue = 100f,
        targetValue = 300f,
        animationSpec = infiniteRepeatable(
            tween(4000, easing = LinearEasing),
            RepeatMode.Reverse
        ),
        ""
    )

    // Sky Gradient Animation (from afternoon to evening colors)
    val skyBrush = Brush.verticalGradient(
        colors = listOf(Color.Cyan, Color.Blue, Color(0xFF0D47A1)),
        startY = 0f,
        endY = 600f
    )

    // Sun Gradient Animation
    val sunBrush = Brush.radialGradient(
        colors = listOf(Color.Yellow, Color.Transparent),
        center = Offset(150f, sunPositionY),
        radius = 100f
    )

    // Ocean Animation
    val waveOffset by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 200f,
        animationSpec = infiniteRepeatable(
            tween(3000, easing = LinearEasing)
        ),
        ""
    )
    val oceanBrush = Brush.horizontalGradient(
        colors = listOf(Color(0xFF2196F3), Color(0xFF1976D2), Color(0xFF1565C0)),
        startX = waveOffset,
        endX = waveOffset + 300f
    )

    // Composing Layers
    Box(
        modifier = Modifier
            .size(500.dp)
    ) {
        // Sky layer with vertical gradient
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(400.dp)
                .background(skyBrush)
        )

        // Sun layer with radial gradient
        Box(
            modifier = Modifier
                .size(100.dp)
                .align(Alignment.TopCenter)
                .offset(y = sunPositionY.dp)
                .background(sunBrush, shape = CircleShape)
        )

        // Ocean layer with animated horizontal gradient for waves
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.4f)
                .align(Alignment.BottomCenter)
                .background(oceanBrush)
        )
    }
}
