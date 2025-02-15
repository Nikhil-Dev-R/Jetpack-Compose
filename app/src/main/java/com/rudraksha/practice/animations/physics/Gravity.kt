package com.rudraksha.practice.animations.physics

// Simulates gravity-based motion
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.tooling.preview.Preview
import kotlin.math.abs

@Composable
fun GravityBasedMotionAnimation() {
    // Create an Animatable for the ball's position
    val ballPosition = remember { Animatable(0f) }

    // Launch the animation effect
    LaunchedEffect(Unit) {
        // Gravity simulation parameters
        var velocity = 0f // Initial velocity
        val gravity = 980f // Acceleration due to gravity (pixels per second squared)
        val damping = 0.9f // Damping factor for the bounce
        val groundLevel = 600f // Ground level position

        while (true) {
            // Apply gravity to velocity
            velocity += gravity * 0.016f // Update velocity based on gravity (assuming 60 FPS)

            // Update the ball's position based on its velocity
            ballPosition.snapTo(ballPosition.value + velocity * 0.016f)

            // Check if the ball hits the ground
            if (ballPosition.value >= groundLevel) {
                // Reset to ground level and apply damping
                ballPosition.snapTo(groundLevel)
                velocity = -velocity * damping // Reverse and dampen the velocity
            }

            // Exit the loop if the velocity is small enough to stop bouncing
            if (abs(velocity) < 1) {
                break
            }

            delay(16) // Delay to simulate frame rate
        }
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(250.dp)
            .background(Color.Green)
    ) {
        // Draw the ball on the canvas
        Canvas(modifier = Modifier.fillMaxSize()) {
            drawCircle(
                color = Color.Red,
                radius = 20.dp.toPx(),
                center = androidx.compose.ui.geometry.Offset(size.width / 2, ballPosition.value)
            )
        }
    }
}

@Composable
fun GravityBasedMotionWithDiagonalMovementAnimation() {
    // Create an Animatable for the ball's vertical position
    val ballVerticalPosition = remember { Animatable(0f) }
    // Create an Animatable for the ball's horizontal position
    val ballHorizontalPosition = remember { Animatable(0f) }
    val frameDelayInMilliSeconds = 16L
    val frameDelayInSeconds = (16f / 1000)

    // Launch the animation effect
    LaunchedEffect(Unit) {
        // Gravity simulation parameters
        var velocity = 0f // Initial vertical velocity
        val gravity = 980f // Acceleration due to gravity (pixels per second squared)
        val damping = 0.9f // Damping factor for the bounce
        val groundLevel = 600f // Ground level position
        val targetHorizontal = 800f // Target horizontal position (bottom-right)
        val totalDuration = 6000 // Total duration for the horizontal movement in milliseconds
        val horizontalSpeed = targetHorizontal / totalDuration * frameDelayInMilliSeconds
        // Horizontal speed based on frame delay

        // Start moving horizontally
        while (ballHorizontalPosition.value < targetHorizontal) {
            // Apply gravity to vertical velocity
            velocity += gravity * frameDelayInSeconds // Update velocity based on gravity (assuming 60 FPS)

            // Update the ball's vertical position based on its velocity
            ballVerticalPosition.snapTo(ballVerticalPosition.value + velocity * frameDelayInSeconds)
            // Update the ball's horizontal position
            ballHorizontalPosition.snapTo(ballHorizontalPosition.value + horizontalSpeed)

            // Check if the ball hits the ground
            if (ballVerticalPosition.value >= groundLevel) {
                // Reset to ground level and apply damping
                ballVerticalPosition.snapTo(groundLevel)
                velocity = -velocity * damping // Reverse and dampen the velocity
            }

            // Exit the loop if the velocity is small enough to stop bouncing
            if (abs(velocity) < 1 && ballVerticalPosition.value >= groundLevel) {
                break
            }

            delay(frameDelayInMilliSeconds) // Delay to simulate frame rate
        }
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(250.dp)
            .background(Color.Magenta)
    ) {
        // Draw the ball on the canvas
        Canvas(modifier = Modifier.fillMaxSize()) {
            drawCircle(
                color = Color.Red,
                radius = 20.dp.toPx(),
                center = androidx.compose.ui.geometry.Offset(
                    ballHorizontalPosition.value,
                    ballVerticalPosition.value
                )
            )
        }
    }
}


@Composable
fun BouncingBall() {
    val infiniteTransition = rememberInfiniteTransition("")
    val bounceHeight by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = -200f,
        animationSpec = infiniteRepeatable(
            tween(1000, easing = FastOutSlowInEasing),
            RepeatMode.Reverse
        ), ""
    )

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .size(50.dp)
                .graphicsLayer {
                    translationY = bounceHeight
                    scaleX = 1 + bounceHeight / -400f
                    scaleY = 1 + bounceHeight / -400f
                }
                .background(Color.Red, shape = CircleShape)
        )
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewGravityBasedMotionWithDiagonalMovementAnimation() {
    GravityBasedMotionWithDiagonalMovementAnimation()
}

@Preview(showBackground = true)
@Composable
fun PreviewGravityBasedMotionAnimation() {
    GravityBasedMotionAnimation()
}
