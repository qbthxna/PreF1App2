package com.example.pref1app2.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.pref1app2.R
import com.example.pref1app2.data.TracksItem
import com.example.pref1app2.databinding.ScheduleItemBinding

class SchedulesAdapter(
    private val onClickTrack: (TracksItem) -> Unit,
    private val onClickDate: (TracksItem) -> Unit

) : ListAdapter<TracksItem, SchedulesAdapter.ScheduleListViewHolder>(DiffCallBack) {

    companion object DiffCallBack : DiffUtil.ItemCallback<TracksItem>() {
        override fun areItemsTheSame(oldItem: TracksItem, newItem: TracksItem): Boolean {
            return oldItem.event == newItem.event
        }

        override fun areContentsTheSame(oldItem: TracksItem, newItem: TracksItem): Boolean {
            return oldItem.date == newItem.date
        }

    }

    class ScheduleListViewHolder(private val binding: ScheduleItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(tracksItem: TracksItem, onClickDate: (TracksItem) -> Unit) {

//            binding.deleteButton.setOnClickListener { onClickDelete(meal) }

            binding.date.setOnClickListener {
                onClickDate(tracksItem)
            }


            binding.country.text = tracksItem.country
            binding.date.text = tracksItem.date
            binding.fullName.text = tracksItem.event

            binding.lenght.text = "Lenght: ${tracksItem.circuitLength.toString()} km"
            binding.record.text = "Track Record: ${tracksItem.lapRecord}"

            val countryFlagUrl =
                "https://www.countryflags.com/wp-content/uploads/${
                    tracksItem.country.lowercase().replace(" ", "-", true)
                }-flag-png-large.png"
            binding.countryFlag.load(countryFlagUrl) {
                placeholder(R.drawable.loading_animation)
                error(R.drawable.ic_baseline_broken_image_24)
            }

            binding.trackImage.load(tracksItem.image) {
                placeholder(R.drawable.loading_animation)
                error(R.drawable.ic_baseline_broken_image_24)
            }


        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScheduleListViewHolder {
        val viewHolder = ScheduleListViewHolder(
            ScheduleItemBinding.inflate(
                LayoutInflater.from(parent.context)//, parent, false
            )
        )
        // Itemin tamami
        viewHolder.itemView.setOnClickListener {
            val position = viewHolder.adapterPosition
            onClickTrack(getItem(position))
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: ScheduleListViewHolder, position: Int) {
        holder.bind(getItem(position), onClickDate)
    }
}