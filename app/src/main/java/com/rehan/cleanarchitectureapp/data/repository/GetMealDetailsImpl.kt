package com.rehan.cleanarchitectureapp.data.repository

import com.rehan.cleanarchitectureapp.data.api.MealAPI
import com.rehan.cleanarchitectureapp.data.model.MealsDTO
import com.rehan.cleanarchitectureapp.domain.repository.MealDetailRepository

class GetMealDetailsImpl(private val mealAPI: MealAPI) : MealDetailRepository {
    override suspend fun getMealDetailList(id: String): MealsDTO {
        return mealAPI.getMealDetailsList(id)
    }
}