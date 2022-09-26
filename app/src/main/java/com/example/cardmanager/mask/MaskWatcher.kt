package com.example.cardmanager.mask

import android.text.Editable
import android.text.TextWatcher

//https://medium.com/@diegoy_kuri/masks-in-android-edit-text-fields-33a2fd47f1af
class MaskWatcher(private val mask: String): TextWatcher {
    private var isRunning = false
    private var isDeleting = false


    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        isDeleting = count > after;
    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
    }

    override fun afterTextChanged(editable: Editable) {
        if (isRunning || isDeleting) {
            return
        }
        isRunning = true
        val editableLength = editable.length
        if (editableLength < mask.length) {
            if (mask[editableLength] != '#') {
                editable.append(mask[editableLength])
            } else if (mask[editableLength - 1] != '#') {
                editable.insert(editableLength - 1, mask, editableLength - 1, editableLength)
            }
        }
        isRunning = false
    }
}