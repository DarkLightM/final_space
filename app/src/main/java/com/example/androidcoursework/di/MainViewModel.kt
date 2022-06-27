package com.example.androidcoursework.di

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidcoursework.App
import com.example.androidcoursework.domain.models.Character
import com.example.androidcoursework.domain.models.Episode
import com.example.androidcoursework.domain.models.Location
import com.example.androidcoursework.domain.models.Quote
import com.example.androidcoursework.domain.repository.*
import com.example.androidcoursework.domain.usecases.getCharacter.IGetCharacterUseCase
import com.example.androidcoursework.domain.usecases.getEpisode.IGetEpisodeUseCase
import com.example.androidcoursework.domain.usecases.getLocation.IGetLocationUseCase
import com.example.androidcoursework.domain.usecases.getQuote.IGetQuoteUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.newSingleThreadContext
import javax.inject.Inject

class MainViewModel() : ViewModel() {

    @Inject
    lateinit var characterUseCase: IGetCharacterUseCase

    @Inject
    lateinit var episodeUseCase: IGetEpisodeUseCase

    @Inject
    lateinit var locationUseCase: IGetLocationUseCase

    @Inject
    lateinit var quoteUseCase: IGetQuoteUseCase

    @Inject
    lateinit var characterRepository: ICharacterRepository

    @Inject
    lateinit var episodeRepository: IEpisodeRepository

    @Inject
    lateinit var locationRepository: ILocationRepository

    @Inject
    lateinit var quoteRepository: IQuoteRepository

    var characters = MutableLiveData<List<Character>>()
    var episodes = MutableLiveData<List<Episode>>()
    var locations = MutableLiveData<List<Location>>()
    var quotes = MutableLiveData<List<Quote>>()

    var character = MutableLiveData<Character>()
    var episode = MutableLiveData<Episode>()
    var location = MutableLiveData<Location>()

    init {
        App.appComponent.inject(this)
    }

    fun getCharacters(mode: Boolean) {
        viewModelScope.launch {
            if (!mode)
                characters.value = characterUseCase.getCharacters()
            else
                characters.value = characterRepository.getCharactersFromDb()
        }
    }

    fun getCharacter(mode: Boolean, id: Int) {
        viewModelScope.launch {
            character.value = if (!mode)
                characterUseCase.getCharacter(id)
            else
                characterRepository.getCharacterFromDb(id)
        }
    }

    fun getEpisodes(mode: Boolean) {
        viewModelScope.launch {
            if (!mode)
                episodes.value = episodeUseCase.getEpisodes()
            else
                episodes.value = episodeRepository.getEpisodesFromDb()
        }
    }

    fun getEpisode(mode: Boolean, id: Int) {
        viewModelScope.launch {
            if (!mode)
                episode.value = episodeUseCase.getEpisode(id)
            else
                episode.value = episodeRepository.getEpisodeFromDb(id)
        }
    }

    fun getLocations(mode: Boolean) {
        viewModelScope.launch {
            if (!mode)
                locations.value = locationUseCase.getLocations()
            else
                locations.value = locationRepository.getLocationsFromDb()
        }
    }

    fun getLocation(mode: Boolean, id: Int) {
        viewModelScope.launch {
            if (!mode)
                location.value = locationUseCase.getLocation(id)
            else
                location.value = locationRepository.getLocationFromDb(id)
        }
    }

    fun getQuotes(mode: Boolean) {
        viewModelScope.launch {
            if (!mode)
                quotes.value = quoteUseCase.getQuotes()
            else
                quotes.value = quoteRepository.getQuotesFromDb()
        }
    }

    fun fillDatabase() {
        viewModelScope.launch(Dispatchers.IO) {
            characterUseCase.getCharacters().forEach {
                characterRepository.addCharacter(it)
            }
            episodeUseCase.getEpisodes().forEach {
                episodeRepository.addEpisode(it)
            }
            locationUseCase.getLocations().forEach {
                locationRepository.addLocation(it)
            }
            quoteUseCase.getQuotes().forEach {
                quoteRepository.addQuote(it)
            }
        }
    }
}