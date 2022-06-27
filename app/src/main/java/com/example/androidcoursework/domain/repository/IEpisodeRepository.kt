package com.example.androidcoursework.domain.repository

import com.example.androidcoursework.domain.models.Episode

interface IEpisodeRepository {
    suspend fun getEpisodes(): List<Episode>

    suspend fun getEpisode(id: Int): Episode

    suspend fun getEpisodesFromDb(): List<Episode>

    suspend fun getEpisodeFromDb(id: Int): Episode

    suspend fun addEpisode(episode: Episode)
}