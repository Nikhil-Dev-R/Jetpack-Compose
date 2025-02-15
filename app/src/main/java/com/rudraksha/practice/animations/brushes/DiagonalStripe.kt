package com.rudraksha.practice.animations.brushes

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

//Animate a RadialGradientBrush to create a pulsing effect, expanding and contracting a circular gradient.
//Suitable for focus indicators or attention-grabbing elements.
@Composable
fun DiagonalStripe() {
    val transition = rememberInfiniteTransition("")
    val offset by transition.animateFloat(
        initialValue = 0f,
        targetValue = 1000f,
        animationSpec = infiniteRepeatable(
            tween(3000, easing = LinearEasing)
        ),
        ""
    )

    val brush = Brush.linearGradient(
        colors = listOf(Color.Black, Color.Transparent, Color.Black),
        start = Offset(0f, offset),
        end = Offset(300f, 300f + offset)
    )

    Box(modifier = Modifier.size(200.dp).background(brush))

}