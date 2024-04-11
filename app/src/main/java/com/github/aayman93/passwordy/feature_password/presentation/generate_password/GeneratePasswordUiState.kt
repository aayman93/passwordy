package com.github.aayman93.passwordy.feature_password.presentation.generate_password

import kotlinx.coroutines.flow.MutableStateFlow

data class GeneratePasswordUiState(
    var password: MutableStateFlow<String> = MutableStateFlow(""),
    var passwordLength: MutableStateFlow<Int> = MutableStateFlow(8),
    var includeLowercase: MutableStateFlow<Boolean> = MutableStateFlow(true),
    var includeUppercase: MutableStateFlow<Boolean> = MutableStateFlow(false),
    var includeDigits: MutableStateFlow<Boolean> = MutableStateFlow(false),
    var includeSpecialCharacters: MutableStateFlow<Boolean> = MutableStateFlow(false)
)
