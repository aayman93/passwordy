package com.github.aayman93.passwordy.feature_password.presentation.generate_password

import com.github.aayman93.passwordy.feature_password.presentation.utils.PasswordAction

sealed interface GeneratePasswordUiEvent {
    data class ShowToast(val message: String) : GeneratePasswordUiEvent
    data class Navigate(val data: PasswordAction) : GeneratePasswordUiEvent
}