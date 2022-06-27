package com.example.androidcoursework.domain.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "quotes")
data class Quote(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val quote: String,
    val by: String,
    val character: String,
    val image: String?,
)
