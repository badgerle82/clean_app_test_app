package com.example.data.di

import com.example.data.repository.GreetingRepositoryImpl
import com.example.domain.repository.GreetingRepository
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import org.koin.dsl.module

val dataModule = module {
    single<GreetingRepository> { GreetingRepositoryImpl(get(), get())  }

    single<Gson> { GsonBuilder().setLenient().create() }
}