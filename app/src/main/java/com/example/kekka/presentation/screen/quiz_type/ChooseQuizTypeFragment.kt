package com.example.kekka.presentation.screen.quiz_type

import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.kekka.databinding.FragmentChooseQuizTypeBinding
import com.example.kekka.presentation.base.BaseFragment
import com.example.kekka.presentation.event.start_quiz.StartQuizEvent
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ChooseQuizTypeFragment :
    BaseFragment<FragmentChooseQuizTypeBinding>(FragmentChooseQuizTypeBinding::inflate) {

    private val viewModel: ChooseQuizTypeViewModel by viewModels()

    private val categories = listOf(
        Pair("", "Any Category"),
        Pair("9", "General Knowledge"),
        Pair("10", "Entertainment: Books"),
        Pair("11", "Entertainment: Films"),
        Pair("12", "Entertainment: Music"),
        Pair("13", "Entertainment: Musicals & Theatres"),
        Pair("14", "Entertainment: Television"),
        Pair("15", "Entertainment: Video Games"),
        Pair("16", "Entertainment: Board Games"),
        Pair("17", "Science & Nature"),
        Pair("18", "Science: Computers"),
        Pair("19", "Science: Mathematics"),
        Pair("20", "Mythology"),
        Pair("21", "Sports"),
        Pair("22", "Geography"),
        Pair("23", "History"),
        Pair("24", "Politics"),
        Pair("25", "Art"),
        Pair("26", "Celebrities"),
        Pair("27", "Animals"),
        Pair("28", "Vehicles"),
        Pair("29", "Entertainments: Comics"),
        Pair("30", "Science: Gadgets"),
        Pair("31", "Entertainments: Japanese Anime & Manga"),
        Pair("32", "Entertainments: Cartoons & Animations")
    )


    override fun bind() {
        binding.btnStart.setOnClickListener {
            startQuiz()
        }
    }

    override fun bindViewActionListeners() {
        binding.acNumberOfQuestions.adapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_spinner_item,
            categories.map { it.second },
        ).apply { setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item) }

        binding.acNumberOfQuestions.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    viewModel.onEvent(StartQuizEvent.SelectQuizCategory(categories[position].first))
                }

                override fun onNothingSelected(p0: AdapterView<*>?) {}
            }
    }

    override fun bindObserves() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiEvent.collect {
                    handleUiEvent(it)
                }
            }
        }
    }

    private fun handleUiEvent(event: ChooseQuizTypeViewModel.StartQuizUiEvent) {
        when (event) {
            is ChooseQuizTypeViewModel.StartQuizUiEvent.NavigateToQuiz -> findNavController().navigate(
                ChooseQuizTypeFragmentDirections.actionChooseQuizTypeFragmentToQuizFragment(
                    categoryId = event.categoryId
                )
            )
        }
    }

    private fun startQuiz() {
        viewModel.onEvent(StartQuizEvent.StartQuiz)
    }
}
