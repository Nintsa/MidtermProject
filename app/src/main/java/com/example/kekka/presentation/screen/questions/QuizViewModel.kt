package com.example.kekka.presentation.screen.questions

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kekka.data.common.Resource
import com.example.kekka.domain.usecase.quiz.GetQuizUseCase
import com.example.kekka.presentation.screen.state.quiz.QuizState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QuizViewModel @Inject constructor(
    private val getQuizUseCase: GetQuizUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _logInState = MutableStateFlow(QuizState())
    val logInState: SharedFlow<QuizState> = _logInState.asStateFlow()

    private val _correctAnswersCount = MutableStateFlow(0)
    val correctAnswersCount: SharedFlow<Int> = _correctAnswersCount.asStateFlow()

    init {
        loadQuiz(requireNotNull(savedStateHandle.get<String>("categoryId")))
    }

    fun score() {
        _correctAnswersCount.value += 1
    }

    private fun loadQuiz(categoryId: String) {
        viewModelScope.launch {
            getQuizUseCase(categoryId).collect { result ->
                when (result) {
                    is Resource.Error -> {}
                    is Resource.Loading -> {}
                    is Resource.Success -> {
                        _logInState.update {
                            it.copy(questions = result.data.results)
                        }
                    }
                }
            }
        }
    }
}
