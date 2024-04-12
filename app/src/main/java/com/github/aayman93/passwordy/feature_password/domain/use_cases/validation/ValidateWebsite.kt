package com.github.aayman93.passwordy.feature_password.domain.use_cases.validation

class ValidateWebsite {

    operator fun invoke(website: String): ValidationResult {
        if (website.isBlank()) {
            return ValidationResult(
                successful = false,
                errorMessage = "Please add website or application name"
            )
        }

        return ValidationResult(
            successful = true
        )
    }
}