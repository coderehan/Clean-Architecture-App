package com.rehan.cleanarchitectureapp.presentation.meal_search

import com.rehan.cleanarchitectureapp.domain.model.MealSearch

data class MealSearchState(
    val data: List<MealSearch>? = null,
    val error: String = "",
    val isLoading: Boolean = false          // Initially we will set to false
)
