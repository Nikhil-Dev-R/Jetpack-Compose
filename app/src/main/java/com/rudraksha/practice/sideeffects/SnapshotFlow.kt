package com.rudraksha.practice.sideeffects

import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshotFlow


//Use when: You need to convert a value into a Flow that emits updates.

@Composable
fun SnapshotFlowExample() {
    val counter = remember { mutableStateOf(0) }
    /*
    val counter = remember { mutableStateOf(0) }:

    This creates a mutable state holder (of type MutableState<Int>) and assigns it to counter.
    To access the value of counter, you would use counter.value.

    val counter by remember { mutableStateOf(0) }:

    This uses Kotlin's delegated properties feature (by) to provide direct access to the value
    of the MutableState.
    You can simply use counter to access the value, without needing .value.*/

    // Creating a snapshotFlow that emits when counter changes
    val flow = snapshotFlow { counter.value }

    LaunchedEffect(Unit) {
        flow.collect { value ->
            println("Counter updated: $value")
        }
    }

    // UI components to increment/decrement the counter
    Row {
        Button(onClick = { counter.value++ }) {
            Text("Increment")
        }
        Button(onClick = { counter.value-- }) {
            Text("Decrement")
        }
    }

    Text("Current Counter: ${counter.value}")
}
