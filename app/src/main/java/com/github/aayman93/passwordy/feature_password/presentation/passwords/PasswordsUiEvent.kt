package com.github.aayman93.passwordy.feature_password.presentation.passwords

sealed interface PasswordsUiEvent {
    data class ShowToast(val message: String) : PasswordsUiEvent
}