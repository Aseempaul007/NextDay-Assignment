package com.example.nextdayassignment.data.remote.api

import com.example.nextdayassignment.data.remote.model.UserResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface UserApi {

    @GET("users")
    suspend fun getUsers(
        @Query("page") page: Int
    ): Response<UserResponse>

}