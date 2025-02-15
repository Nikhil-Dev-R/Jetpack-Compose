package com.rudraksha.practice.animations.brushes

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.*
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
fun RainbowText() {
    val colors = listOf(Color.Red, Color.Green, Color.Blue, Color.Yellow, Color.Magenta)
    val infiniteTransition = rememberInfiniteTransition("")
    val colorOffset by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            tween(5000, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        ),
        ""
    )

    val brush = Brush.linearGradient(
        colors = colors,
        start = Offset(0f, 0f),
        end = Offset(colorOffset * 1000f, colorOffset * 100f)
    )

    Text(
        "Rainbow Text",
        style = TextStyle(
            brush = brush,
            fontSize = 30.sp
        )
    )
}

