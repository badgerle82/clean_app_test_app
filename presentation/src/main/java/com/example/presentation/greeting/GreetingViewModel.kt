package com.example.presentation.greeting

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.navigator.NavigationRoutes
import com.example.domain.navigator.Navigator
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class GreetingViewModel(
    private val interactor: GreetingInteractor,
    private val navigator: Navigator
): ViewModel() {

    init {
        viewModelScope.launch(Dispatchers.IO) {
            val greetings = interactor.obtainGreeting()
        }
    }

    fun navigateToPresentation2Screen() {
        navigator.navigateTo(NavigationRoutes.PRESENTATION2)
    }
}