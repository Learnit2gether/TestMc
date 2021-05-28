package com.example.testmc.view.adapter

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

val IMAGE_BASE = "https://image.tmdb.org/t/p/w500"

@BindingAdapter("srcUri")
fun ImageView.loadUrl( url: String?){
    url?.let { uri ->
        Glide.with(this)
            .load("$IMAGE_BASE$uri")
            .into(this)
    }
}