package com.rehan.cleanarchitectureapp.data.repository

import com.rehan.cleanarchitectureapp.data.api.MealAPI
import com.rehan.cleanarchitectureapp.data.model.MealsResponse
import com.rehan.cleanarchitectureapp.domain.repository.MealSearchRepository

class GetMealSearchListRepository(private val mealAPI: MealAPI): MealSearchRepository {
    override suspend fun getMealSearchList(s: String): MealsResponse {
        return mealAPI.getMealSearchList(s)
    }
}