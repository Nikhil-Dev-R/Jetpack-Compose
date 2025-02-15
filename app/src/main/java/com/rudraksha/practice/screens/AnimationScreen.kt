package com.rudraksha.practice.screens

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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.rudraksha.practice.animations.PathAnimations
import com.rudraksha.practice.animations.advanced.AdvancedAnimations
import com.rudraksha.practice.animations.basic.AlphaAnimation
import com.rudraksha.practice.animations.basic.AlphaAnimationAnimateFloatAsState
import com.rudraksha.practice.animations.basic.AlphaFadeAnimation
import com.rudraksha.practice.animations.basic.AnimateAlphaWithAnimateFloatAsState
import com.rudraksha.practice.animations.basic.AnimateAlphaWithUpdateTransition
import com.rudraksha.practice.animations.basic.BasicAnimations
import com.rudraksha.practice.animations.basic.ColorAnimation
import com.rudraksha.practice.animations.basic.CustomKeyframeAnimation
import com.rudraksha.practice.animations.basic.FadeAnimation
import com.rudraksha.practice.animations.basic.GraphicsLayerAnimation
import com.rudraksha.practice.animations.basic.GraphicsLayerAnimationX
import com.rudraksha.practice.animations.basic.GraphicsLayerAnimationY
import com.rudraksha.practice.animations.basic.GraphicsLayerAnimationZ
import com.rudraksha.practice.animations.basic.GraphicsLayerAnimationZInfinite
import com.rudraksha.practice.animations.basic.OffsetAnimation
import com.rudraksha.practice.animations.basic.RippleAnimation
import com.rudraksha.practice.animations.basic.RotateAnimation
import com.rudraksha.practice.animations.basic.ScaleAnimation
import com.rudraksha.practice.animations.basic.SideEffectAnimationUsingLaunchedEffect
import com.rudraksha.practice.animations.basic.SideEffectAnimationUsingSideEffect
import com.rudraksha.practice.animations.basic.SizeAnimation
import com.rudraksha.practice.animations.basic.TranslateAnimation
import com.rudraksha.practice.animations.basic.UpdateTransitionAnimation
import com.rudraksha.practice.animations.custom.CustomAnimations
import com.rudraksha.practice.animations.gestures.GestureAnimations
import com.rudraksha.practice.animations.other.OtherAnimations
import com.rudraksha.practice.animations.physics.PhysicsAnimations
import com.rudraksha.practice.ui.theme.PracticeTheme

@Composable
fun AnimationScreen(modifier: Modifier = Modifier) {
    Column(
//        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
//        BasicAnimations()
//    AdvancedAnimations()
//    GestureAnimations()
//    PhysicsAnimations()
//    CustomAnimations(modifier)
//        OtherAnimations(modifier)
//        Home()
//        BrushAnimations()
//        ComplexAnimations()

        PathAnimations()
    }
}


@Preview(showBackground = true)
@Composable
fun AppPreview() {
    PracticeTheme {
        AnimationScreen()
    }
}
