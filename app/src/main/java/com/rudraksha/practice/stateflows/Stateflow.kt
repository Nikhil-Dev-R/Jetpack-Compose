package com.rudraksha.practice.stateflows

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

//You can create a StateFlow using the MutableStateFlow class, which allows you to update the value:

data class MyState(val count: Int = 0)

class CounterStateViewModel : ViewModel() {
    private val _state = MutableStateFlow(MyState())
    val state: StateFlow<MyState> = _state.asStateFlow()

    /*
    Use _state.asStateFlow() when you want to expose a read-only flow to prevent
    external modification and ensure encapsulation.
    Use _state directly only if it's already a StateFlow that you intend to
    expose without concerns about external mutation.
    */

    fun increment() {
        _state.value = _state.value.copy(count = _state.value.count + 1)
    }
}

@Composable
fun CounterStateScreen(viewModel: CounterStateViewModel) {
    val state by viewModel.state.collectAsState()

    Column {
        Text(text = "Count: ${state.count}")
        Button(onClick = { viewModel.increment() }) {
            Text("Increment")
        }
    }
}
