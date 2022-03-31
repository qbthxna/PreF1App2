package com.example.pref1app2.Fragments

import android.content.Intent
import android.os.Bundle
import android.provider.CalendarContract
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
import java.util.*


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

            },
            onClickDate = {
                val dateListString: List<String> = it.dateForCal.split("-")
                val dateListLong = dateListString.map { a -> a.toDouble() }
                val startMilis = Calendar.getInstance().run {
                    set(dateListLong[2].toInt(), dateListLong[1].toInt()-1, dateListLong[0].toInt())
                    timeInMillis
                }
                val endMilis = Calendar.getInstance().run {
                    set(dateListLong[2].toInt(), dateListLong[1].toInt()-1, dateListLong[0].toInt()+2)
                    timeInMillis
                }
                val intent = Intent(Intent.ACTION_INSERT)
                    .setData(CalendarContract.Events.CONTENT_URI)
                    .putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, startMilis)
                    .putExtra(CalendarContract.EXTRA_EVENT_END_TIME, endMilis)
                    .putExtra(CalendarContract.Events.TITLE, it.event)
                    .putExtra(CalendarContract.Events.DESCRIPTION, it.country)
                    .putExtra(CalendarContract.Events.EVENT_LOCATION, it.location)
                startActivity(intent)

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