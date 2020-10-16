package com.cassiano.mindgeekapp.utils

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import androidx.databinding.BindingAdapter

object BindingAdapterUtil {

    @JvmStatic
    @BindingAdapter("textChanged")
    fun textChanged(editText: EditText, textChanged: TextChanged) {
        editText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(value: CharSequence?, p1: Int, p2: Int, p3: Int) {
                value?.toString()?.let {
                    textChanged.onChanged(it)
                    editText.setSelection(it.length)
                }
            }

            override fun afterTextChanged(value: Editable?) {
                value?.let {
                    textChanged.afterChanged(it.toString())
                }
            }
        })
    }
}