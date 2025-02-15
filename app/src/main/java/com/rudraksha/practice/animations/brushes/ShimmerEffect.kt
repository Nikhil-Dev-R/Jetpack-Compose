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
fun ShimmerEffect() {
    val shimmerTransition = rememberInfiniteTransition()
    val shimmerX by shimmerTransition.animateFloat(
        initialValue = 0f,
        targetValue = 200f,
        animationSpec = infiniteRepeatable(tween(1000, easing = LinearEasing)),
        ""
    )

    val brush = Brush.linearGradient(
        colors = listOf(Color.Gray, Color.LightGray, Color.Gray),
        start = Offset(shimmerX, shimmerX),
        end = Offset(shimmerX + 100f, shimmerX + 100f)
    )

    Box(modifier = Modifier.size(200.dp, 200.dp).background(brush))

}

