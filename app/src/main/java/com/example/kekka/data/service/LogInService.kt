package com.example.kekka.data.service

import com.example.kekka.data.model.LogInDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface LogInService {

    @GET("55a15c01-1fd8-4cb3-b044-941f904bd694")
    suspend fun logIn(
        @Query("email") email: String,
        @Query("password") password: String
    ): Response<LogInDto>
}
