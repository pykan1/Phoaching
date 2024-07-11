package com.example.phoaching.platform

import androidx.compose.runtime.Composable

public interface MPFile<out T : Any> {
    // on JS this will be a file name, on other platforms it will be a file path
    public val path: String
    public val platformFile: T
}

public typealias FileSelected = (MPFile<Any>?) -> Unit

@Composable
public expect fun FilePicker(
    show: Boolean,
    initialDirectory: String? = null,
    fileExtensions: List<String> = emptyList(),
    onFileSelected: FileSelected
)