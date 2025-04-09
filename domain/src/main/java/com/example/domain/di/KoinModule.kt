package com.example.domain.di

import com.example.domain.usecase.ObserveSelectedAssetsRatesUseCase
import com.example.domain.usecase.ObserveAllAssetsUseCase
import com.example.domain.usecase.UpdateRatesUseCase
import com.example.domain.usecase.UpdateSelectedForAssetUseCase
import org.koin.dsl.module


val domainModule = module {

}

val useCaseModule = module {
    factory { ObserveSelectedAssetsRatesUseCase(get()) }
    factory { ObserveAllAssetsUseCase(get()) }
    factory { UpdateRatesUseCase(get()) }
    factory { UpdateSelectedForAssetUseCase(get()) }
}