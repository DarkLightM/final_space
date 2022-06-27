package com.example.androidcoursework.domain.repository

import com.example.androidcoursework.database.EpisodeDao
import com.example.androidcoursework.domain.api.ApiProvider
import com.example.androidcoursework.domain.models.Episode

class EpisodeRepository(private val apiProvider: ApiProvider, private val episodeDao: EpisodeDao) :
    IEpisodeRepository {
    override suspend fun getEpisodes(): List<Episode> = apiProvider.getApi().getEpisodeList()

    override suspend fun getEpisode(id: Int): Episode = apiProvider.getApi().getEpisode(id)

    override suspend fun getEpisodesFromDb(): List<Episode> = episodeDao.getEpisodes()

    override suspend fun getEpisodeFromDb(id: Int): Episode = episodeDao.getEpisode(id)

    override suspend fun addEpisode(episode: Episode) {
        episodeDao.insertEpisode(episode)
    }
}