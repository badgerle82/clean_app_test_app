package com.example.presentation.greeting

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class GreetingViewModel(
    private val interactor: GreetingInteractor
): ViewModel() {

    init {
        viewModelScope.launch(Dispatchers.IO) {
            val greetings = interactor.obtainGreeting()
        }
    }
}