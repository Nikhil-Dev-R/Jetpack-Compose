package com.rudraksha.practice.animations.brushes

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
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

@Composable
fun SweepGradientRotation() {
    val transition = rememberInfiniteTransition()

    val boxSize = 200.dp // Set a fixed box size
    var centerOffset by remember { mutableStateOf(Offset.Zero) }

    // Animation for rotating the gradient
    val rotation by transition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            tween(4000, easing = LinearEasing)
        )
    )

    Box(
        modifier = Modifier
            .size(boxSize)
            .onSizeChanged { size ->
                // Calculate the center offset based on the Box's size
                centerOffset = Offset(size.width / 2f, size.height / 2f)
            }
            .graphicsLayer(rotationZ = rotation)
            .background(
                Brush.sweepGradient(
                    colors = listOf(Color.Cyan, Color.Magenta, Color.Yellow, Color.Red, Color.Cyan),
                    center = centerOffset // Use the dynamic center offset
                )
            )
    )
}
