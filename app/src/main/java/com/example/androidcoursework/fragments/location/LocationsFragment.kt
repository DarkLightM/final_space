package com.example.androidcoursework.fragments.location

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.androidcoursework.App
import com.example.androidcoursework.R
import com.example.androidcoursework.di.MainViewModel
import com.example.androidcoursework.domain.models.Episode
import com.example.androidcoursework.domain.models.Location
import com.example.androidcoursework.fragments.episode.EpisodeAdapter
import javax.inject.Inject

class LocationsFragment : Fragment() {
    @Inject
    lateinit var viewModel: MainViewModel

    private lateinit var viewGlobal: View

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        App.appComponent.inject(this)
        return inflater.inflate(R.layout.fragment_locations, container, false)
    }

    override fun onResume() {
        super.onResume()
        val sharedPreferences =
            activity?.getSharedPreferences(getString(R.string.shared_prefs), Context.MODE_PRIVATE)
                ?: return
        val dbState = sharedPreferences.getBoolean("dbSwitch", false)
        viewModel.getLocations(dbState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        viewGlobal = view
        viewModel.locations.observe(viewLifecycleOwner) {
            setCharacters(it)
        }
    }

    private fun setCharacters(locations: List<Location>) {
        activity?.runOnUiThread {
            val recyclerViewCards = viewGlobal.findViewById<RecyclerView>(R.id.location_list)
            recyclerViewCards.layoutManager = GridLayoutManager(this.context, 3)
            val adapter = LocationAdapter()
            adapter.submitList(locations)
            recyclerViewCards.adapter = adapter
        }
    }
}