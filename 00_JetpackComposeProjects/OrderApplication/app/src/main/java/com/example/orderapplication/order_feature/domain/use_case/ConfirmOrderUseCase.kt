package com.example.orderapplication.order_feature.domain.use_case

import com.example.orderapplication.order_feature.domain.model.BoughtProduct
import com.example.orderapplication.order_feature.domain.model.Order
import com.example.orderapplication.order_feature.domain.repository.OrderRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Date
import java.util.UUID
import javax.inject.Inject

class ConfirmOrderUseCase @Inject constructor(
    private val orderRepository: OrderRepository
) {
    operator fun invoke( products : List<BoughtProduct>, vendorId : String){
        val coroutineScope = CoroutineScope(Dispatchers.IO)
//        val formatter : DateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss")
//        val date = formatter.format(LocalDateTime.now())

        val sdf = SimpleDateFormat("dd/M/yyyy hh:mm:ss")
        val date = sdf.format(Date())

        coroutineScope.launch {
            val vendorName = orderRepository.getVendorNameById(vendorId)
            orderRepository.insertOrder(
                order = Order(
                    orderId = UUID.randomUUID().toString(),
                    date = date,
                    time = "As fast as possible",
                    vendorName = vendorName,
                    boughtProducts = products
                )
            )
        }
    }
}