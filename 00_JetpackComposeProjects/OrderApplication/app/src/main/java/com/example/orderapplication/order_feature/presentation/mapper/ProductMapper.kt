package com.example.orderapplication.order_feature.presentation.mapper

import com.example.orderapplication.order_feature.domain.model.BoughtProduct
import com.example.orderapplication.order_feature.presentation.state.ProductUiState

fun BoughtProduct.toProductUiState() : ProductUiState{
    return ProductUiState(
        id = productId,
        name = name,
        pricePerAmount = pricePerAmount,
        selectedAmount = amount,
        isExpanded = false
    )
}