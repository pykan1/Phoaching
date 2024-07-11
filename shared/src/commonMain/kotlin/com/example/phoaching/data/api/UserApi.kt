package com.example.phoaching.data.api

import de.jensklingenberg.ktorfit.http.Body
import de.jensklingenberg.ktorfit.http.Multipart
import de.jensklingenberg.ktorfit.http.POST
import io.ktor.client.request.forms.MultiPartFormDataContent

interface UserApi {

    @Multipart
    @POST("api/v2/user-profile/avatar")
    suspend fun changeAvatar(
        @Body body: MultiPartFormDataContent
    )

}