package com.example.phoaching.platform

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob

actual abstract class PlatformViewModel {

    actual val viewModelScope = CoroutineScope(Dispatchers.Main + SupervisorJob())
    public actual open fun onCleared() { }
}