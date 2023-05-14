package com.example.orderapplication.order_feature.presentation.state

data class ProductItemUiState(
    val id : String,
    val name : String,
    val pricePerAmount : Float,
    val selectedAmount : Int,
    val isExpanded : Boolean
)
