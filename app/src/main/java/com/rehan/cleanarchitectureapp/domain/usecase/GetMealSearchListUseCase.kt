package com.rehan.cleanarchitectureapp.domain.usecase

import com.rehan.cleanarchitectureapp.constants.NetworkResult
import com.rehan.cleanarchitectureapp.data.model.toDomainMealSearch
import com.rehan.cleanarchitectureapp.domain.model.MealSearch
import com.rehan.cleanarchitectureapp.domain.repository.MealSearchRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetMealSearchListUseCase @Inject constructor(private val repository: MealSearchRepository) {

    operator fun invoke(s: String): Flow<NetworkResult<List<MealSearch>>> = flow {
        try {
            // First we will show loading progessbar before getting data from server
            emit(NetworkResult.Loading())

            // We will store the success response here
            val response = repository.getMealSearchList(s)
            val list = if (response.meals.isNullOrEmpty()) emptyList<MealSearch>() else response.meals.map { it.toDomainMealSearch() }
            emit(NetworkResult.Success(data = list))

        } catch (e: HttpException) {
            emit(NetworkResult.Error(message = e.localizedMessage?:"Unknown Error"))

        } catch (e: IOException) {
            emit(NetworkResult.Error(message = e.localizedMessage?:"Check your internet connection"))

        } catch (e: Exception) {
        }
    }

}