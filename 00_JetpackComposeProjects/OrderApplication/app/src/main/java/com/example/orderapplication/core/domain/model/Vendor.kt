package com.example.orderapplication.core.domain.model

data class Vendor(
    val vendorId : String,
    val name : String,
    val products : List<Product>
)
