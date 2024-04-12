package com.github.aayman93.passwordy.feature_password.domain.repository

import com.github.aayman93.passwordy.feature_password.domain.models.PasswordInfo

interface PasswordRepository {

    suspend fun savePasswordInfo(passwordInfo: PasswordInfo): Long
}