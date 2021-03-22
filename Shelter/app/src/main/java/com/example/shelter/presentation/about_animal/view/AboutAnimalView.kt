package com.example.shelter.presentation.about_animal.view

interface AboutAnimalView {
    fun showAnimalInfo(name: String)
    fun showPhotoUser(visibility: Boolean)
    fun showBreed(visibility: Boolean)
    fun showAge(visibility: Boolean)
    fun showPassport(visibility: Boolean)
    fun showSterilization(visibility: Boolean)
    fun showGrowth(visibility: Boolean)
    fun showDescription(visibility: Boolean)
    fun showPhoto(visibility: Boolean)

    fun openUserHomepage()
}