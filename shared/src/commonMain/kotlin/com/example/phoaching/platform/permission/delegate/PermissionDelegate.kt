package com.example.phoaching.platform.permission.delegate

import com.example.phoaching.platform.permission.model.PermissionState

internal interface PermissionDelegate {
    fun getPermissionState(): PermissionState
    suspend fun providePermission()
    fun openSettingPage()
}
