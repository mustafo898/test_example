package com.example.testexample.data.common

import com.example.testexample.data.remote.dto.APIErrorResponse
import com.example.testexample.data.remote.dto.MainResponseDto
import com.example.testexample.domain.common.Resource
import com.google.gson.Gson
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException

open class ResponseHandler {
    protected suspend fun <T, K> loadResult(
        requestSource: suspend () -> Response<MainResponseDto<T>>,
        successBody: suspend (T, FlowCollector<Resource<K>>) -> Unit
    ): Flow<Resource<K>> =
        flow {
            try {
                emit(Resource.Loading())
                val response = requestSource.invoke()
                if (response.isSuccessful) {
                    val result = response.body()
                    if (result != null) {
                        val data = result.offers
                        if (data != null) {
                            successBody.invoke(data, this)
                        } else {
                            emit(
                                Resource.Error(
                                    "An expected error occurred!"
                                )
                            )
                        }
                    } else {
                        emit(
                            Resource.Error(
                                "An expected error occurred!"
                            )
                        )
                    }
                } else {
                    try {
                        val me: APIErrorResponse = Gson().fromJson(
                            response.errorBody()!!.charStream(),
                            APIErrorResponse::class.java
                        )
                        emit(
                            Resource.Error(
                                me.message
                            )
                        )
                    } catch (e: Exception) {
                        emit(
                            Resource.Error(
                                "An expected error occurred!"
                            )
                        )
                    }
                }
            } catch (e: HttpException) {
                emit(
                    Resource.Error(
                        "An expected error occurred!"
                    )
                )
            } catch (e: IOException) {
                emit(
                    Resource.Error(
                        "Couldn't reach server. Please check your internet connection!"
                    )
                )
            }
        }
}