package com.example.shelter.presentation.edit_user.reducer

import com.example.shelter.presentation.base.inrefaces.BaseReducer
import com.example.shelter.presentation.edit_user.model.EditUserDestination
import com.example.shelter.presentation.edit_user.model.EditUserException
import com.example.shelter.presentation.edit_user.model.EditUserState
import com.example.shelter.presentation.model.User
import io.reactivex.subjects.PublishSubject

interface IEditUserReducer: BaseReducer {
    val updateState: PublishSubject<EditUserState>
    val updateException: PublishSubject<EditUserException>
    val updateUser: PublishSubject<User>
    val updateDestination: PublishSubject<EditUserDestination>

    fun downloadInfo()

}