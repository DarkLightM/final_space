package com.example.androidcoursework.domain.usecases.getEpisode

import com.example.androidcoursework.domain.models.Episode

interface IGetEpisodeUseCase {
    suspend fun getEpisodes(): List<Episode>

    suspend fun getEpisode(id: Int): Episode
}