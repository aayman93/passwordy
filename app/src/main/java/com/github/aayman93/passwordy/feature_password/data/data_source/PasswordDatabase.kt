package com.github.aayman93.passwordy.feature_password.data.data_source

import androidx.room.Database
import androidx.room.RoomDatabase
import com.github.aayman93.passwordy.feature_password.domain.models.PasswordInfo

@Database(entities = [PasswordInfo::class], version = 1)
abstract class PasswordDatabase : RoomDatabase() {

    abstract val passwordDao: PasswordDao

    companion object {
        const val DATABASE_NAME = "password_db"
    }
}