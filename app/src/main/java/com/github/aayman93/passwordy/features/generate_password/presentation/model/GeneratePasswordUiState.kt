package com.github.aayman93.passwordy.features.generate_password.presentation.model

import kotlinx.coroutines.flow.MutableStateFlow

data class GeneratePasswordUiState(
    var password: MutableStateFlow<String> = MutableStateFlow(""),
    var passwordLength: MutableStateFlow<Int> = MutableStateFlow(8),
    var includeLowercase: MutableStateFlow<Boolean> = MutableStateFlow(false),
    var includeUppercase: MutableStateFlow<Boolean> = MutableStateFlow(false),
    var includeDigits: MutableStateFlow<Boolean> = MutableStateFlow(false),
    var includeSpecialCharacters: MutableStateFlow<Boolean> = MutableStateFlow(false)
)
