package com.example.domain.model

data class Asset(
    val assetId: String,
    val base: String,
    val rate: Double,
    val selected: Boolean,
    val timestamp: Long
)