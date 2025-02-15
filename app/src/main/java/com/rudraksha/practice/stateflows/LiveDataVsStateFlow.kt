package com.rudraksha.practice.stateflows

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch

//### LiveData Example

@Composable
fun LiveDataExample(viewModel: MyLiveDataViewModel) {
    val data by viewModel.liveData.observeAsState()

    data?.let {it ->
        Text(text = it)
    }

    Button(onClick = { viewModel.updateData(newData = "New Data") }) {
        Text("Update Data")
    }
    Button(onClick = { viewModel.updateData() }) {
        Text("Update")
    }
}

class MyLiveDataViewModel : ViewModel() {
    private val _liveData = MutableLiveData<String>()
    val liveData: LiveData<String> = _liveData

    fun updateData(newData: String) {
        _liveData.value = newData
    }
    fun updateData() {
        var count = 0
        viewModelScope.launch {
            while (true) {
                _liveData.value = "New Data $count"
                delay(1000)
                count++
            }
        }
    }
}

//### StateFlow Example

@Composable
fun StateFlowExample(viewModel: MyStateFlowViewModel) {
    val data by viewModel.stateFlow.collectAsState()

    Text(text = data)
    Button(onClick = { viewModel.updateData("New Data") }) {
        Text("Update Data")
    }
    Button(onClick = { viewModel.updateData() }) {
        Text("Update")
    }
}

class MyStateFlowViewModel : ViewModel() {
    private val _stateFlow = MutableStateFlow("Initial Data")
    val stateFlow: StateFlow<String> = _stateFlow


    val _dataFlow = flow {
        emit(5)
        delay(1000)
        emit(10)
        delay(1000)
        emit(15)
        delay(1000)
    }
    val dataFlow: Flow<Int> = _dataFlow

    fun updateData(newData: String) {
        _stateFlow.value = newData
    }
    @OptIn(DelicateCoroutinesApi::class)
    fun updateData() {
        var count = 0
        GlobalScope.launch {
            while (true) {
                _stateFlow.value = "New Data $count"
                delay(1000)
                count++
            }
        }
    }
}

//### Key Differences

/*
1. **Lifecycle Awareness**:
- **LiveData** is lifecycle-aware and automatically stops observing when the
        lifecycle is not active, which helps prevent memory leaks.
- **StateFlow** is not lifecycle-aware by default, so you need to manage the lifecycle manually.

2. **API Simplicity**:
- **LiveData** is simpler to use with Jetpack Compose due to its lifecycle
        awareness and built-in support.
- **StateFlow** requires more boilerplate code to handle lifecycle events
        but offers more flexibility and power.

3. **Performance**:
- **StateFlow** can be more performant in scenarios where you need to handle a large
        number of updates or complex data transformations.

State Flow requires an Initial value hence no need to check for nullability.
*/
