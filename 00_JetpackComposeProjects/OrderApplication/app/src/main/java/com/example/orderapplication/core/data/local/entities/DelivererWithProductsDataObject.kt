package com.example.orderapplication.core.data.local.entities

import androidx.room.Embedded
import androidx.room.Relation

//https://developer.android.com/training/data-storage/room/relationships#one-to-many
data class DelivererWithProductsDataObject(
    @Embedded val delivererEntity: DelivererEntity,
    @Relation(
        parentColumn = "delivererId",
        entityColumn = "belongsToDeliverer"
    )
    val products : List<ProductEntity>
)
