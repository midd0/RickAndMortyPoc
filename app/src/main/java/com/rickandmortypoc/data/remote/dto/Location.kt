package com.rickandmortypoc.data.remote.dto

import com.google.gson.annotations.SerializedName

data class Location(
    @SerializedName("name") var name: String? = null,
    @SerializedName("url") var url: String? = null


) {
    override fun toString(): String {
        return "$name, $url"
    }
}