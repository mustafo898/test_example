package com.example.testexample.domain.repo

import com.example.testexample.domain.common.Resource
import com.example.testexample.domain.common.model.SimpleResponseModel
import kotlinx.coroutines.flow.Flow

interface MainRepository {
    suspend fun getList(): Flow<Resource<List<SimpleResponseModel>>>
}