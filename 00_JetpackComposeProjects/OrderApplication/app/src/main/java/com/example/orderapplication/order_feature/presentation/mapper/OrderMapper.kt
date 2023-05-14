package com.example.orderapplication.order_feature.presentation.mapper

import com.example.orderapplication.order_feature.domain.model.Order
import com.example.orderapplication.order_feature.presentation.state.OrderDetailUiState
import com.example.orderapplication.order_feature.presentation.state.OrderItemUiState

fun Order.toOrderDetailUiState() : OrderDetailUiState {
    return OrderDetailUiState(
        orderId = orderId,
        vendorName = vendorName,
        orderDate = date,
        products = boughtProducts.map {
            it.toProductItemUiState()
        }
    )
}

fun Order.toOrderItemUiState() : OrderItemUiState {
    return OrderItemUiState(
        orderId = orderId,
        vendorName = vendorName,
        orderDate = date,
        totalAmount = boughtProducts.sumOf { (it.amount + it.pricePerAmount).toDouble() }
    )
}