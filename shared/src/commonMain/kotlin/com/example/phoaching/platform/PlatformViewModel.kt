package com.example.phoaching.platform

import kotlinx.coroutines.CoroutineScope

expect abstract class PlatformViewModel() {

    val viewModelScope : CoroutineScope
    open fun onCleared()
}