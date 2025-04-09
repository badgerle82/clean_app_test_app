package com.example.presentation.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.presentation.R
import com.example.presentation.greeting.AssetsViewModel
import com.example.presentation.uimodel.AssetUIModel

@Composable
fun AssetsScreen(
    modifier: Modifier = Modifier,
    vm: AssetsViewModel,
    onBackClicked: () -> Unit,
    onDoneClicked: () -> Unit
) {
    val searchText by vm.searchText.collectAsState()
    val assets by vm.filteredAssetList.collectAsState()

    LaunchedEffect(Unit) {
        vm.onSearchTextChange("")
        vm.onSearchEmitted("")
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                onClick = {
                    onBackClicked.invoke()
                }
            ) {
                Icon(Icons.Filled.ArrowBackIosNew, contentDescription = "back")
            }
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                modifier = Modifier
                    .padding(horizontal = 16.dp),
                text = stringResource(R.string.add_asset),
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Start
            )
            Spacer(modifier = Modifier.weight(1f))
            TextButton(
                onClick = {
                    onDoneClicked.invoke()
                }
            ) {
                Text(
                    text = stringResource(R.string.done),
                    color = Color.Black,
                    fontSize = 16.sp
                )
            }
        }
        Spacer(modifier = Modifier.height(12.dp))
        TextField(
            value = searchText,
            onValueChange = {
                if (it.length <= 3) {
                    vm.onSearchTextChange(it)
                    vm.onSearchEmitted(it)
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .clip(RoundedCornerShape(24.dp)),
            leadingIcon = {
                Icon(Icons.Filled.Search, contentDescription = "search")
            },
            placeholder = { Text(text = stringResource(R.string.search_assets)) }
        )
    }
    Spacer(modifier = Modifier.height(24.dp))
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(color = Color.Gray.copy(alpha = 0.1f))
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            Text(
                text = stringResource(R.string.popular_assets).uppercase()
            )
            Spacer(modifier = Modifier.height(8.dp))
        }

        items(
            assets
        ) { item ->
            AssetItem(
                item = item,
                onCheckedChange = { checked ->
                    vm.setItemChecked(item.title, checked)
                }
            )
        }
    }
}


@Composable
private fun AssetItem(
    item: AssetUIModel,
    onCheckedChange: (Boolean) -> Unit,
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
            modifier = Modifier.weight(1f)
        ) {
            Text(text = item.title, fontSize = 16.sp, fontWeight = FontWeight.Bold)
            Text(text = item.subtitle, color = Color.Gray, fontSize = 12.sp)
        }
        Checkbox(
            checked = item.isSelected,
            onCheckedChange = onCheckedChange,
            modifier = Modifier.align(Alignment.CenterVertically),
            colors = CheckboxDefaults.colors().copy(
                checkedBoxColor = Color.Black,
                checkedBorderColor = Color.Black
            )
        )
    }
}