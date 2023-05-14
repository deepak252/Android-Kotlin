package com.example.orderapplication.core.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.orderapplication.core.data.local.entities.ProductEntity

@Dao
interface ProductDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProduct(productEntity: ProductEntity)

    @Query( "SELECT * FROM ProductEntity  WHERE belongsToVendor = :vendorId"  )
    suspend fun getProductsForVendor(vendorId : String) : List<ProductEntity>
}