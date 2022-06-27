package com.example.androidcoursework.activities

import android.content.Context
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.androidcoursework.App
import com.example.androidcoursework.R
import com.example.androidcoursework.di.MainViewModel
import com.example.androidcoursework.domain.models.Character
import com.example.androidcoursework.domain.models.RecyclerViewItem
import com.example.androidcoursework.fragments.character.RecyclerViewItemAdapter
import com.squareup.picasso.Picasso
import javax.inject.Inject

class CharacterInfoActivity: AppCompatActivity() {

    @Inject
    lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val sharedPrefs = this.getSharedPreferences(getString(R.string.shared_prefs), Context.MODE_PRIVATE)
        val dbState = sharedPrefs.getBoolean("dbSwitch", false)

        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        App.appComponent.inject(this)
        setContentView(R.layout.character_info)

        val characterId = intent.getIntExtra("id", 0)
        viewModel.getCharacter(dbState, characterId)
        viewModel.character.observe(this){
            setParameters(it)
        }

    }

    private fun setParameters(character: Character){
        val image = findViewById<ImageView>(R.id.character_picture)
        Picasso.get().load(character.img_url).into(image)

        val name = findViewById<TextView>(R.id.character_name)
        name.text = "Name: " + character.name

        val status = findViewById<TextView>(R.id.character_status)
        status.text = "Status: " + character.status

        val species = findViewById<TextView>(R.id.character_species)
        species.text = "Species: " + character.species

        val gender = findViewById<TextView>(R.id.character_gender)
        gender.text = "Gender: " + character.gender

        val origin = findViewById<TextView>(R.id.character_origin)
        origin.text = "Origin: " + character.origin

        val alias = changeStringToRecyclerViewItem(character.alias)
        setAlias(alias)

        val abilities = changeStringToRecyclerViewItem(character.abilities)
        setAbilities(abilities)
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

    private fun setAlias(alias: List<RecyclerViewItem>){
        this.runOnUiThread{
            val recyclerView = findViewById<RecyclerView>(R.id.character_alias)
            val adapter = RecyclerViewItemAdapter()
            adapter.submitList(alias)
            recyclerView.adapter = adapter
        }
    }

    private fun setAbilities(abilities: List<RecyclerViewItem>){
        this.runOnUiThread(){
            val recyclerView = findViewById<RecyclerView>(R.id.character_abilities)
            val adapter = RecyclerViewItemAdapter()
            adapter.submitList(abilities)
            recyclerView.adapter = adapter
        }
    }
}