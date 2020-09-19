package com.example.shelter.presentation.base2

interface BasePresenter{
    fun attachView(baseView: BaseView)
    fun detachView()
    fun bind()
}