package com.example.orderapplication.core.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class OrderEntity(
    @PrimaryKey val orderId : String,
    val date : String,
    val time : String,
    val vendorName : String
)
