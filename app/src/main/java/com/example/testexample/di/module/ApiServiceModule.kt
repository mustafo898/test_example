package com.example.testexample.di.module

import com.example.testexample.data.remote.ApiService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
class ApiServiceModule {

    @Singleton
    @Provides
    fun provideMainService(retrofit: Retrofit): ApiService =
        retrofit.create(ApiService::class.java)

}