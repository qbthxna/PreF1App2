package com.example.pref1app2.adapters

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.pref1app2.data.TeamStandingItem
import com.example.pref1app2.databinding.TeamsStandingsItemBinding


class TeamsStandingsAdapter(
    private val onClickDriver: (TeamStandingItem) -> Unit
) : ListAdapter<TeamStandingItem, TeamsStandingsAdapter.TeamsStandingViewHolder>(
    DiffCallBack
) {

    companion object DiffCallBack : DiffUtil.ItemCallback<TeamStandingItem>() {
        override fun areItemsTheSame(
            oldItem: TeamStandingItem,
            newItem: TeamStandingItem
        ): Boolean {
            return oldItem.teamName == newItem.teamName
        }

        override fun areContentsTheSame(
            oldItem: TeamStandingItem,
            newItem: TeamStandingItem
        ): Boolean {
            return oldItem.teamColor == newItem.teamColor
        }

    }

    class TeamsStandingViewHolder(private val binding: TeamsStandingsItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(teamStandingItem: TeamStandingItem) {
            binding.divider1.setBackgroundColor(Color.parseColor(teamStandingItem.teamColor))
            binding.teamName.text = teamStandingItem.teamName
            binding.points.text = "${teamStandingItem.point} pts"
            binding.standingNumber.text = (adapterPosition + 1).toString()
        }
    }


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TeamsStandingViewHolder {
        val viewHolder = TeamsStandingViewHolder(
            TeamsStandingsItemBinding.inflate(
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
        holder: TeamsStandingViewHolder,
        position: Int
    ) {
        holder.bind(getItem(position))
    }
}