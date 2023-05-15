package com.example.orderapplication.core.domain.model

import com.example.orderapplication.core.domain.SelectAndSortableByName

data class Product(
    val productId : String,
    override val name : String,
    val pricePerAmount : Float
)  : SelectAndSortableByName
