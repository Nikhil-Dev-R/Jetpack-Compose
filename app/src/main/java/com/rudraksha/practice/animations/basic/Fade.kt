package com.rudraksha.practice.animations.basic

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

// Appear / Disappear
@Composable
fun FadeAnimation() {
    var visible by remember { mutableStateOf(true) }

    Column {
        Text("Fade Animation")
        Button(onClick = { visible = !visible }) {
            Text("Toggle")
        }
        AnimatedVisibility(
            visible = visible,
            enter = fadeIn(),
            exit = fadeOut(),
        ) {
            Box(
                modifier = Modifier
                    .size(100.dp)
                    .background(Color.Yellow)
            )
            Text("Fading Text", modifier = Modifier.padding(16.dp))
        }
    }
}
