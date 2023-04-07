package com.rehan.cleanarchitectureapp.presentation.meal_search

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rehan.cleanarchitectureapp.constants.NetworkResult
import com.rehan.cleanarchitectureapp.domain.usecase.GetMealSearchListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

// Here we will inject usecase instead of repository
@HiltViewModel
class MealSearchViewModel @Inject constructor(private val getMealSearchListUseCase: GetMealSearchListUseCase) : ViewModel() {

    private val _mealSearchListMutableStateFlow = MutableStateFlow<MealSearchState>(MealSearchState())      // Private MutableStateFlow
    val mealSearchListStateFlow: StateFlow<MealSearchState> = _mealSearchListMutableStateFlow               // Public level StateFlow

    fun searchMealList(s: String) {
        getMealSearchListUseCase(s).onEach {
            when (it) {
                is NetworkResult.Loading -> {
                    _mealSearchListMutableStateFlow.value = MealSearchState(isLoading = true)
                }
                is NetworkResult.Error -> {
                    _mealSearchListMutableStateFlow.value = MealSearchState(error = it.message ?: "")
                }
                is NetworkResult.Success -> {
                    _mealSearchListMutableStateFlow.value = MealSearchState(data = it.data)
                }
            }
        }.launchIn(viewModelScope)
    }
}