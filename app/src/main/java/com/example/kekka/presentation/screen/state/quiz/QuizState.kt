package com.example.kekka.presentation.screen.state.quiz

import com.example.kekka.domain.model.quiz.Result

data class QuizState(
    val questions: List<Result> = emptyList(),
    val correctAnswersCount: Int = 0
)
