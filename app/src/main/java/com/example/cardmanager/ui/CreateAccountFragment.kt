package com.example.cardmanager.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.ciandt.cardmanager.mask.Mask.Companion.creditCardMask
import com.ciandt.cardmanager.mask.Mask.Companion.phoneNumberMask
import com.ciandt.cardmanager.mask.MaskWatcher
import com.example.cardmanager.R
import com.example.cardmanager.databinding.FragmentCreateAccountBinding
import com.example.cardmanager.model.CreateAccountViewModel

class CreateAccountFragment : Fragment() {
    private val viewModel: CreateAccountViewModel by viewModels()
    private lateinit var binding: FragmentCreateAccountBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate<FragmentCreateAccountBinding?>(
            inflater,
            R.layout.fragment_create_account,
            container,
            false
        )
            .apply { createAccountViewModel = viewModel }

        binding.buttonBack.setOnClickListener {
            navigateBack()
        }
        binding.buttonCreateAccount.setOnClickListener {
            navigateBack()
        }

        maskFields()

        viewModel.shouldEnableCreateAccountButton.observe(viewLifecycleOwner) { enableButton ->
            binding.buttonCreateAccount.isEnabled = enableButton
        }

        return binding.root
    }


    private fun navigateBack() {
        findNavController().navigateUp()
    }

    private fun maskFields() {
        binding.createAccountCellPhone.addTextChangedListener(
            MaskWatcher(phoneNumberMask)
        )
        binding.createAccountCreditCardNumber.addTextChangedListener(
            MaskWatcher(creditCardMask)
        )
    }
}