package com.example.shelter.presentation.fragment_menu.messager.presenter

import com.example.shelter.presentation.fragment_menu.messager.model.Messager
import com.example.shelter.presentation.fragment_menu.messager.model.MessagerModel
import com.example.shelter.presentation.fragment_menu.messager.view.MessagerFragment

class MessagerPresenter:IMessagerPresenter.Presenter {
    var view: MessagerFragment
    var model: MessagerModel

    constructor(view: MessagerFragment){
        this.view = view
        model = MessagerModel()
    }

    override fun loadMesseges(): List<Messager> {
        val tables = ArrayList<Messager>()
        tables.add(Messager("Игорь Петров", "Пока", "сегодня в 18:05", 4))
        tables.add(Messager("Виктор Петрович", "Ты мне за всё ответишь!", "сегодня в 17:00", 100))
        tables.add(Messager("","","",0))
        tables.add(Messager("","","",0))
        tables.add(Messager("","","",0))
        tables.add(Messager("","","",0))
        return tables
    }
}