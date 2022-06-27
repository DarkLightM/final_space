package com.example.androidcoursework.fragments.character

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.androidcoursework.R
import com.example.androidcoursework.activities.CharacterInfoActivity
import com.example.androidcoursework.domain.models.Character
import com.squareup.picasso.Picasso

class CharacterAdapter :
    ListAdapter<Character, CharacterAdapter.ViewHolder>(CharacterDiffCallback()) {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val characterPicture = view.findViewById<ImageButton>(R.id.characterPicture)
        private val characterName = view.findViewById<TextView>(R.id.characterName)

        fun bind(character: Character) {
            Picasso.get().load(character.img_url).into(characterPicture)
            characterName.text = character.name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.character_card, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(currentList[position])
        holder.itemView.setOnClickListener {
            val intent = Intent(it.context, CharacterInfoActivity::class.java)
            intent.putExtra("id", currentList[position].id)
            it.context.startActivity(intent)
        }
    }

    class CharacterDiffCallback : DiffUtil.ItemCallback<Character>() {
        override fun areItemsTheSame(oldItem: Character, newItem: Character): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Character, newItem: Character): Boolean =
            oldItem == newItem
    }
}
