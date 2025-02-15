package com.rudraksha.practice.animations.brushes

import androidx.compose.animation.animateColor
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp

@Composable
fun Weather() {
    val infiniteTransition = rememberInfiniteTransition("")

    // Sun opacity animation
    val sunAlpha by infiniteTransition.animateFloat(
        initialValue = 0.8f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            tween(3000, easing = LinearEasing),
            RepeatMode.Reverse
        ),
        ""
    )

    // Sun color animation (simulating the time of day)
    val sunColor by infiniteTransition.animateColor(
        initialValue = Color.Yellow,
        targetValue = Color(0xFFFFA726),  // Sunset orange
        animationSpec = infiniteRepeatable(
            tween(4000, easing = LinearEasing),
            RepeatMode.Reverse
        ),
        ""
    )

    // Cloud position animation for wind effect
    val cloudOffsetX by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 300f,
        animationSpec = infiniteRepeatable(
            tween(6000, easing = LinearEasing),
            RepeatMode.Reverse
        ),
        ""
    )

    Box(
        modifier = Modifier
            .size(500.dp)
            .background(Color.Cyan)) {
        // Sun
        Box(
            modifier = Modifier
                .size(100.dp)
                .align(Alignment.TopCenter)
                .background(sunColor, shape = CircleShape)
                .alpha(sunAlpha)
        )

        // Cloud 1
        Box(
            modifier = Modifier
                .size(150.dp, 50.dp)
                .offset(x = cloudOffsetX.dp, y = 100.dp)
                .align(Alignment.TopStart)
                .background(Color.White, shape = RoundedCornerShape(25.dp))
                .alpha(0.8f)
        )

        // Cloud 2
        Box(
            modifier = Modifier
                .size(180.dp, 60.dp)
                .offset(x = -cloudOffsetX.dp, y = 150.dp)
                .align(Alignment.TopEnd)
                .background(Color.White, shape = RoundedCornerShape(30.dp))
                .alpha(0.7f)
        )
    }
}

