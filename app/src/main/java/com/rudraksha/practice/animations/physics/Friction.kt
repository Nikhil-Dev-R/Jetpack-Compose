package com.rudraksha.practice.animations.physics

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import kotlin.math.abs
import kotlin.math.cos
import kotlin.math.sin

// Simulates friction-based motion
@Composable
fun FrictionBasedMotionAnimation() {
    // Create an Animatable for the ball's position
    val ballPosition = remember { Animatable(0f) }
    val ballRotation = remember { Animatable(0f) }

    // Launch the animation effect
    LaunchedEffect(Unit) {
        // Initial velocity
        var velocity = 1500f // pixels per second
        val friction = 0.97f // friction factor

        while (velocity > 100f) { // Limit the movement to 1000 pixels
            // Update the position based on velocity
            ballPosition.animateTo(
                targetValue = ballPosition.value + velocity * 0.016f, // Assuming 60 FPS
                animationSpec = tween(durationMillis = 16)
            )
            velocity *= friction // Apply friction

            // Update rotation based on the vertical velocity
            ballRotation.snapTo((ballRotation.value + (velocity * 0.05f)) % 360)

            delay(16) // Delay to simulate frame rate
        }
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
            .background(Color.Cyan)
    ) {
        // Draw the ball on the canvas
        Canvas(modifier = Modifier.fillMaxSize()) {
            val centerX = ballPosition.value
            val centerY = size.height / 2
            drawCircle(
                color = Color.Red,
                radius = 20.dp.toPx(),
                center = Offset(centerX, centerY)
            )

            // Optionally, draw a rotation effect (like a shadow or a line) to visualize rotation
            drawIntoCanvas { canvas ->
                // Drawing a simple line to represent rotation
                val angleInRadians = Math.toRadians(ballRotation.value.toDouble())
                val endX = centerX + 30 * cos(angleInRadians).toFloat()
                val endY = centerY + 30 * sin(angleInRadians).toFloat()
                canvas.drawLine(
                    p1 = Offset(centerX, centerY),
                    p2 = Offset(endX, endY),
                    paint = Paint().apply {
                        color = Color.Black
                        strokeWidth = 4.dp.toPx()
                    }
                )
            }
        }
    }
}

@Composable
fun FallingJumpingRotatingBallAnimation() {
    // Create Animatables for the ball's position and rotation
    val ballPosition = remember { Animatable(0f) }
    val ballRotation = remember { Animatable(0f) }

    // Launch the animation effect
    LaunchedEffect(Unit) {
        // Falling phase
        ballPosition.animateTo(
            targetValue = 600f, // Fall to 600 pixels from the top
            animationSpec = tween(durationMillis = 1000)
        )

        // Jump phase
        var velocity = -500f // Initial upward velocity
        val gravity = 1000f // Gravity pulling the ball down
        val damping = 0.9f // Damping factor for the jump
        var isJumping = true

        while (isJumping) {
            // Update position based on velocity
            ballPosition.snapTo(ballPosition.value + velocity * 0.016f) // Assuming 60 FPS

            // Apply gravity
            velocity += gravity * 0.016f

            // Check if the ball has hit the ground
            if (ballPosition.value >= 600f) {
                // Bounce back with damping
                ballPosition.snapTo(600f) // Reset to ground level
                velocity = -velocity * damping // Reverse and dampen the velocity

                if (abs(velocity) < 100) { // Stop jumping if the velocity is small
                    isJumping = false
                }
            }

            // Update rotation based on the vertical velocity
            ballRotation.snapTo((ballRotation.value + (velocity * 0.001f)) % 360) // Rotate based on velocity

            delay(16) // Delay to simulate frame rate
        }
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(250.dp)
            .background(Color.Blue)
    ) {
        // Draw the ball on the canvas
        Canvas(modifier = Modifier.fillMaxSize()) {
            val centerX = size.width / 2
            val centerY = ballPosition.value
            val radius = 20.dp
            drawCircle(
                color = Color.Red,
                radius = radius.toPx(),
                center = Offset(centerX, centerY)
            )

            // Optionally, draw a rotation effect (like a shadow or a line) to visualize rotation
            drawIntoCanvas { canvas ->
                // Drawing a simple line to represent rotation
                val angleInRadians = Math.toRadians(ballRotation.value.toDouble())
                val endX = centerX + 30 * cos(angleInRadians).toFloat()
                val endY = centerY + 30 * sin(angleInRadians).toFloat()
                canvas.drawLine(
                    p1 = Offset(centerX, centerY),
                    p2 = Offset(endX, endY),
                    paint = Paint().apply {
                        color = Color.Black
                        strokeWidth = 4.dp.toPx()
                    }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewFallingJumpingRotatingBallAnimation() {
    FallingJumpingRotatingBallAnimation()
}

@Preview(showBackground = true)
@Composable
fun PreviewFrictionBasedMotionAnimation() {
    FrictionBasedMotionAnimation()
}