package com.rudraksha.practice.animations.basic

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

// Transparency Change
@Composable
fun AlphaAnimation() {
    var alpha by remember { mutableFloatStateOf(1f) }

    Column {
        Text("Alpha Animation")
        Button(onClick = { alpha = if (alpha == 1f) 0f else 1f }) {
            Text("Toggle")
        }
        Box(
            modifier = Modifier
                .size(100.dp)
                .alpha(alpha)
                .background(Color.Yellow)
        )
    }
}

@Composable
fun AlphaAnimationAnimateFloatAsState() {
    var visible by remember { mutableStateOf(true) }
    val alpha by animateFloatAsState(if (visible) 1f else 0f, label = "AlphaAnimationAnimateFloatAsState")

    Column {
        Text("Alpha Animation Animate*AsState")
        Button(onClick = { visible = !visible }) {
            Text("Toggle")
        }
        Box(
            modifier = Modifier
                .size(100.dp)
                .background(Color.Blue.copy(alpha = alpha))
        )
    }
}

@Composable
fun AlphaFadeAnimation() {
    var visible by remember { mutableStateOf(true) }
    val alpha by animateFloatAsState(
        targetValue = if (visible) 1f else 0f,
        animationSpec = tween(durationMillis = 1000),
        label = "AlphaFadeAnimation"
    )

    Column {
        Text("Alpha Fade Animation")
        Row {
            Switch(checked = visible, onCheckedChange = { visible = !visible })
            Button(onClick = { visible = !visible }) {
                Text("Toggle")
            }
        }
        Box(
            modifier = Modifier
                .size(100.dp)
                .background(Color.Magenta.copy(alpha = alpha))
        )
    }
}
