package com.rudraksha.practice.sideeffects

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.flowWithLifecycle
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flow

// Use when: You need to combine a Flow with the lifecycle of the composition.

@Composable
fun FlowWithLifecycleExample() {
    // Define flows outside of the composable
    // Create flows using remember to avoid re-creation on recomposition
    val flow1 = remember { createFlow1() }
    val flow2 = remember { createFlow2() }

    // Get the current lifecycle owner
    val lifecycle = LocalLifecycleOwner.current.lifecycle

    /*
    Lifecycle Awareness: The Lifecycle object helps you control when the flow
        should be active. When combined with flowWithLifecycle, it ensures that
        the flow emits values only when the composable is in a state where it
        can safely receive updates (like STARTED or RESUMED).
    Preventing Memory Leaks: By tying the flow's collection to the lifecycle, you
        prevent potential memory leaks. If a flow continues to emit values when the
        composable is no longer active (like when it's in the STOPPED or
        DESTROYED state), it could lead to issues such as crashes or unintended behavior.
    Automatic Suspension: When the lifecycle state changes (e.g., the user navigates
        away from the screen), the flow's collection will automatically suspend,
        stopping the emissions until the composable comes back into an active state.
    */

    // Combine the flows and apply flowWithLifecycle
    val combinedFlow = remember(flow1, flow2) {
        flow1.combine(flow2) { f1, f2 -> "$f1 | $f2" }.flowWithLifecycle(lifecycle)
    }

    // State to hold the latest emitted value
    var combinedValue by remember { mutableStateOf("Waiting for flow...") }

    LaunchedEffect(Unit) {
        combinedFlow.collect { value ->
            combinedValue = value // Update the state with the emitted value
            println("Combined flow emitted: $value")
        }
    }
    Text(text = "Combined Flow: ${combinedValue}")
}

// Since the body of flow { ... } runs in a coroutine, you can call suspending
// functions like delay without needing a separate suspend function.
private fun createFlow1() = flow {
    while (true) {
        emit("Flow 1 at ${System.currentTimeMillis()}")
        delay(1000)
    }
}

private fun createFlow2() = flow {
    while (true) {
        emit("Flow 2 at ${System.currentTimeMillis()}")
        delay(1500)
    }
}
