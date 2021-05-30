package com.example.shelter.data.animal_type.repository

import com.example.shelter.presentation.model.AnimalType
import io.reactivex.Single

interface IAnimalTypeRepository {
    fun getListAnimalType(): Single<List<AnimalType>>
}