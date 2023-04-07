package com.rehan.cleanarchitectureapp.presentation.meal_details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.navArgs
import androidx.fragment.app.viewModels
import androidx.lifecycle.coroutineScope
import com.rehan.cleanarchitectureapp.databinding.FragmentMealDetailsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MealDetailsFragment : Fragment() {

    private var _binding: FragmentMealDetailsBinding? = null
    private val binding: FragmentMealDetailsBinding
        get() = _binding!!

    private val mealDetailsViewModel: MealDetailsViewModel by viewModels()
    private val args: MealDetailsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentMealDetailsBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        args.mealId?.let{
            mealDetailsViewModel.getMealDetails(it)
        }

        lifecycle.coroutineScope.launchWhenCreated {
            // Now we will collect data from stateflow
            mealDetailsViewModel.mealDetailsStateFlow.collect{

                if(it.isLoading){

                }

                if(it.error.isNotBlank()){
                    Toast.makeText(requireContext(), it.error, Toast.LENGTH_LONG).show()
                }

                it.data?.let {
                   binding.mealDetails = it
                }
            }
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}