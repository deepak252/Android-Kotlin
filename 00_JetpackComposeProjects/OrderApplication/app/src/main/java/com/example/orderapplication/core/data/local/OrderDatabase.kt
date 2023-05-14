package com.example.orderapplication.core.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.orderapplication.core.data.local.dao.DelivererDao
import com.example.orderapplication.core.data.local.dao.OrderDao
import com.example.orderapplication.core.data.local.entities.DelivererEntity
import com.example.orderapplication.core.data.local.entities.OrderEntity
import com.example.orderapplication.core.data.local.entities.OrderProductEntity
import com.example.orderapplication.core.data.local.entities.ProductEntity

@Database(
    entities = [
        DelivererEntity::class,
        OrderEntity::class,
        ProductEntity::class,
        OrderProductEntity::class
    ],
    version = 1
)
abstract class OrderDatabase : RoomDatabase(){
    abstract fun delivererDao() : DelivererDao
    abstract fun orderDao() : OrderDao
    abstract fun productDao() : OrderDao
}
