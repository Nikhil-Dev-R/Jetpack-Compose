package com.rudraksha.practice.permissions

import android.Manifest
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.provider.Settings
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.accompanist.permissions.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalPermissionsApi::class)
@Preview
@Composable
fun RequestPermissionsScreen() {
    val context = LocalContext.current
    val permissionsList = remember {
        mutableListOf(
            Manifest.permission.CAMERA,
            Manifest.permission.RECORD_AUDIO
        )
    }
    permissionsList.apply {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) add(Manifest.permission.POST_NOTIFICATIONS)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) add(Manifest.permission.ACCESS_BACKGROUND_LOCATION)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            add(Manifest.permission.BLUETOOTH_SCAN)
            add(Manifest.permission.BLUETOOTH_CONNECT)
        }
    }

    val coroutineScope = rememberCoroutineScope()
//    val permissionsState = rememberMultiplePermissionsState(permissionsList)

    var showPermissionButton by remember { mutableStateOf(false) }
    var permanentlyDenied by remember { mutableStateOf(false) }

/*    LaunchedEffect(Unit) {
        coroutineScope.launch {
            delay(300) // Avoids immediate re-execution on recomposition
            permissionsState.launchMultiplePermissionRequest()
        }
    }

    LaunchedEffect(permissionsState.permissions) {
        coroutineScope.launch {
            delay(300) // Small delay to ensure UI updates correctly
            showPermissionButton = permissionsState.permissions.any { it.status is PermissionStatus.Denied }
            permanentlyDenied = permissionsState.permissions.any {
                !it.status.isGranted && !it.status.shouldShowRationale
            }
        }
    }*/

    /*Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        if (showPermissionButton) {
            Text("Some permissions are denied. Grant them for full functionality.")
            Spacer(modifier = Modifier.height(10.dp))
            Button(onClick = {
                coroutineScope.launch {
                    permissionsState.launchMultiplePermissionRequest()
                }
            }) {
                Text("Grant Permissions")
            }
        } else {
            Text("All permissions granted!")
        }

        if (permanentlyDenied) {
            Spacer(modifier = Modifier.height(20.dp))
            Text("Permissions are permanently denied. Enable them in settings.")
            Spacer(modifier = Modifier.height(10.dp))
            Button(onClick = {
                val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS).apply {
                    data = Uri.fromParts("package", context.packageName, null)
                }
                context.startActivity(intent)
            }) {
                Text("Go to Settings")
            }
        }
    }*/
}
