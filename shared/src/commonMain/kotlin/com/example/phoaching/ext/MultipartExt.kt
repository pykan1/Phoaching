package com.example.phoaching.ext

import com.example.phoaching.platform.FormFull
import io.ktor.client.request.forms.MultiPartFormDataContent
import io.ktor.http.Headers
import io.ktor.http.HttpHeaders
import io.ktor.http.content.PartData

/**
 * Функция создает экземпляр MultiPartFormDataContent на основе списка объектов FormFull.
 * @param files список объектов FormFull, представляющих данные для формы
 * @return экземпляр MultiPartFormDataContent с данными формы
 */
fun multipart(files: List<FormFull>): MultiPartFormDataContent {
    return MultiPartFormDataContent(createFormData(files))

}

/**
 * Функция создает список PartData на основе списка объектов FormFull.
 * @param files список объектов FormFull, представляющих данные для формы
 * @return список PartData с данными формы
 */
fun formData(files: List<FormFull>): List<PartData> {
    return createFormData(files)
}


/**
 * Функция преобразует список объектов FormFull в список PartData для использования в мультипартовом запросе.
 * @param files список объектов FormFull, представляющих данные для формы
 * @return список PartData с данными формы
 */
fun createFormData(files: List<FormFull>): List<PartData> {
    return io.ktor.client.request.forms.formData {
        files.forEach {
            when (it) {
                is FormFull.FormBodyFull -> {
                    append(it.key, it.value)
                }

                is FormFull.FormFileFull -> {
                    println("file byteArray")
                    println(it.byteArray)
                    println(it.name)
                    append(
                        key = it.key,
                        value = it.byteArray,
                        headers = Headers.build {
                            append(
                                HttpHeaders.ContentType,
                                "multipart/form-data"
                            ) // Mime type required
                            append(
                                HttpHeaders.ContentDisposition,
                                "filename=${it.name}"
                            ) // Filename in content disposition required
                        }
                    )
                }

                is FormFull.FromBodyFullBool -> {
                    append(it.key, it.value)
                }

                is FormFull.FromBodyFullInt -> {
                    append(it.key, it.value)
                }

                is FormFull.FormVideoFile -> {
                    append(
                        key = it.key,
                        value = it.byteArray,
                        headers = Headers.build {
                            append(
                                HttpHeaders.ContentType,
                                "multipart/form-data"
                            ) // Mime type required
                            append(
                                HttpHeaders.ContentDisposition,
                                "filename=${it.name}"
                            ) // Filename in content disposition required
                        }
                    )
                    append(
                        key = it.previewKey,
                        value = it.previewByteArray,
                        headers = Headers.build {
                            append(
                                HttpHeaders.ContentType,
                                "multipart/form-data"
                            ) // Mime type required
                            append(
                                HttpHeaders.ContentDisposition,
                                "filename=${it.previewName}"
                            ) // Filename in content disposition required
                        }
                    )
                }
            }

        }

    }
}