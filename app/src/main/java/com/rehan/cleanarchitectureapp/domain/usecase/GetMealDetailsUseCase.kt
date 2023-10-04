package com.rehan.cleanarchitectureapp.domain.usecase

import com.rehan.cleanarchitectureapp.utils.NetworkResult
import com.rehan.cleanarchitectureapp.data.model.toDomainMealDetails
import com.rehan.cleanarchitectureapp.domain.model.MealDetails
import com.rehan.cleanarchitectureapp.domain.repository.MealDetailRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

// Our usecase will talk with repository and in this usecase we will be storing our api response.
class GetMealDetailsUseCase @Inject constructor(private val repository: MealDetailRepository) {

    // Here we will be showing data for MealDetails
    // We will use operator function and whenever we use operator, we have to use invoke keyword only. We cannot use other names.
    operator fun invoke(id: String): Flow<NetworkResult<MealDetails>> = flow {
        try {
            // First we will be showing loading progressbar before getting data from api server
            emit(NetworkResult.Loading())

            // We will store the success response here
            val response = repository.getMealDetailList(id).meals[0].toDomainMealDetails()
            emit(NetworkResult.Success(data = response))

        } catch (e: HttpException) {
            emit(NetworkResult.Error(message = e.localizedMessage?:"Unknown Error"))  // It will return actual localized message. If this localized message also null, we will return Unknown Error

        } catch (e: IOException) {
            emit(NetworkResult.Error(message = e.localizedMessage?:"Check your internet connection"))   // It will return actual localized message. If this localized message also null, we will return Check your internet connection

        } catch (e: Exception) {
            emit(NetworkResult.Error(message = e.message.toString()))
        }
    }

}