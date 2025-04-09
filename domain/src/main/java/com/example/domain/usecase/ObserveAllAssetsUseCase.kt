package com.example.domain.usecase

import com.example.domain.base.UseCase
import com.example.domain.model.Asset
import com.example.domain.repository.AssetsRepository
import kotlinx.coroutines.flow.Flow

class ObserveAllAssetsUseCase(
    private val repository: AssetsRepository
): UseCase<Nothing?, Flow<List<Asset>>> {

    override suspend fun invoke(param: Nothing?): Flow<List<Asset>> =
        repository.observeAllAssets()
}