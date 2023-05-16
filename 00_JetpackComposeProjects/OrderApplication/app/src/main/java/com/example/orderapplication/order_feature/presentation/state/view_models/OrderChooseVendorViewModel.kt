package com.example.orderapplication.order_feature.presentation.state.view_models

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.orderapplication.core.domain.model.Vendor
import com.example.orderapplication.order_feature.domain.repository.OrderRepository
import com.example.orderapplication.order_feature.domain.use_case.FilterListByNameUseCase
import com.example.orderapplication.order_feature.domain.use_case.SortListByNameUseCase
import com.example.orderapplication.order_feature.presentation.mapper.toVendorUiState
import com.example.orderapplication.order_feature.presentation.state.VendorUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OrderChooseVendorViewModel @Inject constructor(
    private val orderRepository: OrderRepository,
    private val sortListByNameUseCase: SortListByNameUseCase,
    private val filterListByNameUseCase: FilterListByNameUseCase
) : ViewModel(){

    private lateinit var vendors : List<Vendor>

    var vendorToShow by mutableStateOf<List<VendorUiState>>(emptyList())
        private set

    var vendorSearchQuery by mutableStateOf("")
        private set

    init {
        viewModelScope.launch {
            vendors = orderRepository.getVendors()
            setupVendorsToShow()
        }
    }

    fun onSearchQueryChange(newValue : String){
        vendorSearchQuery = newValue
        setupVendorsToShow()
    }

    private fun setupVendorsToShow(){
        vendorToShow = filterListByNameUseCase(
            sortListByNameUseCase(
                vendors
            ),
            vendorSearchQuery
        ).map { vendor-> vendor.toVendorUiState() }
    }


}