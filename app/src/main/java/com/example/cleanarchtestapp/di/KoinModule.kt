package com.example.cleanarchtestapp.di

import com.example.data.di.dataModule
import com.example.domain.di.domainModule
import com.example.domain.di.useCaseModule
import com.example.presentation.di.interactorModule
import com.example.presentation.di.presentationModule
import com.example.presentation.di.viewModelModule
import org.koin.dsl.module

val appModule = module {

}

val koinModule = appModule + viewModelModule + interactorModule + presentationModule + dataModule + domainModule + useCaseModule
