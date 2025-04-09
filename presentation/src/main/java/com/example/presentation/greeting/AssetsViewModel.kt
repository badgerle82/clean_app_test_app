package com.example.presentation.greeting

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Money
import androidx.lifecycle.viewModelScope
import com.example.domain.model.Asset
import com.example.presentation.base.BaseViewModel
import com.example.presentation.uimodel.AssetUIModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.launch

class AssetsViewModel(
    private val interactor: AssetsInteractor
): BaseViewModel() {

    private var lastUpdatePeriod: Long = 0L

    private var isActive = true

    private val _searchText = MutableStateFlow("")
    val searchText = _searchText.asStateFlow()

    private val _updatePeriod = MutableStateFlow("Just now")
    val updatePeriod = _updatePeriod.asStateFlow()

    private var _originalAssetList = MutableStateFlow<List<AssetUIModel>>(emptyList())

    private var _filteredAssetList = MutableStateFlow<List<AssetUIModel>>(emptyList())
    val filteredAssetList = _filteredAssetList.asStateFlow()

    private var _assetsList =  MutableStateFlow<List<AssetUIModel>>(emptyList())
    val assetsList = _assetsList.asStateFlow()

    private val searchFlow = MutableSharedFlow<String>(replay = 1, onBufferOverflow = BufferOverflow.DROP_OLDEST)

    init {
        viewModelScope.launch(Dispatchers.Default) {
            interactor.observeSelectedAssetsRates().collect { assets ->
                val assetUIModels = assets.map { asset -> mapAssetToAssetUIModel(asset) }
                _assetsList.value = assetUIModels
            }
        }

        viewModelScope.launch(Dispatchers.IO) {
            interactor.observeAllAssets().collect { assets ->
                val assetUIModels = assets.map { asset -> mapAssetToAssetUIModel(asset) }
                _originalAssetList.value = assetUIModels
                _filteredAssetList.value = if (searchText.value.isEmpty()) {
                    _originalAssetList.value
                } else {
                    _originalAssetList.value.filter { it.title.contains(searchText.value.uppercase()) }
                }
            }
        }

        viewModelScope.launch {
            searchFlow.debounce(300L)
                .collect{ query ->
                    _filteredAssetList.value = if (query.isEmpty()) {
                        _originalAssetList.value
                    } else {
                        _originalAssetList.value.filter { it.title.contains(query.uppercase()) }
                    }
                }
        }

        updateRatesInLoop()

        updateLastUpdatePeriod()
    }

    override fun onCleared() {
        isActive = false
        super.onCleared()
    }

    fun onSearchTextChange(text: String) {
        _searchText.value = text
    }

    fun deleteItem(item: AssetUIModel) {
        updateSelectedForAsset(item.title, false)
    }

    fun setItemChecked(itemId: String, isChecked: Boolean) {
        updateSelectedForAsset(itemId, isChecked)
    }

    fun onSearchEmitted(query: String) = handleErrors {
        loadAsync {
            searchFlow.tryEmit(query)
        }
    }

    private fun updateLastUpdatePeriod() = handleErrors {
        viewModelScope.launch(Dispatchers.Default) {
            while (isActive) {
                delay(1000)
                _updatePeriod.value = if (lastUpdatePeriod == 0L) {
                    "Last update: just now"
                } else {
                    val delta = (System.currentTimeMillis() - lastUpdatePeriod)/1000
                    "Last update: $delta sec ago"
                }

            }
        }
    }

    private fun updateRatesInLoop() = handleErrors {
        viewModelScope.launch(Dispatchers.IO) {
            while (isActive) {
                try {
                    interactor.updateRates()
                    lastUpdatePeriod = System.currentTimeMillis()
                } catch (e: Exception) {
                    e.printStackTrace()
                }
                delay(5000)
            }
        }
    }

    private fun updateSelectedForAsset(assetId: String, isSelected: Boolean) = handleErrors {
        loadAsync {
            interactor.updateSelectedForAsset(assetId, isSelected)
        }
    }

    private fun mapAssetToAssetUIModel(input: Asset): AssetUIModel {
        return AssetUIModel(
            icon = Icons.Filled.Money,
            title = input.assetId,
            subtitle = "base=".plus(input.base),
            rate = input.rate,
            isSelected = input.selected
        )
    }
}