package com.github.aayman93.passwordy.feature_password.domain.use_cases.save_password

import com.github.aayman93.passwordy.feature_password.domain.models.PasswordInfo
import com.github.aayman93.passwordy.feature_password.domain.repository.PasswordRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SavePasswordInfoUseCase @Inject constructor(
    private val repository: PasswordRepository
) {

    suspend operator fun invoke(passwordInfo: PasswordInfo): Long {
        return repository.savePasswordInfo(passwordInfo)
    }
}