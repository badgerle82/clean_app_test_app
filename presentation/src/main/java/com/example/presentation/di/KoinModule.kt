package com.example.presentation.di

import com.example.presentation.greeting.GreetingInteractor
import com.example.presentation.greeting.GreetingViewModel
import com.example.presentation.greeting.mapper.GreetingToGreetingDisplayModelMapper
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val viewModelModule = module {
        viewModel { GreetingViewModel(get(), get()) }
}

val interactorModule = module {
        factory { GreetingInteractor(get(), get(), get()) }
}

val presentationModule = module {
        factory { GreetingToGreetingDisplayModelMapper() }
}