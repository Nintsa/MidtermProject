package com.example.kekka.presentation.screen.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kekka.presentation.event.log_in.LogInEvent
import com.example.kekka.presentation.screen.state.log_in.LogInState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LogInViewModel @Inject constructor() : ViewModel() {

    private val _logInState = MutableStateFlow(LogInState())
    val logInState: SharedFlow<LogInState> = _logInState.asStateFlow()

    private val _uiEvent = MutableSharedFlow<LogInUiEvent>()
    val uiEvent: SharedFlow<LogInUiEvent> get() = _uiEvent

    fun onEvent(event: LogInEvent) {
        viewModelScope.launch {
            when (event) {
                is LogInEvent.Register ->
                    _uiEvent.emit(LogInUiEvent.NavigateToRegistration)
            }
        }
    }

    fun showError(errorMessage: String) {
        _logInState.update {
            it.copy(errorMessage = errorMessage)
        }
    }

    fun auth() {
        viewModelScope.launch {
            _uiEvent.emit(LogInUiEvent.NavigateToQuiz)
        }
    }

    sealed interface LogInUiEvent {
        data object NavigateToQuiz : LogInUiEvent
        data object NavigateToRegistration : LogInUiEvent
    }

}