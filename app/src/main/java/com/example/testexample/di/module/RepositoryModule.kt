package com.example.testexample.di.module

import com.example.testexample.data.remote.ApiService
import com.example.testexample.data.repo_impl.MainRepositoryImpl
import com.example.testexample.domain.repo.MainRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Provides
    @Singleton
    fun provideMainRepository(mainRemoteDataSource: ApiService): MainRepository {
        return MainRepositoryImpl(mainRemoteDataSource)
    }
}