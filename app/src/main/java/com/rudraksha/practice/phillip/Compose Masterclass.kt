package com.rudraksha.practice.phillip

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.SubcomposeLayout
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.dp

@Composable
fun OverlappingLayout(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Layout(
        content = content,
        modifier = modifier
    ) { measurables, constraints ->
        val placeables = measurables.map { it.measure(constraints) }

        layout(constraints.maxWidth, constraints.maxHeight) {
            var yOffset = 0
            placeables.forEach { placeable ->
                placeable.placeRelative(x = 0, y = yOffset)
                yOffset += placeable.height / 2 // Overlapping effect
            }
        }
    }
}
@Preview
@Composable
fun OverlappingExample() {
    OverlappingLayout {
        Text("First", fontSize = 20.sp, modifier = Modifier.background(Color.Red))
        Text("Second", fontSize = 20.sp, modifier = Modifier.background(Color.Green))
        Text("Third", fontSize = 20.sp, modifier = Modifier.background(Color.Blue))
    }
}

// Subcomposition allows you to defer or dynamically define part of the UI within another composition.
// SubcomposeLayout enables us to separately measure different parts (header and content).
// Useful when content needs dynamic positioning based on previous components.
@Composable
fun SubcomposeExample(content: @Composable () -> Unit) {
    SubcomposeLayout { constraints ->
        // First measure header
        val headerPlaceables = subcompose("header") {
            Text("Header", fontSize = 24.sp, color = Color.White)
        }.map { it.measure(constraints) }

        // Measure content dynamically
        val contentPlaceables = subcompose("content", content).map {
            it.measure(constraints)
        }

        val height = headerPlaceables.sumOf { it.height } + contentPlaceables.sumOf { it.height }

        layout(constraints.maxWidth, height) {
            var yOffset = 0

            headerPlaceables.forEach { placeable ->
                placeable.placeRelative(0, yOffset)
                yOffset += placeable.height
            }

            contentPlaceables.forEach { placeable ->
                placeable.placeRelative(0, yOffset)
                yOffset += placeable.height
            }
        }
    }
}
@Preview
@Composable
fun SubcomposeExampleUsage() {
    SubcomposeExample {
        Text("Dynamic Content", fontSize = 20.sp, color = Color.Yellow)
    }
}

fun Modifier.customShadow(
    color: Color = Color.Black,
    offsetX: Dp = 5.dp,
    offsetY: Dp = 5.dp,
    blurRadius: Dp = 10.dp
) = drawBehind {
    this.drawRect(
        color = color,
        topLeft = Offset(offsetX.toPx(), offsetY.toPx()),
        size = size.copy(width = size.width - offsetX.toPx(), height = size.height - offsetY.toPx()),
        alpha = 0.5f
    )
}
@Preview
@Composable
fun CustomShadowExample() {
    Box(
        modifier = Modifier
            .size(120.dp)
            .customShadow()
            .background(Color.Blue),
        contentAlignment = Alignment.Center
    ) {
        Text("Shadow", color = Color.White)
    }
}

val LocalAppTheme = staticCompositionLocalOf { Color.White }
@Preview
@Composable
fun CustomThemeExample() {
    CompositionLocalProvider(LocalAppTheme provides Color.Green) {
        ThemedBox()
    }
}
@Composable
fun ThemedBox() {
    Box(
        modifier = Modifier
            .size(100.dp)
            .background(LocalAppTheme.current)
    ) {
        Text("Themed", modifier = Modifier.align(Alignment.Center))
    }
}

val LocalCounter = compositionLocalOf { mutableStateOf(0) }
@Preview
@Composable
fun CounterApp() {
    val counter = remember { mutableStateOf(0) }

    CompositionLocalProvider(LocalCounter provides counter) {
        CounterDisplay()
    }
}
@Composable
fun CounterDisplay() {
    val counter = LocalCounter.current
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text("Counter: ${counter.value}")
        Button(onClick = { counter.value++ }) {
            Text("Increment")
        }
    }
}
