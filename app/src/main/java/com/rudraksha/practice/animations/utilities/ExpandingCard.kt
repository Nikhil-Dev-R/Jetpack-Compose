package com.rudraksha.practice.animations.utilities

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ExpandingCard() {
    var expanded by remember { mutableStateOf(false) }

//    val transition = updateTransition(targetState = expanded, label = "")
    val cardSize by animateDpAsState(
        targetValue = if (expanded) 400.dp else 200.dp,
        animationSpec = tween(600, easing = FastOutSlowInEasing)
    )
    val cardColor by animateColorAsState(
        targetValue = if (expanded) Color(0xFFFFA726) else Color(0xFF03A9F4),
        animationSpec = tween(400)
    )
    val bounceEffect by animateFloatAsState(
        targetValue = if (expanded) 1.5f else 1f,
        animationSpec = tween(200, easing = FastOutSlowInEasing)
    )

    Box(
        modifier = Modifier
            .padding(16.dp)
            .size(cardSize)
            .background(cardColor, shape = RoundedCornerShape(16.dp))
            .scale(bounceEffect)
            .clickable { expanded = !expanded }
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = if (expanded) "Card Expanded" else "Tap to Expand",
            color = Color.White,
            fontSize = 20.sp
        )
    }
}

