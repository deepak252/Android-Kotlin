package com.example.orderapplication.vendor_feature.domain.repository

import com.example.orderapplication.core.domain.model.Product
import com.example.orderapplication.core.domain.model.Vendor

interface VendorRepository {
    suspend fun insertVendors(list : List<Vendor>)
    suspend fun insertProducts(list : List<Product>, vendorId : String)
}