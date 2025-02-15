package com.rudraksha.practice.animations.other

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.animation.core.LinearEasing
import androidx.compose.foundation.layout.offset

// The wave animation will create a wave effect by moving the composable up and down.
@Composable
fun WaveAnimation() {
    var wave by remember { mutableStateOf(false) }
    val offsetY = animateFloatAsState(
        targetValue = if (wave) 20f else 0f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 500, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        )
    )

    Box(
        modifier = Modifier
            .size(100.dp)
            .offset(y = offsetY.value.dp)
            .background(Color.Green)
            .clickable { wave = !wave },
        contentAlignment = Alignment.Center
    ) {
        Text("Wave")
    }
}

@Preview
@Composable
fun WaveAnimationPreview() {
    WaveAnimation()
}