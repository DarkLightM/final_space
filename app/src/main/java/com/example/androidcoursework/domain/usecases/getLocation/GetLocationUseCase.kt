package com.example.androidcoursework.domain.usecases.getLocation

import com.example.androidcoursework.domain.models.Location
import com.example.androidcoursework.domain.repository.ILocationRepository

class GetLocationUseCase(private val iLocationRepository: ILocationRepository) : IGetLocationUseCase{
    override suspend fun getLocations(): List<Location> = iLocationRepository.getLocations()

    override suspend fun getLocation(id: Int): Location = iLocationRepository.getLocation(id)
}