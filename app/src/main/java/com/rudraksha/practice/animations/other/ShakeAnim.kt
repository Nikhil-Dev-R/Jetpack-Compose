package com.rudraksha.practice.animations.other

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.repeatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp

// The shake animation will move the composable left and right.
@Composable
fun ShakeAnimation() {
    var shake by remember { mutableStateOf(false) }
    val offsetX = animateFloatAsState(
        targetValue = if (shake) 10f else 0f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 100, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = ""
    )

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .size(100.dp)
            .offset { IntOffset(offsetX.value.toInt(), 0) }
            .background(Color.Red)
            .clickable { shake = !shake },
    ) {
        Text("Shake")
    }
}

@Preview
@Composable
fun ShakeAnimationPreview() {
    ShakeAnimation()
}