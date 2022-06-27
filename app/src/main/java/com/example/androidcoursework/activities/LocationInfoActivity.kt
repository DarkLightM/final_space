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
import com.example.androidcoursework.domain.models.Location
import com.example.androidcoursework.domain.models.RecyclerViewItem
import com.example.androidcoursework.fragments.character.CharacterAdapter
import com.example.androidcoursework.fragments.character.RecyclerViewItemAdapter
import com.squareup.picasso.Picasso
import javax.inject.Inject

class LocationInfoActivity: AppCompatActivity() {
    @Inject
    lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val sharedPrefs =
            this.getSharedPreferences(getString(R.string.shared_prefs), Context.MODE_PRIVATE)
        val dbState = sharedPrefs.getBoolean("dbSwitch", false)

        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        App.appComponent.inject(this)
        setContentView(R.layout.location_info)

        val locationId = intent.getIntExtra("id", 0)
        viewModel.getLocation(dbState,locationId)

        val necessaryCharacters = mutableListOf<Character>()

        viewModel.location.observe(this){
            setParameters(it)
            val characters = it.notable_residents
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
        }
    }

    private fun setCharacters(characters: List<Character>) {
        val recyclerViewCards = findViewById<RecyclerView>(R.id.location_notable_residents)
        recyclerViewCards.layoutManager = GridLayoutManager(this, 3)
        val adapter = CharacterAdapter()
        adapter.submitList(characters)
        recyclerViewCards.adapter = adapter
    }

    private fun setParameters(location: Location){
        val image = findViewById<ImageView>(R.id.location_image)
        Picasso.get().load(location.img_url).into(image)

        val name = findViewById<TextView>(R.id.location_name)
        name.text = "Name: " + location.name

        val type = findViewById<TextView>(R.id.location_type)
        type.text = "Type: " + location.type

        val inhabitants = changeStringToRecyclerViewItem(location.inhabitants)
        setInhabitants(inhabitants)
    }

    private fun setInhabitants(inhabitants: List<RecyclerViewItem>){
        this.runOnUiThread{
            val recyclerView = findViewById<RecyclerView>(R.id.location_inhabitants)
            val adapter = RecyclerViewItemAdapter()
            adapter.submitList(inhabitants)
            recyclerView.adapter = adapter
        }
    }

    private fun changeStringToRecyclerViewItem(list: MutableList<String>?): MutableList<RecyclerViewItem>{
        val result: MutableList<RecyclerViewItem> = mutableListOf()
        if (list != null) {
            for ((id, item) in list.withIndex()) {
                val newItem = RecyclerViewItem(id, item)
                result.add(newItem)
            }
        }
        return result
    }
}