package com.example.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.presentation.greeting.GreetingViewModel
import org.koin.androidx.compose.koinViewModel

object NavigationRoutes {
    const val PRESENTATION = "presentation"
    const val PRESENTATION2 = "presentation2"
}

@Composable
fun PresentationScreen(navController: NavController) {
    val vm: GreetingViewModel = koinViewModel()
    Column {
        Text(text = "This is Presentation Module Screen")
        Button(onClick = {
           navController.navigate(NavigationRoutes.PRESENTATION2)
        }, content = {
            Text("Go to 2nd screen")
        })
    }

}