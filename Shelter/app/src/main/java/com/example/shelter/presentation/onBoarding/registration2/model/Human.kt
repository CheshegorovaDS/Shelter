package com.example.shelter.presentation.onBoarding.registration2.model

data class Human (
    val firstName: String? = null,
    val lastName: String? = null,
    val patronymic: String? = null,
    val birthday: Long? = null,
    override var city: String? = null,
    override var country: String? = null,
    override var phone: String? = null,
    override var email: String? = null,
    override var password: String? = null
) : User(city, country, phone, email)