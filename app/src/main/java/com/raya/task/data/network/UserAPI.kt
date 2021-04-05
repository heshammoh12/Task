package com.raya.task.data.network

import com.raya.task.data.model.UserDetailsResponse
import com.raya.task.data.model.UserResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface UserAPI {
    @GET("users")
    suspend fun getUsers(@Query("per_page") pageNumber: String = "20"): Response<UserResponse>

    @GET("users/{id}")
    suspend fun getUserDetailes(@Path("id") userId: String): Response<UserDetailsResponse>
}