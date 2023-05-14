package com.example.orderapplication.core.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.orderapplication.core.data.local.dao.OrderDao
import com.example.orderapplication.core.data.local.dao.ProductDao
import com.example.orderapplication.core.data.local.dao.VendorDao
import com.example.orderapplication.core.data.local.entities.OrderEntity
import com.example.orderapplication.core.data.local.entities.OrderProductEntity
import com.example.orderapplication.core.data.local.entities.ProductEntity
import com.example.orderapplication.core.data.local.entities.VendorEntity

@Database(
    entities = [
        VendorEntity::class,
        OrderEntity::class,
        ProductEntity::class,
        OrderProductEntity::class
    ],
    version = 1
)
abstract class OrderDatabase : RoomDatabase(){
    abstract fun vendorDao() : VendorDao
    abstract fun orderDao() : OrderDao
    abstract fun productDao() : ProductDao
}
