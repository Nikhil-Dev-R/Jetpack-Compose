package com.rudraksha.practice.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.rudraksha.practice.sideeffects.CoroutineScopeExample
import com.rudraksha.practice.sideeffects.CountDownExample
import com.rudraksha.practice.sideeffects.DataFetcher
import com.rudraksha.practice.sideeffects.DataFetcherWithoutLaunchedEffect
import com.rudraksha.practice.sideeffects.FetchDataExample
import com.rudraksha.practice.sideeffects.LaunchedEffectExample
import com.rudraksha.practice.sideeffects.MyComposable
import com.rudraksha.practice.sideeffects.SideEffectExample


@Composable
fun EffectsScreen(modifier: Modifier = Modifier) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = "LaunchedEffect",
            fontSize = MaterialTheme.typography.headlineLarge.fontSize,
        )
        // Use LazyVerticalGrid to create a grid of items
        LazyVerticalGrid(columns = GridCells.Adaptive(150.dp), modifier = modifier) {
            items(2) { // Use items to define the number of items in the grid
                Column(modifier = Modifier.padding(8.dp)) {
                    Text(text = "FetchDataExample")
                    FetchDataExample() // Ensure FetchDataExample is a @Composable function
                }

            }
            items(2) {
                Column(modifier = Modifier.padding(8.dp)) {
                    Text(text = "CountDownExample")
                    CountDownExample(20) // Ensure CountDownExample is a @Composable function
                }
            }
            items(1) {
                Column(modifier = Modifier.padding(8.dp)) {
                    Text(text = "DataFetcher")
                    DataFetcher()
                }
            }
            items(1) {
                Column(modifier = Modifier.padding(8.dp)) {
                    Text(text = "DataFetcherWithoutLaunchedEffect")
                    DataFetcherWithoutLaunchedEffect()
                }
            }
        }

        Text(
            text = "LaunchedEffect vs SideEffect",
            fontSize = MaterialTheme.typography.headlineLarge.fontSize,
        )
        Text(text = "MyComposable")
        MyComposable()
        Row(
            modifier = Modifier.padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(modifier = Modifier.padding(8.dp)) {
                Text(text = "LaunchedEffect")
                LaunchedEffectExample()
            }
            Spacer(modifier = Modifier.weight(1f))
            Column(modifier = Modifier.padding(8.dp)) {
                Text(text = "SideEffect")
                SideEffectExample()
            }
        }

        Text(
            text = "CoroutineScopeExample",
            fontSize = MaterialTheme.typography.headlineLarge.fontSize,)
        CoroutineScopeExample()
    }
}
