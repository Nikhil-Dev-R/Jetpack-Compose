package com.rudraksha.practice.animations.physics

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun PhysicsAnimations() {
    Column(
        verticalArrangement = Arrangement.Top
    ) {
//        CollisionBouncingBox()
        FrictionBasedMotionAnimation()
//        FallingJumpingRotatingBallAnimation()
//        GravityBasedMotionAnimation()
//        GravityBasedMotionWithDiagonalMovementAnimation()
        SpringAnimationExample()
    }
}