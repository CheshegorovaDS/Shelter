package com.example.shelter.data.category.repository

import com.example.shelter.presentation.model.Category
import io.reactivex.Single
import javax.inject.Inject

class CategoryRepository @Inject constructor(
    val categoryApi: ICategoryApi
): ICategoryRepository {

    override fun getCategoryList(): Single<List<Category>> {
        return categoryApi.getAllCategories()
    }

}
