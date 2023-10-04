package com.rehan.cleanarchitectureapp.presentation.meal_details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rehan.cleanarchitectureapp.utils.NetworkResult
import com.rehan.cleanarchitectureapp.domain.usecase.GetMealDetailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

// Our viewmodels talk with usecase. So we will inject our usecase here.
@HiltViewModel
class MealDetailsViewModel @Inject constructor(private val getMealDetailsUseCase: GetMealDetailsUseCase): ViewModel() {

    private val _mealDetailsMutableStateFlow = MutableStateFlow<MealDetailsState>(MealDetailsState())      // Private MutableStateFlow
    val mealDetailsStateFlow: StateFlow<MealDetailsState> = _mealDetailsMutableStateFlow               // Public level StateFlow

    fun getMealDetails(id: String) {
        getMealDetailsUseCase(id).onEach {
            when (it) {
                is NetworkResult.Loading -> {
                    _mealDetailsMutableStateFlow.value = MealDetailsState(isLoading = true)
                }
                is NetworkResult.Error -> {
                    _mealDetailsMutableStateFlow.value = MealDetailsState(error = it.message ?: "")
                }
                is NetworkResult.Success -> {
                    _mealDetailsMutableStateFlow.value = MealDetailsState(data = it.data)
                }
            }
        }.launchIn(viewModelScope)      // We have to tell here on which scope we will launch this viewmodel function
    }
}