package com.example.testexample.data.repo_impl

import com.example.testexample.data.common.ResponseHandler
import com.example.testexample.data.mapper.toModel
import com.example.testexample.data.remote.ApiService
import com.example.testexample.domain.common.Resource
import com.example.testexample.domain.common.model.SimpleResponseModel
import com.example.testexample.domain.repo.MainRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MainRepositoryImpl @Inject constructor(
    private val service: ApiService
) : MainRepository, ResponseHandler() {
    override suspend fun getList(): Flow<Resource<List<SimpleResponseModel>>> =
        loadResult({
            service.getList()
        }, { data, flow ->
            try {
                flow.emit(Resource.Success(data.map { it.toModel() }))
            } catch (e: Exception) {
                flow.emit(Resource.Error(e.message.toString()))
            }
        })
}