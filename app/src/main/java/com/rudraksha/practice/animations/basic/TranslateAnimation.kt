package com.rudraksha.practice.animations.basic

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.widget.ConstraintLayout
import com.rudraksha.practice.ui.theme.PracticeTheme

@Composable
fun TranslateAnimation(modifier: Modifier = Modifier) {
    var translation by remember { mutableFloatStateOf(0f) }
    val animatedTranslation by animateFloatAsState(targetValue = translation, label = "Translation Animation")

    Column(
//        horizontalAlignment = Alignment.CenterHorizontally,
//        verticalArrangement = Arrangement.Center,
        modifier = modifier
            .clickable { translation = (translation + 50f) % 150 }
            .graphicsLayer(
                translationX = animatedTranslation,
                translationY = animatedTranslation,
            )
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = modifier
                .size(100.dp)
                .background(Color.Red)
        ) {
//            ConstraintLayout()
            Text("Translate Animation")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AppPreview() {
    PracticeTheme {
        TranslateAnimation()
    }
}