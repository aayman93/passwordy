package com.github.aayman93.passwordy.feature_password.presentation.save_password

import kotlinx.coroutines.flow.MutableStateFlow

data class SavePasswordUiState(
    val email: MutableStateFlow<String> = MutableStateFlow(""),
    val username: MutableStateFlow<String> = MutableStateFlow(""),
    val phone: MutableStateFlow<String> = MutableStateFlow(""),
    val website: MutableStateFlow<String> = MutableStateFlow(""),
    var password: MutableStateFlow<String> = MutableStateFlow(""),
    
    val id: Int? = null,

    val emailError: MutableStateFlow<String?> = MutableStateFlow(null),
    val websiteError: MutableStateFlow<String?> = MutableStateFlow(null),
    val passwordError: MutableStateFlow<String?> = MutableStateFlow(null)
)
