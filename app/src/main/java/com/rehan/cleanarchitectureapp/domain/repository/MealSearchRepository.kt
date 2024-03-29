package com.rehan.cleanarchitectureapp.domain.repository

import com.rehan.cleanarchitectureapp.data.model.MealsDTO

interface MealSearchRepository {

    // The reason why we use data layer model class (MealsDTO) instead of using domain layer model class is because we will be implementing this interface repository in data layer repository.
    suspend fun getMealSearchList(s: String): MealsDTO
}