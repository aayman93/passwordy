package com.github.aayman93.passwordy.features.generate_password.presentation.view_model

import androidx.lifecycle.ViewModel
import com.github.aayman93.passwordy.features.generate_password.presentation.model.GeneratePasswordUiState
import com.github.aayman93.passwordy.utils.OnSliderValueChangeListener
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class GeneratePasswordViewModel @Inject constructor() : ViewModel(), OnSliderValueChangeListener {

    private val _uiState: MutableStateFlow<GeneratePasswordUiState> =
        MutableStateFlow(GeneratePasswordUiState())

    val uiState = _uiState.asStateFlow()

    override fun onSliderValueChanged(value: Int) {
        _uiState.value.passwordLength.value = value
    }
}