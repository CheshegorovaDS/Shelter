package com.example.shelter.data.news.response

import com.example.shelter.presentation.model.Animal
import com.example.shelter.presentation.model.User

data class NewsResponse(
    val id: Long,
//    val category: Category,
    val animal: Animal,
    val user: User
)