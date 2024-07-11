package com.example.phoaching.platform.permission.delegate

import android.Manifest
import android.app.Activity
import android.content.Context
import com.example.phoaching.di.KoinInjector
import com.example.phoaching.platform.permission.checkPermissions
import com.example.phoaching.platform.permission.model.Permission
import com.example.phoaching.platform.permission.model.PermissionState
import com.example.phoaching.platform.permission.openAppSettingsPage
import com.example.phoaching.platform.permission.providePermissions
import com.example.phoaching.platform.permission.util.PermissionRequestException

actual class CameraPermissionDelegate : PermissionDelegate {
    private val context by KoinInjector.koin.inject<Context>()

    private fun activity(): Activity {
        val temp: Activity by KoinInjector.koin.inject()
        return temp
    }

    override fun getPermissionState(): PermissionState {
        return checkPermissions(context, activity(), permission)
    }

    override suspend fun providePermission() {
        activity().providePermissions(permission) {
            throw PermissionRequestException(Permission.CAMERA.name)
        }
    }

    override fun openSettingPage() {
        context.openAppSettingsPage(Permission.CAMERA)
    }
}

private val permission: List<String> = listOf(Manifest.permission.CAMERA)