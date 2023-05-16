package com.example.orderapplication.order_feature.presentation.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.orderapplication.order_feature.presentation.state.ProductUiState
import com.example.orderapplication.ui.theme.White

@Composable
fun ProductTile(
    productUiState: ProductUiState,
    isExpanded:Boolean,
    onPlusClick:()->Unit,
    onMinusClick:()->Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ){
            Column(
                verticalArrangement = Arrangement.spacedBy(5.dp),
                horizontalAlignment = Alignment.Start
            ){
                Text(
                    productUiState.name,
                    color = White,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    "%.2f".format(productUiState.pricePerAmount)+" $",
                    color = White,
                    fontSize = 14.sp
                )
            }
            AnimatedVisibility(productUiState.selectedAmount>0) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(5.dp),
                    verticalAlignment = Alignment.CenterVertically
                ){
                    Icon(
                        imageVector = Icons.Default.Check,
                        contentDescription = "checkmark",
                        tint = White
                    )
                    Text(
                        "${productUiState.selectedAmount} x",
                        color = White
                    )
                }
            }
        }
        AnimatedVisibility(isExpanded) {
            Divider(color = White)
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(25.dp)
            ){
                IconButton(onClick = {
                    onMinusClick()
                }){
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = "minus",
                        tint = White
                    )
                }
                IconButton(onClick = {
                    onPlusClick()
                }){
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = "plus",
                        tint = White
                    )
                }
            }
        }
    }
}