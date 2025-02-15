package com.rudraksha.practice.animations.brushes

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.rudraksha.practice.animations.utilities.CircularMenuAnimation
import com.rudraksha.practice.animations.utilities.ExpandingCard

//Animate a custom linear gradient with repeating stripes in a diagonal direction.
//Ideal for backgrounds or loading indicators.
@Composable
fun BrushAnimations() {
    LazyColumn(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        item {
            GradientColor()
            RadialPulse()
            DiagonalStripe()
            SweepGradientRotation()
            RainbowText()
//            ColorWave()
            ShimmerEffect()
//            Fire()
            LoadingRing()
//            SunsetHorizon()
//            SunsetScene()
            Weather()
//            AnimatedClock()
            ExpandingCard()
            CircularMenuAnimation()
        }
    }
}