package com.rudraksha.practice.stateflows

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

class CounterSharedViewModel : ViewModel() {
    private val _countFlow = MutableSharedFlow<Int>()
    val countFlow: SharedFlow<Int> = _countFlow

    private var count = 0

    fun increment() {
        count++
        viewModelScope.launch {
            _countFlow.emit(count)
        }
    }
}

@Composable
fun CounterSharedFlowScreen(viewModel: CounterSharedViewModel) {
    var currentCount by remember { mutableStateOf(0) }

    // Collect the count updates from the SharedFlow
    LaunchedEffect(Unit) {
        viewModel.countFlow.collect { count ->
            currentCount = count
        }
    }

    // UI Layout
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Current Count: $currentCount", style = MaterialTheme.typography.headlineLarge)
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = { viewModel.increment() }) {
            Text("Increment")
        }
    }
}
