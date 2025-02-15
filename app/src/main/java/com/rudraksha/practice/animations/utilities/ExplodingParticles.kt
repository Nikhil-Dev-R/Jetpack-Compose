package com.rudraksha.practice.animations.utilities

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun ExplodingParticles() {
    val particleCount = 200
    var explosionTriggered by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .size(400.dp)
            .clickable { explosionTriggered = !explosionTriggered },
        contentAlignment = Alignment.Center
    ) {
        repeat(particleCount) { i ->
            val infiniteTransition = rememberInfiniteTransition("")
            val xOffset by infiniteTransition.animateFloat(
                initialValue = 0f,
                targetValue = (100..300).random().toFloat(),
                animationSpec = infiniteRepeatable(
                    animation = tween(800, easing = FastOutSlowInEasing)
                ), label = ""
            )
            val yOffset by infiniteTransition.animateFloat(
                initialValue = 0f,
                targetValue = (100..300).random().toFloat(),
                animationSpec = infiniteRepeatable(
                    animation = tween(800, easing = FastOutSlowInEasing)
                ), label = ""
            )

            if (explosionTriggered) {
                Box(
                    modifier = Modifier
                        .size(8.dp)
                        .offset(x = xOffset.dp, y = yOffset.dp)
                        .background(
                            color = Color(
                                (0xFF000000..0xFFFFFFFF).random()
                            ),
                            shape = CircleShape
                        )
                )
            }
        }
    }
}

@Preview
@Composable
fun Expl() {
    ExplodingParticles()
}