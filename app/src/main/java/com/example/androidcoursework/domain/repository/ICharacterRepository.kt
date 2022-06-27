package com.example.androidcoursework.domain.repository

import com.example.androidcoursework.domain.models.Character

interface ICharacterRepository {
    suspend fun getCharacters(): List<Character>

    suspend fun getCharacter(id: Int): Character

    suspend fun getCharactersFromDb(): List<Character>

    suspend fun getCharacterFromDb(id: Int): Character

    suspend fun addCharacter(character: Character)
}