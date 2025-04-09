package com.example.data.repository

import com.example.data.db.dao.AssetDao
import com.example.data.db.entity.AssetEntity
import com.example.data.network.ExchangeRatesApi
import com.example.domain.model.Asset
import com.example.domain.repository.AssetsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class AssetsRepositoryImpl(
    private val restApi: ExchangeRatesApi,
    private val assetsDao: AssetDao
) : AssetsRepository {

    //the key shouldn't be here
    private val apiKey = "afe21972d51f48e6b00e4213baae601f"

    override suspend fun updateRates() {
        val data = restApi.getLatestRates(apiKey)
        val assetEntities = data.rates.map { rate ->
            AssetEntity(
                assetId = rate.key,
                base = data.base,
                rate = rate.value,
                selected = false,
                timestamp = data.timestamp
            )
        }
        val currentAssets = assetsDao.getAllAssets()
        if (currentAssets.isEmpty()) {
            assetsDao.insertAll(assetEntities)
        } else {
            assetsDao.updateAssetsPreservingSelected(assetEntities)
        }
    }

    override suspend fun observeSelectedAssetsRates(): Flow<List<Asset>> =
        assetsDao.observeSelectedAssets().map {
            it.map { assetEntity ->
                Asset(
                    assetEntity.assetId,
                    assetEntity.base,
                    assetEntity.rate,
                    assetEntity.selected,
                    assetEntity.timestamp
                )
            }
        }

    override suspend fun observeAllAssets(): Flow<List<Asset>> =
        assetsDao.observeAllAssets().map {
            it.map { assetEntity ->
                Asset(
                    assetEntity.assetId,
                    assetEntity.base,
                    assetEntity.rate,
                    assetEntity.selected,
                    assetEntity.timestamp
                )
            }
        }

    override suspend fun updateSelectedForAsset(assetId: String, isSelected: Boolean) {
        assetsDao.updateSelectedForAsset(assetId, isSelected)
    }
}