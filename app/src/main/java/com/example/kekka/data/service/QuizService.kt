package com.example.kekka.data.service

import com.example.kekka.domain.model.quiz.Questions
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.Response

interface QuizService {
    @GET("api.php")
    suspend fun getQuizQuestions(
        @Query("amount") amount: Int = 15,
        @Query("category") category: String
    ): Response<Questions>
}
