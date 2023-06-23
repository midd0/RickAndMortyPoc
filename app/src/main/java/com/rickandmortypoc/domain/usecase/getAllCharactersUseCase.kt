package com.rickandmortypoc.domain.usecase

import com.rickandmortypoc.data.remote.dto.CharacterList
import com.rickandmortypoc.domain.CharacterRepository
import com.rickandmortypoc.domain.common.BaseResult
import javax.inject.Inject

class getAllCharactersUseCase @Inject constructor(
    private val repository: CharacterRepository
) {
    suspend fun invoke(): BaseResult<CharacterList> =
        repository.getAllCharacters()
}