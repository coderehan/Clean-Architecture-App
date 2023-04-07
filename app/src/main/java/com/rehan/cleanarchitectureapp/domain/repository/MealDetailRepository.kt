package com.rehan.cleanarchitectureapp.domain.repository

import com.rehan.cleanarchitectureapp.data.model.MealsResponse

interface MealDetailRepository {

    suspend fun getMealDetailList(id: String): MealsResponse

}