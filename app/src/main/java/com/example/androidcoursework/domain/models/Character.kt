package com.example.androidcoursework.domain.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.androidcoursework.database.AbilityConverter

@Entity(tableName = "characters")

data class Character(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String,
    val status: String?,
    val species: String?,
    val gender: String?,
    val hair: String?,
    val origin: String?,
    val abilities: MutableList<String>,
    val alias: MutableList<String>,
    val img_url: String,
)