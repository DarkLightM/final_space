package com.example.androidcoursework.fragments.character

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.androidcoursework.R
import com.example.androidcoursework.domain.models.Character
import com.example.androidcoursework.domain.models.RecyclerViewItem
import com.squareup.picasso.Picasso

class RecyclerViewItemAdapter :
    ListAdapter<RecyclerViewItem, RecyclerViewItemAdapter.ViewHolder>(ItemDiffCallback()) {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val text = view.findViewById<TextView>(R.id.recycler_text)
        fun bind(recyclerViewItem: RecyclerViewItem) {
            Log.e("View", itemView.toString())
            text.text = recyclerViewItem.text
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.recycler_view_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(currentList[position])
    }

    class ItemDiffCallback : DiffUtil.ItemCallback<RecyclerViewItem>() {
        override fun areItemsTheSame(oldItem: RecyclerViewItem, newItem: RecyclerViewItem): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: RecyclerViewItem, newItem: RecyclerViewItem): Boolean =
            oldItem == newItem
    }
}