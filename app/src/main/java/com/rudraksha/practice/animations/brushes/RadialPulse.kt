package com.rudraksha.practice.animations.brushes

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
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
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.unit.dp

//    Animate a RadialGradientBrush to create a pulsing effect, expanding and contracting a circular gradient.
//    Suitable for focus indicators or attention-grabbing elements.

@Composable
fun RadialPulse() {
    val transition = rememberInfiniteTransition(label = "")
    val radius by transition.animateFloat(
        initialValue = 1f,
        targetValue = 500f,
        animationSpec = infiniteRepeatable(
            tween(1500, easing = FastOutSlowInEasing),
            RepeatMode.Reverse
        ), label = ""
    )

    val boxSize = 200.dp // Set a fixed box size
    var centerOffset by remember { mutableStateOf(Offset.Zero) }

    val brush = Brush.radialGradient(
        colors = listOf(Color.Green, Color.Magenta, Color.Cyan, Color.Transparent),
        center = centerOffset,
//        center = Offset.Infinite,
//        center = Offset.Zero,
        radius = radius, // radius > 0f
//        tileMode = TileMode.Mirror
//        tileMode = TileMode.Clamp,
//        tileMode = TileMode.Decal,
        tileMode = TileMode.Repeated
    )

    Box(modifier = Modifier
        .size(boxSize)
        .onSizeChanged { size ->
            // Calculate the center offset based on the Box's size
            centerOffset = Offset(size.width / 2f, size.height / 2f)
        }
        .background(brush))
}