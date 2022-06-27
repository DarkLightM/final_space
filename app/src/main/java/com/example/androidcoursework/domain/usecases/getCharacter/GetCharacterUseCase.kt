package com.example.androidcoursework.domain.usecases.getCharacter

import com.example.androidcoursework.domain.models.Character
import com.example.androidcoursework.domain.repository.ICharacterRepository

class GetCharacterUseCase(private val iCharacterRepository: ICharacterRepository) :
    IGetCharacterUseCase {
    override suspend fun getCharacters(): List<Character> = iCharacterRepository.getCharacters()

    override suspend fun getCharacter(id: Int): Character = iCharacterRepository.getCharacter(id)
}