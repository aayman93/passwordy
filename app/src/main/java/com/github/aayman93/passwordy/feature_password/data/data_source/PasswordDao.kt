package com.github.aayman93.passwordy.feature_password.data.data_source

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.github.aayman93.passwordy.feature_password.domain.models.PasswordInfo
import kotlinx.coroutines.flow.Flow

@Dao
interface PasswordDao {

    @Query("SELECT * FROM password")
    fun getPasswords(): Flow<List<PasswordInfo>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun savePasswordInfo(passwordInfo: PasswordInfo): Long

    @Delete
    suspend fun deletePasswordInfo(passwordInfo: PasswordInfo)
}