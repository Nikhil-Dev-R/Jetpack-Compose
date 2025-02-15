package com.rudraksha.practice.animations.custom

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun CustomAnimationSpecExample() {
    var isExpanded by remember { mutableStateOf(false) } // State to toggle size
    val size by animateDpAsState(
        targetValue = if (isExpanded) 200.dp else 100.dp, // Toggle size
        animationSpec = tween(durationMillis = 2000) // Animation duration
    )
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Animation Spec Example")

        Box(
            modifier = Modifier
                .size(size)
                .background(Color.Red)
                .clickable {
                    isExpanded = !isExpanded // Toggle state on click
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
fun PreviewCustomAnimationSpecExample() {
    MaterialTheme {
        CustomAnimationSpecExample()
    }
}