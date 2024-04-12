package com.github.aayman93.passwordy.feature_password.presentation.generate_password

import kotlinx.coroutines.flow.MutableStateFlow

data class GeneratePasswordUiState(
    val password: MutableStateFlow<String> = MutableStateFlow(""),
    val passwordLength: MutableStateFlow<Int> = MutableStateFlow(8),
    val includeLowercase: MutableStateFlow<Boolean> = MutableStateFlow(true),
    val includeUppercase: MutableStateFlow<Boolean> = MutableStateFlow(false),
    val includeDigits: MutableStateFlow<Boolean> = MutableStateFlow(false),
    val includeSpecialCharacters: MutableStateFlow<Boolean> = MutableStateFlow(false)
)
