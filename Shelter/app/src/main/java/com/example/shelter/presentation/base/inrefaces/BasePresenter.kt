package com.example.shelter.presentation.base.inrefaces

interface BasePresenter{
    fun attachView(baseView: BaseView)
    fun detachView()
    fun bind()
}