package com.example.orderapplication.order_feature.presentation.screens

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.orderapplication.core.presentation.ScreenRoutes
import com.example.orderapplication.order_feature.presentation.components.CheckoutDialog
import com.example.orderapplication.order_feature.presentation.components.ProductTile
import com.example.orderapplication.order_feature.presentation.components.VendorTile
import com.example.orderapplication.order_feature.presentation.state.view_models.OrderChooseProductsViewModel
import com.example.orderapplication.ui.theme.Gray400
import com.example.orderapplication.ui.theme.Gray900
import com.example.orderapplication.ui.theme.Orange500
import com.example.orderapplication.ui.theme.White

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OrderChooseProductsScreen(
    navController: NavController,
    vendorId : String?=null,
    orderChooseProductsViewModel: OrderChooseProductsViewModel  = hiltViewModel()
) {
    LaunchedEffect(key1 = true){
        if(vendorId!=null){
            orderChooseProductsViewModel.initProductList(vendorId)
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Product Section")
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = Orange500
                )
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    orderChooseProductsViewModel.onCheckoutClick()
                }
            ) {
                Icon(
                    imageVector = Icons.Default.Check,
                    contentDescription = "fab_add_order"
                )
            }
        },
    ) {paddingValues ->
        Box(modifier = Modifier.padding(paddingValues)){
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 15.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                TextField(
                    value = orderChooseProductsViewModel.productSearchQuery,
                    onValueChange = {
                        orderChooseProductsViewModel.onProductSearchQueryChange(it)
                    },
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = White,
                        textColor = Gray900,
                        cursorColor = Orange500,
                        focusedLabelColor = Orange500,
                        focusedIndicatorColor = Orange500
                    ),
                    label = {
                        Text("Search Product")
                    },
                    maxLines = 1
                )
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(12.dp),
                    verticalArrangement = Arrangement.spacedBy(5.dp),
                ){
                    items(
                        orderChooseProductsViewModel.productsToShow,
                        key = {productUiState ->
                            productUiState.id
                        }
                    ){
                        ProductTile(
                            productUiState = it,
                            modifier = Modifier
                                .fillMaxWidth()
                                .clip(RoundedCornerShape(10.dp))
                                .border(1.dp, color = White, RoundedCornerShape(10.dp))
                                .clickable {
                                    orderChooseProductsViewModel.onListItemClick(it.id)
                                }
                                .padding(10.dp),
                            isExpanded = it.isExpanded,
                            onMinusClick = {
                                orderChooseProductsViewModel.onMinusClick(it.id)
                            },
                            onPlusClick = {
                                orderChooseProductsViewModel.onPlusClick(it.id)
                            }
                        )

                    }
                }
            }
        }
    }

    if(orderChooseProductsViewModel.isCheckoutDialogShown){
        CheckoutDialog(
            onDismiss = {
                orderChooseProductsViewModel.onDismissCheckoutDialog()
            },
            onConfirm = {
                orderChooseProductsViewModel.onBuy()
                navController.navigate(ScreenRoutes.OrderScreen.route){
                    popUpTo(0)
                }
            },
            selectedProducts = orderChooseProductsViewModel.selectedProducts
        )
    }
}