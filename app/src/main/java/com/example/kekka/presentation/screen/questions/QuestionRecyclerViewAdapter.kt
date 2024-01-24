package com.example.kekka.presentation.screen.questions

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.RadioButton
import androidx.recyclerview.widget.RecyclerView
import com.example.kekka.databinding.QuestionRecyclerviewBinding
import com.example.kekka.databinding.QuestionTrueFalseRecyclerviewBinding
import com.example.kekka.domain.model.quiz.Result
import com.example.kekka.presentation.base.BaseViewHolder

class QuestionRecyclerViewAdapter(
    private val items: List<Result>,
    private val onCorrect: () -> Unit,
) : RecyclerView.Adapter<BaseViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return when (viewType) {
            1 -> TrueFalseViewHolder(
                QuestionTrueFalseRecyclerviewBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )

            else -> MultiQuestionViewHolder(
                QuestionRecyclerviewBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (items[position].type == "boolean") 1 else 0
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        holder.onBind(items[position])
    }

    class TrueFalseViewHolder(private val binding: QuestionTrueFalseRecyclerviewBinding) :
        BaseViewHolder(binding.root) {
        override fun onBind(data: Result) {
            binding.tvQuestion.text = data.question
            binding.tvTrue.text = data.correctAnswer
            binding.tvFalse.text = data.incorrectAnswers.first()
        }
    }

    inner class MultiQuestionViewHolder(private val binding: QuestionRecyclerviewBinding) :
        BaseViewHolder(binding.root) {
        override fun onBind(data: Result) {
            val allAnswers = (data.incorrectAnswers + data.correctAnswer).toMutableList()
            allAnswers.shuffle()
            binding.tvQuestion.text = data.question
            binding.tvFirstAnswer.text = allAnswers[0]
            binding.tvSecondAnswer.text = allAnswers[1]
            binding.tvThirdAnswer.text = allAnswers[2]
            binding.tvFourthAnswer.text = allAnswers[3]
            binding.rgAnswers.setOnCheckedChangeListener { view, _ ->
                val answer = view.findViewById<RadioButton>(view.checkedRadioButtonId).text
                if (answer.toString() == data.correctAnswer) onCorrect()
            }
        }
    }
}
