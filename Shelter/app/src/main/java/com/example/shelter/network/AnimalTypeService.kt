package com.example.shelter.network

import com.example.shelter.data.animal_type.response.AnimalTypeResponse
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET

interface AnimalTypeService {
    @GET("/api/animal_types/")
    fun getAll(): Single<Response<List<AnimalTypeResponse>>>
}
