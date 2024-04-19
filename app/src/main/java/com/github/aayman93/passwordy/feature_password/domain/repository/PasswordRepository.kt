package com.github.aayman93.passwordy.feature_password.domain.repository

import com.github.aayman93.passwordy.feature_password.domain.models.PasswordInfo
import kotlinx.coroutines.flow.Flow

interface PasswordRepository {

    fun getPasswords(): Flow<List<PasswordInfo>>

    suspend fun savePasswordInfo(passwordInfo: PasswordInfo): Long
}