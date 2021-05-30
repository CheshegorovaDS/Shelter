package com.example.shelter.network

import com.example.shelter.data.category.response.CategoryResponse
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET

interface CategoryService {
    @GET("/api/categories/")
    fun getAll(): Single<Response<List<CategoryResponse>>>
}
