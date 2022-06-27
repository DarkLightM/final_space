package com.example.androidcoursework.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.androidcoursework.domain.models.Character
import com.example.androidcoursework.domain.models.Episode

@Dao
interface EpisodeDao {
    @Query("SELECT * FROM episodes")
    fun getEpisodes(): List<Episode>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertEpisode(episode: Episode)

    @Query("SELECT * FROM episodes WHERE id = :id")
    fun  getEpisode(id : Int) : Episode
}