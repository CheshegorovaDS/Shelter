package com.example.shelter.presentation.log_in_app.reducer

import com.example.shelter.presentation.base2.BaseReducer
import com.example.shelter.presentation.log_in_app.model.LogInAppDestination
import io.reactivex.subjects.PublishSubject

interface ILogInAppReducer: BaseReducer {
    val updateDestination: PublishSubject<LogInAppDestination>
    fun login()
    fun registration()
}