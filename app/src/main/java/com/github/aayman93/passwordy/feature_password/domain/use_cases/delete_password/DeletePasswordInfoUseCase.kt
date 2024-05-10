package com.github.aayman93.passwordy.feature_password.domain.use_cases.delete_password

import com.github.aayman93.passwordy.feature_password.domain.models.PasswordInfo
import com.github.aayman93.passwordy.feature_password.domain.repository.PasswordRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DeletePasswordInfoUseCase @Inject constructor(
    private val repository: PasswordRepository
) {

    suspend operator fun invoke(passwordInfo: PasswordInfo) {
        return repository.deletePasswordInfo(passwordInfo)
    }
}