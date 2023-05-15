package com.example.orderapplication.order_feature.presentation.state

data class OrderUiState(
    val orderId : String,
    val vendorName : String,
    val totalAmount : Double,
    val orderDate : String
)