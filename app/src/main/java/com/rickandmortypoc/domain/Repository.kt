package com.rickandmortypoc.domain

import com.rickandmortypoc.domain.common.BaseResult
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException

abstract class Repository {
    inline fun <T : Any> getResponse(call: () -> Response<T>): BaseResult<T> {
        try {
            val response = call()
            if (response.isSuccessful) {
                val body = response.body()
                if (body != null) return BaseResult.Success(body)
            }
            return BaseResult.Error(" ${response.code()} ${response.message()}")


        } catch (e: Exception) {
            return BaseResult.Error(e.message.orEmpty())
        } catch (e: IOException) {
            return BaseResult.Error(e.message.orEmpty())
        } catch (e: HttpException) {
            return BaseResult.Error(e.message.orEmpty())
        } catch (e: Exception) {
            return BaseResult.Error(e.message.orEmpty())
        }
    }
}