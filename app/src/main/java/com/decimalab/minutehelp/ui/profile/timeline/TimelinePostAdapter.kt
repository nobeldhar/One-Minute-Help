package com.decimalab.minutehelp.ui.profile.timeline

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.decimalab.minutehelp.BR
import com.decimalab.minutehelp.R
import com.decimalab.minutehelp.data.local.entities.TimeLinePost
import com.decimalab.minutehelp.databinding.PostRowBinding
import com.decimalab.minutehelp.databinding.RowTimelinePostBinding
import com.decimalab.minutehelp.utils.CustomOnClickListener
import org.jetbrains.anko.textColor
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.util.*

class TimelinePostAdapter(val postList: List<TimeLinePost>, val customOnClickListener: CustomOnClickListener) : RecyclerView.Adapter<TimelinePostAdapter.MyViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = DataBindingUtil.inflate<RowTimelinePostBinding>(LayoutInflater.from(parent.context), R.layout.row_timeline_post, parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val post = postList[position]
        holder.bind(post, customOnClickListener)
    }

    override fun getItemCount(): Int {
        return postList.size
    }

    inner class MyViewHolder(val binding: RowTimelinePostBinding) : RecyclerView.ViewHolder(binding.root),
        View.OnClickListener {

        fun bind(timeLinePost: TimeLinePost, customOnClickListener: CustomOnClickListener){
            binding.interested.setOnClickListener(this)
            binding.setVariable(BR.postModel, timeLinePost)
            binding.setVariable(BR.listener, customOnClickListener)
            binding.executePendingBindings();
        }

        override fun onClick(v: View?) {
            when(v){
                binding.interested->{
                    val post = postList[adapterPosition]
                    if (post.isLiked){
                        post.isLiked = false
                        binding.icInterest.setImageDrawable(ContextCompat.getDrawable(v.context, R.drawable.ic_interest_raw))
                        binding.interested.textColor = Color.parseColor("#7B7B7B")
                        binding.tbLkCt.text = (post.like_count ).toString()
                    } else{
                        post.isLiked = true
                        binding.icInterest.setImageDrawable(ContextCompat.getDrawable(v.context, R.drawable.ic_interest_colored))
                        binding.interested.textColor = Color.parseColor("#039EFF")
                        binding.tbLkCt.text = (post.like_count + 1).toString()
                    }
                    customOnClickListener.onTMInterestedClicked(post)
                }
            }
        }
    }


}