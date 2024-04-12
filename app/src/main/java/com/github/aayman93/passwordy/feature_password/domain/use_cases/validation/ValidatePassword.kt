package com.github.aayman93.passwordy.feature_password.domain.use_cases.validation

class ValidatePassword {

    operator fun invoke(password: String): ValidationResult {
        if (password.isBlank()) {
            return ValidationResult(
                successful = false,
                errorMessage = "Password can't be empty"
            )
        }

        return ValidationResult(
            successful = true
        )
    }
}