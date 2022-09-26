package com.example.cardmanager.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.cardmanager.R
import com.example.cardmanager.databinding.FragmentCreateAccountBinding
import com.example.cardmanager.mask.Mask.Companion.creditCardMask
import com.example.cardmanager.mask.Mask.Companion.phoneNumberMask
import com.example.cardmanager.mask.MaskWatcher

class CreateAccountFragment : Fragment() {
    private lateinit var binding: FragmentCreateAccountBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCreateAccountBinding.inflate(inflater, container, false)

        binding.buttonBack.setOnClickListener {
            navigateBack()
        }
        binding.buttonCreateAccount.setOnClickListener {
            navigateBack()
        }

        maskFields()
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