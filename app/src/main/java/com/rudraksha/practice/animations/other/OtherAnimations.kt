package com.rudraksha.practice.animations.other

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun OtherAnimations(modifier: Modifier = Modifier) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
//        ShakeAnimation()
//        BlinkAnimation()
//        WaveAnimation()
        ScrollAnimation()
        Spacer(modifier = modifier.height(32.dp))
        ScrollAnimationTwoWay()
//        PulseAnimation()
//        MovingSquareBox()
    }
}

@Preview
@Composable
fun PreviewCustomAnimations() {
    MaterialTheme {
        OtherAnimations()
    }
}