package com.decimalab.minutehelp.utils

import android.graphics.Color
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import coil.load
import coil.transform.CircleCropTransformation
import coil.transform.RoundedCornersTransformation
import com.avatarfirst.avatargenlib.AvatarConstants
import com.avatarfirst.avatargenlib.AvatarGenerator
import com.decimalab.minutehelp.R
import org.jetbrains.anko.textColor

@BindingAdapter(value = ["bind:imageUrl", "bind:name"])
fun loadImage(view: ImageView, url: String?, name: String?) {
    if (!url.isNullOrBlank()) {
        view.load("http://oneminutehelp.com/uploads/images/$url") {
            crossfade(true)
            crossfade(300)
            placeholder(name?.let { it1 ->
                AvatarGenerator.avatarImage(
                    view.context, 200, AvatarConstants.CIRCLE,
                    it1
                )
            })
            transformations(CircleCropTransformation())
        }
        Log.d("TAG", "loadImage: url:$url name:$name")
    } else {
        view.load(name?.let {
            AvatarGenerator.avatarImage(view.context, 200, AvatarConstants.CIRCLE, it)
        }) {
            crossfade(true)
            crossfade(300)
            transformations(CircleCropTransformation())
        }
        Log.d("TAG", "loadImage: url:$url name:$name")
    }
}

@BindingAdapter(value = ["bind:CornerRoundImageUrl", "bind:name"])
fun loadImageRoundCorner(view: ImageView, url: String?, name: String?) {
    if (!url.isNullOrBlank()) {
        view.load("http://oneminutehelp.com/uploads/images/$url") {
            crossfade(true)
            crossfade(300)
            placeholder(name?.let { it1 ->
                AvatarGenerator.avatarImage(
                    view.context, 200, AvatarConstants.CIRCLE,
                    it1
                )
            })
            transformations(RoundedCornersTransformation(radius = 10f))
        }
        Log.d("TAG", "loadImage: url:$url name:$name")
    } else {
        view.load(name?.let {
            AvatarGenerator.avatarImage(view.context, 200, AvatarConstants.RECTANGLE, it)
        }) {
            crossfade(true)
            crossfade(300)
            transformations(RoundedCornersTransformation(radius = 10f))
        }
        Log.d("TAG", "loadImage: url:$url name:$name")
    }
}

@BindingAdapter(value = ["bind:isChecked"])
fun TextView.isChecked(isChecked: Boolean) {
    if (isChecked) {
        this.textColor = Color.parseColor("#039EFF")
    }
}

@BindingAdapter(value = ["bind:isChecked"])
fun ImageView.isChecked(isChecked: Boolean) {
    if (isChecked) {
        this.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_interest_colored))
    }
}