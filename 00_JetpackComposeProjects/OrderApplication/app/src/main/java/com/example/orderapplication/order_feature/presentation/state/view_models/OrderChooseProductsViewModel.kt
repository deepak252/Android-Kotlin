package com.example.orderapplication.order_feature.presentation.state.view_models

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.orderapplication.core.domain.model.Product
import com.example.orderapplication.order_feature.domain.repository.OrderRepository
import com.example.orderapplication.order_feature.domain.use_case.ConfirmOrderUseCase
import com.example.orderapplication.order_feature.domain.use_case.FilterListByNameUseCase
import com.example.orderapplication.order_feature.domain.use_case.SortListByNameUseCase
import com.example.orderapplication.order_feature.presentation.mapper.toBoughtProduct
import com.example.orderapplication.order_feature.presentation.mapper.toProductUiState
import com.example.orderapplication.order_feature.presentation.state.ProductUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OrderChooseProductsViewModel @Inject constructor(
    private val orderRepository: OrderRepository,
    private val filterListByNameUseCase: FilterListByNameUseCase,
    private val sortListByNameUseCase: SortListByNameUseCase,
    private val confirmOrderUseCase: ConfirmOrderUseCase,

) : ViewModel(){
    private lateinit var products : List<Product>
    private lateinit var vendorId : String

    var productsToShow = mutableStateListOf<ProductUiState>()
        private set // mutable for list items also

    var selectedProducts by mutableStateOf<List<ProductUiState>>(emptyList())
        private set // Mutable for whole list

    var productSearchQuery by mutableStateOf("")
        private set

    var isCheckoutDialogShown by mutableStateOf(false)
        private set

    fun initProductList(vendorId : String){
        viewModelScope.launch {
            products = orderRepository.getProductsForVendor(vendorId)
            this@OrderChooseProductsViewModel.vendorId = vendorId
            setupProductsToShow()
        }
    }

    fun onProductSearchQueryChange(newValue : String){
        productSearchQuery = newValue
        setupProductsToShow()
    }

    private fun setupProductsToShow(){
        productsToShow = filterListByNameUseCase(
            sortListByNameUseCase(products),productSearchQuery
        ).map { product ->
            product.toProductUiState()
        }.map {productUiState ->
            val selectedProduct = selectedProducts.firstOrNull{
                it.id==productUiState.id
            }
            if(selectedProduct!=null){
                productUiState.copy(selectedAmount = selectedProduct.selectedAmount)
            }else{
                productUiState
            }
        }.toMutableStateList()
    }

    fun onListItemClick(productId : String){
        val index = getIndexOfProduct(productId)
        if(index<0){
            return
        }
        productsToShow[index] = productsToShow[index].copy(
            isExpanded = !productsToShow[index].isExpanded
        )
    }

    fun onPlusClick(productId: String){
        val index = getIndexOfProduct(productId)
        if(index<0){
            return
        }
        val currentSelectionAmount = productsToShow[index].selectedAmount
        productsToShow[index] = productsToShow[index].copy(
            selectedAmount = currentSelectionAmount+1
        )
        if(currentSelectionAmount==0){
            selectedProducts = selectedProducts + productsToShow[index]
        }else{
            selectedProducts = selectedProducts.map {
                if(it.id==productId){
                    it.copy(selectedAmount = it.selectedAmount+1)
                }else{
                    it
                }
            }
        }

    }

    fun onMinusClick(productId: String){
        val index = getIndexOfProduct(productId)
        if(index<0){
            return
        }
        val currentSelectionAmount = productsToShow[index].selectedAmount
        if(currentSelectionAmount==0){
            return
        }
        if(currentSelectionAmount==1){
            //remove product
            selectedProducts = selectedProducts.toMutableList().apply {
                removeAll{it.id == productsToShow[index].id}
            }
        }
        productsToShow[index] = productsToShow[index].copy(
            selectedAmount = currentSelectionAmount-1
        )
    }

    fun onCheckoutClick(){
        isCheckoutDialogShown=true
    }

    fun onDismissCheckoutDialog(){
        isCheckoutDialogShown=false
    }

    fun onBuy(){
        confirmOrderUseCase(
            selectedProducts.map {
                it.toBoughtProduct()
            },
            vendorId = vendorId
        )
    }

    private fun getIndexOfProduct(productId : String) : Int{
        return productsToShow.indexOfFirst {
            it.id==productId
        }
    }
}