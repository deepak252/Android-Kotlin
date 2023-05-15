package com.example.orderapplication.order_feature.presentation.mapper

import com.example.orderapplication.core.domain.model.Vendor
import com.example.orderapplication.order_feature.presentation.state.VendorUiState

fun Vendor.toVendorUiState() : VendorUiState {
    return VendorUiState(
        vendorId = vendorId,
        name = name
    )
}