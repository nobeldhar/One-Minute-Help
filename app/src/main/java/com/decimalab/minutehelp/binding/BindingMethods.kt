package com.decimalab.minutehelp.binding

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView


@BindingAdapter("android:src")
fun setImageViewResource(imageView: ImageView, resource_name: String?) {
    val resources = imageView.context.resources
    resource_name?.let {
         val resourceId = resources.getIdentifier(resource_name, "drawable", imageView.context.packageName)
        imageView.setImageResource(resourceId) }

}

@BindingAdapter(value = ["setAdapter"])
fun RecyclerView.bindRecyclerViewAdapter(adapter: RecyclerView.Adapter<*>) {
    this.run {
        this.setHasFixedSize(true)
        this.adapter = adapter
    }
}
/*
@BindingAdapter("setData")
fun RecyclerView.bindDataWithRecycler(data: List<Item>?){
    this.adapter = data?.let { ItemAdapter(it) }
}*/
