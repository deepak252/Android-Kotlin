package com.example.orderapplication.order_feature.domain.model

import com.example.orderapplication.core.domain.model.Product

data class Order(
    val orderId : String,
    val data : String,
    val time : String,
    val vendorName : String,
    val products : List<Product>
)
