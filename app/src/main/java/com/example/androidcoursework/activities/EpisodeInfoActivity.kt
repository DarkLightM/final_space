package com.example.androidcoursework.activities

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.androidcoursework.App
import com.example.androidcoursework.R
import com.example.androidcoursework.di.MainViewModel
import com.example.androidcoursework.domain.models.Character
import com.example.androidcoursework.domain.models.Episode
import com.example.androidcoursework.fragments.character.CharacterAdapter
import com.squareup.picasso.Picasso
import javax.inject.Inject

class EpisodeInfoActivity : AppCompatActivity() {
    @Inject
    lateinit var viewModel: MainViewModel

    private val nameToImage = mapOf(
        "Mike Roberts" to R.drawable.mike_roberts,
        "Olan Rogers" to R.drawable.olan_rogers,
        "Anne Walker Farrell" to R.drawable.anne_walker,
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val sharedPrefs =
            this.getSharedPreferences(getString(R.string.shared_prefs), Context.MODE_PRIVATE)
        val dbState = sharedPrefs.getBoolean("dbSwitch", false)

        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        App.appComponent.inject(this)
        setContentView(R.layout.episode_info)

        val necessaryCharacters = mutableListOf<Character>()

        val episodeId = intent.getIntExtra("id", 0)
        viewModel.getEpisode(dbState, episodeId)
        viewModel.episode.observe(this) {
            val characters = it.characters
            for (character in characters) {
                val id = character.substring(43)
                viewModel.getCharacter(dbState, id.toInt())
                viewModel.character.observe(this) { item ->
                    Log.e("Character", item.toString())
                    if (!necessaryCharacters.contains(item))
                        necessaryCharacters.add(item)
                    setCharacters(necessaryCharacters.toList())
                }
            }
            setParameters(it)
        }
    }


    private fun setParameters(episode: Episode) {
        val name = findViewById<TextView>(R.id.episode_name)
        name.text = "Episode name " + episode.name

        val airDate = findViewById<TextView>(R.id.episode_air_date)
        airDate.text = "Air Date " + episode.air_date

        val director = findViewById<TextView>(R.id.episode_director)
        director.text = episode.director

        val writer = findViewById<TextView>(R.id.episode_writer)
        writer.text = episode.writer

        val directorImage = findViewById<ImageView>(R.id.director_picture)
        setImage(episode.director, directorImage)

        val writerImage = findViewById<ImageView>(R.id.writer_picture)
        setImage(episode.writer, writerImage)
    }

    private fun setImage(name: String?, imageView: ImageView) {
        if (!nameToImage.containsKey(name))
            Picasso.get().load(R.drawable.no_photo).into(imageView)
        else
            Picasso.get().load(nameToImage[name]!!).into(imageView)
    }

    private fun setCharacters(characters: List<Character>) {
        val recyclerViewCards = findViewById<RecyclerView>(R.id.characters)
        recyclerViewCards.layoutManager = GridLayoutManager(this, 3)
        val adapter = CharacterAdapter()
        adapter.submitList(characters)
        recyclerViewCards.adapter = adapter
    }
}