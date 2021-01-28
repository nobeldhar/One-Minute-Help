package com.decimalab.minutehelp.ui.comments

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.decimalab.minutehelp.BR
import com.decimalab.minutehelp.R
import com.decimalab.minutehelp.data.local.entities.Comment
import com.decimalab.minutehelp.databinding.RowCommentsBinding
import com.decimalab.minutehelp.utils.CustomOnClickListener

class CommentsAdapter(val commentList: List<Comment>, val customOnClickListener: CustomOnClickListener, val userId: Int) : RecyclerView.Adapter<CommentsAdapter.MyViewHolder>() {
    private lateinit var binding: RowCommentsBinding
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        binding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.row_comments, parent, false)
        return MyViewHolder(binding, customOnClickListener, commentList)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val comment = commentList[position]
        if (comment.childs.isNotEmpty()){
            holder.binding.hideReplies.visibility = View.VISIBLE
            holder.binding.childCommentRecycler.adapter = ChildCommentAdapter(comment.childs, customOnClickListener, comment, userId)
        }
        if (comment.user.id == userId){
            holder.binding.idUpdate.visibility = View.VISIBLE
        }

        holder.bind(comment)
    }

    override fun getItemCount(): Int {
        return commentList.size
    }

    class MyViewHolder(
        val binding: RowCommentsBinding,
        private val customOnClickListener: CustomOnClickListener,
        private val commentList: List<Comment>
    ) : RecyclerView.ViewHolder(binding.root), View.OnClickListener {


        fun bind(any: Any){
            binding.hideReplies.setOnClickListener(this)
            binding.setVariable(BR.comment, any)
            binding.setVariable(BR.listener, customOnClickListener)
            binding.executePendingBindings();
        }

        override fun onClick(v: View?) {
            Log.d(Companion.TAG, "onClick: hide replies clicked")
            val comment = commentList[adapterPosition]
            when(v?.id){
                binding.hideReplies.id -> {
                    Log.d(TAG, "onClick: hide reply inside")
                    if (comment.expanded) {
                        binding.childCommentRecycler.visibility = View.GONE
                        binding.hideReplies.text = "See Replies..."
                        comment.expanded = false
                        Log.d(TAG, "onClick: hide reply hidden")
                    } else {
                        binding.childCommentRecycler.visibility = View.VISIBLE
                        binding.hideReplies.text = "Hide Replies..."
                        comment.expanded = true
                        Log.d(TAG, "onClick: hide reply shown")
                    }
                }
            }
        }
    }


    companion object {
        private const val TAG = "CommentsAdapter"
    }
}