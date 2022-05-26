package com.example.centeralperk.util

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.example.centeralperk.R

class CustomBinding {

    companion object {

        /** ImageView custom binding */
        @JvmStatic
        @BindingAdapter("imageUrl")
        fun image(view: ImageView, url: String?) {
            url.let { imageUrl ->
                Glide.with(view.context).load(imageUrl).error(R.drawable.ic_profile).circleCrop()
                    .into(view)
            }
        }
    }
}