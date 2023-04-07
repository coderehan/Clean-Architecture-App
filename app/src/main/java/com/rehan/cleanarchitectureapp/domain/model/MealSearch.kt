package com.rehan.cleanarchitectureapp.domain.model

// This model class comes under domain layer.
// So in domain layer, we keep only those things which we want to show in UI.
data class MealSearch(
    val mealId: String,
    val mealName: String,
    val mealImage: String
)