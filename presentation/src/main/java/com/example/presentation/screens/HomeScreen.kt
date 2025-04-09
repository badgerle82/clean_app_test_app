package com.example.presentation.screens

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.SwipeToDismissBox
import androidx.compose.material3.SwipeToDismissBoxValue
import androidx.compose.material3.Text
import androidx.compose.material3.rememberSwipeToDismissBoxState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.presentation.greeting.AssetsViewModel
import com.example.presentation.uimodel.AssetUIModel

@Composable
fun HomeScreen(
    vm: AssetsViewModel,
    modifier: Modifier = Modifier
) {
    val items by vm.assetsList.collectAsStateWithLifecycle()
    val lastUpdate by vm.updatePeriod.collectAsStateWithLifecycle()

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(color = Color.Gray.copy(alpha = 0.1f))
            .padding(16.dp)
    ) {
        Text(
            text = lastUpdate
        )
        Spacer(modifier = Modifier.height(36.dp))
        LazyColumn(
            modifier = Modifier.graphicsLayer {
                clip = false
            },
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(
                items = items,
                key = { item ->
                    item.title
                }
            ) { item ->
                val dismissState = rememberSwipeToDismissBoxState()
                SwipeToDismissBox(
                    state = dismissState,
                    enableDismissFromEndToStart = true,
                    enableDismissFromStartToEnd = false,
                    backgroundContent = {
                        val backgroundColor by animateColorAsState(
                            when (dismissState.targetValue) {
                                SwipeToDismissBoxValue.EndToStart -> {
                                    Color.Red.copy(alpha = 0.5f)
                                }

                                else -> Color.Gray.copy(alpha = 0.4f)
                            }
                        )
                        val iconScale by animateFloatAsState(
                            targetValue = if (dismissState.targetValue == SwipeToDismissBoxValue.EndToStart) 1.3f else 0.5f
                        )
                        Box(
                            Modifier
                                .fillMaxSize()
                                .clip(RoundedCornerShape(8.dp))
                                .background(color = backgroundColor)
                                .padding(16.dp),
                            contentAlignment = Alignment.CenterEnd
                        ) {
                            Icon(
                                modifier = Modifier.scale(iconScale),
                                imageVector = Icons.Outlined.Delete,
                                contentDescription = "delete",
                                tint = Color.White
                            )
                        }
                    },
                    content = {
                        SelectedAssetItem(
                            item = item
                        )
                    }
                )
                when (dismissState.currentValue) {
                    SwipeToDismissBoxValue.EndToStart -> {
                        vm.deleteItem(item)
                    }

                    SwipeToDismissBoxValue.StartToEnd -> {
                        LaunchedEffect(dismissState) {
                            dismissState.snapTo(SwipeToDismissBoxValue.Settled)
                        }
                    }

                    SwipeToDismissBoxValue.Settled -> {
                        //do nothing
                    }
                }
            }
        }
    }
}

@Composable
private fun SelectedAssetItem(
    item: AssetUIModel,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clip(shape = RoundedCornerShape(8.dp))
            .background(color = Color.White)
            .padding(vertical = 8.dp, horizontal = 16.dp)
    ) {
        Image(
            modifier = Modifier
                .align(Alignment.CenterVertically)
                .size(30.dp),
            imageVector = item.icon,
            contentDescription = "icon"
        )
        Spacer(modifier = Modifier.width(8.dp))
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            ItemRow(
                value1 = item.title,
                value2 = item.rate.toString(),
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(4.dp))
            ItemRow(
                value1 = item.subtitle,
                value2 = "",
                color = Color.Gray,
                fontSize = 12.sp
            )
        }
    }
}

@Composable
private fun ItemRow(
    value1: String,
    value2: String,
    modifier: Modifier = Modifier,
    color: Color = Color.Unspecified,
    fontSize: TextUnit = TextUnit.Unspecified,
    fontWeight: FontWeight? = null
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = value1, fontSize = fontSize, fontWeight = fontWeight)
        Text(text = value2, color = color, fontSize = fontSize)
    }
}