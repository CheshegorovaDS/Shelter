package com.example.shelter.presentation

import com.example.shelter.presentation.model.User
import java.util.regex.Pattern

const val MIN_EMAIL_LENGTH: Int = 3;
const val DEBOUNCE_VALUE: Long = 500;
const val APP_PREFERENCES = "mysettings"
const val APP_PREFERENCES_NAME = "Email"
const val APP_LOGIN = "Login"


fun checkEmailAndPassword(user: User):Boolean{
    return checkPassword(user.password) && checkEmail(user.email)
}

fun checkEmailAndPassword(email: String, password: String):Boolean{
    return checkPassword(password) && checkEmail(email)
}

private fun checkPassword(password:String):Boolean{
    return password.length >= MIN_EMAIL_LENGTH;
}

private fun checkEmail(email:String):Boolean{
    val checkLogin = "^([a-z0-9_-]+\\.)*[a-z0-9_-]+@[a-z0-9_-]+(\\.[a-z0-9_-]+)*\\.[a-z]{2,6}$"
    return Pattern.matches(checkLogin, email)
}