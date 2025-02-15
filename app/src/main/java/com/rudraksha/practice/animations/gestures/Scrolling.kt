package com.rudraksha.practice.animations.gestures

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun ScrollingList() {
//    val lazyRowState = rememberLazyListState()
//    val lazyColumnState = rememberLazyListState()

    LazyRow {
        items(50) { index -> // Replace with your actual data
            LazyColumn {
                items(50) { innerIndex -> // Replace with your actual data
                    Text("Item $innerIndex in Column $index",
                        modifier = Modifier
                            .padding(8.dp)
                            .background(
                                if ((innerIndex + index) % 2 == 0)
                                    androidx.compose.ui.graphics.Color.Red
                                else
                                    androidx.compose.ui.graphics.Color.Green
                            )
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ScrollingListPreview() {
    ScrollingList()
}
