package com.rudraksha.practice

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.max
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.Arc
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.Dimension
import androidx.constraintlayout.compose.ExperimentalMotionApi
import androidx.constraintlayout.compose.MotionLayout
import androidx.constraintlayout.compose.Transition
import androidx.constraintlayout.motion.widget.KeyAttributes
import com.rudraksha.practice.animations.DetailScreen
import com.rudraksha.practice.animations.advanced.ExpandingCardGrid
import com.rudraksha.practice.ui.theme.PracticeTheme
import kotlin.math.max
import kotlin.math.min


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PracticeTheme {
                DetailScreen()
//                PracticeApp(
//                    modifier = Modifier
//                )
            }
        }
    }
}


//@Preview
@Composable
fun DetailedItemScreen(
    imageRes: Int = R.drawable.ic_launcher_foreground,
    title: String = "Super Smartphone X",
    description: String = "The latest innovation in mobile technology with top-tier performance..."
) {
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .background(MaterialTheme.colorScheme.background)
    ) {
        val scrollOffset = scrollState.value.toFloat()

        // Animated properties based on scroll
        val boxHeight by animateDpAsState(
            targetValue = max(250.dp, 400.dp - (scrollOffset * 0.5f).dp),
            animationSpec = tween(500, easing = FastOutSlowInEasing)
        )
        val boxColor by animateColorAsState(
            targetValue = if (scrollOffset > 400f) Color.DarkGray else Color.LightGray,
            animationSpec = tween(500)
        )
        val imageSize by animateFloatAsState(
            targetValue = max(100f, 300f - scrollOffset * 0.4f),
            animationSpec = tween(500)
        )
        val imageY by animateFloatAsState(
            targetValue = max(0f, scrollOffset * 0.25f),
            animationSpec = tween(500)
        )
        val textSize by animateFloatAsState(
            targetValue = max(16f, 32f - scrollOffset * 0.08f),
            animationSpec = tween(500)
        )
        val textX by animateFloatAsState(
            targetValue = max(0f, scrollOffset * 0.3f),
            animationSpec = tween(500)
        )
        val titleColor by animateColorAsState(
            targetValue = if (scrollOffset > 300f) Color.Red else Color.Black,
            animationSpec = tween(500)
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(boxHeight)
                .background(boxColor)
                .padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = imageRes),
                contentDescription = "Item Image",
                modifier = Modifier
                    .size(imageSize.dp)
                    .offset(y = imageY.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .shadow(8.dp, RoundedCornerShape(16.dp))
            )

            Text(
                text = title,
                fontSize = textSize.sp,
                fontWeight = FontWeight.Bold,
                color = titleColor,
                modifier = Modifier
                    .offset(x = textX.dp, y = (imageY * -0.5f).dp)
                    .background(Color.White.copy(alpha = 0.7f), RoundedCornerShape(8.dp))
                    .padding(8.dp)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = description,
            fontSize = 18.sp,
            color = Color.Gray,
            modifier = Modifier.padding(16.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Additional Content (Specifications, Reviews, etc.)
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = "Specifications",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "• High-quality material\n• Long-lasting battery\n• 5G connectivity\n• Waterproof design",
                fontSize = 16.sp,
                modifier = Modifier.padding(vertical = 8.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Customer Reviews",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "⭐⭐⭐⭐⭐ - Amazing product! Highly recommend.\n⭐⭐⭐⭐ - Works great, but could be improved.\n⭐⭐⭐ - Average experience, nothing special.",
                fontSize = 16.sp,
                modifier = Modifier.padding(vertical = 8.dp)
            )
        }

        Spacer(modifier = Modifier.height(800.dp)) // Ensures more scrolling
    }
}

//@Preview
@Composable
fun DetailedItemScreen0(
    imageRes: Int = R.drawable.ic_launcher_foreground,
    title: String = "Super Smartphone X",
    description: String = "The latest innovation in mobile technology with top-tier performance..."
) {
    val scrollState = rememberScrollState()
    val scrollOffset = scrollState.value.toFloat()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .background(MaterialTheme.colorScheme.background)
    ) {
        // Animated properties based on scroll
        val boxHeight by animateDpAsState(
            targetValue = max(150.dp, 400.dp - (scrollOffset * 0.4f).dp),
            animationSpec = tween(500, easing = FastOutSlowInEasing)
        )
        val boxColor by animateColorAsState(
            targetValue = if (scrollOffset > 300f) Color.DarkGray else Color.LightGray,
            animationSpec = tween(500)
        )
        val imageSize by animateDpAsState(
            targetValue = max(60.dp, 250.dp - (scrollOffset * 0.4f).dp),
            animationSpec = tween(500)
        )
        val textSize by animateFloatAsState(
            targetValue = kotlin.math.max(16f, 40f - (scrollOffset * 0.4f)),
            animationSpec = tween(500)
        )
        val horizontalAlignment by animateFloatAsState(
            targetValue = min(1f, scrollOffset / 400f), // 0: vertical, 1: horizontal
            animationSpec = tween(500)
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(boxHeight)
                .background(boxColor)
                .padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            Row(
                modifier = Modifier.fillMaxSize(),
                horizontalArrangement = Arrangement.spacedBy(16.dp * horizontalAlignment),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = imageRes),
                    contentDescription = "Item Image",
                    modifier = Modifier
                        .size(imageSize)
                        .clip(RoundedCornerShape(16.dp))
                        .shadow(8.dp, RoundedCornerShape(16.dp))
                )

                Text(
                    text = title + scrollOffset,
                    fontSize = with(LocalDensity.current) { textSize.toSp() },
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    modifier = Modifier
                        .background(Color.Black.copy(alpha = 0.5f), RoundedCornerShape(8.dp))
                        .padding(8.dp)
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = description,
            fontSize = 18.sp,
            color = Color.Gray,
            modifier = Modifier.padding(16.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Additional Content
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = "Specifications",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "• High-quality material\n• Long-lasting battery\n• 5G connectivity\n• Waterproof design",
                fontSize = 16.sp,
                modifier = Modifier.padding(vertical = 8.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Customer Reviews",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "⭐⭐⭐⭐⭐ - Amazing product! Highly recommend.\n⭐⭐⭐⭐ - Works great, but could be improved.\n⭐⭐⭐ - Average experience, nothing special.",
                fontSize = 16.sp,
                modifier = Modifier.padding(vertical = 8.dp)
            )
        }

        Spacer(modifier = Modifier.height(100.dp)) // Ensures more scrolling

        // Additional Content
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = "Specifications",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "• High-quality material\n• Long-lasting battery\n• 5G connectivity\n• Waterproof design",
                fontSize = 16.sp,
                modifier = Modifier.padding(vertical = 8.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Customer Reviews",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "⭐⭐⭐⭐⭐ - Amazing product! Highly recommend.\n⭐⭐⭐⭐ - Works great, but could be improved.\n⭐⭐⭐ - Average experience, nothing special.",
                fontSize = 16.sp,
                modifier = Modifier.padding(vertical = 8.dp)
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PracticeApp(modifier: Modifier = Modifier) {
    val appBarState = rememberTopAppBarState()
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(appBarState)
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text("Practise")
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Red,
                    scrolledContainerColor = Color(0xFFEDA050),
                    navigationIconContentColor = Color.Green,
                    titleContentColor = Color.White,
                    actionIconContentColor = Color.Blue
                ),
                scrollBehavior = scrollBehavior
            )
        }
    ) { innerPadding ->
        Column(
            modifier = modifier
                .padding(innerPadding)
                .nestedScroll(
                    scrollBehavior.nestedScrollConnection
                ),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            //EffectsScreen(modifier)
            //StateFlowsScreen(modifier)
            //FlowWithLifecycleExample()
//        AnimationScreen()
            ExpandingCardGrid()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AppPreview() {
    PracticeTheme {
//        PracticeApp()
    }
}
