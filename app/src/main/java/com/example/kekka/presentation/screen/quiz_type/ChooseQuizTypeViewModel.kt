package com.example.kekka.presentation.screen.quiz_type

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kekka.presentation.event.start_quiz.StartQuizEvent
import com.example.kekka.presentation.screen.state.start_quiz.StartQuizState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChooseQuizTypeViewModel @Inject constructor() : ViewModel() {

    private val _startQuizState = MutableStateFlow(StartQuizState())
    val startQuizState: SharedFlow<StartQuizState> = _startQuizState.asStateFlow()

    private val _uiEvent = MutableSharedFlow<StartQuizUiEvent>()
    val uiEvent: SharedFlow<StartQuizUiEvent> get() = _uiEvent

    fun onEvent(event: StartQuizEvent) {
        viewModelScope.launch {
            when (event) {
                is StartQuizEvent.SelectQuizCategory -> _startQuizState.update {
                    it.copy(selectedCategory = event.categoryId)
                }

                StartQuizEvent.StartQuiz -> _uiEvent.emit(
                    StartQuizUiEvent.NavigateToQuiz(
                        _startQuizState.value.selectedCategory
                    )
                )
            }
        }
    }

    sealed interface StartQuizUiEvent {
        data class NavigateToQuiz(val categoryId: String) : StartQuizUiEvent
    }
}
