package com.example.kekka.presentation.event.start_quiz

sealed interface StartQuizEvent {

    data class SelectQuizCategory(val categoryId: String) : StartQuizEvent

    data object StartQuiz : StartQuizEvent
}
