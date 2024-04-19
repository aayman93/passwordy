package com.github.aayman93.passwordy.feature_password.domain.use_cases.get_passwords

import com.github.aayman93.passwordy.feature_password.domain.models.PasswordInfo
import com.github.aayman93.passwordy.feature_password.domain.repository.PasswordRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetPasswordsUseCase @Inject constructor(
    private val repository: PasswordRepository
) {

    operator fun invoke(): Flow<List<PasswordInfo>> {
        return repository.getPasswords()
    }
}