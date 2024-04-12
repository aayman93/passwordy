package com.github.aayman93.passwordy.di

import android.app.Application
import androidx.room.Room
import com.github.aayman93.passwordy.feature_password.data.data_source.PasswordDatabase
import com.github.aayman93.passwordy.feature_password.data.repository.PasswordRepositoryImpl
import com.github.aayman93.passwordy.feature_password.domain.repository.PasswordRepository
import com.github.aayman93.passwordy.feature_password.domain.use_cases.validation.ValidateEmail
import com.github.aayman93.passwordy.feature_password.domain.use_cases.validation.ValidatePassword
import com.github.aayman93.passwordy.feature_password.domain.use_cases.validation.ValidatePasswordFormUseCase
import com.github.aayman93.passwordy.feature_password.domain.use_cases.validation.ValidateWebsite
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providePasswordDatabase(app: Application): PasswordDatabase {
        return Room.databaseBuilder(
            app,
            PasswordDatabase::class.java,
            PasswordDatabase.DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun providePasswordRepository(database: PasswordDatabase): PasswordRepository {
        return PasswordRepositoryImpl(database.passwordDao)
    }

    @Provides
    @Singleton
    fun provideValidatePasswordFormUseCase(): ValidatePasswordFormUseCase {
        return ValidatePasswordFormUseCase(
            validateEmail = ValidateEmail(),
            validatePassword = ValidatePassword(),
            validateWebsite = ValidateWebsite()
        )
    }
}