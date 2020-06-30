package com.example.shelter.presentation.about_animal.presenter

import com.example.shelter.presentation.model.Animal

interface IAboutAnimalPresenter {
    fun openUser()
    fun showAnimal(animal: Animal)
}