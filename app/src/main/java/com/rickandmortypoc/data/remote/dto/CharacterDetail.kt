package com.rickandmortypoc.data.remote.dto

import com.google.gson.annotations.SerializedName


data class CharacterDetail(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("status") val status: String,
    @SerializedName("species") val species: String,
    @SerializedName("type") val type: String,
    @SerializedName("gender") val gender: String,
    @SerializedName("origin") val origin: Origin? = Origin(),
    @SerializedName("location") val location: Location? = Location(),
    @SerializedName("image") val image: String,
    @SerializedName("episode") val episode: ArrayList<String> = arrayListOf(),
    @SerializedName("url") val url: String,
    @SerializedName("created") val created: String
)