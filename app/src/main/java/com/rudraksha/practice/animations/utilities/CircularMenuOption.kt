package com.rudraksha.practice.animations.utilities

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun CircularMenuAnimation() {
    var expanded by remember { mutableStateOf(false) }
    val rotationAngle by animateFloatAsState(
        targetValue = if (expanded) 360f else 0f,
        animationSpec = tween(800)
    )
    val iconScale by animateFloatAsState(
        targetValue = if (expanded) 1f else 0f,
        animationSpec = tween(500)
    )
    val buttonColor by animateColorAsState(
        targetValue = if (expanded) Color.Red else Color.Blue,
        animationSpec = tween(500)
    )

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.size(200.dp)
    ) {
        // Main Button
        Box(
            modifier = Modifier
                .size(56.dp)
                .background(buttonColor, shape = CircleShape)
                .clickable { expanded = !expanded },
            contentAlignment = Alignment.Center
        ) {
            Icon(Icons.Filled.Menu, contentDescription = null, tint = Color.White)
        }

        // Menu Icons (three icons expanding in circular pattern)
        Icon(
            Icons.Filled.Favorite,
            contentDescription = null,
            modifier = Modifier
                .offset(x = if (expanded) 70.dp else 0.dp, y = if (expanded) (-70).dp else 0.dp)
                .scale(iconScale)
                .rotate(rotationAngle),
            tint = Color.Red
        )

        Icon(
            Icons.Filled.Share,
            contentDescription = null,
            modifier = Modifier
                .offset(x = if (expanded) (-70).dp else 0.dp, y = if (expanded) -70.dp else 0.dp)
                .scale(iconScale)
                .rotate(rotationAngle),
            tint = Color.Green
        )

        Icon(
            Icons.Filled.Call,
            contentDescription = null,
            modifier = Modifier
                .offset(x = if (expanded) 0.dp else 0.dp, y = if (expanded) (-100).dp else 0.dp)
                .scale(iconScale)
                .rotate(rotationAngle),
            tint = Color.Magenta
        )
    }
}

