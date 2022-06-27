package com.example.androidcoursework.domain.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.androidcoursework.database.AbilityConverter

@Entity(tableName = "locations")
@TypeConverters(AbilityConverter::class)
data class Location(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String,
    val type: String,
    val inhabitants: MutableList<String>,
    val notable_residents: MutableList<String>,
    val img_url: String,
)
