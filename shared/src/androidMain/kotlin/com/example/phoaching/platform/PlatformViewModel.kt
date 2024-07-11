package com.example.phoaching.platform

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import androidx.lifecycle.viewModelScope as baseViewModelScope


actual abstract class PlatformViewModel : ViewModel() {

    actual val viewModelScope: CoroutineScope
        get() = baseViewModelScope

    public actual override fun onCleared() {}
}