package com.rehan.cleanarchitectureapp.presentation.meal_search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.rehan.cleanarchitectureapp.databinding.AdapterMealSearchItemBinding
import com.rehan.cleanarchitectureapp.domain.model.MealSearch

class MealSearchAdapter : RecyclerView.Adapter<MealSearchAdapter.MyViewHolder>() {

    // OnClickListener when user click on Search Fragment page
    private var listener: ((MealSearch) -> Unit)? = null

    fun itemClickListener(l: (MealSearch) -> Unit) {
        listener = l
    }

    var list = mutableListOf<MealSearch>()

    fun setContentList(list: MutableList<MealSearch>) {
        this.list = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MealSearchAdapter.MyViewHolder {
        val binding = AdapterMealSearchItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MealSearchAdapter.MyViewHolder, position: Int) {
        val mealSearch = this.list[position]
        val binding = holder.binding

        binding.tvMealName.text = mealSearch.mealName



        binding.ivMealImage.setOnClickListener {
            listener?.let {
                it(this.list[position])
            }
        }
    }

    override fun getItemCount(): Int {
        return this.list.size
    }

    inner class MyViewHolder(val binding: AdapterMealSearchItemBinding) : RecyclerView.ViewHolder(binding.root)

}