package com.rehan.cleanarchitectureapp.data.repository

import com.rehan.cleanarchitectureapp.data.api.MealAPI
import com.rehan.cleanarchitectureapp.data.model.MealsDTO
import com.rehan.cleanarchitectureapp.domain.repository.MealSearchRepository

class GetMealSearchListImpl(private val mealAPI: MealAPI): MealSearchRepository {
    override suspend fun getMealSearchList(s: String): MealsDTO {
        return mealAPI.getMealSearchList(s)
    }
}