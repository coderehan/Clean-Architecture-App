package com.rehan.cleanarchitectureapp.data.repository

import com.rehan.cleanarchitectureapp.data.api.MealAPI
import com.rehan.cleanarchitectureapp.data.model.MealsResponse
import com.rehan.cleanarchitectureapp.domain.repository.MealDetailRepository

class GetMealDetailsRepository(private val mealAPI: MealAPI) : MealDetailRepository {
    override suspend fun getMealDetailList(id: String): MealsResponse {
        return mealAPI.getMealDetailsList(id)
    }
}