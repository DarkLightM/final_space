package com.example.androidcoursework.fragments.episode

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.androidcoursework.R
import com.example.androidcoursework.activities.EpisodeInfoActivity
import com.example.androidcoursework.domain.models.Episode

class EpisodeAdapter :
    androidx.recyclerview.widget.ListAdapter<Episode, EpisodeAdapter.ViewHolder>(EpisodeDiffCallback()) {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val episodeName = view.findViewById<TextView>(R.id.episodeName)
        private val episodeDirector = view.findViewById<TextView>(R.id.directorName)
        private val episodeAirDate = view.findViewById<TextView>(R.id.airDate)
        private val divider: View = view.findViewById(R.id.divider)

        fun bind(episode: Episode, isLastItem: Boolean) {
            episodeName.text = episode.name
            episodeDirector.text = episode.director
            episodeAirDate.text = episode.air_date

            if (isLastItem) {
                divider.visibility = View.GONE
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.episode_card, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(currentList[position], position == (itemCount - 1))
        holder.itemView.setOnClickListener {
            val intent = Intent(it.context, EpisodeInfoActivity::class.java)
            intent.putExtra("id", currentList[position].id)
            it.context.startActivity(intent)
        }
    }

    class EpisodeDiffCallback : DiffUtil.ItemCallback<Episode>() {
        override fun areItemsTheSame(oldItem: Episode, newItem: Episode): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Episode, newItem: Episode): Boolean =
            oldItem == newItem
    }
}