package com.rudraksha.practice.sideeffects

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

/*LaunchedEffect is a composable function in Jetpack Compose that allows you
to run side effects in response to changes in state or other triggers.
It is designed to launch a coroutine in the composition lifecycle, enabling
tasks like making network requests, starting animations, or other asynchronous operations.

### Key Features:
- *Lifecycle-aware*: Automatically cancels the coroutine when the key changes or
        when the composable leaves the composition.
- *Key parameter*: You can specify a key (or multiple keys) that triggers the effect
        to run when those keys change.
*/
@Composable
fun FetchDataExample() {
    var data by remember { mutableStateOf<String?>(null) }

    LaunchedEffect(Unit) { // Runs when the composable is first displayed
        data = fetchDataFromNetwork() // Replace with your actual data-fetching logic
    }

    // Display the fetched data
    if (data != null) {
        Text(text = "Data: $data")
    } else {
        Text(text = "Loading...")
    }
}

//### Using Keys
//You can pass a key to LaunchedEffect to run the coroutine whenever the key changes:
@Composable
fun CountDownExample(count: Int) {
    var timeLeft by remember { mutableIntStateOf(count) }
    /*
    If timeLeft is the key to LaunchedEffect, this would cause the coroutine to
        restart, effectively resetting the timer every second.
    count acts as the trigger for starting the countdown.
    timeLeft is the value being updated during the countdown.
    Using count as the LaunchedEffect key ensures that the countdown starts once and
        proceeds without interruption.
    */

    // Runs only whenever 'count' changes. If count remains same then it will not run
    LaunchedEffect(count) {
        while (timeLeft > 0) {
            delay(1000)
            timeLeft--
            // timeLeft is modified so the composable part will be recomposed.
        }
    }

    Text(text = "Time left: $timeLeft seconds")
}

/*
### Summary
LaunchedEffect is useful for managing side effects in a composable, allowing you
to run coroutines that are tied to the lifecycle of the composable and respond
to changes in state or other triggers. This helps keep your UI reactive and
maintains a clear separation between UI and side-effect logic.
*/

/*
### Example 1: Using LaunchedEffect
*Scenario*: Fetching data from a network when the composable first appears.
*/
@Composable
fun DataFetcher() {
    var data by remember { mutableStateOf<String?>(null) }

    // This will run only when the composable is first displayed
    LaunchedEffect(Unit) {
        data = fetchDataFromNetwork() // Replace with your actual network call
    }

    // Display the fetched data
    if (data != null) {
        Text(text = "Data: $data")
    } else {
        Text(text = "Loading...")
    }
}

// Simulated network call
suspend fun fetchDataFromNetwork(): String {
    delay(1500) // Simulate a network delay
    return "Fetched Data"
}

/*
### Explanation
- *LaunchedEffect(Unit)*: The effect runs when the composable enters the composition.
- *Asynchronous Call*: It fetches data asynchronously, allowing the UI to remain responsive.
- *State Management*: The UI reacts to state changes; when data is fetched, the UI updates to display it.

### Example 2: Without LaunchedEffect
*Scenario*: Fetching data directly in the composable without using LaunchedEffect.
*/

@Composable
fun DataFetcherWithoutLaunchedEffect() {
    var data by remember { mutableStateOf<String?>(null) }

    // This runs every recomposition; can lead to multiple network calls
    if (data == null) {
        // This will block the UI, making it unresponsive
        data = runBlocking { fetchDataFromNetwork() }
    }

    // Display the fetched data
    if (data == null) {
        Text(text = "Loading...")
    } else {
        Text(text = "Data: $data")
    }
}


/*
### Explanation
- *Blocking Call*: runBlocking is used to fetch data, blocking the UI thread,
        making the app unresponsive until the data is fetched.
- *Multiple Calls*: Since this runs on every recomposition, it could lead to
        multiple unnecessary network calls, degrading performance.
- *No Lifecycle Awareness*: The logic isn't tied to the composable's lifecycle,
        potentially causing issues with stale data.
*/

//### Example 3: Using Keys with LaunchedEffect
//*Scenario*: A countdown timer that updates every second.

@Composable
fun CountdownTimer(initialCount: Int) {
    var timeLeft by remember { mutableStateOf(initialCount) }

    // Runs every time the initialCount changes
    LaunchedEffect(initialCount) {
        while (timeLeft > 0) {
            delay(1000)
            timeLeft--
        }
    }

    Text(text = "Time left: $timeLeft seconds")
}

/*
### Explanation
- *Dynamic Behavior*: The countdown timer will restart if the
        initialCount changes, thanks to the key in LaunchedEffect.
- *Responsive UI*: The timer updates every second without blocking the UI thread.

### Summary
- **Using LaunchedEffect**: Ideal for running side effects asynchronously, maintaining
        responsiveness, and reacting to state changes.
- **Without LaunchedEffect**: Can lead to blocking UI, multiple unnecessary executions,
        and unresponsive apps.
*/
