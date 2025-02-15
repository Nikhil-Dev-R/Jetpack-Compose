package com.rudraksha.practice.animations.utilities

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun MultiStepProgress() {
    val infiniteTransition = rememberInfiniteTransition()

    // Step 1: Small Pulse
    val pulseScale1 by infiniteTransition.animateFloat(
        initialValue = 1f,
        targetValue = 2.5f,
        animationSpec = infiniteRepeatable(
            tween(1200, easing = FastOutSlowInEasing),
            RepeatMode.Restart
        ),
        ""
    )
    // Step 2: Medium Pulse
    val pulseScale2 by infiniteTransition.animateFloat(
        initialValue = 1f,
        targetValue = 2f,
        animationSpec = infiniteRepeatable(
            tween(1400, easing = FastOutSlowInEasing),
            RepeatMode.Restart
        ),
        ""
    )

    // Step 3: Large Pulse
    val pulseScale3 by infiniteTransition.animateFloat(
        initialValue = 1f,
        targetValue = 1.5f,
        animationSpec = infiniteRepeatable(
            tween(1600, easing = FastOutSlowInEasing),
            RepeatMode.Restart
        ),
        ""
    )

    Box(
        modifier = Modifier
            .size(200.dp),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .size(60.dp)
                .scale(pulseScale1)
                .background(Color.Blue, shape = CircleShape)
        )
        Box(
            modifier = Modifier
                .size(40.dp)
                .scale(pulseScale2)
                .background(Color.Cyan, shape = CircleShape)
        )
        Box(
            modifier = Modifier
                .size(20.dp)
                .scale(pulseScale3)
                .background(Color(0xFF00ABAB), shape = CircleShape)
        ) {
            Text("❤️", fontSize = 16.sp, color = Color.White)
        }
    }
}

@Composable
fun DynamicLoadingBar() {
    val infiniteTransition = rememberInfiniteTransition("")
    val offset by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 200f,
        animationSpec = infiniteRepeatable(
            tween(2000, easing = LinearEasing),
            RepeatMode.Reverse
        ), ""
    )

    Box(
        modifier = Modifier
            .width(250.dp)
            .height(10.dp)
            .background(Color.LightGray, shape = RoundedCornerShape(5.dp))
    ) {
        Box(
            modifier = Modifier
                .width(50.dp)
                .height(10.dp)
                .offset(x = offset.dp)
                .background(Color.Blue, shape = RoundedCornerShape(5.dp))
        )
    }
}