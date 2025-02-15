package com.rudraksha.practice.sideeffects

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay

@Composable
fun LaunchedEffectExample() {
    var isButtonPressed by remember { mutableStateOf(false) }

    LaunchedEffect(isButtonPressed) {
        if (isButtonPressed) {
            delay(2000)
            println("Button press effect: Delayed action completed")
        }
    }

    Button(
        onClick = { isButtonPressed = true },
        modifier = Modifier.padding(vertical = 16.dp)
    ) {
        Text("Press me")
    }
}
/*
In this example:
- LaunchedEffect is triggered when isButtonPressed changes.
- The coroutine delays for 2 seconds and then prints a message.
*/

//SideEffect Example
@Composable
fun SideEffectExample() {
    var counter by remember { mutableStateOf(0) }

    SideEffect {
        counter++
        println("SideEffect: Counter incremented")
    }

    Column {
        Text("Counter: $counter")
        Button(onClick = {  }) {
            Text("Recompose")
        }
    }
}

/*
In this example:
- SideEffect is called on every composition (recomposition).
- The counter increments and prints a message on each recomposition.
*/

/*
LaunchedEffect and SideEffect are two Compose functions used to perform side effects,
but they serve different purposes and have distinct differences:

LaunchedEffect
1. Used to launch a coroutine that runs suspend functions.
2. Automatically cancels and restarts the coroutine when the key changes.
3. Supports structured concurrency using CoroutineScope.
4. Can handle exceptions using try-catch blocks.

SideEffect
1. Used to perform simple side effects, like updating a value.
2. Runs on every composition (recomposition).
3. Does not support structured concurrency.
4. Does not automatically cancel or restart.

Key differences:
    - Coroutine support: LaunchedEffect supports coroutines, while SideEffect does not.
    - Cancellation and restart: LaunchedEffect automatically cancels and restarts on key changes,
        while SideEffect runs on every composition.
    - Concurrency: LaunchedEffect supports structured concurrency, while SideEffect does not.

Choosing between LaunchedEffect and SideEffect:
    - Use LaunchedEffect when:
        - You need to launch a coroutine.
        - You want automatic cancellation and restart.
        - You require structured concurrency.
    - Use SideEffect when:
        - You need a simple side effect.
        - You don't require coroutine support.
*/

/*
Yes, both LaunchedEffect and SideEffect are automatically called by Jetpack Compose
under certain conditions:

LaunchedEffect:
1. Initially, when the composition is first built.
2. When the key (parameter) changes.
3. When the composition is restarted (e.g., due to configuration changes).

SideEffect:
1. On every composition (recomposition).
2. Whenever the composition's dependencies change.

This automatic calling allows you to focus on the logic inside these functions without
having to worry about manually triggering them.
*/
@Composable
fun MyComposable() {
    var counter by remember { mutableStateOf(0) }

    LaunchedEffect(counter) {
        println("LaunchedEffect called")
        // Code to be executed
    }

    SideEffect {
        println("SideEffect called")
        // Code to be executed
    }

    Button(onClick = { counter++ }) {
        Text("Increment Counter")
    }
}
/*
In this example:
- LaunchedEffect is called initially and whenever counter changes.
- SideEffect is called on every composition (recomposition), including when counter changes.
*/

