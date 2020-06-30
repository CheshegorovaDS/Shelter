package com.example.shelter.presentation.fragment_menu.messager.presenter

import com.example.shelter.presentation.fragment_menu.messager.model.Messager

interface IMessagerPresenter {
    interface View

    interface Presenter{
        fun loadMesseges(): List<Messager>
    }
}