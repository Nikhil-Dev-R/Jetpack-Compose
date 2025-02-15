package com.rudraksha.practice.animations.advanced

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.rudraksha.practice.R

// Animates shared elements between screens

@Composable
fun SharedElementTransitionExample() {
    val navController = rememberNavController()

    NavHost(navController, startDestination = "screen1") {
        composable("screen1") { Screen1(navController) }
        composable("screen2") { Screen2(navController) }
    }
}

@Composable
fun Screen1(navController: NavController) {
    Column(
        modifier = Modifier.size(250.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_launcher_foreground),
            contentDescription = null,
            modifier = Modifier
                .size(100.dp)
                .background(MaterialTheme.colorScheme.secondary)
                .clickable { navController.navigate("screen2") }
        )
        Text("Click the image to navigate")
    }
}

@Composable
fun Screen2(navController: NavController) {
    Column(
        modifier = Modifier.size(250.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Here you would display the shared element
        Image(
            painter = painterResource(id = R.drawable.ic_launcher_foreground),
            contentDescription = null,
            modifier = Modifier.size(200.dp)
                .background(MaterialTheme.colorScheme.primary)
                .clickable {
//                    navController.popBackStack()
                    navController.navigate("screen1")
                    // By pressing back key user can go to screen1 even without using clickable method
                }
        )
        Text("This is the second screen")
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewSharedElementTransitionExample() {
    SharedElementTransitionExample()
}