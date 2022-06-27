package com.example.androidcoursework.domain.usecases.getCharacter

import com.example.androidcoursework.domain.models.Character

interface IGetCharacterUseCase {
    suspend fun getCharacters(): List<Character>
    suspend fun getCharacter(id: Int): Character
}