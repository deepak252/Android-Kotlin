package com.example.orderapplication.order_feature.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.orderapplication.order_feature.presentation.state.VendorUiState
import com.example.orderapplication.ui.theme.White

@Composable
fun VendorTile(
    vendorUiState: VendorUiState,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ){
        Text(
            vendorUiState.name,
            color = White
        )
        Icon(
            imageVector = Icons.Default.ArrowForward,
            contentDescription = "arrow_right",
            tint = White
        )
    }
}