package com.example.presentation.di

import com.example.presentation.greeting.AssetsInteractor
import com.example.presentation.greeting.AssetsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val viewModelModule = module {
        viewModel { AssetsViewModel(get()) }
}

val interactorModule = module {
        factory { AssetsInteractor(get(), get(), get(), get()) }
}

val presentationModule = module {

}