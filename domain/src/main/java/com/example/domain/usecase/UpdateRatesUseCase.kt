package com.example.domain.usecase

import com.example.domain.base.UseCase
import com.example.domain.repository.AssetsRepository

class UpdateRatesUseCase(
    private val repository: AssetsRepository
) : UseCase<Nothing?, Unit> {

    override suspend fun invoke(param: Nothing?) =
        repository.updateRates()
}