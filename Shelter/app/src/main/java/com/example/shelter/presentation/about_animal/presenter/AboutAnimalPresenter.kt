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
        view.showAnimalInfo()
        view.showAge(!animal.age.equals(""))
        view.showBreed(!animal.breed.equals(""))
        view.showPhoto(!animal.photo.equals(""))
        view.showGrowth(!animal.growth.equals(""))
        view.showPassport(!animal.passport.equals(""))
        view.showSterilization(!animal.sterilization.equals(""))
        view.showDescription(!animal.description.equals(""))
    }
}