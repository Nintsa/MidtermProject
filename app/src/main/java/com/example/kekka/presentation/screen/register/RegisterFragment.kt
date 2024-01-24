package com.example.kekka.presentation.screen.register

import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.kekka.presentation.base.BaseFragment
import com.example.kekka.databinding.FragmentRegisterBinding
import com.example.kekka.presentation.event.register.RegisterEvent
import com.example.kekka.presentation.screen.view.showSnackBar
import com.example.kekka.presentation.screen.state.register.RegisterState
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import kotlinx.coroutines.launch

class RegisterFragment : BaseFragment<FragmentRegisterBinding>(FragmentRegisterBinding::inflate) {

    private val viewModel: RegisterViewModel by viewModels()

    private lateinit var auth: FirebaseAuth

    override fun bind() {
        auth = Firebase.auth
    }

    override fun bindViewActionListeners() {
        binding.btnRegister.setOnClickListener {
            register()
        }
    }

    override fun bindObserves() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.registerState.collect {
                    handleRegistrationState(it)
                }
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiEvent.collect {
                    handleUiEvent(it)
                }
            }
        }
    }

    private fun handleRegistrationState(state: RegisterState) {
        state.errorMessage?.let {
            binding.root.showSnackBar(message = it)
        }
    }

    private fun handleUiEvent(event: RegisterViewModel.RegisterUiEvent) {
        when (event) {
            RegisterViewModel.RegisterUiEvent.NavigateToQuiz -> findNavController().navigate(
                RegisterFragmentDirections.actionRegisterFragmentToChooseQuizTypeFragment()
            )
        }
    }

    private fun register() {
        val email = binding.etEmail.text.toString()
        val password = binding.etPassword.text.toString()

        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    viewModel.onEvent(RegisterEvent.Registered)
                } else {
                    viewModel.showError("Could not create user!")
                }
            }
    }
}
