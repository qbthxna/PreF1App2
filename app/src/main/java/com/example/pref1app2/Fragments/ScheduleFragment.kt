package com.example.pref1app2.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.coroutineScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pref1app2.adapters.SchedulesAdapter
import com.example.pref1app2.databinding.FragmentScheduleBinding
import com.example.pref1app2.viewModelFactory.ScheduleViewModelFactory
import kotlinx.coroutines.launch


class ScheduleFragment : Fragment() {

    private lateinit var binding: FragmentScheduleBinding

    private val viewModel: ScheduleViewModel by activityViewModels {
        ScheduleViewModelFactory(
            requireContext()
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment

        binding = FragmentScheduleBinding.inflate(inflater, container, false)


        //binding.imageView.setImageResource()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val scheduleAdapter = SchedulesAdapter(
            onClickTrack = {

            }
        )

        binding.scheduleListRecycler.layoutManager = LinearLayoutManager(requireContext())
        binding.scheduleListRecycler.adapter = scheduleAdapter

        lifecycle.coroutineScope.launch {
            scheduleAdapter.submitList(viewModel.listOfTracks)
        }


    }

    override fun onDestroy() {
        super.onDestroy()
    }


}