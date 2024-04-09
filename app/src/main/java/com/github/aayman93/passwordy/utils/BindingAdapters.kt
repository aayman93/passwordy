package com.github.aayman93.passwordy.utils

import androidx.databinding.BindingAdapter
import com.google.android.material.slider.Slider

@BindingAdapter(value = ["onValueChanges"])
fun setOnValueChangeListener(slider: Slider, listener: OnSliderValueChangeListener) {

    slider.addOnChangeListener { _: Slider?, value: Float, _: Boolean ->
        listener.onSliderValueChanged(value.toInt())
    }
}

interface OnSliderValueChangeListener {
    fun onSliderValueChanged(value: Int)
}