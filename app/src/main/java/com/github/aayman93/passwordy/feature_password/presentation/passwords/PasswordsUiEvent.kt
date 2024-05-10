package com.github.aayman93.passwordy.feature_password.presentation.passwords

import com.github.aayman93.passwordy.feature_password.presentation.utils.PasswordAction

sealed interface PasswordsUiEvent {
    data class ShowToast(val message: String) : PasswordsUiEvent
    data class Navigate(val data: PasswordAction) : PasswordsUiEvent
}