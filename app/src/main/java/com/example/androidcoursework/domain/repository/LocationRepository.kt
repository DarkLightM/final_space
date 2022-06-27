package com.example.androidcoursework.domain.repository

import com.example.androidcoursework.database.LocationDao
import com.example.androidcoursework.domain.api.ApiProvider
import com.example.androidcoursework.domain.models.Location

class LocationRepository(
    private val apiProvider: ApiProvider,
    private val locationDao: LocationDao
) : ILocationRepository {
    override suspend fun getLocations(): List<Location> = apiProvider.getApi().getLocationList()

    override suspend fun getLocation(id: Int): Location = apiProvider.getApi().getLocation(id)

    override suspend fun getLocationsFromDb(): List<Location> = locationDao.getLocations()

    override suspend fun getLocationFromDb(id: Int): Location = locationDao.getLocation(id)

    override suspend fun addLocation(location: Location) = locationDao.insertLocation(location)
}