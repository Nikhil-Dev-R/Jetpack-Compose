package com.rudraksha.practice.animations.basic

import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun CustomKeyframeAnimation() {
    var start by remember { mutableStateOf(true) }
    val transition = updateTransition(targetState = start, label = "Custom Transition")

    val rotation by transition.animateFloat(
        transitionSpec = { tween(durationMillis = 2000) },
        label = "Rotation"
    ) { state -> if (state) 0f else 360f }

    val scale by transition.animateFloat(
        transitionSpec = { tween(durationMillis = 2000) },
        label = "Scale"
    ) {
        state -> if (state) 1f else 2f
    }

    val alpha by transition.animateFloat(
        label = "alpha",
    ) {
        state -> if (state) 1f else 0.5f
    }

    Column {
        Button(onClick = { start = !start }) {
            Text("Toggle Rotation CustomKeyframeAnimation")
        }
        Box(
            modifier = Modifier
                .size(100.dp)
                .rotate(rotation)
                .scale(scale)
                .background(Color.Cyan.copy(alpha = alpha))
        )
    }
}
