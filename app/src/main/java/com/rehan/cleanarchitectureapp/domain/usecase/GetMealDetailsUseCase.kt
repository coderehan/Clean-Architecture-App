package com.rehan.cleanarchitectureapp.domain.usecase

import com.rehan.cleanarchitectureapp.constants.NetworkResult
import com.rehan.cleanarchitectureapp.data.model.toDomainMealDetails
import com.rehan.cleanarchitectureapp.domain.model.MealDetails
import com.rehan.cleanarchitectureapp.domain.repository.MealDetailRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetMealDetailsUseCase @Inject constructor(private val repository: MealDetailRepository) {

    operator fun invoke(id: String): Flow<NetworkResult<MealDetails>> = flow {
        try {
            // First we will show loading progessbar before getting data from server
            emit(NetworkResult.Loading())

            // We will store the success response here
            val response = repository.getMealDetailList(id).meals[0].toDomainMealDetails()
            emit(NetworkResult.Success(data = response))

        } catch (e: HttpException) {
            emit(NetworkResult.Error(message = e.localizedMessage?:"Unknown Error"))

        } catch (e: IOException) {
            emit(NetworkResult.Error(message = e.localizedMessage?:"Check your internet connection"))

        } catch (e: Exception) {
        }
    }

}