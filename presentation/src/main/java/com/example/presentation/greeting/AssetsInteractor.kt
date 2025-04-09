package com.example.presentation.greeting

import com.example.domain.base.invoke
import com.example.domain.model.Asset
import com.example.domain.usecase.ObserveSelectedAssetsRatesUseCase
import com.example.domain.usecase.ObserveAllAssetsUseCase
import com.example.domain.usecase.UpdateRatesUseCase
import com.example.domain.usecase.UpdateSelectedForAssetUseCase
import kotlinx.coroutines.flow.Flow

class AssetsInteractor(
    private val updateRatesUseCase: UpdateRatesUseCase,
    private val observeAllAssetsUseCase: ObserveAllAssetsUseCase,
    private val updateSelectedForAssetUseCase: UpdateSelectedForAssetUseCase,
    private val observeSelectedAssetsRatesUseCase: ObserveSelectedAssetsRatesUseCase
) {
    suspend fun updateRates() = updateRatesUseCase.invoke()

    suspend fun observeSelectedAssetsRates(): Flow<List<Asset>> =
        observeSelectedAssetsRatesUseCase.invoke()

    suspend fun observeAllAssets(): Flow<List<Asset>> = observeAllAssetsUseCase.invoke()

    suspend fun updateSelectedForAsset(assetId: String, isSelected: Boolean) =
        updateSelectedForAssetUseCase.invoke(assetId to isSelected)
}