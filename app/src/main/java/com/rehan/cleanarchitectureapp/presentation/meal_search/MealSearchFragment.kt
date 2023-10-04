package com.rehan.cleanarchitectureapp.presentation.meal_search

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.lifecycle.coroutineScope
import androidx.navigation.fragment.findNavController
import com.rehan.cleanarchitectureapp.databinding.FragmentMealSearchBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MealSearchFragment : Fragment() {

    private var _binding: FragmentMealSearchBinding? = null
    private val binding: FragmentMealSearchBinding
        get() = _binding!!

    private val mealSearchViewModel: MealSearchViewModel by viewModels()
    private val mealSearchAdapter = MealSearchAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentMealSearchBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.svMeal.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let {
                    mealSearchViewModel.searchMealList(it)
                }
                return false
            }

            override fun onQueryTextChange(query: String?): Boolean {
                return false
            }

        })

        binding.rvMeals.apply {
            adapter = mealSearchAdapter
        }

        lifecycle.coroutineScope.launchWhenCreated {
            // Now we will collect data from stateflow
            mealSearchViewModel.mealSearchListStateFlow.collect{
                // Here we are representing MealSearchState. So we don't need to use when statement.
                // So we have 3 states i.e. isLoading, error and data
                if(it.isLoading){
                    binding.tvNothingFound.visibility = View.GONE
                    binding.progressBar.visibility = View.VISIBLE
                }
                // If we get any error, we will hide progressbar
                if(it.error.isNotBlank()){
                    binding.tvNothingFound.visibility = View.GONE
                    binding.progressBar.visibility = View.GONE
                }

                it.data?.let {
                    if(it.isEmpty()){
                        binding.tvNothingFound.visibility = View.VISIBLE
                    }
                    binding.progressBar.visibility = View.GONE
                    mealSearchAdapter.setContentList(it.toMutableList())
                }
            }
        }

        // Passing data from this fragment to details fragment on item click in recyclerview adapter
        mealSearchAdapter.itemClickListener {
            findNavController().navigate(
                MealSearchFragmentDirections.actionMealSearchFragmentToMealDetailsFragment(
                    mealId = it.mealId
                )
            )
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}