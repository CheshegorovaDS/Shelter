package com.example.shelter.presentation.edit_user.model

import com.example.shelter.presentation.onBoarding.registration.model.UserType

data class EditUserState(
    val userFieldsVisibility: Boolean = false,
    val userType: UserType? = null,
    val progressBarVisibility: Boolean = true,
    val exceptionVisibility: Boolean = false,
    val applyVisibility: Boolean = false,
    val applyEnabled: Boolean = false
)