package com.rehan.cleanarchitectureapp.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.rehan.cleanarchitectureapp.R

// This is basically used to load image from server when using data binding in xml
@BindingAdapter("urlToImage")
fun urlToImage(imageView: ImageView, string: String?) {     // String is the actual datatype of image that is coming from api server

    string?.let {
        Glide.with(imageView.context)
            .load(it)
            .placeholder(R.drawable.loading_image)
            .error(R.drawable.error_image)
            .into(imageView)
    }
}