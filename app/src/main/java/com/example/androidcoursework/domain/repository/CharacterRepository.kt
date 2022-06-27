package com.example.androidcoursework.domain.repository

import com.example.androidcoursework.database.CharacterDao
import com.example.androidcoursework.domain.api.ApiProvider
import com.example.androidcoursework.domain.models.Character

class CharacterRepository(
    private val apiProvider: ApiProvider,
    private val characterDao: CharacterDao
) : ICharacterRepository {
    override suspend fun getCharacters(): List<Character> = apiProvider.getApi().getCharacterList()

    override suspend fun getCharacter(id: Int): Character = apiProvider.getApi().getCharacter(id)

    override suspend fun getCharactersFromDb(): List<Character> = characterDao.getCharacters()

    override suspend fun getCharacterFromDb(id: Int): Character = characterDao.getCharacter(id)

    override suspend fun addCharacter(character: Character) =
        characterDao.insertCharacter(character)
}