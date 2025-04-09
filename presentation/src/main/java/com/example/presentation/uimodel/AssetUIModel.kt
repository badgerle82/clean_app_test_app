package com.example.presentation.uimodel

import androidx.compose.ui.graphics.vector.ImageVector

data class AssetUIModel(
    val icon: ImageVector,
    val title: String,
    val subtitle: String,
    val rate: Double,
    val isSelected: Boolean = false
)