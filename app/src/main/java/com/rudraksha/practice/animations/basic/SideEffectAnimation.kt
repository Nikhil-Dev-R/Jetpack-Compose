package com.rudraksha.practice.animations.basic

import androidx.compose.animation.animateColor
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationVector1D
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.TwoWayConverter
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.animateValue
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

// Side effect means that the animation's behavior is influenced by changes in
// state or external events, rather than being purely driven by the function's local state.
@Composable
fun SideEffectAnimationUsingLaunchedEffect() {
    var trigger by remember { mutableStateOf(false) }
    val alpha = remember { Animatable(initialValue = 1f) }

    LaunchedEffect(trigger) {
        if (trigger) {
            // Suspend function 'animateTo' should be called only from a coroutine or another suspend function
            alpha.animateTo(0.2f)
        } else {
            alpha.animateTo(0.7f)
        }
    }

    Column {
        Button(onClick = { trigger = !trigger }) {
            Text("Trigger Launched Effect Animation")
        }
        Box(
            modifier = Modifier
                .size(100.dp)
                .background(Color.Blue.copy(alpha = alpha.value))
        )
    }
}

@Composable
fun SideEffectAnimationUsingSideEffect() {
    var isAnimating by remember { mutableStateOf(false) }
    val transitionState = rememberInfiniteTransition(label = "SideEffectAnimationUsingSideEffect")

    val boxSize by transitionState.animateValue(
        initialValue = 100.dp,
        targetValue = if (isAnimating) 200.dp else 100.dp,
        typeConverter = TwoWayConverter<Dp, AnimationVector1D>(
            convertToVector = { AnimationVector1D(it.value) },
            convertFromVector = { Dp(it.value) },
        ),
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 2000, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "boxSize"
    )

    val boxColor by transitionState.animateColor(
        initialValue = Color.Red,
        targetValue = if (isAnimating) Color.Green else Color.Red,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 2000, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "boxColor"
    )

    val boxRotation by transitionState.animateFloat(
        initialValue = 0f,
        targetValue = if (isAnimating) 360f else 0f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 4000, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "rotation"
    )

    // SideEffect to log whenever the animation starts or stops
    SideEffect {
        if (isAnimating) {
            println("Animation started")
        } else {
            println("Animation stopped")
        }
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .size(boxSize)
                .rotate(boxRotation)
                .background(boxColor)
                .clickable {
                    isAnimating = !isAnimating
                }
        )
    }

    Button(onClick = { isAnimating = !isAnimating }) {
        Text(text = if (isAnimating) "Stop Animation" else "Start Animation")
    }
}

