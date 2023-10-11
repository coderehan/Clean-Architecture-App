package com.rehan.cleanarchitectureapp.hilt

import com.rehan.cleanarchitectureapp.utils.Constants
import com.rehan.cleanarchitectureapp.data.api.MealAPI
import com.rehan.cleanarchitectureapp.data.repository.GetMealDetailsImpl
import com.rehan.cleanarchitectureapp.data.repository.GetMealSearchListImpl
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

    // Mention all the API interfaces of data layer and repositories of domain layer here in Hilt that we are going to use in this app.
    @Singleton
    @Provides
    fun providesMealAPI(retrofit: Retrofit): MealAPI {
        return retrofit.create(MealAPI::class.java)
    }

    @Provides
    fun providesMealSearchRepository(mealAPI: MealAPI): MealSearchRepository {
        return GetMealSearchListImpl(mealAPI)   // MealSearchRepository is an interface. So we cannot return anything. Interface has always impl to return. So we will return MealSearchListImpl.
    }

    @Provides
    fun providesMealDetailsRepository(mealAPI: MealAPI): MealDetailRepository {
        return GetMealDetailsImpl(mealAPI)      // MealDetailsRepository is an interface. So we cannot return anything. Interface has always impl to return. So we will return MealDetailsListImpl.
    }
}