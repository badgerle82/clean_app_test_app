package com.example.domain.di

import com.example.domain.usecase.CacheGreetingsUseCase
import com.example.domain.usecase.GetGreetingsUseCase
import org.koin.dsl.module


val domainModule = module {

}

val useCaseModule = module {
    factory { CacheGreetingsUseCase(get()) }
    factory { GetGreetingsUseCase(get()) }
}