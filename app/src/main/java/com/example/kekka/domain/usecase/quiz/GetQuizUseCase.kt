package com.example.kekka.domain.usecase.quiz

import com.example.kekka.domain.repository.QuizRepository
import javax.inject.Inject

class GetQuizUseCase @Inject constructor(
    private val quizRepository: QuizRepository
) {
    suspend operator fun invoke(categoryId: String) =
        quizRepository.getQuiz(categoryId)
}
