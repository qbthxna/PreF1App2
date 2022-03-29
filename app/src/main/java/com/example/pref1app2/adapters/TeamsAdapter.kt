package com.example.pref1app2.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.pref1app2.R
import com.example.pref1app2.data.TeamsItem
import com.example.pref1app2.databinding.TeamsItemBinding

class TeamsAdapter(
    private val onClickTeams: (TeamsItem) -> Unit
) : ListAdapter<TeamsItem, TeamsAdapter.TeamListViewHolder>(DiffCallBack) {


    companion object DiffCallBack : DiffUtil.ItemCallback<TeamsItem>() {

        override fun areItemsTheSame(oldItem: TeamsItem, newItem: TeamsItem): Boolean {
            return oldItem.teamName == newItem.teamName
        }

        override fun areContentsTheSame(oldItem: TeamsItem, newItem: TeamsItem): Boolean {
            return oldItem.engine == newItem.engine
        }

    }

    class TeamListViewHolder(private val binding: TeamsItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(teamsItem: TeamsItem) {
            binding.teamName.text = teamsItem.teamName
            binding.engineName.text = teamsItem.engine
            binding.driver1.text = teamsItem.driver1
            binding.driver2.text = teamsItem.driver2
            binding.driver1Number.text = teamsItem.driver1number.toString()
            binding.driver2Number.text = teamsItem.driver2number.toString()

            binding.teamImage.load(teamsItem.car) {
                placeholder(R.drawable.loading_animation)
                error(R.drawable.ic_baseline_broken_image_24)
            }
            binding.teamLogo.load(teamsItem.logo) {
                placeholder(R.drawable.loading_animation)
                error(R.drawable.ic_baseline_broken_image_24)
            }
        }

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TeamListViewHolder {
        val viewHolder = TeamListViewHolder(
            TeamsItemBinding.inflate(
                LayoutInflater.from(parent.context)
            )
        )
        viewHolder.itemView.setOnClickListener {
            val position = viewHolder.adapterPosition
            onClickTeams(getItem(position))
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: TeamListViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}