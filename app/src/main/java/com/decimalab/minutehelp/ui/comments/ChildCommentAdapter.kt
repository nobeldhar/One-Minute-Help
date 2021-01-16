package com.decimalab.minutehelp.ui.comments

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.decimalab.minutehelp.BR
import com.decimalab.minutehelp.R
import com.decimalab.minutehelp.data.local.entities.Comment
import com.decimalab.minutehelp.databinding.RowChildBinding
import com.decimalab.minutehelp.utils.CustomOnClickListener

class ChildCommentAdapter(val childList: List<Comment.Child>,
                          val customOnClickListener: CustomOnClickListener,
                          val comment: Comment):
    RecyclerView.Adapter<ChildCommentAdapter.MyViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = DataBindingUtil.inflate<RowChildBinding>(LayoutInflater.from(parent.context), R.layout.row_child, parent, false)
        return MyViewHolder(binding, customOnClickListener, comment)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val child = childList[position]
        holder.bind(child)
    }

    override fun getItemCount(): Int {
        return childList.size
    }

    class MyViewHolder(
        val binding: RowChildBinding,
        val customOnClickListener: CustomOnClickListener,
        val comment: Comment
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(any: Any){
            binding.setVariable(BR.child, any)
            binding.setVariable(BR.listener, customOnClickListener)
            binding.setVariable(BR.comment, comment)
            binding.executePendingBindings();
        }
    }
}