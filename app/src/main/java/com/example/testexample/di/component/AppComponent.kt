package com.example.testexample.di.component

import com.example.testexample.databinding.FragmentMainBinding
import com.example.testexample.di.module.ApiServiceModule
import com.example.testexample.di.module.NetworkModule
import com.example.testexample.di.module.RepositoryModule
import com.example.testexample.presentation.main.MainFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        ApiServiceModule::class,
        NetworkModule::class,
        RepositoryModule::class,
    ]
)

interface AppComponent {
    fun inject(profileFragment: MainFragment)

}