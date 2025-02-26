package com.rudraksha.practice.permissions

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.provider.Settings
import androidx.activity.ComponentActivity
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.core.app.ActivityCompat.shouldShowRequestPermissionRationale

class PermissionsManager(
    private val context: Context,
    private val permissionLauncher: ManagedActivityResultLauncher<Array<String>, Map<String, Boolean>>,
) {
    fun requestPermissions(permissions: Array<String>) {
        permissionLauncher.launch(permissions)
    }

    fun isPermissionPermanentlyDenied(permission: String): Boolean {
        return !shouldShowRequestPermissionRationale(
            context as ComponentActivity, permission
        )
    }

    fun openAppSettings() {
        val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS).apply {
            data = Uri.fromParts("package", context.packageName, null)
        }
        context.startActivity(intent)
    }
}
