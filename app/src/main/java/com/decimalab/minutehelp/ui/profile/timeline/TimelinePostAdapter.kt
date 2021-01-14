package com.decimalab.minutehelp.ui.profile.timeline

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.decimalab.minutehelp.BR
import com.decimalab.minutehelp.R
import com.decimalab.minutehelp.data.local.entities.TimeLinePost
import com.decimalab.minutehelp.databinding.PostRowBinding
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.util.*

class TimelinePostAdapter(val postList: List<TimeLinePost>) : RecyclerView.Adapter<TimelinePostAdapter.MyViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = DataBindingUtil.inflate<PostRowBinding>(LayoutInflater.from(parent.context), R.layout.post_row, parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val post = postList[position]

        val parser = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
        val formatter = SimpleDateFormat("EEE, MMM d, 'at' hh:mm aaa")
        val output = formatter.format(parser.parse(post.createdAt))


        post.createdAt = output

        holder.bind(post)
    }

    override fun getItemCount(): Int {
        return postList.size
    }

    inner class MyViewHolder(val binding: PostRowBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(any: Any){
            binding.setVariable(BR.postModel, any)
            binding.executePendingBindings();
        }
    }
}