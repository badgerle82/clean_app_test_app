package com.example.domain.repository

import com.example.domain.model.Asset
import kotlinx.coroutines.flow.Flow

interface AssetsRepository {

    suspend fun updateRates()

    suspend fun observeSelectedAssetsRates(): Flow<List<Asset>>

    suspend fun observeAllAssets(): Flow<List<Asset>>

    suspend fun updateSelectedForAsset(assetId: String, isSelected: Boolean)
}