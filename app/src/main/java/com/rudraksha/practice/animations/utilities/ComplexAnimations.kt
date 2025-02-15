package com.rudraksha.practice.animations.utilities

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

//Animate a custom linear gradient with repeating stripes in a diagonal direction.
//Ideal for backgrounds or loading indicators.
@Composable
fun ComplexAnimations() {
    ParallaxScroll()
    LazyColumn(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        item {
//            MultiStepProgress()
//            ParallaxScroll()
            //MorphingCircularRevealButton()
            //FoldingScreenTransition()
//            ExplodingParticles()
            //GalaxyStarfield()
//            FireworksEffect()
//            AnimatedClock()
//            AnimatedClock2()
            //BouncingBall()
            //DynamicLoadingBar()
        }
        item {
//            TouchWaveRipples()
//            PortalWarpAnimation()
            //SolarSystemOrbit()
        }
    }
}

@Composable
fun PortalWarpAnimation() {
    var warpTriggered by remember { mutableStateOf(false) }
    val size by animateDpAsState(if (warpTriggered) 800.dp else 50.dp)
    val alpha by animateFloatAsState(if (warpTriggered) 0f else 1f)

    Box(
        modifier = Modifier
            .fillMaxSize()
            .clickable { warpTriggered = !warpTriggered },
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .size(size)
                .background(
                    Color(0xFF6200EA).copy(alpha = alpha),
                    shape = CircleShape
                )
        )
    }
}
