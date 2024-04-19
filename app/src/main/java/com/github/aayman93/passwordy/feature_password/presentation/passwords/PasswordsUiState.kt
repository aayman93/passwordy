package com.github.aayman93.passwordy.feature_password.presentation.passwords

import com.github.aayman93.passwordy.feature_password.domain.models.PasswordInfo

data class PasswordsUiState(
    val isLoading: Boolean = false,
    val data: List<PasswordInfo>? = null
)
