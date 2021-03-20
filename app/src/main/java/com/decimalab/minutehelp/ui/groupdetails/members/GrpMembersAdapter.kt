package com.decimalab.minutehelp.ui.groupdetails.members

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.decimalab.minutehelp.BR
import com.decimalab.minutehelp.R
import com.decimalab.minutehelp.data.local.entities.TimeLinePost
import com.decimalab.minutehelp.data.remote.responses.TimeLineResponse2
import com.decimalab.minutehelp.databinding.RowMemberBinding

class GrpMembersAdapter(val users: List<TimeLinePost.User>) : RecyclerView.Adapter<GrpMembersAdapter.MyViewHolder>() {

    val bloodList = listOf<String>("A+", "A-", "B+", "B-", "O+", "O-", "AB+", "AB-")
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = DataBindingUtil.inflate<RowMemberBinding>(LayoutInflater.from(parent.context), R.layout.row_member, parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val user = users[position]
        holder.bind(user)
    }

    override fun getItemCount(): Int {
        return users.size
    }

    class MyViewHolder(val binding: RowMemberBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(any: Any){
            binding.setVariable(BR.user, any)
            binding.executePendingBindings()
        }
    }
}