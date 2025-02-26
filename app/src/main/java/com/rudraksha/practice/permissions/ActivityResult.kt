package com.rudraksha.practice.permissions

import android.Manifest
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.provider.Settings
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Preview
@Composable
fun RequestPermissionsActivity() {
    val context: Context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()

    val permissionsList = remember {
        mutableListOf(
            Manifest.permission.CAMERA,
            Manifest.permission.RECORD_AUDIO,
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION,
        )
    }
    permissionsList.apply {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) add(Manifest.permission.POST_NOTIFICATIONS)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            add(Manifest.permission.BLUETOOTH_SCAN)
            add(Manifest.permission.BLUETOOTH_CONNECT)
        }
    }

    var showPermissionButton by remember { mutableStateOf(true) }
    var permanentlyDenied by remember { mutableStateOf(false) }

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestMultiplePermissions(),
        onResult = { results ->
            coroutineScope.launch {
                delay(300) // Ensure UI updates smoothly
                showPermissionButton = results.any { !it.value }
                permanentlyDenied = results.any { !it.value && !shouldShowRequestPermissionRationale(context, it.key) }
                Toast.makeText(
                    context,
                    if (showPermissionButton) "Some permissions denied" else "All permissions granted",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    )

    LaunchedEffect(Unit) {
        coroutineScope.launch {
            delay(300)
            launcher.launch(permissionsList.toTypedArray())
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
            Button(onClick = {
                coroutineScope.launch {
                    launcher.launch(permissionsList.toTypedArray())
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
    }
}

// Helper function to check if we should show rationale for a permission
fun shouldShowRequestPermissionRationale(context: Context, permission: String): Boolean {
    return androidx.core.app.ActivityCompat.shouldShowRequestPermissionRationale(
        (context as androidx.activity.ComponentActivity), permission
    )
}