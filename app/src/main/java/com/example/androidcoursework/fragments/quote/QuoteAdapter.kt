package com.example.androidcoursework.fragments.quote

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.androidcoursework.R
import com.example.androidcoursework.domain.models.Location
import com.example.androidcoursework.domain.models.Quote
import com.squareup.picasso.Picasso

class QuoteAdapter :
    androidx.recyclerview.widget.ListAdapter<Quote, QuoteAdapter.ViewHolder>(QuoteDiffCallback()) {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val badUrls = listOf(
            "https://finalspaceapi.com/api/character/avatar/lord_commander.jpg",
            "https://finalspaceapi.com/api/character/avatar/ash_graven.jpg"
        )
        private val quotePicture = view.findViewById<ImageView>(R.id.quotePicture)
        private val quoteName = view.findViewById<TextView>(R.id.quoteName)
        private val author = view.findViewById<TextView>(R.id.saidBy)

        fun bind(quote: Quote) {
            author.text = quote.by
            if (quote.image in badUrls) {
                Picasso.get().load("https://i.ibb.co/ygCKswC/final-space.png").into(quotePicture)
            }
            else
                Picasso.get().load(quote.image).into(quotePicture)
            quoteName.text = quote.quote
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.quote_card, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(currentList[position])
    }

    class QuoteDiffCallback : DiffUtil.ItemCallback<Quote>() {
        override fun areItemsTheSame(oldItem: Quote, newItem: Quote): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Quote, newItem: Quote): Boolean =
            oldItem == newItem
    }
}