package com.rickandmortypoc.domain

import com.rickandmortypoc.data.remote.dto.CharacterDetail
import com.rickandmortypoc.data.remote.dto.CharacterList
import com.rickandmortypoc.domain.common.BaseResult


interface CharacterRepository {

    suspend fun getCharacter(id: String): BaseResult<CharacterDetail>

    suspend fun getAllCharacters(): BaseResult<CharacterList>
}