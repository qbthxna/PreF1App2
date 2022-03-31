package com.example.pref1app2.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.pref1app2.fragments.DriverStandingsFragment
import com.example.pref1app2.fragments.TeamStandingsFragment

class ViewPager2Adapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(fragmentManager, lifecycle) {

    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> {
                DriverStandingsFragment()
            }
            1 -> {
                TeamStandingsFragment()
            }
            else -> {
                Fragment()
            }
        }
    }
}