package com.example.androidcoursework.domain.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "episodes")
data class Episode(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String,
    val air_date: String,
    val director: String?,
    val writer: String?,
    val characters: List<String>,
    val img_url: String,
)
