package com.example.shelter.data.di

import com.example.shelter.data.animal_type.repository.AnimalTypeApi
import com.example.shelter.data.animal_type.repository.IAnimalTypeRepository
import dagger.Component

@Component(modules = [ApiBinds::class])
interface AnimalTypeRepositoryComponent {
    fun getAnimalTypeRepository(): IAnimalTypeRepository
    fun getAnimalTypeApi(): AnimalTypeApi
}
