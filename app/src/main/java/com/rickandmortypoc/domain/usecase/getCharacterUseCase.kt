package com.rickandmortypoc.domain.usecase

import com.rickandmortypoc.data.remote.dto.CharacterDetail
import com.rickandmortypoc.domain.CharacterRepository
import com.rickandmortypoc.domain.common.BaseResult
import javax.inject.Inject

class getCharacterUseCase @Inject constructor(private val repository: CharacterRepository) {
    suspend fun invoke(
        id: String
    ): BaseResult<CharacterDetail> {
        return repository.getCharacter(id)
    }
}