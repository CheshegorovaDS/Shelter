package com.example.shelter.presentation.storage

import android.content.SharedPreferences
import com.example.shelter.presentation.onBoarding.registration.model.User
import javax.inject.Inject

class LoggedUserManager @Inject constructor(
    private val sharedPreferences: SharedPreferences
) : LoggedUserProvider {

    private var loggedInUser: User? = null
    private var token: String? = null
    private var userId: Int? = null

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

    override fun setToken(token: String) {
        this.token = token

        val editor: SharedPreferences.Editor = sharedPreferences.edit()
        editor.putString(USER_NAME_ACCESS_TOKEN, token)
        editor.apply()
    }

    override fun getToken(): String? {
        return if (token == null) {
            if (sharedPreferences.contains(USER_NAME_ACCESS_TOKEN)) {
                sharedPreferences.getString(USER_NAME_ACCESS_TOKEN, DEFAULT_STRING_VALUE)
            } else {
                null
            }
        } else {
            token
        }
    }

    override fun setId(id: Int) {
        this.userId = id

        val editor: SharedPreferences.Editor = sharedPreferences.edit()
        editor.putInt(USER_ID, id)
        editor.apply()
    }

    override fun getId(): Int? {
        return if (userId == null) {
            if (sharedPreferences.contains(USER_ID)) {
                sharedPreferences.getInt(USER_ID, DEFAULT_INT_VALUE)
            } else {
                null
            }
        } else {
            userId
        }
    }

    override fun logout() {
        loggedInUser = null
        sharedPreferences.edit()
            .remove(USER_NAME_ACCESS_TOKEN)
            .remove(USER_EMAIL)
            .remove(USER_ID)
            .remove(USER_NAME_PASSWORD)
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

    companion object {
        private const val USER_EMAIL: String = "logged_user_email"
        private const val USER_NAME_PASSWORD: String = "logged_user_password"
        private const val USER_NAME_ACCESS_TOKEN: String = "logged_user_access_token"
        private const val USER_ID: String = "logged_user_id"
        private const val DEFAULT_STRING_VALUE: String = ""
        private const val DEFAULT_INT_VALUE: Int = -1
    }
}