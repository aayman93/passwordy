package com.github.aayman93.passwordy.feature_password.domain.use_cases.validation

data class ValidationResult(
    val successful: Boolean,
    val errorMessage: String? = null
)