package com.rehan.cleanarchitectureapp.domain.repository

import com.rehan.cleanarchitectureapp.data.model.MealsResponse

interface MealSearchRepository {

    suspend fun getMealSearchList(s: String): MealsResponse
}