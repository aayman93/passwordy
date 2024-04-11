package com.github.aayman93.passwordy.feature_password.presentation.generate_password

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.aayman93.passwordy.feature_password.domain.use_cases.CopyToClipboardUseCase
import com.github.aayman93.passwordy.feature_password.domain.use_cases.GeneratePasswordUseCase
import com.github.aayman93.passwordy.feature_password.presentation.utils.PasswordAction
import com.github.aayman93.passwordy.utils.OnSliderValueChangeListener
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GeneratePasswordViewModel @Inject constructor(
    private val generatePasswordUseCase: GeneratePasswordUseCase,
    private val copyToClipboardUseCase: CopyToClipboardUseCase
) : ViewModel(), OnSliderValueChangeListener {

    private val _state: MutableStateFlow<GeneratePasswordUiState> =
        MutableStateFlow(GeneratePasswordUiState())

    val state = _state.asStateFlow()

    private val eventChannel = Channel<GeneratePasswordUiEvent>()
    val eventFlow = eventChannel.receiveAsFlow()

    override fun onSliderValueChanged(value: Int) {
        _state.value.passwordLength.value = value
    }

    fun onGenerateButtonClicked() {
        viewModelScope.launch {
            try {
                val result = generatePasswordUseCase(
                    passwordLength = _state.value.passwordLength.value,
                    includeLowercase = _state.value.includeLowercase.value,
                    includeUppercase = _state.value.includeUppercase.value,
                    includeDigits = _state.value.includeDigits.value,
                    includeSpecialCharacters = _state.value.includeSpecialCharacters.value
                )
                _state.value.password.value = result
            } catch (e: Exception) {
                eventChannel.send(GeneratePasswordUiEvent.ShowToast(e.message ?: "Unknown error"))
            }
        }
    }

    fun onCopyButtonClicked() {
        viewModelScope.launch {
            if (copyToClipboardUseCase(_state.value.password.value)) {
                eventChannel.send(GeneratePasswordUiEvent.ShowToast("Password copied to clipboard"))
            }
        }
    }

    fun onSaveButtonClicked() {
        viewModelScope.launch {
            eventChannel.send(
                GeneratePasswordUiEvent.Navigate(
                    PasswordAction.SavePassword(
                        data = _state.value.password.value
                    )
                )
            )
        }
    }
}