package com.github.aayman93.passwordy.feature_password.domain.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "password")
data class PasswordInfo(
    val email: String,
    val username: String,
    val phone: String,
    val website: String,
    val password: String,
    @PrimaryKey val id: Int? = null
)
