package com.rickandmortypoc.data.remote.dto

import com.google.gson.annotations.SerializedName

data class CharacterList(
    @SerializedName("info") var info: Info? = Info(),
    @SerializedName("results") var results: List<CharacterDetail> = listOf()
)