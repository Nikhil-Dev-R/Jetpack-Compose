package com.rudraksha.practice.animations.advanced

import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
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
import androidx.compose.ui.unit.sp

// Crossfade animation fades between two composables
@Composable
fun CrossfadeAnimation(modifier: Modifier = Modifier) {
    // State to control which composable to show
    var currentPage by remember { mutableStateOf("A") }

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .wrapContentWidth()
    ) {
        // Crossfade composable
        Crossfade(
            targetState = currentPage,
            animationSpec = tween(durationMillis = 5000),
            label = "Crossfade",
            modifier = modifier
                .size(100.dp),
        ) { screen ->
            when (screen) {
                "A" -> Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .size(100.dp)
                        .fillMaxSize()
                        .background(Color.Red)
                ) {
                    Text(
                        text = "Page A",
                        fontSize = 24.sp,
                        modifier = Modifier.fillMaxSize()
                    )
                }

                "B" -> Box(
                    modifier = Modifier
                        .height(100.dp)
                        .fillMaxSize()
                        .background(Color.Green)
                ) {
                    Text(
                        text = "Page B",
                        fontSize = 24.sp,
                        modifier = Modifier.fillMaxSize()
                    )
                }
            }
        }

        // Button to switch between pages
        Button(onClick = {
            currentPage = if (currentPage == "A") "B" else "A"
        }) {
            Text("Switch Page")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewCrossfadeExample() {
    CrossfadeAnimation()
}