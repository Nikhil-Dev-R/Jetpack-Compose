package com.rudraksha.practice.animations.basic

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.CompositingStrategy
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay

@Composable
fun GraphicsLayerAnimationX() {
    var clicked by remember { mutableStateOf(false) }
    val density = LocalDensity.current.density // Get the current density

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Button(onClick = { clicked = !clicked }) {
            Text("Animate")
        }
        Box(
            modifier = Modifier
                .size(100.dp)
                .graphicsLayer(
                    scaleX = if (clicked) 1.5f else 1f,  // Scales the width of the Box
                    scaleY = if (clicked) 1.5f else 1f,  // Scales the height of the Box
                    alpha = if (clicked) 0.5f else 1f,   // Sets the opacity of the Box
                    translationX = if (clicked) 50f else 0f,  // Moves the Box horizontally
                    translationY = if (clicked) 50f else 0f,  // Moves the Box vertically
                    shadowElevation = if (clicked) 10f else 0f,  // Adds shadow elevation
                    rotationX = if (clicked) 45f else 0f,  // Rotates the Box around the X-axis
//                    rotationY = if (clicked) 15f else 0f,  // Rotates the Box around the Y-axis
//                    rotationZ = if (clicked) 45f else 0f,  // Rotates the Box around the Z-axis
                    cameraDistance = 8 * density, // Distance from the camera for 3D effects
                    transformOrigin = TransformOrigin.Center, // Defines the point of transformation
                    shape = RoundedCornerShape(16.dp), // Rounds the corners of the Box
                    clip = true, // Clips the content to the shape
                    ambientShadowColor = Color.Gray, // Color of ambient shadow
                    spotShadowColor = Color.Green, // Color of spot shadow
                    compositingStrategy = CompositingStrategy.Auto // Controls how compositing is handled
                )
                .background(Color.Magenta)
        ) {
            Text("Graphics Layer Animation X")
        }
        Spacer(modifier = Modifier.height(16.dp))
    }
}

@Composable
fun GraphicsLayerAnimationY() {
    var clicked by remember { mutableStateOf(false) }
    val density = LocalDensity.current.density // Get the current density

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Button(onClick = { clicked = !clicked }) {
            Text("Animate")
        }
        Box(
            modifier = Modifier
                .size(100.dp)
                .graphicsLayer(
                    scaleX = if (clicked) 1.5f else 1f,  // Scales the width of the Box
                    scaleY = if (clicked) 1.5f else 1f,  // Scales the height of the Box
                    alpha = if (clicked) 0.5f else 1f,   // Sets the opacity of the Box
                    translationX = if (clicked) 50f else 0f,  // Moves the Box horizontally
                    translationY = if (clicked) 50f else 0f,  // Moves the Box vertically
                    shadowElevation = if (clicked) 10f else 0f,  // Adds shadow elevation
//                    rotationX = if (clicked) 45f else 0f,  // Rotates the Box around the X-axis
                    rotationY = if (clicked) 45f else 0f,  // Rotates the Box around the Y-axis
//                    rotationZ = if (clicked) 45f else 0f,  // Rotates the Box around the Z-axis
                    cameraDistance = 8 * density, // Distance from the camera for 3D effects
                    transformOrigin = TransformOrigin.Center, // Defines the point of transformation
                    shape = RoundedCornerShape(16.dp), // Rounds the corners of the Box
                    clip = true, // Clips the content to the shape
                    ambientShadowColor = Color.Gray, // Color of ambient shadow
                    spotShadowColor = Color.Green, // Color of spot shadow
                    compositingStrategy = CompositingStrategy.Auto // Controls how compositing is handled
                )
                .background(Color.Magenta)
        ) {
            Text("Graphics Layer Animation Y")
        }
        Spacer(modifier = Modifier.height(16.dp))
    }
}

@Composable
fun GraphicsLayerAnimationZ() {
    var clicked by remember { mutableStateOf(false) }
    val density = LocalDensity.current.density // Get the current density

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Button(onClick = { clicked = !clicked }) {
            Text("Animate")
        }

        Box(
            modifier = Modifier
                .size(100.dp)
                .graphicsLayer(
                    scaleX = if (clicked) 1.5f else 1f,  // Scales the width of the Box
                    scaleY = if (clicked) 1.5f else 1f,  // Scales the height of the Box
                    alpha = if (clicked) 0.5f else 1f,   // Sets the opacity of the Box
                    translationX = if (clicked) 50f else 0f,  // Moves the Box horizontally
                    translationY = if (clicked) 50f else 0f,  // Moves the Box vertically
                    shadowElevation = if (clicked) 10f else 0f,  // Adds shadow elevation
//                    rotationX = if (clicked) 45f else 0f,  // Rotates the Box around the X-axis
//                    rotationY = if (clicked) 15f else 0f,  // Rotates the Box around the Y-axis
                    rotationZ = if (clicked) 45f else 0f,  // Rotates the Box around the Z-axis
                    cameraDistance = 8 * density, // Distance from the camera for 3D effects
                    transformOrigin = TransformOrigin.Center, // Defines the point of transformation
                    shape = RoundedCornerShape(16.dp), // Rounds the corners of the Box
                    clip = true, // Clips the content to the shape
                    ambientShadowColor = Color.Gray, // Color of ambient shadow
                    spotShadowColor = Color.Green, // Color of spot shadow
                    compositingStrategy = CompositingStrategy.Auto // Controls how compositing is handled
                )
                .background(Color.Magenta)
        ) {
            Text("Graphics Layer Animation Z")
        }
    }
}

@Composable
fun GraphicsLayerAnimationZInfinite(modifier: Modifier = Modifier) {
    var clicked by remember { mutableStateOf(false) }
    var rotationAmount by remember { mutableStateOf(0f) }
    val density = LocalDensity.current.density // Get the current density
//    val rotation = animateFloatAsState(targetValue = rotationAmount, label = "")

    LaunchedEffect(clicked) {
        while (clicked) {
            rotationAmount = (rotationAmount + 15f) % 360
            delay(200)
        }
    }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier.fillMaxSize()
    ) {
        Button(onClick = {
            clicked = !clicked
        }) {
            Text("Animate Z Infinite")
        }

        Box(
            contentAlignment = Alignment.Center,
            modifier = modifier
            .size(200.dp)
            ) {
            Box(
                modifier = Modifier
                    .size(100.dp)
//                    .clip(CutCornerShape(16.dp))
//                    .rotate(rotationAmount)
                .graphicsLayer(
                    shadowElevation = 10f,  // Adds shadow elevation
//                    rotationX = if (clicked != 0) rotationAmount else 0f,  // Rotates the Box around the X-axis
//                    rotationY = if (clicked != 0) rotationAmount else 0f,  // Rotates the Box around the Y-axis
                    rotationZ = rotationAmount,  // Rotates the Box around the Z-axis
                    cameraDistance = 1000 * density, // Distance from the camera for 3D effects
                    transformOrigin = TransformOrigin.Center, // Defines the point of transformation
                    shape = CutCornerShape(16.dp),
                    clip = true, // Clips the content to the shape
                    ambientShadowColor = Color.Gray, // Color of ambient shadow
                    spotShadowColor = Color.Green, // Color of spot shadow
                    compositingStrategy = CompositingStrategy.Auto // Controls how compositing is handled
                )
                    .background(Color.Magenta)
            ) {
                Text("Graphics Layer Animation Infinite Z")
            }
        }
    }
}

@Composable
fun GraphicsLayerAnimation(modifier: Modifier = Modifier) {
    var clicked by remember { mutableStateOf(false) }
    var rotationAmount by remember { mutableStateOf(0f) }
    val density = LocalDensity.current.density // Get the current density
//    val rotation = animateFloatAsState(targetValue = rotationAmount, label = "")

    LaunchedEffect(clicked) {
        while (clicked) {
            rotationAmount = (rotationAmount + 15f) % 360
            delay(200)
        }
    }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier.fillMaxSize()
    ) {
        Button(onClick = {
            clicked = !clicked
        }) {
            Text("Animate")
        }

        Box(
            modifier = modifier
                .size(100.dp)
                .graphicsLayer(
                    scaleX = if (clicked) 1.5f else 1f,  // Scales the width of the Box
                    scaleY = if (clicked) 1.5f else 1f,  // Scales the height of the Box
                    alpha = if (clicked) 0.5f else 1f,   // Sets the opacity of the Box
                    translationX = if (clicked) 50f else 0f,  // Moves the Box horizontally
                    translationY = if (clicked) 50f else 0f,  // Moves the Box vertically
                    shadowElevation = if (clicked) 10f else 0f,  // Adds shadow elevation
                    rotationX = rotationAmount,  // Rotates the Box around the X-axis
                    rotationY = rotationAmount,  // Rotates the Box around the Y-axis
                    rotationZ = rotationAmount,  // Rotates the Box around the Z-axis
                    cameraDistance = 1000 * density, // Distance from the camera for 3D effects
                    transformOrigin = TransformOrigin.Center, // Defines the point of transformation
                    shape = RoundedCornerShape(16.dp), // Rounds the corners of the Box
                    clip = true, // Clips the content to the shape
                    ambientShadowColor = Color.Gray, // Color of ambient shadow
                    spotShadowColor = Color.Green, // Color of spot shadow
                    compositingStrategy = CompositingStrategy.Auto // Controls how compositing is handled
                )
                .background(Color.Magenta)
        ) {
            Text("Graphics Layer Animation")
        }
    }
}

