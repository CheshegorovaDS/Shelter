package com.example.shelter.presentation.storage

import android.content.SharedPreferences
import com.example.shelter.presentation.model.User
import javax.inject.Inject

class LoggedUserManager @Inject constructor(
    private val sharedPreferences: SharedPreferences) :
    LoggedUserProvider {

    companion object {
        private const val USER_EMAIL: String = "logged_user_email"
        private const val USER_NAME_PASSWORD: String = "logged_user_password"
        private const val USER_NAME_ACCESS_TOKEN: String = "logged_user_access_token"
        private const val DEFAULT_STRING_VALUE: String = ""

        private var loggedInUser: User? = null
    }


    override fun setLoggedUser(loggedUser: User) {
        loggedInUser = loggedUser
        val editor: SharedPreferences.Editor = sharedPreferences.edit()

        editor.putString(USER_EMAIL, loggedUser.email)
        editor.putString(USER_NAME_PASSWORD, loggedUser.password)
        editor.apply()
    }

    override fun getLoggedUser(): User? {
        if (loggedInUser == null) {
            loggedInUser = tryToGetSavedUserProfile()
        }
        return loggedInUser
    }

    override fun logout() {
        loggedInUser = null
        sharedPreferences.edit()
            .remove(USER_NAME_ACCESS_TOKEN)
            .apply()
    }

    private fun tryToGetSavedUserProfile(): User? {
        var savedUser: User? = null
        val loggedUsername: String = sharedPreferences.getString(
            USER_EMAIL,
            DEFAULT_STRING_VALUE
        ) ?: DEFAULT_STRING_VALUE

        if (loggedUsername != DEFAULT_STRING_VALUE) {
            val password: String = sharedPreferences.getString(
                USER_NAME_PASSWORD,
                DEFAULT_STRING_VALUE
            ) ?: DEFAULT_STRING_VALUE
            savedUser =
                User(loggedUsername, password)
        }
        return savedUser
    }
}