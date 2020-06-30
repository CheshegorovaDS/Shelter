package com.example.shelter.presentation.fragment_menu.homepage.presenter

import com.example.shelter.presentation.fragment_menu.homepage.view.HomepageView

class HomepagePresenter:IHomepagePresenter {
    var view: HomepageView

    constructor(view: HomepageView){
        this.view = view
    }
}