package com.example.pref1app2.adapters

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.pref1app2.data.DriverStandingsItem
import com.example.pref1app2.databinding.DriverStandingsItemBinding


class DriverStandingsAdapter(
    private val onClickDriver: (DriverStandingsItem) -> Unit
) : ListAdapter<DriverStandingsItem, DriverStandingsAdapter.DriverStandingViewHolder>(
    DiffCallBack
) {

    companion object DiffCallBack : DiffUtil.ItemCallback<DriverStandingsItem>() {
        override fun areItemsTheSame(
            oldItem: DriverStandingsItem,
            newItem: DriverStandingsItem
        ): Boolean {
            return oldItem.driver == newItem.driver
        }

        override fun areContentsTheSame(
            oldItem: DriverStandingsItem,
            newItem: DriverStandingsItem
        ): Boolean {
            return oldItem.driver == newItem.driver
        }

    }

    class DriverStandingViewHolder(private val binding: DriverStandingsItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(driverStandingsItem: DriverStandingsItem) {
            binding.driverName.text = driverStandingsItem.driver
            binding.driverNumber.text = driverStandingsItem.driverNumber.toString()
            binding.points.text = "${driverStandingsItem.points} pts"
            binding.driverTeam.text = driverStandingsItem.team
            binding.divider1.setBackgroundColor(Color.parseColor(driverStandingsItem.color))
            binding.driverNumber.setTextColor(Color.parseColor(driverStandingsItem.color))
            binding.standingNumber.text = (adapterPosition + 1).toString()
        }
    }


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): DriverStandingViewHolder {
        val viewHolder = DriverStandingViewHolder(
            DriverStandingsItemBinding.inflate(
                LayoutInflater.from(parent.context)
            )
        )
        viewHolder.itemView.setOnClickListener {
            val position = viewHolder.adapterPosition
            onClickDriver(getItem(position))
        }
        return viewHolder
    }

    override fun onBindViewHolder(
        holder: DriverStandingViewHolder,
        position: Int
    ) {
        holder.bind(getItem(position))
    }
}