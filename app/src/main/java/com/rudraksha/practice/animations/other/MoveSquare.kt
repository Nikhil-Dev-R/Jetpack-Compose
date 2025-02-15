package com.rudraksha.practice.animations.other

import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.VectorConverter
import androidx.compose.animation.core.animateValue
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.animation.core.rememberInfiniteTransition

@Composable
fun MovingSquareBox() {
    // Define the positions for points A, B, C, D
    val positions = listOf(
        0f to 0f,      // A: Top-left
        300f to 0f,    // B: Top-right
        300f to 300f,  // C: Bottom-right
        0f to 300f     // D: Bottom-left
    )

    // Create an infinite transition
    val transition = rememberInfiniteTransition(label = "")
    val animatedIndex = transition.animateValue(
        initialValue = 0,
        targetValue = 3,
        typeConverter = Int.VectorConverter,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 1000),
            repeatMode = RepeatMode.Restart
        ),
        label = ""
    )

    // Get current position
    val currentPosition = positions[animatedIndex.value]

    Box(
        modifier = Modifier
            .size(100.dp)
            .offset(x = currentPosition.first.dp, y = currentPosition.second.dp)
            .background(Color.Cyan)
            .fillMaxSize(), // Ensure the box is centered
        contentAlignment = Alignment.Center
    ) {
        Text("Scroll")
    }
}

@Preview(showBackground = true)
@Composable
fun MovingSquareBoxPreview() {
    MovingSquareBox()
}
