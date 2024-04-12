package com.github.aayman93.passwordy.feature_password.data.data_source

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.github.aayman93.passwordy.feature_password.domain.models.PasswordInfo

@Dao
interface PasswordDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun savePasswordInfo(passwordInfo: PasswordInfo): Long
}