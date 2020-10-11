package com.example.shelter.presentation.log_in_app.view

import com.example.shelter.presentation.base.inrefaces.BaseView
import com.example.shelter.presentation.log_in_app.model.LogInAppDestination
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject

interface LogInAppView:
    BaseView {
    val back: PublishSubject<Unit>
    fun clickLogin(): Observable<Any>
    fun clickRegistration(): Observable<Any>
    fun navigationTo(destination: LogInAppDestination)
}