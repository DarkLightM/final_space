package com.example.androidcoursework.fragments.location

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.androidcoursework.R
import com.example.androidcoursework.domain.models.Location
import com.example.androidcoursework.activities.LocationInfoActivity
import com.squareup.picasso.Picasso

class LocationAdapter :
    androidx.recyclerview.widget.ListAdapter<Location, LocationAdapter.ViewHolder>(LocationDiffCallback()) {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val locationPicture = view.findViewById<ImageView>(R.id.locationPicture)
        private val locationName = view.findViewById<TextView>(R.id.locationName)

        fun bind(location: Location) {
            Picasso.get().load(location.img_url).into(locationPicture)
            locationName.text = location.name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.location_card, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(currentList[position])
        holder.itemView.setOnClickListener {
            val intent = Intent(it.context, LocationInfoActivity::class.java)
            intent.putExtra("id", currentList[position].id)
            it.context.startActivity(intent)
        }
    }

    class LocationDiffCallback : DiffUtil.ItemCallback<Location>() {
        override fun areItemsTheSame(oldItem: Location, newItem: Location): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Location, newItem: Location): Boolean =
            oldItem == newItem
    }
}