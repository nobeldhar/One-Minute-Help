package com.decimalab.minutehelp.ui.dashboard

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.decimalab.minutehelp.ui.dashboard.allposts.AllPostsFragment
import com.decimalab.minutehelp.ui.dashboard.members.MembersFragment
import com.decimalab.minutehelp.ui.dashboard.topdoner.TopDonorsFragment
import com.decimalab.minutehelp.ui.dashboard.group.GroupFragment

class DashboardPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int {
        return 4
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> AllPostsFragment()
            1 -> MembersFragment()
            2 -> GroupFragment()
            3 -> TopDonorsFragment()
            else -> AllPostsFragment()
        }
    }
}