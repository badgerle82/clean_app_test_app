package com.example.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.example.data.db.entity.AssetEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface AssetDao {
    @Query("SELECT * FROM assets")
    fun getAllAssets(): List<AssetEntity>

    @Query("SELECT * FROM assets WHERE selected = 1")
    fun observeSelectedAssets(): Flow<List<AssetEntity>>

    @Query("SELECT * FROM assets")
    fun observeAllAssets(): Flow<List<AssetEntity>>

    @Query("UPDATE assets SET selected = :isSelected WHERE asset_id = :assetId")
    suspend fun updateSelectedForAsset(assetId: String, isSelected: Boolean)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(assets: List<AssetEntity>)

    @Transaction
    suspend fun updateAssetsPreservingSelected(newAssets: List<AssetEntity>) {
        newAssets.forEach {
            updateAssetPreserveSelected(
                assetId = it.assetId,
                base = it.base,
                rate = it.rate,
                timestamp = it.timestamp
            )
        }
    }

    @Query("""
        UPDATE assets
        SET base = :base,
            rate = :rate,
            timestamp = :timestamp
        WHERE asset_id = :assetId
    """)
    suspend fun updateAssetPreserveSelected(
        assetId: String,
        base: String,
        rate: Double,
        timestamp: Long
    )
}