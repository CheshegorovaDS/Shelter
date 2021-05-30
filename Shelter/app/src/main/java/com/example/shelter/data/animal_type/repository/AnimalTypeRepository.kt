package com.example.shelter.data.animal_type.repository

import com.example.shelter.presentation.model.AnimalType
import io.reactivex.Single
import javax.inject.Inject

class AnimalTypeRepository @Inject constructor(
    val animalTypeApi: IAnimalTypeApi
): IAnimalTypeRepository {

    override fun getListAnimalType(): Single<List<AnimalType>> {
        return animalTypeApi.getAllAnimalTypes()
    }

}
