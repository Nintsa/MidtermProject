package com.example.kekka.presentation.screen.login

import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.kekka.presentation.base.BaseFragment
import com.example.kekka.databinding.FragmentHomePageBinding
import com.example.kekka.presentation.event.log_in.LogInEvent
import com.example.kekka.presentation.screen.view.showSnackBar
import com.example.kekka.presentation.screen.state.log_in.LogInState
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomePageFragment : BaseFragment<FragmentHomePageBinding>(FragmentHomePageBinding::inflate) {

    private val viewModel: LogInViewModel by viewModels()

    private lateinit var auth: FirebaseAuth

    override fun bind() {
        auth = Firebase.auth
    }

    override fun bindViewActionListeners() {
        binding.btnLogin.setOnClickListener {
            logIn()
        }
        binding.btnRegister.setOnClickListener {
            viewModel.onEvent(LogInEvent.Register)
        }
    }

    override fun bindObserves() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.logInState.collect {
                    handleLogInState(logInState = it)
                }
            }
        }
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiEvent.collect {
                    handleNavigationEvents(event = it)
                }
            }
        }
    }

    private fun logIn() {
        val email = binding.etEmail.text.toString()
        val password = binding.etPassword.text.toString()
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    viewModel.auth()
                } else {
                    viewModel.showError("User not found")
                }
            }
    }

    private fun handleLogInState(logInState: LogInState) {
        binding.loaderInclude.loaderContainer.visibility =
            if (logInState.isLoading) View.VISIBLE else View.GONE

        logInState.errorMessage?.let {
            binding.root.showSnackBar(message = it)
        }
    }

    private fun handleNavigationEvents(event: LogInViewModel.LogInUiEvent) {
        when (event) {
            is LogInViewModel.LogInUiEvent.NavigateToQuiz -> findNavController().navigate(
                HomePageFragmentDirections.actionHomePageFragmentToChooseQuizTypeFragment()
            )

            LogInViewModel.LogInUiEvent.NavigateToRegistration -> findNavController().navigate(
                HomePageFragmentDirections.actionHomePageFragmentToRegisterFragment()
            )
        }
    }
}