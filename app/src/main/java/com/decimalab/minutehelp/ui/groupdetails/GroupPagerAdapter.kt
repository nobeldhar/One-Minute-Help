package com.decimalab.minutehelp.ui.groupdetails

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.decimalab.minutehelp.ui.groupdetails.members.GroupMembersFragment
import com.decimalab.minutehelp.ui.groupdetails.settings.GroupSettingsFragment
import com.decimalab.minutehelp.ui.groupdetails.timeline.GroupTimelineFragment

class GroupPagerAdapter (fragment: Fragment, val groupId: Int,  val isAdmin: Boolean = false) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int {
        return if (isAdmin) 3 else 2
    }

    override fun createFragment(position: Int): Fragment {
        return if (isAdmin) when (position) {
            0 -> GroupTimelineFragment(groupId)
            1 -> GroupMembersFragment(groupId)
            2 -> GroupSettingsFragment(groupId)
            else -> GroupTimelineFragment(groupId)
        } else when (position) {
            0 -> GroupTimelineFragment(groupId)
            1 -> GroupMembersFragment(groupId)
            else -> GroupTimelineFragment(groupId)
        }
    }
}