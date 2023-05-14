package com.example.orderapplication.order_feature.data.mapper

import com.example.orderapplication.core.data.local.entities.OrderWithProductsDataObject
import com.example.orderapplication.order_feature.domain.model.BoughtProduct
import com.example.orderapplication.order_feature.domain.model.Order

fun OrderWithProductsDataObject.toOrder() : Order {
    return Order(
        orderId = orderEntity.orderId,
        date = orderEntity.date,
        time = orderEntity.time,
        vendorName = orderEntity.vendorName,
        boughtProducts = products.zip(orderProductEntities).map { pair ->
            BoughtProduct(
                productId = pair.component1().productId,
                name = pair.component1().name,
                pricePerAmount = pair.component1().pricePerAmount,
                amount = pair.component2().amount
            )
        }
    )
}