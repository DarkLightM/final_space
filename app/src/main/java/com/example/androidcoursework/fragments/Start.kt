package com.example.androidcoursework.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.androidcoursework.R
import com.example.androidcoursework.di.MainViewModel
import javax.inject.Inject

class Start : Fragment() {

    @Inject
    lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        viewModel.fillDatabase()
        val view = inflater.inflate(R.layout.fragment_start, container, false)
        val charactersButton = view.findViewById<Button>(R.id.characters_button)
        charactersButton.setOnClickListener {
            findNavController().navigate(R.id.action_start3_to_characterFragment2)
        }

        val episodesButton = view.findViewById<Button>(R.id.episodes_button)
        episodesButton.setOnClickListener {
            findNavController().navigate(R.id.action_start3_to_episodesFragment)
        }

        val locationsButton = view.findViewById<Button>(R.id.locations_button)
        locationsButton.setOnClickListener{
            findNavController().navigate(R.id.action_start3_to_locationsFragment)
        }

        val quotesButton = view.findViewById<Button>(R.id.quotes_button)
        quotesButton.setOnClickListener{
            findNavController().navigate(R.id.action_start3_to_quotesFragment)
        }

        return view
    }
}