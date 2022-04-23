package com.bamboonozzle.applearningarchitecture.weaponslist

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bamboonozzle.applearningarchitecture.data.Weapon
import com.bumptech.glide.Glide

@BindingAdapter("app:items")
fun setList(recyclerView: RecyclerView, weapons: List<Weapon>?) {
    weapons?.let {
        (recyclerView.adapter as WeaponsAdapter).submitList(it)
    }
}

@BindingAdapter("app:imageUrl")
fun setImageFromUrl(imageView: ImageView, url: String?) {
    url?.let {
        Glide.with(imageView).load(url).into(imageView)
    }
}