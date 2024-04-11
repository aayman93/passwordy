package com.github.aayman93.passwordy.feature_password.presentation.utils

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
sealed class PasswordAction : Parcelable {
    data object AddPassword : PasswordAction()
    data class SavePassword(val data: String) : PasswordAction()
}