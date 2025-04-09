package com.example.domain.usecase

import com.example.domain.base.UseCase
import com.example.domain.repository.AssetsRepository

class UpdateSelectedForAssetUseCase(
    private val repository: AssetsRepository
): UseCase<Pair<String, Boolean>, Unit> {

    override suspend fun invoke(param: Pair<String, Boolean>) {
        val (assertId, isSelected) = param
        repository.updateSelectedForAsset(assertId, isSelected)
    }
}