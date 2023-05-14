package com.example.orderapplication.core.data.mapper

import com.example.orderapplication.core.data.local.entities.VendorEntity
import com.example.orderapplication.core.domain.model.Product
import com.example.orderapplication.core.domain.model.Vendor

fun VendorEntity.toVendor(products : List<Product>): Vendor{
    return Vendor(
        vendorId = vendorId,
        name = name,
        products = products
    )
}