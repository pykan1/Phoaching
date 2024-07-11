package com.example.phoaching.platform.permission

import com.example.phoaching.platform.permission.delegate.CameraPermissionDelegate
import com.example.phoaching.platform.permission.delegate.PermissionDelegate
import com.example.phoaching.platform.permission.delegate.ReadFilesPermissionDelegate
import com.example.phoaching.platform.permission.model.Permission
import com.example.phoaching.platform.permission.service.PermissionsService
import com.example.phoaching.platform.permission.service.PermissionsServiceImpl
import org.koin.core.module.Module
import org.koin.core.qualifier.named
import org.koin.dsl.module


val platformPermissionsModule: Module = module {

    single<PermissionDelegate>(named(Permission.CAMERA.name)) { CameraPermissionDelegate() }
    single<PermissionDelegate>(named(Permission.READ_EXTERNAL_STORAGE.name)) { ReadFilesPermissionDelegate() }

    single<PermissionsService> {
        PermissionsServiceImpl()
    }
}
