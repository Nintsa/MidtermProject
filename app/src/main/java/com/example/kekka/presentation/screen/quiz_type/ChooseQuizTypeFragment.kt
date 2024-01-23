package com.example.kekka.presentation.screen.quiz_type

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import com.example.kekka.R
import com.example.kekka.databinding.FragmentChooseQuizTypeBinding
import com.example.kekka.presentation.base.BaseFragment

class ChooseQuizTypeFragment :
    BaseFragment<FragmentChooseQuizTypeBinding>(FragmentChooseQuizTypeBinding::inflate) {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_choose_quiz_type, container, false)

        setData()
    }

    fun setData() {
        setCategoriesData()
        setQuestionsQuantity()
        setTypeData()
        setDifficultyData()
    }

    fun setCategoriesData() {
        val categories = listOf(
            "Any Category",
            "General Knowledge",
            "Entertainment: Books",
            "Entertainment: Films",
            "Entertainment: Music",
            "Entertainment: Musicals & Theatres",
            "Entertainment: Television",
            "Entertainment: Video Games",
            "Entertainment: Board Games",
            "Science & Nature",
            "Science: Computers",
            "Science: Mathematics",
            "Mythology",
            "Sports",
            "Geography",
            "History",
            "Politics",
            "Art",
            "Celebrities",
            "Animals",
            "Vehicles",
            "Entertainments: Comics",
            "Science: Gadgets",
            "Entertainments: Japanese Anime & Manga",
            "Entertainments: Cartoons & Animations"
        )

        val autocomplete: AutoCompleteTextView = binding.acCategory

        val categoriesAdapter = ArrayAdapter(
            requireContext(), R.layout.fragment_choose_quiz_type, categories
        )
        autocomplete.setAdapter(categoriesAdapter)

        autocomplete.onItemClickListener =
            AdapterView.OnItemClickListener { AdapterView, view, i, l ->

                val categorySelected = AdapterView.getItemAtPosition(i).toString()
            }
    }

    fun setQuestionsQuantity() {

        val questionsQuantity =
            listOf("5", "10", "20", "30", "40", "50")
        val autocomplete: AutoCompleteTextView = binding.acNumberOfQuestions

        val questionsQuantityAdapter = ArrayAdapter(
            requireContext(), R.layout.fragment_choose_quiz_type, questionsQuantity
        )
        autocomplete.setAdapter(questionsQuantityAdapter)

        autocomplete.onItemClickListener =
            AdapterView.OnItemClickListener { AdapterView, view, i, l ->

                val quantity = AdapterView.getItemAtPosition(i).toString()
            }
    }

    fun setDifficultyData() {
        val difficultyData = listOf("Any Difficulty", "Easy", "Medium", "Hard")

        val autocomplete: AutoCompleteTextView = binding.acDifficulty

        val difficultyAdapter = ArrayAdapter(
            requireContext(), R.layout.fragment_choose_quiz_type, difficultyData
        )

        autocomplete.setAdapter(difficultyAdapter)

        autocomplete.onItemClickListener =
            AdapterView.OnItemClickListener { AdapterView, vies, i, l ->
                val difficulty = AdapterView.getItemAtPosition(i).toString()
            }
    }

    fun setTypeData() {
        val types = listOf("Any Type", "Multiple Choice", "True/False")

        val autocomplete: AutoCompleteTextView = binding.acType

        val typeAdapter = ArrayAdapter(
            requireContext(), R.layout.fragment_choose_quiz_type, types
        )

        autocomplete.setAdapter(typeAdapter)

        autocomplete.onItemClickListener =
            AdapterView.OnItemClickListener { AdapterView, view, i, l ->

                val type = AdapterView.getItemAtPosition(i).toString()
            }
    }

    override fun bind() {
    }

    override fun bindViewActionListeners() {
    }

    override fun bindObserves() {
    }


}