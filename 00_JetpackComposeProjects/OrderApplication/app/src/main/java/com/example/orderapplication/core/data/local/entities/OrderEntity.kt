package com.example.orderapplication.core.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class OrderEntity(
    @PrimaryKey val orderId : String,
    val data : String,
    val delivererTime : String,
    val delivererName : String
)
