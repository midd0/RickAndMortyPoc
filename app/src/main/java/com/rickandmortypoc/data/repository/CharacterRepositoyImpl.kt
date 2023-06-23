package com.rickandmortypoc.data.repository

import com.rickandmortypoc.data.remote.api.CharacterService
import com.rickandmortypoc.domain.CharacterRepository
import com.rickandmortypoc.domain.Repository
import javax.inject.Inject

class CharacterRepositoyImpl @Inject constructor(
    private val apiService: CharacterService
) : CharacterRepository, Repository() {

    override suspend fun getCharacter(id: String) =
        getResponse { apiService.getCharacterDetail(id) }


    override suspend fun getAllCharacters() =
        getResponse { apiService.getAllCharacters() }
}