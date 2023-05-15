package com.example.orderapplication.order_feature.presentation.state.view_models

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.orderapplication.order_feature.domain.model.Order
import com.example.orderapplication.order_feature.domain.repository.OrderRepository
import com.example.orderapplication.order_feature.presentation.mapper.toOrderDetailUiState
import com.example.orderapplication.order_feature.presentation.mapper.toOrderUiState
import com.example.orderapplication.order_feature.presentation.state.OrderDetailUiState
import com.example.orderapplication.order_feature.presentation.state.OrderUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OrderViewModel @Inject constructor(
    private val orderRepository : OrderRepository
) : ViewModel() {

    private lateinit var orders : List<Order>
    var orderList by mutableStateOf<List<OrderUiState>>(emptyList())
        private set

    var isOrderDialogShown by mutableStateOf(false)
        private set

    var selectedOrderItem by mutableStateOf<OrderDetailUiState?>(null)

    init {
        viewModelScope.launch {
            orders = orderRepository.getOrders()
            setupOrderList()
//            orderRepository.insertOrder(
//                Order(
//                    "1",
//                    "2022.10.15 12:05:12",
//                    "quick",
//                    "Paper Factory Ltd",
//                    listOf(
//                        BoughtProduct("1","Notebook Big",1.45f,2),
//                        BoughtProduct("2","Notebook Medium",1.25f,5),
//                    )
//                )
//            )
        }
    }

    fun onOrderClick(orderId : String){
        initOrderForDialog(orderId)
        isOrderDialogShown = true
    }
    private fun initOrderForDialog(orderId: String){
        selectedOrderItem = orders.firstOrNull{
            it.orderId==orderId
        }?.toOrderDetailUiState()
    }

    fun onDismissOrderDialog(){
        isOrderDialogShown = false
        selectedOrderItem = null
    }

    private fun setupOrderList(){
//        val formatter : DateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss")

        orderList = orders.map { order ->
            order.toOrderUiState()
        }
//            .sortedByDescending {orderItemUiState ->
//            LocalDateTime.parse(orderItemUiState.orderDate, formatter)
//        }
    }
}