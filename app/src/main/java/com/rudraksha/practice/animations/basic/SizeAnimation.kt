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
fun SizeAnimation() {
    var size by remember { mutableStateOf(100.dp) }

    Column {
        Button(onClick = { size = if (size.value == 100f) 200.dp else 100.dp }) {
            Text("Toggle Size")
        }
        Box(
            modifier = Modifier
                .size(size)
                .background(Color.Cyan)
        )
    }
}
