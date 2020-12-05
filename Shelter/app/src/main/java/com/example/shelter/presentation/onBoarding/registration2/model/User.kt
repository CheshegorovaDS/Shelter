package com.example.shelter.presentation.onBoarding.registration2.model

open class User(
    open val city: String? = null,
    open val country: String? = null,
    open val phone: String? = null,
    open val email: String? = null,
    open val password: String? = null
) {
    override fun equals(other: Any?): Boolean {
        if (other == null || (other !is User &&
                other !is Human && other !is Organisation)) {
            return false
        }
        other as User

        if (email.equals(other.email) &&
                phone.equals(other.phone) &&
                email.equals(other.email) &&
                password.equals(other.password)) {
            return true
        }

        return false
    }
}