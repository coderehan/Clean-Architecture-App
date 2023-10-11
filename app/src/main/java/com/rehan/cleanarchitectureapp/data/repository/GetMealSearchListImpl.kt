package com.rehan.cleanarchitectureapp.data.repository

import com.rehan.cleanarchitectureapp.data.api.MealAPI
import com.rehan.cleanarchitectureapp.data.model.MealsDTO
import com.rehan.cleanarchitectureapp.domain.repository.MealSearchRepository

// Here in this repository, we will be implementing domain layer repository interface
// This repository will talk with api interface
class GetMealSearchListImpl(private val mealAPI: MealAPI): MealSearchRepository {
    override suspend fun getMealSearchList(s: String): MealsDTO {
        return mealAPI.getMealSearchList(s)
    }
}