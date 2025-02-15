package com.rudraksha.practice.animations.basic

import androidx.compose.animation.animateColor
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay

@Composable
fun UpdateTransitionAnimation() {
    var isVisible by remember { mutableStateOf(true) }
    val transition = updateTransition(targetState = isVisible, label = "Visibility Transition")
    // updateTransition is used to create multiple animations with similar state values

    val scale by transition.animateFloat(label = "Scale") { state ->
        if (state) 1f else 0f
    }
    val color by transition.animateColor(label = "Color") { state ->
        if (state) Color.Cyan else Color.Magenta
    }
    val rotation by transition.animateFloat(label = "Rotation") { state ->
        if (state) 0f else 45f
    }

    // updateTransition.animateFloat is similar to animateFloatAsState
    // updateTransition.animateColor is similar to animateColorAsState
    // updateTransition.animateDp is similar to animateDpAsState

    // But the difference is that updateTransition is used to create
    // multiple animations with similar state values and its more flexible
    // than animateFloatAsState

    Column {
        Button(onClick = { isVisible = !isVisible }) {
            Text("Toggle UpdateTransitionAnimation")
        }
        Box(
            modifier = Modifier
                .size(100.dp)
                .scale(scale)
                .background(color)
                .rotate(rotation)
        )
    }
}

@Composable
fun AnimateAlphaWithAnimateFloatAsState() {
    var trigger by remember { mutableStateOf(false) }
    val alpha by animateFloatAsState(
        targetValue = if (trigger) 1f else 0f,
        animationSpec = tween(durationMillis = 300),
        label = "AnimateAlphaWithAnimateFloatAsState"
    )
    Button(onClick = { trigger = !trigger }) {
        Box(
            modifier = Modifier
                .size(100.dp)
                .background(Color.Red.copy(alpha = alpha))
        ) {
            Text("AnimateAlphaWithAnimateFloatAsState")
        }
    }
}

enum class BoxState { Visible, Hidden }

@Composable
fun AnimateAlphaWithUpdateTransition() {
    var boxState by remember { mutableStateOf(BoxState.Visible) }
    val transition = updateTransition(
        targetState = boxState,
        label = "AnimateAlphaWithUpdateTransition"
    )

    val alpha by transition.animateFloat(
        label = "alphaAnimation",
        transitionSpec = { tween(durationMillis = 300) }
    ) { state ->
        when (state) {
            BoxState.Visible -> 1f
            BoxState.Hidden -> 0f
        }
    }
    Button(
        onClick = {
            boxState = when(boxState) {
                BoxState.Visible -> BoxState.Hidden
                BoxState.Hidden -> BoxState.Visible
            }
        }
    ) {
        Box(
            modifier = Modifier
                .size(100.dp)
                .background(Color.Red.copy(alpha = alpha))
        ) {
            Text("AnimateAlphaWithUpdateTransition")
        }
    }
}
