package com.rehan.cleanarchitectureapp.domain.usecase

import com.rehan.cleanarchitectureapp.utils.NetworkResult
import com.rehan.cleanarchitectureapp.data.model.toDomainMealSearch
import com.rehan.cleanarchitectureapp.domain.model.MealSearch
import com.rehan.cleanarchitectureapp.domain.repository.MealSearchRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

// Our usecase will talk with repository and in this usecase we will be storing our api response.
class GetMealSearchListUseCase @Inject constructor(private val repository: MealSearchRepository) {

    // Here we will be showing list of data for MealSearch
    // We will use operator function and whenever we use operator, we have to use invoke keyword only. We cannot use other names.
    operator fun invoke(s: String): Flow<NetworkResult<List<MealSearch>>> = flow {
        try {
            // First we will be showing loading progressbar before getting data from api server
            emit(NetworkResult.Loading())

            // We will store the success response here
            val response = repository.getMealSearchList(s)
            // First we will check if response is null or not.
            // We will be returning emptyList if there is no response from api. If there is response, we will return success response.
            val list = if (response.meals.isNullOrEmpty()) emptyList<MealSearch>() else response.meals.map {
                it.toDomainMealSearch() }
            emit(NetworkResult.Success(data = list))

        } catch (e: HttpException) {
            emit(NetworkResult.Error(message = e.localizedMessage?:"Unknown Error"))    // It will return actual localized message. If this localized message also null, we will return Unknown Error

        } catch (e: IOException) {
            emit(NetworkResult.Error(message = e.localizedMessage?:"Check your internet connection")) // It will return actual localized message. If this localized message also null, we will return Check your internet connection

        } catch (e: Exception) {
            emit(NetworkResult.Error(message = e.message.toString()))
        }
    }

}