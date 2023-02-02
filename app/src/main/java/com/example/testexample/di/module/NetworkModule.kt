package com.example.testexample.di.module

import android.content.Context
import com.example.testexample.utils.Constants
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class NetworkModule(val context: Context) {

    @Singleton
    @Provides
    fun provideGsonConvertorFactory(): GsonConverterFactory = GsonConverterFactory.create()

    @Singleton
    @Provides
    fun provideOkHttpClient(): OkHttpClient {
//        val chuckInterceptor = ChuckerInterceptor.Builder(context)
//            .maxContentLength(500_000L)
//            .alwaysReadResponseBody(true)
//            .build()
        return OkHttpClient.Builder()
//            .addInterceptor(chuckInterceptor)
            .connectTimeout(10000L, TimeUnit.SECONDS)
            .build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(
        gsonGsonConverterFactory: GsonConverterFactory,
        builder: OkHttpClient
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constants.BaseUrl)
            .client(builder)
            .addConverterFactory(gsonGsonConverterFactory)
            .build()
    }
}