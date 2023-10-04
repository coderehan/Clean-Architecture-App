package com.rehan.cleanarchitectureapp.data.api

import com.rehan.cleanarchitectureapp.data.model.MealsDTO
import retrofit2.http.GET
import retrofit2.http.Query

interface MealAPI {

    @GET("api/json/v1/1/search.php")
    suspend fun getMealSearchList(@Query("s") s: String): MealsDTO     // "s" is the actual query in endpoint and for this we will use @Query annotation

    @GET("api/json/v1/1/lookup.php")
    suspend fun getMealDetailsList(@Query("i") i: String): MealsDTO     // "i" is the actual query in endpoint and for this we will use @Query annotation
}