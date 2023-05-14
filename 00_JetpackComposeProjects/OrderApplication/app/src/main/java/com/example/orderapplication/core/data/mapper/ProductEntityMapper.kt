package com.example.orderapplication.core.data.mapper

import com.example.orderapplication.core.data.local.entities.ProductEntity
import com.example.orderapplication.core.domain.model.Product

fun ProductEntity.toProduct() : Product {
    return Product(
        productId = productId,
        name = name,
        pricePerAmount = pricePerAmount
    )
}