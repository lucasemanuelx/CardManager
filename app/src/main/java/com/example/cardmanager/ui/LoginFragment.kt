package com.example.cardmanager.ui

import android.app.AlertDialog
import android.content.Context
import android.content.SharedPreferences
import android.net.ConnectivityManager
import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.cardmanager.R
import com.example.cardmanager.databinding.FragmentLoginBinding
import com.example.cardmanager.model.LoginViewModel
import com.example.cardmanager.network.UserInfo
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class LoginFragment : Fragment() {
    private lateinit var viewModel: LoginViewModel
    private lateinit var binding: FragmentLoginBinding
    private lateinit var request: UserInfo
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(inflater, container, false)

        sharedPreferences = activity?.getSharedPreferences("Prefs", Context.MODE_PRIVATE)!!
        viewModel = LoginViewModel(sharedPreferences)

        binding.buttonShowHide.setOnClickListener {
            showHide()
        }

        binding.buttonCreateAccount.setOnClickListener {
            navigateCreateAccount()
        }

        binding.buttonLogin.setOnClickListener {
            if (isOnline()) {
                tryLogin()
            }
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        ObserveLoginIsValid()
        OberserveShouldNavigateToMyCards()
    }

    private fun ObserveLoginIsValid() {
        viewModel.loginIsValid.observe(viewLifecycleOwner) {
            if (!it) {
                dialog(
                    "Login Failed", "Your email or password is incorrect.\n" +
                            "Please try again."
                )
            }
        }
    }

    private fun OberserveShouldNavigateToMyCards() {
        viewModel.shouldNavigateHome.observe(viewLifecycleOwner) {
            if (it) {
                navigateToMyCards()
            }
        }
    }

    private fun tryLogin() {
        val currentEmail = viewModel.loginEmail.value.toString()
        val currentPassword = viewModel.loginPassword.value.toString()
        request = UserInfo(
            avatar = null,
            email = currentEmail,
            password = currentPassword,
            name = null,
            token = null
        )
        viewModel.onLogin(request)
    }

    private fun showHide() {
        if (binding.buttonShowHide.contentDescription.equals("Show")) {
            binding.loginPassword.transformationMethod =
                HideReturnsTransformationMethod.getInstance()
            binding.buttonShowHide.setImageResource(R.drawable.ic_baseline_visibility_off_24)
            binding.buttonShowHide.contentDescription = "Hide"
        } else {
            binding.loginPassword.transformationMethod = PasswordTransformationMethod.getInstance()
            binding.buttonShowHide.setImageResource(R.drawable.ic_baseline_visibility_24)
            binding.buttonShowHide.contentDescription = "Show"
        }
    }

    private fun navigateCreateAccount() {
        findNavController().navigate(R.id.action_loginFragment_to_createAccountFragment)
    }

    private fun navigateToMyCards() {
        findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
    }

    private fun dialog(title: String, message: String) {
        val textColor = context?.getColorStateList(R.color.global_black)
        val alert = MaterialAlertDialogBuilder(requireContext())
            .setTitle(title)
            .setMessage(message)
            .setCancelable(true)
            .setPositiveButton("OK") { _, _ ->
            }
            .show()

        alert.getButton(AlertDialog.BUTTON_POSITIVE).apply {
            setTextColor(textColor)
        }
    }

    fun isOnline(): Boolean {
        val connectivityManager =
            context?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val capabilities =
            connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)

        if (capabilities != null) {
            return true
        }

        dialog(
            "Login Failed", "No internet connection.\n" +
                    "Please try again later."
        )
        return false
    }
}