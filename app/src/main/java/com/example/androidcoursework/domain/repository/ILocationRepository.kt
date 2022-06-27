package com.example.androidcoursework.domain.repository

import com.example.androidcoursework.domain.models.Location

interface ILocationRepository {
    suspend fun getLocations(): List<Location>

    suspend fun getLocation(id: Int): Location

    suspend fun getLocationsFromDb(): List<Location>

    suspend fun getLocationFromDb(id: Int): Location

    suspend fun addLocation(location: Location)
}