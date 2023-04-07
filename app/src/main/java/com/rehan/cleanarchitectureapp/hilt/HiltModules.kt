package com.rehan.cleanarchitectureapp.hilt

import com.rehan.cleanarchitectureapp.constants.Constants
import com.rehan.cleanarchitectureapp.data.api.MealAPI
import com.rehan.cleanarchitectureapp.data.repository.GetMealDetailsRepository
import com.rehan.cleanarchitectureapp.data.repository.GetMealSearchListRepository
import com.rehan.cleanarchitectureapp.domain.repository.MealDetailRepository
import com.rehan.cleanarchitectureapp.domain.repository.MealSearchRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object HiltModules {

    @Singleton
    @Provides
    fun providesRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun providesMealAPI(retrofit: Retrofit): MealAPI {
        return retrofit.create(MealAPI::class.java)
    }

    @Provides
    fun providesMealSearchRepository(mealAPI: MealAPI): MealSearchRepository {
        return GetMealSearchListRepository(mealAPI)
    }

    @Provides
    fun providesMealDetailsRepository(mealAPI: MealAPI): MealDetailRepository {
        return GetMealDetailsRepository(mealAPI)
    }
}