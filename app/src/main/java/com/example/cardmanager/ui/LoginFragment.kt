package com.example.cardmanager.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.cardmanager.R
import com.example.cardmanager.databinding.FragmentLoginBinding
import com.example.cardmanager.model.LoginViewModel

class LoginFragment : Fragment() {
    private lateinit var binding: FragmentLoginBinding
    private lateinit var viewModel: LoginViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        viewModel = LoginViewModel()

        binding.buttonCreateAccount.setOnClickListener {
            navigateCreateAccount()
        }


        binding.buttonLogin.setOnClickListener {
            tryLogin()
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    private fun tryLogin() {
        viewModel.onLogin()
    }

    private fun navigateCreateAccount() {
        findNavController().navigate(R.id.action_loginFragment_to_createAccountFragment)
    }
}