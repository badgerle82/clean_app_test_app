package com.example.data.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "assets")
data class AssetEntity(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo("asset_id") val assetId: String,
    @ColumnInfo("base") val base: String,
    @ColumnInfo("rate") val rate: Double,
    @ColumnInfo("selected") val selected: Boolean,
    @ColumnInfo("timestamp") val timestamp: Long
)