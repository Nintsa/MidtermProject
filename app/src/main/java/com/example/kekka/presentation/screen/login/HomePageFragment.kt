package com.example.kekka.presentation.screen.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.kekka.R
import com.example.kekka.presentation.base.BaseFragment
import com.example.kekka.databinding.FragmentHomePageBinding
import com.example.kekka.presentation.event.log_in.LogInEvent
import com.example.kekka.presentation.view.showSnackBar
import com.example.kekka.state.log_in.LogInState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomePageFragment : BaseFragment<FragmentHomePageBinding>(FragmentHomePageBinding::inflate) {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home_page, container, false)
    }

    private val viewModel: LogInViewModel by viewModels()

    override fun bind() {
    }

    override fun bindViewActionListeners() {
        binding.btnLogin.setOnClickListener {
            logIn()
        }
        binding.btnRegister.setOnClickListener{

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
        viewModel.onEvent(
            LogInEvent.LogIn(
                email = binding.etEmail.text.toString(),
                password = binding.etPassword.text.toString()
            )
        )
    }

    private fun handleLogInState(logInState: LogInState) {
        binding.loaderInclude.loaderContainer.visibility =
            if (logInState.isLoading) View.VISIBLE else View.GONE

        logInState.errorMessage?.let {
            binding.root.showSnackBar(message = it)
            viewModel.onEvent(LogInEvent.ResetErrorMessage)
        }
    }

    private fun handleNavigationEvents(event: LogInViewModel.LogInUiEvent) {
        when (event) {
            is LogInViewModel.LogInUiEvent.NavigateToConnections -> findNavController().navigate(
                HomePageFragmentDirections.actionHomePageFragmentToChooseQuizTypeFragment()
            )
        }
    }

}