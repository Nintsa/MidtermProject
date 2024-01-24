package com.example.kekka.domain.repository

import com.example.kekka.data.common.Resource
import com.example.kekka.domain.model.quiz.Questions
import kotlinx.coroutines.flow.Flow

interface QuizRepository {

    suspend fun getQuiz(categoryId: String): Flow<Resource<Questions>>
}
