package com.rudraksha.practice.animations.utilities

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun FoldingScreenTransition() {
    var flipped by remember { mutableStateOf(false) }
    val rotation by animateFloatAsState(
        targetValue = if (flipped) 180f else 0f,
        animationSpec = tween(1000), label = ""
    )
    val color by animateColorAsState(if (flipped) Color.Cyan else Color.Magenta, label = "")
    val density = LocalDensity.current.density

    Box(
        modifier = Modifier
            .fillMaxSize()
            .graphicsLayer (
//                rotationX = rotation,
//                rotationY = rotation,
                rotationZ = rotation,
                cameraDistance = 12 * density
            )
            .background(color)
            .clickable { flipped = !flipped },
        contentAlignment = Alignment.Center
    ) {
         Text(
             text = if (rotation <= 90f) "Screen 1" else "Screen 2",
             fontSize = 32.sp,
             color = Color.White
         )
    }
}
