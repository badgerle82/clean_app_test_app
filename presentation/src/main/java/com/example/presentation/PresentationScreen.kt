package com.example.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.presentation.greeting.AssetsViewModel
import com.example.presentation.screens.AssetsScreen
import com.example.presentation.screens.HomeScreen
import org.koin.androidx.compose.koinViewModel


@Composable
fun PresentationScreen(
    modifier: Modifier = Modifier
) {
    val vm: AssetsViewModel = koinViewModel()
    var showAssetsScreen by remember { mutableStateOf(false) }

    Column(
        modifier = modifier
    ) {
        if (showAssetsScreen) {
            AssetsScreen(
                vm = vm,
                onBackClicked = {
                    showAssetsScreen = false
                },
                onDoneClicked = {
                    showAssetsScreen = false
                }
            )
        } else {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 16.dp, top = 16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = stringResource(R.string.rates_title),
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Start
                )
                IconButton(
                    onClick = {
                        showAssetsScreen = true
                    }
                ) {
                    Icon(Icons.Filled.Add, contentDescription = "add")
                }
            }
            Spacer(modifier = Modifier.height(24.dp))
            HomeScreen(vm = vm)
        }
    }
}