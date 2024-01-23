package com.example.kekka.presentation.screen.register

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.kekka.R
import com.example.kekka.presentation.base.BaseFragment
import com.example.kekka.databinding.FragmentRegisterBinding
import com.example.kekka.presentation.event.log_in.LogInEvent
import com.example.kekka.presentation.event.register.RegisterEvent
import com.example.kekka.presentation.screen.login.LogInViewModel


class RegisterFragment : BaseFragment<FragmentRegisterBinding>(FragmentRegisterBinding::inflate) {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.fragment_register, container, false)

    private val viewModel: RegisterViewModel by viewModels()

    override fun bind() {
    }

    override fun bindViewActionListeners() {
        binding.btnRegister.setOnClickListener {
            register()
        }
    }

    override fun bindObserves() {
        TODO("Not yet implemented")
    }

    private fun register() {
        viewModel.onEvent(
            RegisterEvent.Register(
                name = "IDK",
                email = binding.etEmail.text.toString(),
                username = binding.etFullName.text.toString(),
                password = binding.etPassword.text.toString()
            )
        )
    }
}