package com.example.kekka.presentation.base

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.kekka.domain.model.quiz.Result

abstract class BaseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    abstract fun onBind(data: Result)
}
