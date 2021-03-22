package com.example.shelter.presentation.news.utils

import android.content.Intent
import com.example.shelter.presentation.about_animal.model.Sex
import com.example.shelter.presentation.about_animal.model.Sterilization
import com.example.shelter.presentation.model.Animal

fun convertAnimalToIntent(animal: Animal): Intent {
    val intent = Intent()
    intent.putExtra(NAME, animal.name)
    intent.putExtra(PHOTO, animal.photo)
    intent.putExtra(TYPE, animal.type)
    intent.putExtra(CATEGORY, animal.category)
    intent.putExtra(SEX, animal.sex)
    intent.putExtra(BREED, animal.breed)
    intent.putExtra(AGE, animal.age)
    intent.putExtra(PASSPORT, animal.passport)
    intent.putExtra(STERILIZATION, animal.sterilization)
    intent.putExtra(GROWTH, animal.growth)
    intent.putExtra(DESCRIPTION, animal.description)
    return intent
}

fun convertIntentToAnimal(intent: Intent): Animal {
    return Animal(
        name = intent.getStringExtra(NAME) ?: "",
        photo = intent.getStringExtra(PHOTO) ?: "",
        type = intent.getStringExtra(TYPE) ?: "",
        category = intent.getStringExtra(CATEGORY) ?: "",
        sex = Sex.valueOf(intent.getStringExtra(SEX) ?: "NONE"),
        breed = intent.getStringExtra(BREED) ?: "",
        age = intent.getIntExtra(AGE, -1),
        passport = intent.getStringExtra(PASSPORT) ?: "",
        sterilization = Sterilization.valueOf(intent.getStringExtra(STERILIZATION) ?: "NONE"),
        growth = intent.getStringExtra(GROWTH) ?: "",
        description = intent.getStringExtra(DESCRIPTION) ?: ""
    )
}

const val NAME = "name"
const val PHOTO = "photo"
const val TYPE = "type"
const val CATEGORY = "category"
const val SEX = "sex"
const val BREED = "breed"
const val AGE = "age"
const val PASSPORT = "passport"
const val STERILIZATION = "sterilization"
const val GROWTH = "growth"
const val DESCRIPTION = "description"