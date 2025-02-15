package com.rudraksha.practice.animations.utilities

import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp

@Composable
fun AnimatedClock() {
    val infiniteTransition = rememberInfiniteTransition("")

    // Simulate second hand movement
    val secondsAngle by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            tween(60000, easing = LinearEasing)
        ),
        ""
    )

    // Simulate minute hand movement
    val minutesAngle by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            tween(3600000, easing = LinearEasing)
        ),
        ""
    )

    // Simulate hour hand movement
    val hoursAngle by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            tween(43200000, easing = LinearEasing)
        ),
        ""
    )

    Box(modifier = Modifier
        .size(300.dp)
        .clip(CircleShape)
        .background(Color.White),
        contentAlignment = Alignment.Center
    ) {
        // Hour hand
        Box(
            modifier = Modifier
                .width(4.dp)
                .height(50.dp)
                .background(Color.Blue, shape = RoundedCornerShape(2.dp))
                .align(Alignment.Center)
                .rotate(hoursAngle)
        )

        // Minute hand
        Box(
            modifier = Modifier
                .width(3.dp)
                .height(70.dp)
                .background(Color.Green, shape = RoundedCornerShape(2.dp))
//                .align(Alignment.Center)
                .rotate(minutesAngle)
        )

        // Second hand
        Box(
            modifier = Modifier
                .width(2.dp)
                .height(90.dp)
                .background(Color.Red, shape = RoundedCornerShape(1.dp))
                .align(Alignment.Center)
                .rotate(secondsAngle)
        )
    }
}

@Composable
fun AnimatedClock2() {
    val infiniteTransition = rememberInfiniteTransition("")

    val minuteRotation by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            tween(60000, easing = LinearEasing)
        ),""
    )

    val hourRotation by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            tween(12 * 60000, easing = LinearEasing)
        ),""
    )

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.size(200.dp)
    ) {
        // Clock face
        Canvas(modifier = Modifier.size(200.dp)) {
            drawCircle(color = Color.LightGray)
            drawCircle(color = Color.DarkGray, radius = 10.dp.toPx())
        }

        // Hour hand
        Box(
            modifier = Modifier
                .width(8.dp)
                .height(50.dp)
                .background(Color.Black)
                .graphicsLayer {
                    rotationZ = hourRotation
                },
        )

        // Minute hand
        Box(
            modifier = Modifier
                .width(4.dp)
                .height(70.dp)
                .background(Color.Black)
                .graphicsLayer {
                    rotationZ = minuteRotation
                },
        )
    }
}

