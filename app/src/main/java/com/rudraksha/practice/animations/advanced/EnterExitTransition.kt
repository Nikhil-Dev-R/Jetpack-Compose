package com.rudraksha.practice.animations.advanced

import androidx.compose.animation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun EnterTransitionExample() {
    var visible by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier.size(500.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Text(text = "Enter Exit Transition")
        Button(onClick = { visible = !visible }) {
            Text("Toggle Visibility")
        }

        AnimatedVisibility(
            visible = visible,
            enter = slideInVertically(initialOffsetY = { -it }) + fadeIn(),
            exit = slideOutVertically(targetOffsetY = { it }) + fadeOut()
        ) {
            Text(
                text = "Hello, World!",
                fontSize = 24.sp,
                modifier = Modifier.padding(top = 16.dp)
            )
        }
    }
    /*
    Column(
        modifier = Modifier.size(500.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Enter Exit Transition")
        Button(onClick = { visible = !visible }) {
            Text("Toggle Visibility")
        }

        AnimatedVisibility(
            visible = visible,
            enter = slideInVertically(initialOffsetY = { -it }) + fadeIn(),
            exit = slideOutVertically(targetOffsetY = { it }) + fadeOut()
        ) {
            Text(
                text = "Hello, World!",
                fontSize = 24.sp,
                modifier = Modifier.padding(top = 16.dp)
            )
        }
    }
    */
}

@Preview(showBackground = true)
@Composable
fun PreviewEnterTransitionExample() {
    EnterTransitionExample()
}