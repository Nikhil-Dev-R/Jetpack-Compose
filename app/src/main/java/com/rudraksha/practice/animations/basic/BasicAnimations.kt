package com.rudraksha.practice.animations.basic

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun BasicAnimations(modifier: Modifier = Modifier) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = "Animations",
            fontSize = MaterialTheme.typography.headlineLarge.fontSize,
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp)
            // Ensure the Row fills the width
        ) {
            GraphicsLayerAnimationZInfinite(
                modifier = modifier.weight(1f) // Apply weight here
            )
            GraphicsLayerAnimation(
                modifier = modifier.weight(1f) // Apply weight here
            )
        }

        Row (
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp)
        ) {
            TranslateAnimation(modifier = modifier.weight(1f))
        }

        Row (
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp)
        ) {
            OffsetAnimation(modifier = modifier.weight(1f))
        }

        // Use LazyVerticalGrid to create a grid of items
        LazyVerticalGrid(
            columns = GridCells.Adaptive(150.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalArrangement = Arrangement.SpaceBetween,
            contentPadding = PaddingValues(16.dp),
            modifier = modifier

        ) {
            items(1) {
                FadeAnimation()
            }
            items(1) {
                AlphaAnimation()
            }
            items(1) {
                AlphaAnimationAnimateFloatAsState()
            }
            items(1) {
                AlphaFadeAnimation()
            }
            items(1) {
                ColorAnimation()
            }
            items(1) {
                RotateAnimation()
            }
            items(1) {
                ScaleAnimation()
            }
            items(1) {
                SizeAnimation()
            }
            items(1) {
                UpdateTransitionAnimation()
            }
            items(1) {
                AnimateAlphaWithUpdateTransition()
            }
            items(1) {
                AnimateAlphaWithAnimateFloatAsState()
            }
            items(1) {
                GraphicsLayerAnimationX()
            }
            items(1) {
                GraphicsLayerAnimationY()
            }
            items(1) {
                GraphicsLayerAnimationZ()
            }
            items(1) {
                CustomKeyframeAnimation()
            }
            items(1) {
                RippleAnimation()
            }
            items(1) {
                SideEffectAnimationUsingLaunchedEffect()
            }
            items(1) {
                SideEffectAnimationUsingSideEffect()
            }
        }
    }
}
