package com.github.aayman93.passwordy.feature_password.presentation.save_password

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.aayman93.passwordy.feature_password.domain.models.PasswordInfo
import com.github.aayman93.passwordy.feature_password.domain.use_cases.save_password.SavePasswordInfoUseCase
import com.github.aayman93.passwordy.feature_password.domain.use_cases.validation.ValidatePasswordFormUseCase
import com.github.aayman93.passwordy.feature_password.presentation.utils.PasswordAction
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SavePasswordViewModel @Inject constructor(
    private val validatePasswordFormUseCase: ValidatePasswordFormUseCase,
    private val savePasswordInfoUseCase: SavePasswordInfoUseCase
) : ViewModel() {

    private val _state: MutableStateFlow<SavePasswordUiState> =
        MutableStateFlow(SavePasswordUiState())

    val state = _state.asStateFlow()

    private val eventChannel = Channel<SavePasswordUiEvent>()
    val eventFlow = eventChannel.receiveAsFlow()

    fun setInitialValues(passwordAction: PasswordAction) {
        when (passwordAction) {
            is PasswordAction.SavePassword -> {
                _state.value = _state.value.copy(
                    password = MutableStateFlow(passwordAction.data)
                )
            }
            PasswordAction.AddPassword -> Unit
        }
    }

    fun onSaveButtonClicked() {
        val emailResult = validatePasswordFormUseCase.validateEmail(
            email = _state.value.email.value,
            username = _state.value.username.value,
            phone = _state.value.phone.value
        )
        val websiteResult = validatePasswordFormUseCase.validateWebsite(_state.value.website.value)
        val passwordResult =
            validatePasswordFormUseCase.validatePassword(_state.value.password.value)

        _state.value = _state.value.copy(
            emailError = MutableStateFlow(emailResult.errorMessage),
            websiteError = MutableStateFlow(websiteResult.errorMessage),
            passwordError = MutableStateFlow(passwordResult.errorMessage)
        )

        val hasError = listOf(
            emailResult,
            websiteResult,
            passwordResult
        ).any { !it.successful }

        if (hasError) return

        viewModelScope.launch {
            val passwordInfo = PasswordInfo(
                email = _state.value.email.value,
                username = _state.value.username.value,
                phone = _state.value.phone.value,
                website = _state.value.website.value,
                password = _state.value.password.value,
                id = _state.value.id
            )
            val result = savePasswordInfoUseCase(passwordInfo)
            _state.value = _state.value.copy(id = result.toInt())
            eventChannel.send(SavePasswordUiEvent.ShowToast("Password saved successfully!"))
        }
    }
}