package com.example.shelter.presentation.edit_user.view

import com.example.shelter.presentation.base.inrefaces.BaseView
import com.example.shelter.presentation.edit_user.model.EditUserException
import com.example.shelter.presentation.model.User
import com.example.shelter.presentation.onBoarding.registration.model.UserType
import io.reactivex.subjects.PublishSubject

interface EditUserView: BaseView {
    val downloadInfo: PublishSubject<Unit>
    val editUser: PublishSubject<User>

    fun showFields(typeUser: UserType)
    fun setHumanInfo(user: User)
    fun setOrganisationInfo(user: User)
    fun showException(exception: EditUserException)
    fun exit()
}