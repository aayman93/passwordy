package com.github.aayman93.passwordy.utils

import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.github.aayman93.passwordy.feature_password.domain.models.PasswordInfo
import com.google.android.material.slider.Slider
import com.google.android.material.textfield.TextInputLayout

@BindingAdapter(value = ["onValueChanges"])
fun setOnValueChangeListener(slider: Slider, listener: OnSliderValueChangeListener) {

    slider.addOnChangeListener { _: Slider?, value: Float, _: Boolean ->
        listener.onSliderValueChanged(value.toInt())
    }
}

@BindingAdapter(value = ["errorText"])
fun setErrorText(view: TextInputLayout, errorMessage: String?) {
    view.error = errorMessage
}

@BindingAdapter(value = ["setLogin"])
fun TextView.setLoginText(passwordInfo: PasswordInfo) {
    this.text = passwordInfo.email.ifBlank {
        passwordInfo.username.ifBlank {
            passwordInfo.phone
        }
    }
}

interface OnSliderValueChangeListener {
    fun onSliderValueChanged(value: Int)
}