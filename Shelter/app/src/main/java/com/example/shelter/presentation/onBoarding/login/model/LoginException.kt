package com.example.shelter.presentation.onBoarding.login.model

import java.lang.Exception

class LoginException(
    override val message: String? = ""
) : Exception(message) {

    lateinit var id: LoginErrorCode

    constructor(errorCode: LoginErrorCode, message: String?) : this(message) {
        id = errorCode
    }

    override fun equals(other: Any?): Boolean {
        if (other == null || other !is LoginException) {
            return false
        }
        if (message.equals(other.message) && id == other.id) {
            return true
        }
        return false
    }
}