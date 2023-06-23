package com.rickandmortypoc.domain.common

data class DataResponse<RequestData>(
    var responseType: UiStatus, var data: RequestData? = null, var error: String? = null
)

enum class UiStatus { SUCCESS, ERROR, LOADING }