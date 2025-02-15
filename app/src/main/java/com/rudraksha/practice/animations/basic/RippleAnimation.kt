package com.rudraksha.practice.animations.basic

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp

@Composable
fun RippleAnimation() {
    val context = LocalContext.current
    // State to hold the background color of the Box
    val boxColor by remember { mutableStateOf(Color.LightGray) }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.size(200.dp) // Added a size to the Column for better layout
    ) {
        Button(onClick = { /* Button click action */ }) {
            Text("Toggle Ripple")
        }
        Box(
            modifier = Modifier
                .size(100.dp)
                .background(boxColor)
                .clickable(
//                    interactionSource = remember { MutableInteractionSource() },
//                    indication = rememberRipple(
//                        bounded = true, // Optional: set bounded ripple
//                        color = Color.Green, // Optional: set the ripple color
//                    ),
                    onClick = {
//                        boxColor = when (boxColor) {
//                            Color.LightGray, Color.Magenta -> Color.Cyan
//                            Color.Cyan -> Color.Magenta
//                            else -> {
//                                Color.LightGray
//                            }
//                        }
                        Toast.makeText(context, "Box Clicked", Toast.LENGTH_SHORT).show()
                    }
                )
        ) {
            Text(
                text = "Ripple",
                modifier = Modifier.align(Alignment.Center)
            )
        }
    }
}