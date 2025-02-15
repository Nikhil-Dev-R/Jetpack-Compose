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

// The pulse animation will scale the composable up and down.
@Composable
fun PulseAnimation() {
    var pulsing by remember { mutableStateOf(false) }
    val scale by animateFloatAsState(
        targetValue = if (pulsing) 1.2f else 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 500, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = ""
    )

    Box(
        modifier = Modifier
            .size(100.dp)
            .scale(scale)
            .background(Color.Blue)
            .clickable { pulsing = !pulsing },
        contentAlignment = Alignment.Center
    ) {
        Text("Pulse")
    }
}

@Preview
@Composable
fun PulseAnimationPreview() {
    PulseAnimation()
}
