package com.rehan.cleanarchitectureapp.presentation.meal_search

import com.rehan.cleanarchitectureapp.domain.model.MealSearch

data class MealSearchState(
    val data: List<MealSearch>? = null,     // From our usecase we are getting list of MealSearch
    val error: String = "",
    val isLoading: Boolean = false          // Initially we will set to false
)
