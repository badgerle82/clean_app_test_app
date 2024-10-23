package com.example.cleanarchtestapp

import android.os.Bundle
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.bluelinelabs.conductor.Conductor
import com.bluelinelabs.conductor.Router
import com.bluelinelabs.conductor.RouterTransaction
import com.example.cleanarchtestapp.navigation.ComposableController
import com.example.domain.navigator.NavigationRoutes
import com.example.domain.navigator.Navigator
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf
import org.koin.java.KoinJavaComponent.getKoin

class MainActivity : AppCompatActivity() {
    private lateinit var router: Router

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val container = findViewById<ViewGroup>(R.id.controller_container)
        router = Conductor.attachRouter(this, container, savedInstanceState)

        getKoin().setProperty("router", router)

        val appNavigator: Navigator by inject { parametersOf(router) }

        if (!router.hasRootController()) {
            // Start with the initial controller, from presentation module
            router.setRoot(RouterTransaction.with(ComposableController.newInstance(NavigationRoutes.PRESENTATION)))
        }
    }
}