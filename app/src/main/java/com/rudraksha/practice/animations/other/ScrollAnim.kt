package com.rudraksha.practice.animations.other

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay

// The scroll animation will move the composable horizontally across the screen.
@Composable
fun ScrollAnimation() {
    var isScrolling by remember { mutableStateOf(false) }
    val scrollTarget by remember { mutableFloatStateOf(400f) }

    val offsetX = animateFloatAsState(
        targetValue = if (isScrolling) scrollTarget else 0f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 1000, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse // Reverse the animation direction
        ),
        label = ""
    )

    Box(
        modifier = Modifier
            .size(100.dp)
            .offset{
                IntOffset(x = offsetX.value.toInt(), y = 0)
            }
            .background(Color.Cyan)
            .clickable {
                isScrolling = true
            },
        contentAlignment = Alignment.Center
    ) {
        Text("Scroll")
    }
}

@Composable
fun ScrollAnimationTwoWay() {
    var isScrolling by remember { mutableStateOf(false) }
    var scrollTarget by remember { mutableFloatStateOf(0f) }
    val animationDuration = 1500
    val maxScrollLimit = 400f

    val offsetX = animateFloatAsState(
        targetValue = scrollTarget,
        animationSpec =
            if (isScrolling) {
                infiniteRepeatable(
                    animation = tween(
                        durationMillis = animationDuration,
                        easing = FastOutSlowInEasing
                    ),
                    repeatMode = RepeatMode.Reverse // Reverse the animation direction
                )
            } else {
                tween(durationMillis = animationDuration, easing = LinearEasing)
            },
        label = ""
    )

    LaunchedEffect(isScrolling) {
        if (isScrolling) {
            while (true) {
                scrollTarget = if (scrollTarget == 0f) maxScrollLimit else -scrollTarget // Toggle the scroll target
                delay(animationDuration.toLong())
            }
        } else {
            // Reset scrollTarget when not scrolling
            scrollTarget = 0f
        }
    }

    Box(
        modifier = Modifier
            .size(100.dp)
            .offset{
                IntOffset(x = offsetX.value.toInt(), y = 0)
            }
            .background(Color.Cyan)
            .clickable {
                isScrolling = !isScrolling
            },
        contentAlignment = Alignment.Center
    ) {
        Text("Scroll2")
    }
}

@Preview
@Composable
fun ScrollAnimationPreview() {
    ScrollAnimation()
}