package com.orgnzphts.service

import android.Manifest
import android.content.Context
import android.widget.Toast
import com.gun0912.tedpermission.PermissionListener
import com.gun0912.tedpermission.normal.TedPermission

class PermissionService(context : Context) {
    var isGranted = false

    private val permissionListener: PermissionListener = object : PermissionListener {
        override fun onPermissionGranted() {
            isGranted = true
        }

        override fun onPermissionDenied(deniedPermissions: List<String?>?) {
            isGranted = false
            Toast.makeText(context,
                "${deniedPermissions?.joinToString(", ")} 권한이 거부되었습니다.", Toast.LENGTH_SHORT).show()
        }
    }

    fun create() {
        TedPermission.create()
            .setPermissionListener(permissionListener)
            .setPermissions(
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
            .check()
    }
}