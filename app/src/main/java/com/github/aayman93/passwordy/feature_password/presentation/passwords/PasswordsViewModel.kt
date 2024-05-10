package com.github.aayman93.passwordy.feature_password.presentation.passwords

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.aayman93.passwordy.feature_password.domain.models.PasswordInfo
import com.github.aayman93.passwordy.feature_password.domain.use_cases.copy_password.CopyToClipboardUseCase
import com.github.aayman93.passwordy.feature_password.domain.use_cases.delete_password.DeletePasswordInfoUseCase
import com.github.aayman93.passwordy.feature_password.domain.use_cases.get_passwords.GetPasswordsUseCase
import com.github.aayman93.passwordy.feature_password.presentation.utils.PasswordAction
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PasswordsViewModel @Inject constructor(
    private val getPasswordsUseCase: GetPasswordsUseCase,
    private val copyToClipboardUseCase: CopyToClipboardUseCase,
    private val deletePasswordInfoUseCase: DeletePasswordInfoUseCase
) : ViewModel() {

    private val _state: MutableStateFlow<PasswordsUiState> =
        MutableStateFlow(PasswordsUiState())

    val state = _state.asStateFlow()

    private val eventChannel = Channel<PasswordsUiEvent>()
    val eventFlow = eventChannel.receiveAsFlow()

    init {
        getPasswords()
    }

    private fun getPasswords() {
        _state.value = _state.value.copy(isLoading = true)

        viewModelScope.launch {
            getPasswordsUseCase().collect { notes ->
                _state.value = _state.value.copy(
                    data = notes,
                    isLoading = false
                )
            }
        }
    }

    fun onCopyButtonClicked(password: String) {
        viewModelScope.launch {
            if (copyToClipboardUseCase(password)) {
                eventChannel.send(PasswordsUiEvent.ShowToast("Password copied to clipboard"))
            }
        }
    }

    fun onDeleteButtonClicked(passwordInfo: PasswordInfo) {
        viewModelScope.launch {
            deletePasswordInfoUseCase(passwordInfo)
        }
    }

    fun onItemClicked(passwordInfo: PasswordInfo) {
        viewModelScope.launch {
            eventChannel.send(
                PasswordsUiEvent.Navigate(
                    PasswordAction.EditPassword(
                        data = passwordInfo
                    )
                )
            )
        }
    }
}