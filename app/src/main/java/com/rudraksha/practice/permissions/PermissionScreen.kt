package com.rudraksha.practice.permissions

import android.Manifest
import android.os.Build
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun PermissionsScreen() {
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()

    val permissionsList = remember {
        mutableListOf(
            Manifest.permission.CAMERA,
            Manifest.permission.RECORD_AUDIO,
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION,
        ).apply {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) add(Manifest.permission.POST_NOTIFICATIONS)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                add(Manifest.permission.BLUETOOTH_SCAN)
                add(Manifest.permission.BLUETOOTH_CONNECT)
            }
        }
    }

    var showPermissionButton by remember { mutableStateOf(true) }
    var permanentlyDenied by remember { mutableStateOf(false) }

    val permissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestMultiplePermissions(),
        onResult = { results ->
            coroutineScope.launch {
                delay(300)
                showPermissionButton = results.any { !it.value }
                permanentlyDenied = results.any { !it.value && !shouldShowRequestPermissionRationale(context, it.key) }
            }
        }
    )

    val permissionsManager = remember { PermissionsManager(context, permissionLauncher) }

    LaunchedEffect(Unit) {
        coroutineScope.launch {
            delay(300)
            permissionsManager.requestPermissions(permissionsList.toTypedArray())
        }
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        if (showPermissionButton) {
            Text("Some permissions are denied. Grant them for full functionality.")
            Spacer(modifier = Modifier.height(10.dp))
            Button(onClick = { permissionsManager.requestPermissions(permissionsList.toTypedArray()) }) {
                Text("Grant Permissions")
            }
        } else {
            Text("All permissions granted!")
        }

        if (permanentlyDenied) {
            Spacer(modifier = Modifier.height(20.dp))
            Text("Permissions are permanently denied. Enable them in settings.")
            Spacer(modifier = Modifier.height(10.dp))
            Button(onClick = { permissionsManager.openAppSettings() }) {
                Text("Go to Settings")
            }
        }
    }
}

