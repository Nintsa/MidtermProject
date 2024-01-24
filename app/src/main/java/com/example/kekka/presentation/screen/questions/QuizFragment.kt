package com.example.kekka.presentation.screen.questions

import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kekka.databinding.FragmentQuizBinding
import com.example.kekka.presentation.base.BaseFragment
import com.example.kekka.presentation.screen.state.quiz.QuizState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class QuizFragment : BaseFragment<FragmentQuizBinding>(FragmentQuizBinding::inflate) {

    private val viewModel: QuizViewModel by viewModels()

    override fun bind() {
    }

    override fun bindViewActionListeners() {
    }

    override fun bindObserves() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.logInState.collect {
                    handleState(it)
                }
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.correctAnswersCount.collect { result ->
                    binding.btnSubmit.setOnClickListener {
                        result
                    }
                }
            }
        }
    }

    private fun handleState(state: QuizState) {
        if (state.questions.isNotEmpty()) {
            binding.recyclerView.layoutManager = LinearLayoutManager(context)
            binding.recyclerView.adapter = QuestionRecyclerViewAdapter(state.questions) {
                viewModel.score()
            }
        }
    }
}
