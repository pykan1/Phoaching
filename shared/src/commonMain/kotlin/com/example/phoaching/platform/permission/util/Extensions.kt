package com.example.phoaching.platform.permission.util

import com.example.phoaching.platform.permission.delegate.PermissionDelegate
import com.example.phoaching.platform.permission.model.Permission
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.qualifier.named

internal fun KoinComponent.getPermissionDelegate(permission: Permission): PermissionDelegate {
    val permissionDelegate by inject<PermissionDelegate>(named(permission.name))
    return permissionDelegate
}
