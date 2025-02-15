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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun ColorAnimation() {
    var colorState by remember { mutableStateOf(Color.Red) }

    Column {
        Button(onClick = { colorState = if (colorState == Color.Red) Color.Blue else Color.Red }) {
            Text("Toggle Color")
        }
        Box(
            modifier = Modifier
                .size(100.dp)
                .background(colorState)
        )
    }
}
