package com.example.kekka.presentation.screen.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kekka.domain.usecase.datastore.SaveTokenUseCase
import com.example.kekka.domain.usecase.register.RegisterUseCase
import com.example.kekka.domain.usecase.validator.EmailValidatorUseCase
import com.example.kekka.domain.usecase.validator.PasswordValidatorUseCase
import com.example.kekka.presentation.event.register.RegisterEvent
import com.example.kekka.presentation.screen.login.LogInViewModel
import com.example.kekka.presentation.screen.state.log_in.LogInState
import com.example.kekka.presentation.screen.state.register.RegisterState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor() : ViewModel() {

    private val _registerState = MutableStateFlow(RegisterState())
    val registerState: SharedFlow<RegisterState> = _registerState.asStateFlow()

    private val _uiEvent = MutableSharedFlow<RegisterUiEvent>()
    val uiEvent: SharedFlow<RegisterUiEvent> get() = _uiEvent

    fun onEvent(event: RegisterEvent) {
        viewModelScope.launch {
            when (event) {
                is RegisterEvent.ResetErrorMessage -> updateErrorMessage(message = null)
                RegisterEvent.Registered -> _uiEvent.emit(RegisterUiEvent.NavigateToQuiz)
            }
        }
    }

    fun showError(message: String) {
        _registerState.update {
            it.copy(errorMessage = message)
        }
    }

    private fun updateErrorMessage(message: String?) {
    }

    sealed interface RegisterUiEvent {
        data object NavigateToQuiz : RegisterUiEvent
    }
}
