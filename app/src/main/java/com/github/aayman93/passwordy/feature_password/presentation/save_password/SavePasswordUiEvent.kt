package com.github.aayman93.passwordy.feature_password.presentation.save_password

sealed interface SavePasswordUiEvent {
    data object PasswordSaved : SavePasswordUiEvent
}