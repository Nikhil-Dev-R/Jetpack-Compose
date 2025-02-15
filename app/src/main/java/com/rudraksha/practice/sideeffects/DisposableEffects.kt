package com.rudraksha.practice.sideeffects

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.rememberCoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

//Use when: You need to dispose of resources when the composition is disposed.

@Composable
fun DisposableEffectExample(url: String) {
    val coroutineScope = rememberCoroutineScope()
    // Usage: Managing a network request and cleaning it up when the composable is disposed.

    DisposableEffect(Unit) {
        // Code to run on composition disposal
        val job = coroutineScope.launch {
            // Simulating a network request
            println("Starting network request for $url")
            delay(2000) // Simulating network delay
            println("Received data from $url")
        }

        onDispose {
            // Code to run on disposal
            job.cancel() // Cancel the job if disposed
            println("Network request canceled for $url")
        }
    }
}
