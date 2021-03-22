package com.example.shelter.presentation.about_animal.presenter

import com.example.shelter.presentation.model.Animal
import com.example.shelter.presentation.about_animal.view.AboutAnimalView

class AboutAnimalPresenter: IAboutAnimalPresenter {

    lateinit var view:AboutAnimalView

    constructor(view: AboutAnimalView){
        this.view = view
    }

    override fun openUser() {
        view.openUserHomepage()
    }

    override fun showAnimal(animal: Animal) {
        view.showAnimalInfo(animal.name)
        view.showAge(!animal.age.equals(""))
        view.showBreed(animal.breed != "")
        view.showPhoto(animal.photo != "")
        view.showGrowth(animal.growth != "")
        view.showPassport(animal.passport != "")
        view.showSterilization(!animal.sterilization.equals(""))
        view.showDescription(animal.description != "")
    }
}