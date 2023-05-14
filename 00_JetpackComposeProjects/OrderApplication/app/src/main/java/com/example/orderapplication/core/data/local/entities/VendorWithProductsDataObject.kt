package com.example.orderapplication.core.data.local.entities

import androidx.room.Embedded
import androidx.room.Relation

//https://developer.android.com/training/data-storage/room/relationships#one-to-many
data class VendorWithProductsDataObject(
    @Embedded val vendorEntity: VendorEntity,
    @Relation(
        parentColumn = "vendorId",
        entityColumn = "belongsToVendor"
    )
    val products : List<ProductEntity>
)
