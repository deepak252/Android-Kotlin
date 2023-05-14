package com.example.orderapplication.order_feature.domain.model


data class Order(
    val orderId : String,
    val date : String,
    val time : String,
    val vendorName : String,
    val boughtProducts : List<BoughtProduct>
)
