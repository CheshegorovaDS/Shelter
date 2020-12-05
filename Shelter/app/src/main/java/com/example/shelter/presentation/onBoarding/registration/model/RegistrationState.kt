package com.example.shelter.presentation.onBoarding.registration.model

data class RegistrationState(
    val userType: UserType? = null,
    val lastNameVisibility: Boolean = false,
    val firstNameVisibility: Boolean = false,
    val organizationNameVisibility: Boolean = false
)