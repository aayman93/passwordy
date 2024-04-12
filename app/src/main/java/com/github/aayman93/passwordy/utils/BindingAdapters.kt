package com.github.aayman93.passwordy.utils

import androidx.databinding.BindingAdapter
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

interface OnSliderValueChangeListener {
    fun onSliderValueChanged(value: Int)
}