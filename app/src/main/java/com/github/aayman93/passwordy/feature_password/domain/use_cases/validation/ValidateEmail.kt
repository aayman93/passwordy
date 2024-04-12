package com.github.aayman93.passwordy.feature_password.domain.use_cases.validation

import android.util.Patterns

class ValidateEmail {

    operator fun invoke(email: String, username: String, phone: String): ValidationResult {
        if (email.isBlank()) {
            if (username.isBlank() && phone.isBlank()) {
                return ValidationResult(
                    successful = false,
                    errorMessage = "At least one of email, username and phone must not be empty"
                )
            }
        }

        if (email.isNotBlank() && !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            return ValidationResult(
                successful = false,
                errorMessage = "Please insert a valid email"
            )
        }

        return ValidationResult(
            successful = true
        )
    }
}