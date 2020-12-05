package com.example.shelter.presentation.onBoarding.registration.model

import java.lang.Exception

class RegistrationException(
    override val message: String? = ""
) : Exception(message) {

    lateinit var id: RegistrationErrorCode

    constructor(errorCode: RegistrationErrorCode, message: String?) : this(message) {
        id = errorCode
    }
}