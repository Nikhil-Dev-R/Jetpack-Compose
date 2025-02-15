package com.rudraksha.practice.animations.custom

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun CustomAnimations(modifier: Modifier = Modifier) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        AnimatableExample()
        CustomAnimationSpecExample()
    }
}

@Preview
@Composable
fun PreviewCustomAnimations() {
    MaterialTheme {
        CustomAnimations()
    }
}