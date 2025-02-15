package com.rudraksha.practice.animations.utilities

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.gestures.detectTapGestures
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
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.PathMeasure

@Composable
fun GalaxyStarfield() {
    val infiniteTransition = rememberInfiniteTransition(label = "")
    val colors = listOf(Color.Yellow, Color.Blue, Color.Red, Color.Cyan, Color.Green, Color.Magenta,
        Color.White, Color(0xFF45B6B6)
    )
    val sizes = listOf(2.dp, 3.dp, 4.dp, 5.dp, 6.dp, 7.dp)

    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        repeat(200) {
            val offsetX by infiniteTransition.animateFloat(
                initialValue = (-1000..500).random().toFloat(),
                targetValue = 0f,
                animationSpec = infiniteRepeatable(
                    tween((3000..8000).random(), easing = LinearEasing),
                    RepeatMode.Restart
                ), label = ""
            )

            val offsetY by infiniteTransition.animateFloat(
                initialValue = (-1000..1000).random().toFloat(),
                targetValue = 0f,
                animationSpec = infiniteRepeatable(
                    tween((3000..8000).random(), easing = LinearEasing),
                    RepeatMode.Restart
                ), label = ""
            )

            Canvas(
                modifier = Modifier
                    .size(sizes.random())
                    .offset(x = offsetX.dp, y = offsetY.dp)
            ) {
                drawCircle(color = colors.random())
            }
        }
    }
}

@Preview
@Composable
fun MyPreview() {
    GalaxyStarfield()
}

@Composable
fun TouchWaveRipples() {
    var touchPosition by remember { mutableStateOf(Offset.Zero) }
    val rippleRadius by animateDpAsState(targetValue = 50.dp, label = "")

    Box(
        modifier = Modifier
            .fillMaxSize()
            .pointerInput(Unit) {
                detectDragGestures { change, dragAmount ->
                    touchPosition = change.position
//                    touchPosition += dragAmount.y or x
                }
            },
        contentAlignment = Alignment.CenterEnd
    ) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            drawCircle(
                color = Color.Cyan,
                radius = rippleRadius.toPx(),
                center = touchPosition,
                alpha = 0.5f
            )
        }
    }
}

@Composable
fun FireworksEffect() {
    val infiniteTransition = rememberInfiniteTransition("")

    repeat(100) {
        val xDirection by infiniteTransition.animateFloat(
            initialValue = 0f,
            targetValue = (50..200).random().toFloat(),
            animationSpec = infiniteRepeatable(
                animation = tween(1000, easing = FastOutSlowInEasing)
            ), ""
        )

        val yDirection by infiniteTransition.animateFloat(
            initialValue = 0f,
            targetValue = (50..200).random().toFloat(),
            animationSpec = infiniteRepeatable(
                animation = tween(1000, easing = FastOutSlowInEasing)
            ), ""
        )

        Box(
            modifier = Modifier
                .size(4.dp)
                .offset(x = xDirection.dp, y = yDirection.dp)
                .background(Color.Red, shape = CircleShape)
        )
    }
}

@Composable
fun SolarSystemOrbit() {
    val infiniteTransition = rememberInfiniteTransition()
    val marsRotation by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            tween(4100, easing = LinearEasing)
        ),
        ""
    )
    val venusRotation by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            tween(6100, easing = LinearEasing)
        ),
        ""
    )
    val earthRotation by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            tween(7900, easing = LinearEasing)
        ),
        ""
    )

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Box(
            modifier = Modifier
                .size(100.dp)
                .background(Color.Yellow, shape = CircleShape)
        )
        Box(
            modifier = Modifier
                .size(16.dp)
                .graphicsLayer {
                    translationX = 160f * kotlin.math
                        .cos(marsRotation * (Math.PI / 180))
                        .toFloat()
                    translationY = 140f * kotlin.math
                        .sin(marsRotation * (Math.PI / 180))
                        .toFloat()
                }
                .background(Color.Red, shape = CircleShape)
        )
        Box(
            modifier = Modifier
                .size(18.dp)
                .graphicsLayer {
                    translationX = 210f * kotlin.math
                        .cos(venusRotation * (Math.PI / 180))
                        .toFloat()
                    translationY = 190f * kotlin.math
                        .sin(venusRotation * (Math.PI / 180))
                        .toFloat()
                }
                .background(Color(0xFFF44336), shape = CircleShape)
        )
        Box(
            modifier = Modifier
                .size(20.dp)
                .graphicsLayer {
                    translationX = 270f * kotlin.math
                        .cos(earthRotation * (Math.PI / 180))
                        .toFloat()
                    translationY = 230f * kotlin.math
                        .sin(earthRotation * (Math.PI / 180))
                        .toFloat()
                }
                .background(Color.Blue, shape = CircleShape)
        )
    }
}


