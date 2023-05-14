package com.example.orderapplication.order_feature.presentation.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.orderapplication.order_feature.presentation.state.view_models.OrderViewModel
import com.example.orderapplication.ui.theme.Orange500

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OrderScreen(
    navController: NavController,
    orderViewModel : OrderViewModel = hiltViewModel()
) {
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = { /*TODO*/ }) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "fab_add_order"
                )
            }
        },
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Order App")
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = Orange500
                )
            )
        }

    ) {paddingValues ->
        if(orderViewModel.orderList.isEmpty()){
            Box(
                modifier = Modifier
                    .padding(paddingValues)
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ){
                Text(text = "No orders yet!")
            }
        }else{
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(12.dp)
            ){
                items(
                    orderViewModel.orderList,
                    key = {orderListItem->
                        orderListItem.orderId
                    }
                ){

                }
            }

        }


    }
}