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
import com.example.pref1app2.adapters.SchedulesAdapter
import com.example.pref1app2.adapters.TeamsAdapter
import com.example.pref1app2.databinding.FragmentScheduleBinding
import com.example.pref1app2.databinding.FragmentTeamsBinding
import com.example.pref1app2.viewModelFactory.TeamsViewModelFactory
import kotlinx.coroutines.launch


class TeamsFragment : Fragment() {


    private lateinit var binding: FragmentTeamsBinding
    private val viewModel: TeamsViewModel by activityViewModels {
        TeamsViewModelFactory(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentTeamsBinding.inflate(inflater, container, false)


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val teamsAdapter = TeamsAdapter(
            onClickTeams = {

            }
        )

        binding.scheduleListRecycler.layoutManager = LinearLayoutManager(requireContext())
        binding.scheduleListRecycler.adapter = teamsAdapter

        lifecycle.coroutineScope.launch {
            teamsAdapter.submitList(viewModel.listOfTeams)
        }
    }


}