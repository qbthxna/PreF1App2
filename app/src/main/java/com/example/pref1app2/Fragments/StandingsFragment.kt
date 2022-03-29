package com.example.pref1app2.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.coroutineScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pref1app2.R
import com.example.pref1app2.adapters.DriverStandingsAdapter
import com.example.pref1app2.adapters.TeamsAdapter
import com.example.pref1app2.databinding.FragmentScheduleBinding
import com.example.pref1app2.databinding.FragmentStandingsBinding
import com.example.pref1app2.viewModelFactory.StandingsViewModelFactory
import kotlinx.coroutines.launch


class StandingsFragment : Fragment() {


    private lateinit var binding: FragmentStandingsBinding
    private val viewModel: StandingsViewModel by activityViewModels {
        StandingsViewModelFactory(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentStandingsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val standingsAdapter = DriverStandingsAdapter(
            onClickDriver = {

            }
        )

        binding.scheduleListRecycler.layoutManager = LinearLayoutManager(requireContext())
        binding.scheduleListRecycler.adapter = standingsAdapter

        lifecycle.coroutineScope.launch {
            standingsAdapter.submitList(viewModel.listOfDriver)
        }
    }

}