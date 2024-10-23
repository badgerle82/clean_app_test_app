package com.example.cleanarchtestapp.di

import com.example.cleanarchtestapp.navigation.AppNavigator
import com.example.data.di.dataModule
import com.example.domain.di.domainModule
import com.example.domain.di.useCaseModule
import com.example.domain.navigator.Navigator
import com.example.presentation.di.interactorModule
import com.example.presentation.di.presentationModule
import com.example.presentation.di.viewModelModule
import org.koin.dsl.module

val appModule = module {
    single<Navigator> { AppNavigator(getProperty("router")) }
}

val koinModule = appModule + viewModelModule + interactorModule + presentationModule + dataModule + domainModule + useCaseModule
