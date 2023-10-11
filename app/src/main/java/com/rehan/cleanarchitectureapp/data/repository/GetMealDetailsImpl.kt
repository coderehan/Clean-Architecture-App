package com.rehan.cleanarchitectureapp.data.repository

import com.rehan.cleanarchitectureapp.data.api.MealAPI
import com.rehan.cleanarchitectureapp.data.model.MealsDTO
import com.rehan.cleanarchitectureapp.domain.repository.MealDetailRepository

// Here in this repository, we will be implementing domain layer repository interface
// This repository will talk with api interface
class GetMealDetailsImpl(private val mealAPI: MealAPI) : MealDetailRepository {
    override suspend fun getMealDetailList(id: String): MealsDTO {
        return mealAPI.getMealDetailsList(id)
    }
}