package com.rudraksha.practice.animations.gestures

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun GestureAnimations() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
//        Row {
//            DraggableBox(Modifier.weight(1f))
//            SwipableBox(Modifier.weight(1f))
//        }
//        Row {
//            RotatableBox(Modifier.weight(1f))
//            ScalableBox(Modifier.weight(1f))
//        }
//        DetectTransformGestures()
//        RotatableBox()
        ScrollingList()
    }
}