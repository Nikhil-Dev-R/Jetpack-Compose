package com.rudraksha.practice.animations.basic

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.rudraksha.practice.ui.theme.PracticeTheme

// Position shift
// The offset animation changes the position of a composable by altering its Modifier.offset
// value over time. This approach moves the element relative to its original position.
// Moves the element relative to its original position.
// Affects layout; other elements may adjust based on the position.

// Use offset animations when you want the layout to respond to the movement
// (e.g., moving items in a list).

@Composable
fun OffsetAnimation(modifier: Modifier = Modifier) {
    var offset by remember { mutableFloatStateOf(0f) }
    val animatedOffsetX by animateFloatAsState(targetValue = offset, label = "OffsetAnimation")
    val animatedOffsetY by animateFloatAsState(targetValue = offset, label = "OffsetAnimation")

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Button(onClick = {
            offset = ( offset + 50f) % 150
        }) {
            Text("Offset Animation")
        }
        Box(
            modifier = modifier.offset(
                y = animatedOffsetY.dp,
                x = animatedOffsetX.dp
            )
        ) {
            Box(
                modifier = modifier
                    .size(100.dp)
                    .background(Color.Blue)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun OffsetPreview() {
    PracticeTheme {
        OffsetAnimation()
    }
}
