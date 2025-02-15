package com.rudraksha.practice.animations.utilities

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun MorphingCircularRevealButton() {
    var clicked by remember { mutableStateOf(false) }
    val size by animateDpAsState(if (clicked) 800.dp else 56.dp, label = "")
    val color by animateColorAsState(if (clicked) Color(0xFFFF6F00) else Color(0xFF0288D1),
        label = ""
    )

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .size(size)
            .clip(CircleShape)
            .background(color)
            .clickable {
                clicked = !clicked
            }
    ) {
        if (!clicked) {
            Text("Go", color = Color.White, fontSize = 20.sp)
        } else {
            // When clicked, it fills the entire screen like a circular reveal
            CircularProgressIndicator(color = Color.White)
            LinearProgressIndicator(color = Color.Green)
        }
    }
}
