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
        // We don't need to set data programmatically here because we have already used data binding in xml.
        val binding = holder.binding
        binding.mealSearch = this.list[position]
        binding.root.setOnClickListener{
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