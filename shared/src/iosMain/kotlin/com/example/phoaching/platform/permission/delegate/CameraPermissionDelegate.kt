package com.example.phoaching.platform.permission.delegate

import com.example.phoaching.platform.permission.model.PermissionState
import com.example.phoaching.platform.permission.openAppSettingsPage
import platform.AVFoundation.AVAuthorizationStatus
import platform.AVFoundation.AVAuthorizationStatusAuthorized
import platform.AVFoundation.AVAuthorizationStatusDenied
import platform.AVFoundation.AVAuthorizationStatusNotDetermined
import platform.AVFoundation.AVAuthorizationStatusRestricted
import platform.AVFoundation.AVCaptureDevice
import platform.AVFoundation.AVMediaType
import platform.AVFoundation.AVMediaTypeVideo
import platform.AVFoundation.authorizationStatusForMediaType
import platform.AVFoundation.requestAccessForMediaType
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine


actual class CameraPermissionDelegate : PermissionDelegate {
    private val type = AVMediaTypeVideo
    override fun getPermissionState(): PermissionState {
        val status: AVAuthorizationStatus = currentAuthorizationStatus()
        return when (status) {
            AVAuthorizationStatusAuthorized -> PermissionState.GRANTED
            AVAuthorizationStatusNotDetermined -> PermissionState.NOT_DETERMINED
            AVAuthorizationStatusDenied -> PermissionState.DENIED
            AVAuthorizationStatusRestricted -> PermissionState.GRANTED
            else -> error("unknown authorization status $status")
        }
    }

    override suspend fun providePermission() {
        val status: AVAuthorizationStatus = currentAuthorizationStatus()
        when (status) {
            AVAuthorizationStatusAuthorized -> return
            AVAuthorizationStatusNotDetermined -> {
                val isGranted: Boolean = suspendCoroutine { continuation ->
                    AVCaptureDevice.requestAccess(type) { continuation.resume(it) }
                }
                if (isGranted) return
//                else throw DeniedAlwaysException(permission)
            }

//            AVAuthorizationStatusDenied -> throw DeniedAlwaysException(permission)
//            else -> error("unknown authorization status $status")
        }
    }

    override fun openSettingPage() {
        openAppSettingsPage()
    }

    private fun currentAuthorizationStatus(): AVAuthorizationStatus {
        return AVCaptureDevice.authorizationStatusForMediaType(type)
    }

    private fun AVCaptureDevice.Companion.requestAccess(
        type: AVMediaType,
        callback: (isGranted: Boolean) -> Unit
    ) {
        this.requestAccessForMediaType(type) { isGranted: Boolean ->
            callback(isGranted)
        }
    }

}