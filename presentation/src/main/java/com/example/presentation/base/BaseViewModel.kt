package com.example.presentation.base

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.coroutines.cancellation.CancellationException

abstract class BaseViewModel: ViewModel() {
    private val _errorStateFlow = MutableStateFlow<Throwable?>(null)
    //Views should observe errorStateFlow to show exception message to the user via Error Info popup
    private val errorStateFlow: StateFlow<Throwable?> = _errorStateFlow
    val errorState = errorStateFlow
        .stateIn(viewModelScope, SharingStarted.Lazily, null)

    protected suspend fun inMain(block: suspend () -> Unit) {
        withContext(Dispatchers.Main) {
            block()
        }
    }

    protected fun <T> handleErrors(
        mute: Boolean = false, block: suspend () -> T
    ) = viewModelScope.launch {
        try {
            block()
        } catch (throwable: Throwable) {
            Log.e("BaseVM", throwable.message ?: "Unknown Exception",throwable)
            if (!mute && throwable !is CancellationException) {
                onNewError(throwable)
            }
        }
    }

    protected suspend fun <T> loadAsync(block: suspend () -> T): T? {
        try {
            return withContext(SupervisorJob() + IO) {
                block()
            }
        } catch (throwable: Exception) {
            throw throwable
        }
    }

    open fun onNewError(throwable: Throwable) {
        _errorStateFlow.tryEmit(throwable)
    }
}