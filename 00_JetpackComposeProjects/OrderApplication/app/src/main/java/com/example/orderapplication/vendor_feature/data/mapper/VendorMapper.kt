package com.example.orderapplication.vendor_feature.data.mapper

import com.example.orderapplication.core.data.local.entities.VendorEntity
import com.example.orderapplication.core.domain.model.Vendor

fun Vendor.toVendorEntity() : VendorEntity{
    return VendorEntity(
        vendorId = vendorId,
        name = name
    )
}