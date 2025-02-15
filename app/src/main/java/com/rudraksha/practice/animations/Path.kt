package com.rudraksha.practice.animations

import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.RoundRect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun PathAnimations() {
    LazyColumn(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        item {
//            AnimatedPathMorphing()
            WaveEffect()
//            HeartbeatPulse()
//            CircularProgressPath()
//            HandwrittenText()
        }
        item {
//            CombinedPathsExample()
            MorphingShapeAnimation()
//            RotatingSpiralAnimation()
        }
    }
}

@Composable
fun AnimatedPathMorphing() {
    val infiniteTransition = rememberInfiniteTransition("")
    val progress by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 2000, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        ), ""
    )

    Canvas(modifier = Modifier.size(300.dp)) {
        val path = Path().apply {
            moveTo(100f, 200f)// Starting
//            quadraticTo(100f, 200f, 200f, 300f)
//            quadraticTo(300f, 400f, 200f, 500f)
//            quadraticTo(100f, 400f, 200f, 300f)
//            quadraticTo(300f, 200f, 200f, 100f)

            cubicTo(150f, 150f, 150f, 250f, 200f, 300f)
            cubicTo(350f, 250f, 350f, 450f, 500f, 200f)
            cubicTo(100f, 400f, 100f, 400f, 200f, 300f)
            cubicTo(300f, 200f, 300f, 200f, 200f, 100f)
//            addOval(oval = Rect(center = Offset(250f, 200f), radius = 50f))
        }

        val pathMeasure = PathMeasure()
        pathMeasure.setPath(path, false)
        val segmentPath = Path()
        pathMeasure.getSegment(0f, pathMeasure.length * progress, segmentPath, true)

        path.close()// Enclose the formed shape
        drawPath(
            path = segmentPath,
            color = Color.Magenta,
            style = Stroke(width = 4.dp.toPx())
        )
    }
}

@Composable
fun CombinedPathsExample() {
    Canvas(modifier = Modifier.size(200.dp)) {
        val circlePath = Path().apply { addOval(Rect(Offset(50f, 50f), Size(100f, 100f))) }
        val squarePath = Path().apply { addRect(Rect(Offset(100f, 100f), Size(100f, 100f))) }

        val combinedPath = Path().apply {
            op(circlePath, squarePath, PathOperation.Intersect) // Intersection of circle and square
        }

        drawPath(path = combinedPath, color = Color.Green)
    }
}

@Composable
fun WaveEffect() {
    val waveAnimation = rememberInfiniteTransition("")
    val offset by waveAnimation.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            tween(2000, easing = LinearEasing)
        ), ""
    )

    Canvas(modifier = Modifier
        .fillMaxWidth()
        .height(200.dp)) {
        val path = Path().apply {
            moveTo(0f, 100f)
//            val heights = listOf(20f, 30f, 40f, 50f)
//            var next = 0
            for (i in 0..size.width.toInt() step 10) {
                val y = 100f + 30f * kotlin.math.sin((i + offset) * (Math.PI / 180)).toFloat()
                lineTo(i.toFloat(), y)
//                next = (next + 1) % heights.size
            }
            lineTo(size.width, size.height)
            lineTo(0f, size.height)
            close()
        }
        drawPath(path = path, color = Color.Cyan)
    }
}

@Composable
fun MorphingShapeAnimation() {
    val infiniteTransition = rememberInfiniteTransition("")
    val morphFactor by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(2000, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        ), ""
    )

    Canvas(modifier = Modifier.size(200.dp)) {
        val path = Path().apply {
            addRoundRect(
                RoundRect(
                    rect = Rect(Offset.Zero, Size(200f, 200f)),
                    cornerRadius = CornerRadius(morphFactor * 100f, morphFactor * 100f)
                )
            )
        }
        drawPath(path = path, color = Color.Magenta)
    }
}

@Composable
fun HeartbeatPulse() {
    val infiniteTransition = rememberInfiniteTransition("")
    val progress by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(2000, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Restart
        ), ""
    )

    Canvas(modifier = Modifier.fillMaxWidth().height(200.dp)) {
        val path = Path().apply {
            moveTo(0f, 100f)
            for (i in 0..size.width.toInt()) {
                val y = 100f + (if ((i / 100) % 2 == 0) -1 else 1) * 30f * kotlin.math.sin((i + progress * size.width) * (Math.PI / 60)).toFloat()
                lineTo(i.toFloat(), y)
            }
        }
        drawPath(path = path, color = Color.Red, style = Stroke(width = 4.dp.toPx()))
    }
}

@Composable
fun CircularProgressPath() {
    val infiniteTransition = rememberInfiniteTransition()
    val progress by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            tween(2000, easing = LinearEasing)
        )
    )

    Canvas(modifier = Modifier.size(150.dp)) {
        val path = Path().apply {
            addOval(Rect(Offset(0f, 0f), Size(150f, 150f)))
        }

        val pathMeasure = PathMeasure()
        pathMeasure.setPath(path, false)

        val segmentPath = Path()
        pathMeasure.getSegment(0f, pathMeasure.length * progress, segmentPath)

        drawPath(path = segmentPath, color = Color.Green, style = Stroke(width = 4.dp.toPx()))
    }
}
/*
@Composable
fun SnowflakeFallingAnimation() {
    val infiniteTransition = rememberInfiniteTransition("")

    Canvas(modifier = Modifier.fillMaxSize()) {
        val snowflakeCount = 10
        repeat(snowflakeCount) { i ->
            val offsetX by infiniteTransition.animateFloat(
                initialValue = (0..size.width.toInt()).random().toFloat(),
                targetValue = (0..size.width.toInt()).random().toFloat(),
                animationSpec = infiniteRepeatable(tween(4000 + (i * 500), easing = LinearEasing), RepeatMode.Restart),
                label = ""
            )

            val offsetY by infiniteTransition.animateFloat(
                initialValue = -100f,
                targetValue = size.height + 100f,
                animationSpec = infiniteRepeatable(tween(4000 + (i * 500), easing = LinearEasing), RepeatMode.Restart),
                label = ""
            )

            val path = Path().apply {
                moveTo(offsetX, offsetY)
                lineTo(offsetX + 10, offsetY + 10)
                lineTo(offsetX, offsetY + 20)
                lineTo(offsetX - 10, offsetY + 10)
                close()
            }

            drawPath(path = path, color = Color.White, style = Stroke(width = 2.dp.toPx()))
        }
    }
}*/

@Composable
fun RotatingSpiralAnimation() {
    val infiniteTransition = rememberInfiniteTransition("")
    val rotation by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            tween(4000, easing = LinearEasing),
            RepeatMode.Restart
        ), label = ""
    )

    Canvas(modifier = Modifier.size(300.dp)) {
        val path = Path().apply {
            var angle = 0.0
            var radius = 20.0
            while (radius < 150) {
                val x = (size.width / 2) + radius * kotlin.math.cos(Math.toRadians(angle)).toFloat()
                val y = (size.height / 2) + radius * kotlin.math.sin(Math.toRadians(angle)).toFloat()
                if (angle == 0.0)
                    moveTo(x.toFloat(), y.toFloat())
                else
                    lineTo(x.toFloat(), y.toFloat())
                angle += 10
                radius += 2
            }
        }

        drawPath(
            path = path,
            color = Color.Magenta,
            style = Stroke(width = 3.dp.toPx()),
            alpha = 0.7f,
            blendMode = BlendMode.SrcAtop
        )
        rotate(degrees = rotation, pivot = Offset(size.width / 2, size.height / 2)) {
            drawPath(path, color = Color.Magenta)
        }
    }
}


@Composable
fun HandwrittenText() {
    val infiniteTransition = rememberInfiniteTransition(label = "")
    val progress by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            tween(4000, easing = LinearEasing),
            RepeatMode.Restart
        ), label = ""
    )

    Canvas(modifier = Modifier.fillMaxWidth().height(100.dp)) {
        val textPath = Path().apply {
            moveTo(50f, 50f)
            lineTo(100f, 30f)
            lineTo(150f, 50f)
            lineTo(200f, 80f)
            lineTo(250f, 50f)
        }

        val pathMeasure = PathMeasure()
        pathMeasure.setPath(textPath, false)

        val animatedPath = Path()
        pathMeasure.getSegment(0f, pathMeasure.length * progress, animatedPath, true)

        drawPath(
            path = animatedPath,
            color = Color.Black,
            style = Stroke(width = 2.dp.toPx())
        )
    }
}
/*
@OptIn(ExperimentalTextApi::class)
@Composable
fun HandwritingTextAnimation(text: String) {
    val textMeasurer = rememberTextMeasurer()
    val textSize = 50.sp
    val density = LocalDensity.current

    // Animate the progress of the text drawing
    val transition = rememberInfiniteTransition(label = "")
    val progress by transition.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            tween(durationMillis = 3000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ), label = ""
    )

    Canvas(modifier = Modifier.fillMaxWidth().height(100.dp)) {
        // Measure text layout and get the path
        val textLayout = textMeasurer.measure(text = text, fontSize = textSize)
        val textPath = Path().apply {
            addOutline(textLayout.getPathForText())
        }

        // Use PathMeasure to calculate the path length
        val pathMeasure = PathMeasure()
        pathMeasure.setPath(textPath, false)
        val pathLength = pathMeasure.length

        // Create a path that reveals progressively based on animation progress
        val animatedPath = Path().apply {
            pathMeasure.getSegment(0f, pathLength * progress, this)
        }

        // Draw the animated path to simulate handwriting
        drawPath(
            path = animatedPath,
            color = Color.Black,
            style = Stroke(width = 4.dp.toPx(), cap = StrokeCap.Round)
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HandwritingTextScreen() {
    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Handwriting Text Animation") })
        }
    ) { paddingValues ->
        Box(modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues),
            contentAlignment = Alignment.Center
        ) {
            HandwritingTextAnimation(text = "Compose Magic")
        }
    }
}*/

/*
@Composable
fun TextProgressWithOutlineAnimation(text: String) {
    val textMeasurer = rememberTextMeasurer()
    val textSize = 50.sp
    val density = LocalDensity.current

    // Animate the progress of the text drawing
    val transition = rememberInfiniteTransition("")
    val progress by transition.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            tween(durationMillis = 4000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart,
        ), ""
    )

    // Canvas for drawing
    Canvas(modifier = Modifier.fillMaxWidth().height(150.dp)) {
        // Measure text and get outline path
        val textLayout = textMeasurer.measure(text = text, fontSize = textSize)
        val textPath = Path().apply {
            addOutline(textLayout.getPathForText())
        }

        // Use PathMeasure to get the length and animate drawing segments
        val pathMeasure = PathMeasure()
        pathMeasure.setPath(textPath, false)
        val pathLength = pathMeasure.length

        // Create animated path segments to reveal text progressively
        val animatedPath = Path().apply {
            pathMeasure.getSegment(0f, pathLength * progress, this)
        }

        // Draw an outline for the text
        drawPath(
            path = animatedPath,
            color = Color.Blue.copy(alpha = 0.5f),
            style = Stroke(width = 8.dp.toPx(), cap = StrokeCap.Round)
        )

        // Draw the main text in black as it progresses
        drawPath(
            path = animatedPath,
            color = Color.Black,
            style = Stroke(width = 4.dp.toPx(), cap = StrokeCap.Round)
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextProgressWithOutlineScreen() {
    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Text Progress with Outline Animation") })
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentAlignment = Alignment.Center
        ) {
            TextProgressWithOutlineAnimation(text = "Compose Magic")
        }
    }
}*/

