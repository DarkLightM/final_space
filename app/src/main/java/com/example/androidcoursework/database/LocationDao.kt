package com.example.androidcoursework.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.androidcoursework.domain.models.Character
import com.example.androidcoursework.domain.models.Episode
import com.example.androidcoursework.domain.models.Location

@Dao
interface LocationDao {
    @Query("SELECT * FROM locations")
    fun getLocations(): List<Location>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertLocation(location: Location)

    @Query("SELECT * FROM locations WHERE id = :id")
    fun getLocation(id: Int): Location
}