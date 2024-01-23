package com.example.kekka.presentation.screen.splash

import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.challenge.presentation.screen.splash.SplashViewModel
import com.example.kekka.databinding.FragmentSplashBinding
import com.example.kekka.presentation.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SplashFragment : BaseFragment<FragmentSplashBinding>(FragmentSplashBinding::inflate) {
    private val viewModel: SplashViewModel by viewModels()

    override fun bind() {

    }

    override fun bindViewActionListeners() {

    }

    override fun bindObserves() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiEvent.collect {
                    handleNavigationEvents(event = it)
                }
            }
        }
    }

    private fun handleNavigationEvents(event: SplashViewModel.SplashUiEvent) {
        when (event) {
            is SplashViewModel.SplashUiEvent.NavigateToHomePage -> findNavController().navigate(
                SplashFragmentDirections.actionSplashFragmentToHomePageFragment()
            )

            is SplashViewModel.SplashUiEvent.NavigateToChooseQuiz -> findNavController().navigate(
                SplashFragmentDirections.actionSplashFragmentToChooseQuizTypeFragment()
            )
        }
    }
}
