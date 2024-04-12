package com.github.aayman93.passwordy.feature_password.domain.use_cases.validation

data class ValidatePasswordFormUseCase(
    val validateEmail: ValidateEmail,
    val validatePassword: ValidatePassword,
    val validateWebsite: ValidateWebsite
)