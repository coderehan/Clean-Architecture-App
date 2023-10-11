package com.rehan.cleanarchitectureapp.domain.model

// This model class comes under domain layer.
// So in domain layer, we keep only those things which we want to show in UI.
// Here we create variables as per our need and then we map data layer model class variables to these domain layer model class variables which we created here.
data class MealSearch(
    val mealId: String,
    val mealName: String,
    val mealImage: String
)