package com.example.cleanarchtestapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.presentation.PresentationScreen
import com.example.presentation2.Presentation2Screen

@Composable
fun AppNavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = NavigationRoutes.PRESENTATION) {
        composable(NavigationRoutes.PRESENTATION) {
            PresentationScreen(navController = navController)
        }
        composable(NavigationRoutes.PRESENTATION2) {
            Presentation2Screen(navController = navController)
        }
    }
}