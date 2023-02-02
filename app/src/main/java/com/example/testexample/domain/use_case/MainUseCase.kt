package com.example.testexample.domain.use_case

import com.example.testexample.domain.common.Resource
import com.example.testexample.domain.common.model.SimpleResponseModel
import com.example.testexample.domain.repo.MainRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MainUseCase @Inject constructor(private val repository: MainRepository) {
    suspend operator fun invoke(): Flow<Resource<List<SimpleResponseModel>>> {
        return repository.getList()
    }
}