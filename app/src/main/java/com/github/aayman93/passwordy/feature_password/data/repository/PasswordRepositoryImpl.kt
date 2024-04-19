package com.github.aayman93.passwordy.feature_password.data.repository

import com.github.aayman93.passwordy.feature_password.data.data_source.PasswordDao
import com.github.aayman93.passwordy.feature_password.domain.models.PasswordInfo
import com.github.aayman93.passwordy.feature_password.domain.repository.PasswordRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PasswordRepositoryImpl @Inject constructor(
    private val dao: PasswordDao
) : PasswordRepository {

    override fun getPasswords(): Flow<List<PasswordInfo>> {
        return dao.getPasswords()
    }

    override suspend fun savePasswordInfo(passwordInfo: PasswordInfo): Long {
        return dao.savePasswordInfo(passwordInfo)
    }
}