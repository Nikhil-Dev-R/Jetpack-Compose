package com.rudraksha.practice.animations.utilities

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ParallaxScroll() {
    val scrollState = rememberScrollState()

    Box(modifier = Modifier.verticalScroll(scrollState)) {
        // Background layer (moves slower)
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(500.dp)
                .graphicsLayer{
                    translationY = scrollState.value * 0.5f
                }
                .background(Color(0xFFB0BEC5))
        )

        // Mid-ground layer (moves at a normal speed)
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(400.dp)
                .graphicsLayer {
                    translationY = scrollState.value * 0.8f
                }
                .background(Color(0xFF78909C))
        )

        // Foreground layer (moves faster)
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
                .graphicsLayer {
                    translationY = scrollState.value.toFloat()
                }
                .background(Color(0xFF455A64))
        )

        // Content (text or images) to complete the parallax effect
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .graphicsLayer {
                    translationY = scrollState.value.toFloat() //Adjust parallax factor as needed
                }
                .background(Color.Cyan), // Or any desired background
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Parallax Scroll Animation",
                fontSize = 32.sp,
                color = Color.White,
                modifier = Modifier
                    .align(Alignment.Center)
            )
        }
    }
}