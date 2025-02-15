package com.rudraksha.practice.animations.physics

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

// Simulates physical spring behavior
@Composable
fun SpringAnimationExample() {
    val scope = rememberCoroutineScope()
    val offsetY = remember { Animatable(0f) } // Initial position of the ball

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(500.dp),
        contentAlignment = Alignment.TopCenter
    ) {
        // Ball
        Box(
            modifier = Modifier
                .size(100.dp)
                .offset {
                    IntOffset(
                        x = 0, // No horizontal movement
                        y = offsetY.value.toInt() // Use the vertical offset
                    )
                }
                /*.graphicsLayer(
                    translationX = 0f, // No horizontal translation
                    translationY = offsetY.value // Apply the vertical offset
                )*/
                .background(Color.Red)
        )

        // Button to trigger the spring animation
        Button(onClick = {
            scope.launch {
                // Reset the position before animation
                offsetY.snapTo(0f)
                // Start the spring animation
                offsetY.animateTo(
                    targetValue = 300f, // Move down
                    animationSpec = spring(
                        dampingRatio = Spring.DampingRatioHighBouncy, // Adjust the damping ratio
                        stiffness = Spring.StiffnessLow // Adjust the stiffness
                    )
                )
            }
        }) {
            Text("Bounce")
        }
    }
}