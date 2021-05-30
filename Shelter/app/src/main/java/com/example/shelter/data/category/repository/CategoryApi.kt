package com.example.shelter.data.category.repository

import com.example.shelter.network.NetworkService
import com.example.shelter.presentation.model.Category
import io.reactivex.Single
import java.lang.Exception
import javax.inject.Inject

class CategoryApi @Inject constructor(): ICategoryApi {
    val builder = NetworkService
    private val service = builder.buildCategoryService()

    override fun getAllCategories(): Single<List<Category>> {
        return service.getAll().map {
            if (it.isSuccessful) {
                val list = mutableListOf<Category>()
                it.body()?.forEach { item ->
                    list.add(Category(item.id, item.title))
                }
                list
            } else {
                throw Exception("fail")
            }
        }
    }

}
