package com.example.orderapplication.vendor_feature.data.mapper

import com.example.orderapplication.core.data.local.entities.ProductEntity
import com.example.orderapplication.core.domain.model.Product
import com.example.orderapplication.order_feature.domain.model.Order

fun Product.toProductEntity(vendorId : String) : ProductEntity {
    return ProductEntity(
        productId = productId,
        name = name,
        pricePerAmount = pricePerAmount,
        belongsToVendor = vendorId
    )
}