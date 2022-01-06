package com.example.kinowiki.presentation.common

import android.widget.ImageView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.example.kinowiki.R

fun ImageView.setImageUrl(url : String) {
    val drawable = CircularProgressDrawable(context).apply {
        strokeWidth = 10f
        centerRadius = 50f
        start()
    }
    Glide.with(context)
        .load(url)
        .error(R.drawable.ic_broken_image)
        .placeholder(drawable)
        .into(this)


}