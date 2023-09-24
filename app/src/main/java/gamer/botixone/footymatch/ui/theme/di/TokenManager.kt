package gamer.botixone.footymatch.ui.theme.di

import android.content.SharedPreferences
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TokenManager @Inject constructor(private val preferences: SharedPreferences) {

    companion object {
        private const val TOKEN_KEY = "token"
    }

    fun getToken(): String? {
        return preferences.getString(TOKEN_KEY, null)
    }

    fun saveToken(token: String) {
        preferences.edit().putString(TOKEN_KEY, token).apply()
    }

}