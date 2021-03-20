package com.decimalab.minutehelp.ui.dashboard.group

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.decimalab.minutehelp.BR
import com.decimalab.minutehelp.R
import com.decimalab.minutehelp.data.local.entities.Group
import com.decimalab.minutehelp.data.local.entities.Post
import com.decimalab.minutehelp.databinding.RowGroupBinding
import com.decimalab.minutehelp.utils.CustomOnClickListener

class GroupsAdapter(val groupList: List<Group>, val customOnClickListener: CustomOnClickListener) : RecyclerView.Adapter<GroupsAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = DataBindingUtil.inflate<RowGroupBinding>(LayoutInflater.from(parent.context), R.layout.row_group, parent, false)
        return MyViewHolder(binding, customOnClickListener)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val group = groupList[position]
        holder.bind(group)
    }

    override fun getItemCount(): Int {
        return groupList.size
    }

    class MyViewHolder(val binding: RowGroupBinding, val customOnClickListener: CustomOnClickListener) : RecyclerView.ViewHolder(binding.root) {
        fun bind(any: Any){
            binding.setVariable(BR.group, any)
            binding.setVariable(BR.listener, customOnClickListener)
            binding.executePendingBindings();
        }
    }
}