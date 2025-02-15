package com.rudraksha.practice.animations.advanced

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.SpringSpec
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateIntOffsetAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.rememberTooltipState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInRoot
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.constrainHeight
import androidx.compose.ui.unit.constrainWidth
import androidx.compose.ui.unit.dp
import kotlin.math.max

@Composable
fun ExpandingCardGrid() {
    val gridItems = List(40) { "Item $it" }
    var expandedCardIndex by remember { mutableStateOf<Int?>(null) }
    var startOffset by remember { mutableStateOf(Offset.Zero) }
    var startSize by remember { mutableStateOf(IntSize.Zero) }
    var isCardExpanded by remember { mutableStateOf(false) }

    val density = LocalDensity.current // Access density for conversions

    val screenWidthPx = with(density) { LocalConfiguration.current.screenWidthDp.dp.toPx() }
    val screenHeightPx = with(density) { LocalConfiguration.current.screenHeightDp.dp.toPx() }

    Box(modifier = Modifier.fillMaxSize()) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier
                .fillMaxSize(),
            contentPadding = PaddingValues(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(gridItems.size) { index ->
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(1f)
                        .onGloballyPositioned { layoutCoordinates ->
                            if (expandedCardIndex == index) {
                                val position = layoutCoordinates.positionInRoot()
                                startOffset = Offset(position.x, position.y)
                                startSize = layoutCoordinates.size
                            }
                        }
                        .clickable {
                            expandedCardIndex = index
                            isCardExpanded = true
                        }
                ) {
                    Card(
                        shape = RoundedCornerShape(16.dp),
                        colors = CardDefaults.cardColors(containerColor = Color(0xFF6200EE))
                    ) {
                        Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
                            Text(
                                text = gridItems[index],
                                style = MaterialTheme.typography.bodyLarge,
                                color = Color.White
                            )
                        }
                    }
                }
            }
        }

        if (isCardExpanded) {
            val springSpecOffset: SpringSpec<IntOffset> = spring(
                dampingRatio = Spring.DampingRatioMediumBouncy,
                stiffness = Spring.StiffnessMedium
            )

            val springSpecSize: SpringSpec<Dp> = spring(
                dampingRatio = Spring.DampingRatioMediumBouncy,
                stiffness = Spring.StiffnessMedium
            )

            val animatedOffset by animateIntOffsetAsState(
                targetValue = if (isCardExpanded) IntOffset(0, 0) else IntOffset(startOffset.x.toInt(), startOffset.y.toInt()),
                animationSpec = springSpecOffset
            )

            val animatedWidth by animateDpAsState(
                targetValue = if (isCardExpanded) with(density) { screenWidthPx.toDp() } else with(density) { startSize.width.toDp() },
                animationSpec = springSpecSize
            )

            val animatedHeight by animateDpAsState(
                targetValue = if (isCardExpanded) with(density) { screenHeightPx.toDp() } else with(density) { startSize.height.toDp() },
                animationSpec = springSpecSize
            )

            Box(
                modifier = Modifier
                    .offset { animatedOffset }
                    .size(width = animatedWidth, height = animatedHeight)
                    .clickable {
                        isCardExpanded = false
                        expandedCardIndex = null
                    }
            ) {
                Card(
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = Color(0xFF6200EE))
                ) {
                    Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
                        Text(
                            text = gridItems[expandedCardIndex ?: 0],
                            style = MaterialTheme.typography.headlineLarge,
                            color = Color.White
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun AdvancedAnimations() {
    Column {
//        CrossfadeAnimation()
//        EnterTransitionExample()
//        SharedElementTransitionExample()
//        AnimatedVisibilityAnim()
//        MoreOptionBottomBar()
//        AnimatedContentAnim()
//        ShimmerEffect()
//        Sequential()
//        Simultaneous()
//        ImagePostLikeAnim()
//        LottieAnimations()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RichTooltipWithCustomCaretSample() {
    val tooltipState = rememberTooltipState(isPersistent = true)
    val scope = rememberCoroutineScope()
    /*TooltipBox(
        positionProvider = TooltipDefaults.rememberRichTooltipPositionProvider(),
        tooltip = {
            RichTooltip(
                title = { Text(richTooltipSubheadText) },
                action = {
                    TextButton(onClick = { scope.launch { tooltipState.dismiss() } }) {
                        Text(richTooltipActionText)
                    }
                },
                caretSize = DpSize(32.dp, 16.dp)
            ) {
                Text(richTooltipText)
            }
        },
        state = tooltipState
    ) {
        IconButton(onClick = { *//* Icon button's click event *//* }) {
            Icon(imageVector = Icons.Filled.Info, contentDescription = "Localized Description")
        }
    }*/
}


@Composable
fun InterestsAdaptiveContentLayout(
    modifier: Modifier = Modifier,
    topPadding: Dp = 0.dp,
    itemSpacing: Dp = 4.dp,
    itemMaxWidth: Dp = 450.dp,
    multipleColumnsBreakPoint: Dp = 600.dp,
    content: @Composable () -> Unit,
) {
    Layout(modifier = modifier, content = content) { measurables, outerConstraints ->
        // Convert parameters to Px. Safe to do as `Layout` measure block runs in a `Density` scope
        val multipleColumnsBreakPointPx = multipleColumnsBreakPoint.roundToPx()
        val topPaddingPx = topPadding.roundToPx()
        val itemSpacingPx = itemSpacing.roundToPx()
        val itemMaxWidthPx = itemMaxWidth.roundToPx()

        // Number of columns to display on the screen. This is harcoded to 2 due to
        // the design mocks, but this logic could change in the future.
        val columns = if (outerConstraints.maxWidth < multipleColumnsBreakPointPx) 1 else 2
        // Max width for each item taking into account available space, spacing and `itemMaxWidth`
        val itemWidth = if (columns == 1) {
            outerConstraints.maxWidth
        } else {
            val maxWidthWithSpaces = outerConstraints.maxWidth - (columns - 1) * itemSpacingPx
            (maxWidthWithSpaces / columns).coerceIn(0, itemMaxWidthPx)
        }
        val itemConstraints = outerConstraints.copy(maxWidth = itemWidth)

        // Keep track of the height of each row to calculate the layout's final size
        val rowHeights = IntArray(measurables.size / columns + 1)
        // Measure elements with their maximum width and keep track of the height
        val placeables = measurables.mapIndexed { index, measureable ->
            val placeable = measureable.measure(itemConstraints)
            // Update the height for each row
            val row = index.floorDiv(columns)
            rowHeights[row] = max(rowHeights[row], placeable.height)
            placeable
        }

        // Calculate maxHeight of the Interests layout. Heights of the row + top padding
        val layoutHeight = topPaddingPx + rowHeights.sum()
        // Calculate maxWidth of the Interests layout
        val layoutWidth = itemWidth * columns + (itemSpacingPx * (columns - 1))

        // Lay out given the max width and height
        layout(
            width = outerConstraints.constrainWidth(layoutWidth),
            height = outerConstraints.constrainHeight(layoutHeight)
        ) {
            // Track the y co-ord we have placed children up to
            var yPosition = topPaddingPx
            // Split placeables in lists that don't exceed the number of columns
            // and place them taking into account their width and spacing
            placeables.chunked(columns).forEachIndexed { rowIndex, row ->
                var xPosition = 0
                row.forEach { placeable ->
                    placeable.placeRelative(x = xPosition, y = yPosition)
                    xPosition += placeable.width + itemSpacingPx
                }
                yPosition += rowHeights[rowIndex]
            }
        }
    }
}

@Preview
@Composable
fun LayoutPreview() {
    InterestsAdaptiveContentLayout() {
        Row {
            Column {
                listOf(1, 2, 3, 4, 5, 6, 7, 7, 9, 20).forEach {
                    Text("Item ${it}")
                }
            }
            Column {
                listOf(1, 2, 3, 4, 5, 6, 7, 7, 9, 20).forEach {
                    Text("Item ${it}")
                }
            }
            Column {
                listOf(1, 2, 3, 4, 5, 6, 7, 7, 9, 20).forEach {
                    Text("Item ${it}")
                }
            }
        }
    }
}