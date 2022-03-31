package com.example.pref1app2.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.pref1app2.adapters.ViewPager2Adapter
import com.example.pref1app2.databinding.FragmentStandingsBinding
import com.example.pref1app2.viewModelFactory.StandingsViewModelFactory
import com.google.android.material.tabs.TabLayoutMediator


class StandingsFragment : Fragment() {


    private lateinit var binding: FragmentStandingsBinding
    private val viewModel: StandingsViewModel by activityViewModels {
        StandingsViewModelFactory(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentStandingsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = ViewPager2Adapter(requireActivity().supportFragmentManager, lifecycle)

        binding.standingTabLayout

        binding.standingPager.adapter = adapter

        TabLayoutMediator(binding.standingTabLayout, binding.standingPager) { tab, position ->
            when (position) {
                0 -> {
                    tab.text = "Drivers"
                }
                1 -> {
                    tab.text = "Teams"
                }
                else -> {

                }
            }
        }.attach()


    }

}


