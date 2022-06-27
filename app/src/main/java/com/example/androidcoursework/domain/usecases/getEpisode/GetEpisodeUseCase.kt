package com.example.androidcoursework.domain.usecases.getEpisode

import com.example.androidcoursework.domain.models.Episode
import com.example.androidcoursework.domain.repository.IEpisodeRepository

class GetEpisodeUseCase(private val iEpisodeRepository: IEpisodeRepository) : IGetEpisodeUseCase{
    override suspend fun getEpisodes(): List<Episode> = iEpisodeRepository.getEpisodes()

    override suspend fun getEpisode(id: Int): Episode = iEpisodeRepository.getEpisode(id)
}