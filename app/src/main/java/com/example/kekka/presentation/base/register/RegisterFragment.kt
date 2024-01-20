package com.example.kekka.presentation.base.register

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.kekka.R
import com.example.kekka.presentation.base.base.BaseFragment
import com.example.kekka.databinding.FragmentRegisterBinding
import com.example.kekka.presentation.base.event.log_in.LogInEvent
import com.example.kekka.presentation.base.login.LogInViewModel


class RegisterFragment : BaseFragment<FragmentRegisterBinding>(FragmentRegisterBinding::inflate) {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_register, container, false)
    }

    private val viewModel: RegisterViewModel by viewModels()

    override fun bind() {
    }

    override fun bindViewActionListeners() {
        binding.btnRegister.setOnClickListener{
            register()
        }
    }

    override fun bindObserves() {
        TODO("Not yet implemented")
    }

    fun register(){
        viewModel.onEvent(
            LogInEvent.LogIn(
                email = binding.etEmail.text.toString(),
                password = binding.etPassword.text.toString()
            )
        )
    }


}