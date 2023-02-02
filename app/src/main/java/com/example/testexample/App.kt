package com.example.testexample

import android.app.Application
import android.content.res.Resources
import com.example.testexample.di.component.AppComponent
import com.example.testexample.di.component.DaggerAppComponent
import com.example.testexample.di.module.ApiServiceModule
import com.example.testexample.di.module.NetworkModule
import com.example.testexample.di.module.RepositoryModule


class App : Application() {
    companion object {
        lateinit var resource: Resources
        lateinit var appComponent: AppComponent
    }

    override fun onCreate() {
        super.onCreate()
        resource = resources

        appComponent = DaggerAppComponent.builder()
            .networkModule(NetworkModule(this))
            .repositoryModule(RepositoryModule())
            .apiServiceModule(ApiServiceModule())
            .build()
    }
}