package com.rudraksha.practice.animations.gestures

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTransformGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.rudraksha.practice.R
import kotlin.math.roundToInt

// Animates scale based on user pinch
@Composable
fun ScalableBox(modifier: Modifier = Modifier) {
    var scaleState by remember { mutableFloatStateOf(1f) }

    // Animate the scale based on the current scale state
    val scale by animateFloatAsState(targetValue = scaleState, label = "")

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.padding(8.dp).height(200.dp)
    ) {
        Text(text = "Scalable Box")
        Box(
            modifier = modifier
                .size(200.dp)
                .pointerInput(Unit) {
                    detectTransformGestures(
                        panZoomLock = false,
                    ) { _, _, zoom, _ ->
                        // Update the scale state based on the pinch zoom gesture
                        scaleState *= zoom
                    }
                }
                .background(Color.Green),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_launcher_foreground), // Replace with your icon resource
                contentDescription = "Scalable Icon",
                modifier = modifier
                    .size(200.dp)
                    .graphicsLayer(scaleX = scale, scaleY = scale)
            )
        }
    }
}

@Composable
fun DetectTransformGestures(modifier: Modifier = Modifier) {
    var scale by remember { mutableFloatStateOf(1f) }
    var rotation by remember { mutableFloatStateOf(0f) }
    var offset by remember { mutableStateOf(Offset.Zero) }
    var centroid by remember { mutableStateOf(Offset.Zero) }

    val scaleAnimation = animateFloatAsState(targetValue = scale, label = "")
    val rotationAnimation = animateFloatAsState(targetValue = rotation, label = "")
    val offsetAnimationX = animateFloatAsState(targetValue = offset.x, label = "")
    val offsetAnimationY = animateFloatAsState(targetValue = offset.y, label = "")
    val centroidAnimation = animateFloatAsState(targetValue = centroid.x, label = "")

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .padding(8.dp)
            .height(200.dp)
    ) {
        Text(text = "Detect Transform Gestures Box")
        Box(
            modifier = modifier
                .size(200.dp)
                .offset {
                    IntOffset(
                        offsetAnimationX.value.toInt(),
                        offsetAnimationY.value.toInt()
                    )
                }
                .graphicsLayer {
                    scaleX = scaleAnimation.value
                    scaleY = scaleAnimation.value
                    rotationZ = rotationAnimation.value
                    translationX = centroidAnimation.value
                }
                .background(Color.Green)
                .pointerInput(Unit) {
                    detectTransformGestures { centre, pan, zoom, rotationChange ->
                        centroid = centre
                        scale *= zoom
                        rotation += rotationChange
                        offset += pan
                    }
                },
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_launcher_foreground),
                contentDescription = "Scalable Icon",
                modifier = Modifier.size(200.dp)
            )
        }
    }
}