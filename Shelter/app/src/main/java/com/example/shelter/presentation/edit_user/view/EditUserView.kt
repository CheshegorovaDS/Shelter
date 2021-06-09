package com.example.shelter.presentation.edit_user.view

import com.example.shelter.presentation.base.inrefaces.BaseView
import com.example.shelter.presentation.edit_user.model.EditUserErrorCode
import com.example.shelter.presentation.edit_user.model.EditUserException
import com.example.shelter.presentation.model.User
import com.example.shelter.presentation.onBoarding.registration.model.UserType
import io.reactivex.subjects.PublishSubject

interface EditUserView: BaseView {
    val downloadInfo: PublishSubject<Unit>
    val editUser: PublishSubject<User>

    fun showProgressBar(visibility: Boolean)
    fun showFields(typeUser: UserType)
    fun showApply(visibility: Boolean)
    fun enabledApply(isEnabled: Boolean)
    fun hideFields()
    fun showException(visibility: Boolean)
    fun setHumanInfo(user: User)
    fun setOrganisationInfo(user: User)
    fun setException(exception: EditUserErrorCode)
    fun exit()
}