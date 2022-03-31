package com.example.pref1app2.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.coroutineScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pref1app2.adapters.TeamsStandingsAdapter
import com.example.pref1app2.databinding.FragmentTeamStandingsBinding
import com.example.pref1app2.viewModelFactory.StandingsViewModelFactory
import kotlinx.coroutines.launch

class TeamStandingsFragment : Fragment() {
    private lateinit var binding: FragmentTeamStandingsBinding
    private val viewModel: StandingsViewModel by activityViewModels {
        StandingsViewModelFactory(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentTeamStandingsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val standingsAdapter = TeamsStandingsAdapter(
            onClickDriver = {

            }
        )

        binding.teamsListRecyler.layoutManager = LinearLayoutManager(requireContext())
        binding.teamsListRecyler.adapter = standingsAdapter

        lifecycle.coroutineScope.launch {
            standingsAdapter.submitList(viewModel.listOfTeam)
        }
    }
}