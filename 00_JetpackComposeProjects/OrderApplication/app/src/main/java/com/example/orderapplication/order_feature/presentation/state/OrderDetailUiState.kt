package com.example.orderapplication.order_feature.presentation.state

data class OrderDetailUiState(
    val orderId : String,
    val vendorName : String,
    val orderDate : String,
    val products : List<ProductUiState>
)