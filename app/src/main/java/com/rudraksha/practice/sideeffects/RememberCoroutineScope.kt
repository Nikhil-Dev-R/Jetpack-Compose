package com.rudraksha.practice.sideeffects

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

//Use when: You need to launch coroutines from within a composable function.

@Composable
fun CoroutineScopeExample() {
    val scope = rememberCoroutineScope()

    Button(onClick = {
        scope.launch {
            // Code to run in coroutine
            while(true) {
                println("Coroutine ran")
                delay(1000)
            }
        }
    }) {
        Text("Click me")
    }
}
