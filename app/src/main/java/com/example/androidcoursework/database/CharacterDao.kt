package com.example.androidcoursework.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.androidcoursework.domain.models.Character

@Dao
interface CharacterDao {
    @Query("SELECT * FROM characters")
    fun getCharacters(): List<Character>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCharacter(character: Character)

    @Query("SELECT * FROM characters where id = :id")
    fun getCharacter(id: Int) : Character
}