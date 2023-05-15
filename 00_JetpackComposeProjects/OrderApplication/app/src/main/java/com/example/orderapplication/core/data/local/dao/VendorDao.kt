package com.example.orderapplication.core.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.example.orderapplication.core.data.local.entities.VendorEntity
import com.example.orderapplication.core.data.local.entities.VendorWithProductsDataObject

@Dao
interface VendorDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertVendor(vendorEntity: VendorEntity)

    @Transaction
    @Query("SELECT * FROM VendorEntity")
    suspend fun getVendors() : List<VendorWithProductsDataObject>

    @Query("SELECT name FROM vendorEntity WHERE vendorId = :vendorId")
    suspend fun getVendorNameById(vendorId : String) : String
}