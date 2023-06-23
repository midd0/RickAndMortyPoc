package com.rickandmortypoc.data.remote.api

import com.rickandmortypoc.data.remote.dto.CharacterDetail
import com.rickandmortypoc.data.remote.dto.CharacterList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface CharacterService {

    @GET("character")
    suspend fun getAllCharacters(): Response<CharacterList>

    @GET("character/{id}")
    suspend fun getCharacterDetail(@Path("id") id: String): Response<CharacterDetail>
}