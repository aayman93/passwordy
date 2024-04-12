package com.github.aayman93.passwordy.feature_password.domain.use_cases.generate_password

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.random.Random

@Singleton
class GeneratePasswordUseCase @Inject constructor() {

    private val lowercaseChars = "abcdefghijklmnopqrstuvwxyz"
    private val uppercaseChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
    private val digits = "0123456789"
    private val specialCharacters = "@!\$%#&"

    suspend operator fun invoke(
        passwordLength: Int,
        includeLowercase: Boolean,
        includeUppercase: Boolean,
        includeDigits: Boolean,
        includeSpecialCharacters: Boolean
    ): String = withContext(Dispatchers.Default) {
        val charPool = StringBuilder()
        val passwordBuilder = StringBuilder()

        if (includeLowercase) {
            charPool.append(lowercaseChars)
            passwordBuilder.append(lowercaseChars[Random.nextInt(lowercaseChars.length)])
        }
        if (includeUppercase) {
            charPool.append(uppercaseChars)
            passwordBuilder.append(uppercaseChars[Random.nextInt(uppercaseChars.length)])
        }
        if (includeDigits) {
            charPool.append(digits)
            passwordBuilder.append(digits[Random.nextInt(digits.length)])
        }
        if (includeSpecialCharacters) {
            charPool.append(specialCharacters)
            passwordBuilder.append(specialCharacters[Random.nextInt(specialCharacters.length)])
        }

        if (charPool.isEmpty()) {
            throw IllegalArgumentException("At least one character type must be included.")
        }

        repeat(passwordLength - passwordBuilder.length) {
            passwordBuilder.append(charPool[Random.nextInt(charPool.length)])
        }

        return@withContext passwordBuilder.toString().toCharArray().apply {
            shuffle()
        }.joinToString("")
    }
}