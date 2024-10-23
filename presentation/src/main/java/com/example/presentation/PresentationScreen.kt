package com.example.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.example.presentation.greeting.GreetingViewModel
import org.koin.androidx.compose.koinViewModel


@Composable
fun PresentationScreen() {
    val vm: GreetingViewModel = koinViewModel()
    Column {
        Text(text = "This is Presentation Module Screen")
        Button(onClick = {
           vm.navigateToPresentation2Screen()
        }, content = {
            Text("Go to 2nd screen")
        })
    }
}