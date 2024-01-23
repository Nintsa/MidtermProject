package com.example.kekka.presentation.screen.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.kekka.R
import com.example.kekka.databinding.FragmentUserProfileBinding
import com.example.kekka.presentation.base.BaseFragment


class UserProfileFragment : BaseFragment<FragmentUserProfileBinding>(FragmentUserProfileBinding::inflate) {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_user_profile, container, false)
    }

    override fun bind() {
        TODO("Not yet implemented")
    }

    override fun bindViewActionListeners() {
        TODO("Not yet implemented")
    }

    override fun bindObserves() {
        TODO("Not yet implemented")
    }

}