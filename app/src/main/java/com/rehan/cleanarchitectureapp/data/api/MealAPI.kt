package com.rehan.cleanarchitectureapp.data.api

import com.rehan.cleanarchitectureapp.data.model.MealsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface MealAPI {

    @GET("api/json/v1/1/search.php")
    suspend fun getMealSearchList(@Query("s") s: String): MealsResponse     // "s" is the actual query in endpoint and for this we will use @Query annotation

    @GET("api/json/v1/1/lookup.php")
    suspend fun getMealDetailsList(@Query("i") i: String): MealsResponse     // "i" is the actual query in endpoint and for this we will use @Query annotation
}