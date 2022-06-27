package com.example.androidcoursework.fragments.character

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.androidcoursework.App
import com.example.androidcoursework.R
import com.example.androidcoursework.di.MainViewModel
import com.example.androidcoursework.domain.models.Character
import javax.inject.Inject

class CharactersFragment : Fragment() {

    @Inject
    lateinit var viewModel: MainViewModel

    private lateinit var viewGlobal: View

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        App.appComponent.inject(this)
        return inflater.inflate(R.layout.fragment_character, container, false)
    }

    override fun onResume() {
        super.onResume()
        val sharedPreferences =
            activity?.getSharedPreferences(getString(R.string.shared_prefs), Context.MODE_PRIVATE)
                ?: return
        val dbState = sharedPreferences.getBoolean("dbSwitch", false)
        viewModel.getCharacters(dbState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        viewModel.characters.observe(viewLifecycleOwner) {
            setCharacters(it)
        }
        viewGlobal = view
    }

    private fun setCharacters(characters: List<Character>) {
        activity?.runOnUiThread {
            val recyclerViewCards = viewGlobal.findViewById<RecyclerView>(R.id.character_list)
            recyclerViewCards.layoutManager = GridLayoutManager(this.context, 3)
            val adapter = CharacterAdapter()
            adapter.submitList(characters)
            recyclerViewCards.adapter = adapter
        }
    }
}