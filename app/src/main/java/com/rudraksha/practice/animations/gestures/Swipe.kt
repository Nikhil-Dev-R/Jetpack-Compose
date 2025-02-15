package com.rudraksha.practice.animations.gestures

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.gestures.detectVerticalDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.rudraksha.practice.R
import kotlin.math.roundToInt
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.height
import androidx.compose.ui.Alignment

// Animates horizontal/vertical motion based on user swipe
@Composable
fun SwipableBox(modifier: Modifier = Modifier) {
    var offsetX by remember { mutableFloatStateOf(0f) }
    var offsetY by remember { mutableFloatStateOf(0f) }
    var isSwiping by remember { mutableStateOf(false) }

    // Animate the position of the box when it's not being swiped
    val animatedOffsetX by animateFloatAsState(
        targetValue = if (isSwiping) offsetX else 0f,
        label = ""
    )
    val animatedOffsetY by animateFloatAsState(
        targetValue = if (isSwiping) offsetY else 0f,
        label = ""
    )

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.padding(8.dp).height(200.dp)
    ) {
        Text(text = "Swipable Box")
        Box(
            modifier = modifier
                .size(200.dp)
                .background(Color.Red)
                .offset { IntOffset(animatedOffsetX.roundToInt(), animatedOffsetY.roundToInt()) }
                /*.pointerInput(Unit) {
                    detectHorizontalDragGestures(
                        onDragEnd = {
                            // Reset the swiping state when the drag ends
                            isSwiping = false
                            // Optionally reset offsets here if needed
                            // offsetX = 0f
                            // offsetY = 0f
                        },

                    ) { change, dragAmount ->
                        // Consume the drag change
                        change.consume()
                        // Update the horizontal offset
                        offsetX += dragAmount
                        isSwiping = true
                    }

                    detectVerticalDragGestures(
                        onDragEnd = {
                            // Reset the swiping state when the drag ends
                            isSwiping = false
                            // Optionally reset offsets here if needed
                            // offsetX = 0f
                            // offsetY = 0f
                        }
                    ) { change, dragAmount ->
                        // Consume the drag change
                        change.consume()
                        // Update the vertical offset
                        offsetY += dragAmount
                        isSwiping = true
                    }
                }*/
                .pointerInput(Unit) {
                    detectDragGestures(
                        onDragStart = {
                            // Reset the swiping state when the drag starts
                            isSwiping = true
                        },
                        onDragEnd = {
                            // Reset the swiping state when the drag ends
                            isSwiping = false
                            // Optionally reset offsets here if needed
                            // offsetX = 0f
                            // offsetY = 0f
                        },
                        onDrag = { change, dragAmount ->
                            // Consume the drag change
                            change.consume()
                            // Update the offsets based on the drag amount
                            offsetX += dragAmount.x
                            offsetY += dragAmount.y
                            isSwiping = true
                        },
                    )
                }
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_launcher_foreground),
                contentDescription = "Rotating Icon",
                modifier = modifier
                    .size(200.dp)
            )
        }
    }
    /*
    var scale by remember { mutableStateOf(1f) }
    Box(
        modifier = Modifier
            .pointerInput(scale) { // Recomposes when 'scale' changes
                detectTapGestures {
                    scale *= 1.2f // Update the scale state
                }
            }
    )
    */
}

@Preview
@Composable
fun SwipableBoxPreview() {
    MaterialTheme {
        SwipableBox()
    }
}
