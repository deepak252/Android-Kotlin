package com.example.orderapplication.order_feature.data.repository

import com.example.orderapplication.core.data.local.dao.OrderDao
import com.example.orderapplication.core.data.local.dao.ProductDao
import com.example.orderapplication.core.data.local.dao.VendorDao
import com.example.orderapplication.core.data.local.entities.OrderProductEntity
import com.example.orderapplication.core.data.mapper.toProduct
import com.example.orderapplication.core.data.mapper.toVendor
import com.example.orderapplication.core.domain.model.Product
import com.example.orderapplication.core.domain.model.Vendor
import com.example.orderapplication.order_feature.data.mapper.toOrder
import com.example.orderapplication.order_feature.data.mapper.toOrderEntity
import com.example.orderapplication.order_feature.domain.model.Order
import com.example.orderapplication.order_feature.domain.repository.OrderRepository
import javax.inject.Inject

class OrderRepositoryImpl @Inject constructor(
    private  val orderDao: OrderDao,
    private val vendorDao: VendorDao,
    private val productDao: ProductDao
) : OrderRepository {
    override suspend fun insertOrder(order: Order) {
        orderDao.insertOrder(order.toOrderEntity())
        val orderProductEntities = order.boughtProducts.map {boughtProduct ->
            OrderProductEntity(order.orderId, boughtProduct.productId,boughtProduct.amount)
        }
        orderDao.insertOrderProductEntities(orderProductEntities)
    }

    override suspend fun getOrders(): List<Order> {
        return orderDao.getOrderWithProducts().map {
            it.toOrder()
        }
    }

    override suspend fun getVendors(): List<Vendor> {
        return vendorDao.getVendors().map {
            it.vendorEntity.toVendor(
                it.products.map { productEntity -> productEntity.toProduct()}
            )
        }
    }

    override suspend fun getProductsForVendor(vendorId: String): List<Product> {
        return productDao.getProductsForVendor(vendorId).map {
            it.toProduct()
        }
    }

    override suspend fun getVendorNameById(vendorId: String): String {
        return vendorDao.getVendorNameById(vendorId)
    }
}