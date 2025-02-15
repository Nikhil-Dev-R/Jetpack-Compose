package com.rudraksha.practice.animations.custom

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview
import kotlinx.coroutines.launch

@Composable
fun AnimatableExample() {
    val scope = rememberCoroutineScope() // Coroutine scope for launching animations
    val size = remember { Animatable(100f) } // Start with a size of 100f

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Animatable Example")

        Box(
            modifier = Modifier
                .size(size.value.dp) // Use the current size value
                .background(Color.Blue)
                .clickable {
                    scope.launch {
                        // Animate to a new size when clicked
                        size.animateTo(
                            targetValue = if (size.value == 100f) 200f else 100f, // Toggle size
                            animationSpec = tween(durationMillis = 2000), // Animation duration
                        )
                    }
                },
            contentAlignment = Alignment.Center
        ) {
            Box(
                modifier = Modifier
                    .size(100.dp)
                    .background(Color.Magenta)
            )
        }
    }
}

@Preview
@Composable
fun PreviewAnimatableExample() {
    MaterialTheme {
        AnimatableExample()
    }
}