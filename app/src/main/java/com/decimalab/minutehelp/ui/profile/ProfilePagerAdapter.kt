package com.decimalab.minutehelp.ui.profile

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.decimalab.minutehelp.ui.profile.donatehistory.DonateHistoryFragment
import com.decimalab.minutehelp.ui.profile.settings.SettingsFragment
import com.decimalab.minutehelp.ui.profile.timeline.TimelineFragment

class ProfilePagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int {
        return 3
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> TimelineFragment()
            1 -> DonateHistoryFragment()
            2 -> SettingsFragment()
            else -> TimelineFragment()
        }
    }
}