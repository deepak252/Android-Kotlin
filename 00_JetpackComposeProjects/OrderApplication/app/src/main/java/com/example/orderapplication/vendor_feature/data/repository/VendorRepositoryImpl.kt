package com.example.orderapplication.vendor_feature.data.repository

import com.example.orderapplication.core.data.local.dao.ProductDao
import com.example.orderapplication.core.data.local.dao.VendorDao
import com.example.orderapplication.core.domain.model.Product
import com.example.orderapplication.core.domain.model.Vendor
import com.example.orderapplication.vendor_feature.data.mapper.toProductEntity
import com.example.orderapplication.vendor_feature.data.mapper.toVendorEntity
import com.example.orderapplication.vendor_feature.domain.repository.VendorRepository
import javax.inject.Inject


class VendorRepositoryImpl @Inject constructor(
    private val vendorDao: VendorDao,
    private val productDao: ProductDao
) : VendorRepository {
    override suspend fun insertVendors(list: List<Vendor>) {
        list.forEach{ vendor ->
            vendorDao.insertVendor(vendor.toVendorEntity())
            insertProducts(vendor.products, vendor.vendorId)
        }
    }

    override suspend fun insertProducts(list: List<Product>, vendorId : String) {
        list.forEach{ product ->
            productDao.insertProduct(product.toProductEntity(vendorId))
        }

    }
}