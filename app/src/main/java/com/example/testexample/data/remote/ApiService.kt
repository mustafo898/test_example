package com.example.testexample.data.remote

import com.example.testexample.data.remote.dto.MainResponseDto
import com.example.testexample.data.remote.dto.main.SimpleResponseDto
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("hh/test/api/v1/offers")
    suspend fun getList(): Response<MainResponseDto<List<SimpleResponseDto>>>
}