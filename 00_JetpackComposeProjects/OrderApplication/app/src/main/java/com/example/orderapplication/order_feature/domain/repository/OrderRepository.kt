package com.example.orderapplication.order_feature.domain.repository

import com.example.orderapplication.core.domain.model.Product
import com.example.orderapplication.core.domain.model.Vendor
import com.example.orderapplication.order_feature.domain.model.Order

interface OrderRepository {
    suspend fun insertOrder(order : Order)

    suspend fun getOrders() : List<Order>

    suspend fun getVendors() : List<Vendor>

    suspend fun getProductsForVendor(vendorId : String) : List<Product>

    suspend fun getVendorNameById(vendorId : String) : String
}