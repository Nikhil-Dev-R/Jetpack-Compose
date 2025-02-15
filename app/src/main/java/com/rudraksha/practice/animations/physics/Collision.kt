package com.rudraksha.practice.animations.physics

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import kotlin.math.roundToInt

// Simulates collision-based motion
@Composable
fun CollisionBouncingBox() {
    // Create an Animatable for the position of the box
    val positionX = remember { Animatable(0f) }
    val positionY = remember { Animatable(0f) }
    val boxSize = 100.dp // Convert dp to pixels for calculations
    val screenWidth = 300.dp // Example screen width
    val screenHeight = 600.dp // Example screen height

    // Convert dp to pixels using LocalDensity
    val density = LocalDensity.current
    val boxSizePx = with(density) { boxSize.toPx() }
    val screenWidthPx = with(density) { screenWidth.toPx() }
    val screenHeightPx = with(density) { screenHeight.toPx() }

    // Coroutine scope for launching animations
    // val scope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        // Start the bouncing animation
        while (true) {
            // Animate to the right
            positionX.animateTo(
                targetValue = screenWidthPx - boxSizePx,
                animationSpec = tween(durationMillis = 1000)
            )
            // Animate to the left
            positionX.animateTo(
                targetValue = 0f,
                animationSpec = tween(durationMillis = 1000)
            )
            // Animate down
            positionY.animateTo(
                targetValue = screenHeightPx - boxSizePx,
                animationSpec = tween(durationMillis = 1000)
            )
            // Animate up
            positionY.animateTo(
                targetValue = 0f,
                animationSpec = tween(durationMillis = 1000)
            )
            delay(1000) // Delay between animations
        }
    }

    // Draw the box at the animated position
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
    ) {
        Box(
            modifier = Modifier
                .size(boxSize)
                .background(Color.Red)
                .offset {
                    IntOffset(
                        positionX.value.roundToInt(),
                        positionY.value.roundToInt()
                    )
                }
        )
    }
}

@Preview
@Composable
fun BouncingBoxPreview() {
    MaterialTheme {
        CollisionBouncingBox()
    }
}