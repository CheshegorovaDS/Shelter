package com.example.shelter.presentation.onBoarding.login2.model

import java.lang.Exception

class LoginException(
    override val message: String? = ""
) : Exception(message) {

    lateinit var id: LoginErrorCode

    constructor(errorCode: LoginErrorCode, message: String?) : this(message) {
        id = errorCode
    }
}