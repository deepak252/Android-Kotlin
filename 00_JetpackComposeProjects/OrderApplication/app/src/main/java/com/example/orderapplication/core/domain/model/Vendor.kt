package com.example.orderapplication.core.domain.model

import com.example.orderapplication.core.domain.SelectAndSortableByName

data class Vendor(
    val vendorId : String,
    override val name : String,
    val products : List<Product>
) : SelectAndSortableByName
