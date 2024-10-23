package com.example.cleanarchtestapp.navigation

import com.bluelinelabs.conductor.Router
import com.bluelinelabs.conductor.RouterTransaction
import com.example.domain.navigator.Navigator

class AppNavigator(private val router: Router) : Navigator {
    override fun navigateTo(screenKey: String) {
        val controller = ComposableController.newInstance(screenKey)
        router.pushController(RouterTransaction.with(controller))
    }
}