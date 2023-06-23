package com.rickandmortypoc.domain.common


sealed class BaseResult<out T : Any> {
    data class Success<T : Any>(val data: T?) : BaseResult<T>()
    data class Error(val exception: String) : BaseResult<Nothing>()

}
