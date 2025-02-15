package com.rudraksha.practice.animations.advanced

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.rudraksha.practice.R

@Composable
fun LottieAnimations() {
    var hasFinished by remember { mutableStateOf(false) }
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        if (hasFinished) {
            CongratsLottieAnim(
                modifier = Modifier.align(Alignment.Center),
            )
        }
        Button(
            modifier = Modifier.align(Alignment.BottomCenter),
            onClick = { hasFinished = !hasFinished }
        ) {
            Text(
                text = if (hasFinished) "Start" else "Running"
            )
        }
    }

    var isPlaying by remember { mutableStateOf(false) }
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        SendingLottieAnim(
            modifier = Modifier.align(Alignment.Center),
            isPlaying = isPlaying
        )
        Button(
            modifier = Modifier.align(Alignment.BottomCenter),
            onClick = { isPlaying = !isPlaying }
        ) {
            Text(text = if (isPlaying) "Stop" else "Play")
        }
    }
}

@Composable
fun CongratsLottieAnim(
    modifier: Modifier = Modifier
) {
    val lottieComposition by rememberLottieComposition(
        spec = LottieCompositionSpec.RawRes(R.raw.lottie_easter_anim)
    )

    LottieAnimation(
        modifier = modifier,
        composition = lottieComposition
    )
}

@Composable
fun SendingLottieAnim(
    modifier: Modifier = Modifier,
    isPlaying: Boolean
) {
    val lottieComposition by rememberLottieComposition(
        spec = LottieCompositionSpec.RawRes(R.raw.lottie_send_anim)
    )

    LottieAnimation(
        modifier = modifier,
        composition = lottieComposition,
        iterations = LottieConstants.IterateForever,
        speed = 2.5f,
        isPlaying = isPlaying
    )
}