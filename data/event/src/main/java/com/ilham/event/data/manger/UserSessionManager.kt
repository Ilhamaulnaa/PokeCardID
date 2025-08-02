package com.ilham.event.data.manger

import android.content.Context
import android.content.SharedPreferences
import dagger.hilt.android.qualifiers.ApplicationContext
import jakarta.inject.Inject

class UserSessionManager @Inject constructor(@ApplicationContext context: Context) {
    private val prefs: SharedPreferences = context.getSharedPreferences("user_prefs", Context.MODE_PRIVATE)
    private val KEY_LOGGED_IN_USER_ID = "logged_in_user_id"

    fun saveLoggedInUserId(userId: Long) {
        prefs.edit().putLong(KEY_LOGGED_IN_USER_ID, userId).apply()
    }

    fun getLoggedInUserId(): Long {
        return prefs.getLong(KEY_LOGGED_IN_USER_ID, -1L)
    }

    fun clearSession() {
        prefs.edit().remove(KEY_LOGGED_IN_USER_ID).apply()
    }

    fun isLoggedIn(): Boolean {
        return getLoggedInUserId() != -1L
    }
}