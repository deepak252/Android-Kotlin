package com.example.orderapplication.core.data.local.entities

import androidx.room.Entity

//https://developer.android.com/training/data-storage/room/relationships#many-to-many
@Entity(primaryKeys = ["orderId", "productId"])
data class OrderProductEntity(
    val orderId : String,
    val productId : String,
    val amount : Int
)