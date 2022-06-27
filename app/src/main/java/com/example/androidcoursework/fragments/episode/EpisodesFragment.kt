package com.example.androidcoursework.fragments.episode

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.androidcoursework.App
import com.example.androidcoursework.R
import com.example.androidcoursework.di.MainViewModel
import com.example.androidcoursework.domain.models.Character
import com.example.androidcoursework.domain.models.Episode
import com.example.androidcoursework.fragments.character.CharacterAdapter
import javax.inject.Inject

class EpisodesFragment : Fragment() {

    @Inject
    lateinit var viewModel: MainViewModel

    private lateinit var viewGlobal: View

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        App.appComponent.inject(this)
        return inflater.inflate(R.layout.fragment_episodes, container, false)
    }

    override fun onResume() {
        super.onResume()
        val sharedPreferences =
            activity?.getSharedPreferences(getString(R.string.shared_prefs), Context.MODE_PRIVATE)
                ?: return
        val dbState = sharedPreferences.getBoolean("dbSwitch", false)
        viewModel.getEpisodes(dbState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        viewGlobal = view
        viewModel.episodes.observe(viewLifecycleOwner) {
            setCharacters(it)
        }
    }

    private fun setCharacters(episodes: List<Episode>) {
        activity?.runOnUiThread {
            val recyclerViewCards = viewGlobal.findViewById<RecyclerView>(R.id.episode_list)
            val adapter = EpisodeAdapter()
            adapter.submitList(episodes)
            recyclerViewCards.adapter = adapter
        }
    }
}