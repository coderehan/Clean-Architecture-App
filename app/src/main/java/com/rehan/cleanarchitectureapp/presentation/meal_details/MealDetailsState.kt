package com.rehan.cleanarchitectureapp.presentation.meal_details

import com.rehan.cleanarchitectureapp.domain.model.MealDetails
import com.rehan.cleanarchitectureapp.domain.model.MealSearch

data class MealDetailsState(
    val data: MealDetails? = null,      // From our usecase we are getting list of MealSearch
    val error: String = "",
    val isLoading: Boolean = false      // Initially we will set to false
)
