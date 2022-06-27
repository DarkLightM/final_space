package com.example.androidcoursework.domain.usecases.getLocation

import com.example.androidcoursework.domain.models.Location

interface IGetLocationUseCase {
    suspend fun getLocations(): List<Location>

    suspend fun getLocation(id: Int): Location
}