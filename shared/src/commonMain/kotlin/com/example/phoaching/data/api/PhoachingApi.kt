package com.example.phoaching.data.api

import com.example.phoaching.data.models.BaseDataResponse
import com.example.phoaching.data.models.phoaching.DetailPhoachingResponse
import com.example.phoaching.data.models.phoaching.PhoachingLessonRequest
import com.example.phoaching.data.models.phoaching.PhoachingLessonResponse
import com.example.phoaching.data.models.phoaching.PhoachingResponse
import com.example.phoaching.data.models.phoaching.TaskDetailResponse
import com.example.phoaching.data.models.phoaching.UpdatePhoachingRequest
import com.example.phoaching.data.models.phoaching.UrlResponse
import de.jensklingenberg.ktorfit.http.Body
import de.jensklingenberg.ktorfit.http.DELETE
import de.jensklingenberg.ktorfit.http.GET
import de.jensklingenberg.ktorfit.http.POST
import de.jensklingenberg.ktorfit.http.PUT
import de.jensklingenberg.ktorfit.http.Path

interface PhoachingApi {
    @GET("api/v2/my-phoachings")
    suspend fun getPhoachings(): BaseDataResponse<List<PhoachingResponse>>

    @GET("api/v2/phoaching-purchases")
    suspend fun getAuthorPhoachings(): BaseDataResponse<List<PhoachingResponse>>

    @GET("api/v2/my-phoachings/{id}")
    suspend fun getPhoachingById(@Path("id") id: String): BaseDataResponse<DetailPhoachingResponse>

    @GET("api/v2/phoaching-purchases/{id}")
    suspend fun getAuthorPhoachingById(@Path("id") id: String): BaseDataResponse<DetailPhoachingResponse>

    @PUT("api/v2/my-phoachings/{id}")
    suspend fun updatePhoaching(@Path("id") id: String, @Body request: UpdatePhoachingRequest)

    @DELETE("api/v2/my-phoachings/{id}")
    suspend fun deletePhoaching(@Path("id") id: String)

    @POST("api/v2/my-phoachings")
    suspend fun createPhoaching(@Body request: UpdatePhoachingRequest)

    @POST("api/v2/my-phoachings/{id}/publish")
    suspend fun linkPhoaching(@Path("id") id: String): BaseDataResponse<UrlResponse>

    @POST("api/v2/my-phoachings/{id}/copy")
    suspend fun copyPhoaching(@Path("id") id: String)

    @GET("api/v2/my-phoachings/{phoachingId}/tasks")
    suspend fun getPhoachingTasks(@Path("phoachingId") phoachingId: String): BaseDataResponse<List<PhoachingLessonResponse>>

    @GET("api/v2/my-phoachings/{phoachingId}/tasks/{taskId}")
    suspend fun getTaskById(
        @Path("phoachingId") phoachingId: String,
        @Path("taskId") taskId: String
    ): BaseDataResponse<TaskDetailResponse>

    @DELETE("api/v2/my-phoachings/{phoachingId}/tasks/{taskId}")
    suspend fun deleteTask(
        @Path("phoachingId") phoachingId: String,
        @Path("taskId") taskId: String
    ): BaseDataResponse<Unit>


    @POST("api/v2/my-phoachings/{phoachingId}/tasks")
    suspend fun createPhoachingLesson(
        @Path("phoachingId") phoachingId: String,
        @Body phoachingLessonRequest: PhoachingLessonRequest
    ): BaseDataResponse<Unit>

    @PUT("api/v2/my-phoachings/{phoachingId}/tasks/{taskId}")
    suspend fun updatePhoachingLesson(
        @Path("taskId") taskId: String,
        @Path("phoachingId") phoachingId: String,
        @Body phoachingLessonRequest: PhoachingLessonRequest
    ): BaseDataResponse<Unit>

    @GET("api/v2/phoaching-purchases/{id}")
    suspend fun phoachingPurchase(@Path("id") phoachingId: String)

}