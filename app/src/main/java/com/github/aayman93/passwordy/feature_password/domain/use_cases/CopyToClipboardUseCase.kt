package com.github.aayman93.passwordy.feature_password.domain.use_cases

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import androidx.core.content.getSystemService
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class CopyToClipboardUseCase @Inject constructor(@ApplicationContext private val context: Context) {

    operator fun invoke(password: String): Boolean {
        return try {
            val clipboardManager = context.getSystemService<ClipboardManager>()
            val clipData = ClipData.newPlainText(
                "Generated Password",
                password
            )
            clipboardManager?.setPrimaryClip(clipData)
            true
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }
}