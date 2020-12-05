package com.example.shelter.presentation.onBoarding.registration2.model

class Organisation (
    val name: String? = null,
    val documentation: String? = null,
    val home: String? = null,
    val addition: String? = null,
    val site: String? = null,
    val conditionsTaking: String? = null,
    override var city: String? = null,
    override var country: String? = null,
    override var phone: String? = null,
    override var email: String? = null,
    override var password: String? = null
) : User(city, country, phone, email)