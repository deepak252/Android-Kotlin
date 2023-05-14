package com.example.orderapplication.core.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.example.orderapplication.core.data.local.entities.OrderEntity
import com.example.orderapplication.core.data.local.entities.OrderProductEntity
import com.example.orderapplication.core.data.local.entities.OrderWithProductsDataObject

@Dao
interface OrderDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrder(orderEntity: OrderEntity)

    @Transaction
    @Query("SELECT * FROM orderEntity")
    suspend fun getOrders() : List<OrderWithProductsDataObject>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrderProductEntities(orderProductEntities : List<OrderProductEntity>)

}