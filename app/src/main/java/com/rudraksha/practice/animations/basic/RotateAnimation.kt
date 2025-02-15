package com.rudraksha.practice.animations.basic

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay

@Composable
fun RotateAnimation() {
    var rotated by remember { mutableStateOf(false) }
    var rotationAmount by remember { mutableFloatStateOf(0f) }

    LaunchedEffect(rotated) {
        while (rotated) {
            rotationAmount = (rotationAmount + 15f) % 720
            delay(200)
        }
    }
    Column {
        Button(onClick = { rotated = !rotated }) {
            Text("Toggle Rotate")
        }
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .size(100.dp)
        ) {
            Box(modifier = Modifier
                .size(50.dp)
                .rotate(rotationAmount)
                .background(Color.Red)
            ) {
                Text("Rotate Animation")
            }
        }
    }
}
