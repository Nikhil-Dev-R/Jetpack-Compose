package com.rudraksha.practice.animations.brushes

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.unit.dp

//Create an infinite animation that shifts colors within a LinearGradientBrush.
//Useful for buttons or backgrounds with a continuously shifting gradient effect.

@Composable
fun GradientColor() {
    val infiniteTransition = rememberInfiniteTransition(label = "")
    val colorOffset by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(5000, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        ), label = ""
    )

    val boxSize = 200.dp // Set a fixed box size
    var centerOffset by remember { mutableStateOf(Offset.Zero) }

    val gradientBrush = Brush.linearGradient(
        // Last color will act as Background
        colors = listOf(Color.Red, Color.Green, Color.Blue, Color.Magenta ),
        start = Offset(0f, 0f),
        end = Offset(colorOffset * 1100f, colorOffset * 1100f)
    )

    Box(modifier = Modifier
        .size(boxSize)
        .onSizeChanged { size ->
            // Calculate the center offset based on the Box's size
            centerOffset = Offset(size.width / 2f, size.height / 2f)
        }
        .background(gradientBrush))

}