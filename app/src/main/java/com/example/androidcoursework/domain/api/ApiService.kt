package com.example.androidcoursework.domain.api

import com.example.androidcoursework.domain.models.Character
import com.example.androidcoursework.domain.models.Episode
import com.example.androidcoursework.domain.models.Location
import com.example.androidcoursework.domain.models.Quote
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("character")
    suspend fun getCharacterList(): List<Character>

    @GET("episode")
    suspend fun getEpisodeList(): List<Episode>

    @GET("location")
    suspend fun getLocationList(): List<Location>

    @GET("quote")
    suspend fun getQuoteList(): List<Quote>

    @GET("character/{id}")
    suspend fun getCharacter(@Path("id") id: Int): Character

    @GET("episode/{id}")
    suspend fun getEpisode(@Path("id") id: Int): Episode

    @GET("location/{id}")
    suspend fun getLocation(@Path("id") id: Int): Location
}