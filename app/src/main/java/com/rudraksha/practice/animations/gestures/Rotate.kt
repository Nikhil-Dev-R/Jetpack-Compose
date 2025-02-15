package com.rudraksha.practice.animations.gestures

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.consumeAllChanges
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.rudraksha.practice.R
import kotlin.math.PI
import kotlin.math.atan2

// Animates rotation based on user rotation
@Composable
fun RotatableBox(modifier: Modifier = Modifier) {
    var rotationState by remember { mutableFloatStateOf(0f) }

    // Animate the rotation based on the current rotation state
    val rotation by animateFloatAsState(targetValue = rotationState, label = "")

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.padding(8.dp).size(200.dp, 200.dp)
    ) {
        Text(text = "Rotatable Box")
        Box(
            modifier = modifier
                .size(200.dp)
                .clip(shape = CutCornerShape(10.dp))
//                .rotate(rotation)
                .graphicsLayer(rotationZ = rotation)
//                .clip(shape = CutCornerShape(10.dp))
                .pointerInput(Unit) {
                    detectDragGestures { change, dragAmount ->
                        // Calculate the angle based on the drag direction
                        val angle = atan2(dragAmount.y, dragAmount.x) *
                                (180 / PI.toFloat())
                        rotationState += angle * 0.075f
                        change.consume()
                    }
                }
                .background(Color.Magenta),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_launcher_foreground),
                contentDescription = "Rotating Icon",
                modifier = modifier
                    .matchParentSize()
//                    .graphicsLayer(rotationZ = rotation)

            )
        }
    }
}

@Preview
@Composable
fun RotatableBoxPreview() {
    RotatableBox()
}
/*
Using acos or asin
If you're primarily interested in the angle relative to a specific axis (e.g., the
x-axis), you could use acos or asin to calculate the angle. However, you'll need to
carefully handle the quadrant to ensure the angle is within the desired range
(0-360 degrees).

val angle = if (dragAmount.x >= 0) {
    acos(dragAmount.y / sqrt(dragAmount.x * dragAmount.x + dragAmount.y * dragAmount.y))
        * (180 / PI.toFloat())
} else {
    360 - acos(dragAmount.y / sqrt(dragAmount.x * dragAmount.x + dragAmount.y * dragAmount.y))
        * (180 / PI.toFloat())
}


Relative angle calculation:
If you want to calculate the angle relative to the previous drag position, you could
store the previous position and use vector math to determine the angle between the
current and previous drag vectors.

var previousDragAmount = Offset.Zero
detectDragGestures { change, dragAmount ->
    val angle = if (previousDragAmount != Offset.Zero) {
        val currentVector = dragAmount - previousDragAmount
        val angleRad = atan2(currentVector.y, currentVector.x)
        angleRad * (180 / PI.toFloat()) // Convert to degrees
    } else {
        0f // Initial angle
    }
    rotationState += angle * 0.08f
    previousDragAmount = dragAmount
    change.consume()
}

offsetFromCenter Modifier:
If you're working with rotation gestures specifically, you could consider using the
offsetFromCenter modifier to get the offset of the touch point relative to the center
of the composable. This can help simplify the angle calculation.

var rotationState by remember { mutableStateOf(0f) }
Box(
    modifier = modifier
        .offset { IntOffset(offset.x.roundToInt(), offset.y.roundToInt()) }
        .size(200.dp)
        .pointerInput(Unit) {
            detectDragGestures { change, dragAmount ->
                // Calculate the angle
                val angle = atan2(change.position.y - 100, change.position.x - 100)
                    * (180f / PI.toFloat())
                rotationState = angle
                change.consume()
            }
        }
        .graphicsLayer {
            rotationZ = rotationState
        }
        .background(Color.Magenta),
    contentAlignment = Alignment.Center
)

*/