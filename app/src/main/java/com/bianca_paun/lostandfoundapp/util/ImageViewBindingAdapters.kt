package com.bianca_paun.lostandfoundapp.util

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("url")
fun loadImageFromUrl(
    imageView: ImageView,
    url: String?
) = url?.let {
    Glide.with(imageView.context)
        .load(url)
        .into(imageView)
}