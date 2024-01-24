package com.example.kekka.data.repository

import com.example.kekka.data.common.HandleResponse
import com.example.kekka.data.common.Resource
import com.example.kekka.data.mapper.asResource
import com.example.kekka.data.service.QuizService
import com.example.kekka.domain.model.quiz.Questions
import com.example.kekka.domain.repository.QuizRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class QuizRepositoryImpl @Inject constructor(
    private val quizService: QuizService,
    private val handleResponse: HandleResponse,
) : QuizRepository {

    override suspend fun getQuiz(categoryId: String): Flow<Resource<Questions>> {
        return handleResponse.safeApiCall {
            quizService.getQuizQuestions(category = categoryId)
        }.asResource { it }
    }
}
