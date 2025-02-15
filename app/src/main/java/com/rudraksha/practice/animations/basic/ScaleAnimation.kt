package com.rudraksha.practice.animations.basic

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun ScaleAnimation() {
    var scaled by remember { mutableStateOf(false) }

    Column {
        Button(onClick = { scaled = !scaled }) {
            Text("Toggle Scale")
        }
        Box(
            modifier = Modifier
                .size(100.dp)
                .scale(if (scaled) 1.5f else 1f)
                .background(Color.Blue)
        )
    }
}
