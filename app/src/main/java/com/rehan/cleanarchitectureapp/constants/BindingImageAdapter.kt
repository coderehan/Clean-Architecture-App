package com.rehan.cleanarchitectureapp.constants

import android.content.Context
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.rehan.cleanarchitectureapp.R

// This is basically used to load image from server when using data binding in xml
@BindingAdapter("urlToImage")
fun urlToImage(imageView: ImageView, string: String?) {

    string?.let {
        val options = RequestOptions.placeholderOf(R.drawable.loading_image).error(R.drawable.error_image)

        Glide.with(imageView.context).load(it).error(R.drawable.error_image).into(imageView)
    }
}