package com.example.phoaching.platform

import android.content.Context
import com.example.phoaching.ext.file
import com.example.phoaching.ext.formData
import com.example.phoaching.ext.multipart
import io.ktor.client.request.forms.MultiPartFormDataContent
import io.ktor.http.content.PartData


class MultipartManagerImpl(private val context: Context) : MultipartManager {

    override suspend fun createMultipart(files: List<Form>): MultiPartFormDataContent {
        return multipart(files.map {
            when (it) {
                is Form.FormBody -> {
                    FormFull.FormBodyFull(it.key, it.value)
                }

                is Form.FormFile -> {
                    val file = file(context, it.uri)
                    FormFull.FormFileFull(
                        key = it.key,
                        name = file?.name.orEmpty(),
                        byteArray = file?.readBytes() ?: byteArrayOf()
                    )
                }

                is Form.FromBodyBool -> {
                    FormFull.FromBodyFullBool(
                        key = it.key,
                        value = it.value
                    )
                }

                is Form.FromBodyInt -> {
                    FormFull.FromBodyFullInt(
                        key = it.key,
                        value = it.value
                    )
                }

            }
        })
    }

    override suspend fun createFormData(files: List<Form>): List<PartData> {
        return formData(files.map {
            when (it) {
                is Form.FormBody -> {
                    FormFull.FormBodyFull(it.key, it.value)
                }

                is Form.FormFile -> {
                    val file = file(context, it.uri)
                    FormFull.FormFileFull(
                        key = it.key,
                        name = file?.name.orEmpty(),
                        byteArray = file?.readBytes() ?: byteArrayOf()
                    )
                }

                is Form.FromBodyBool -> {
                    FormFull.FromBodyFullBool(
                        key = it.key,
                        value = it.value
                    )
                }

                is Form.FromBodyInt -> {
                    FormFull.FromBodyFullInt(
                        key = it.key,
                        value = it.value
                    )
                }
            }
        })
    }

}