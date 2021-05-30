package com.example.shelter.data.animal_type.repository

import com.example.shelter.network.NetworkService
import com.example.shelter.presentation.model.AnimalType
import io.reactivex.Single
import java.lang.Exception
import javax.inject.Inject

class AnimalTypeApi @Inject constructor(): IAnimalTypeApi {
    val builder = NetworkService
    private val service = builder.buildAnimalTypeService()

    override fun getAllAnimalTypes(): Single<List<AnimalType>> {
        return service.getAll().map {
            if (it.isSuccessful) {
                val list = mutableListOf<AnimalType>()
                it.body()?.forEach { item ->
                    list.add(AnimalType(item.id, item.title))
                }
                list
            } else {
                throw Exception("fail")
            }
        }
    }

}
