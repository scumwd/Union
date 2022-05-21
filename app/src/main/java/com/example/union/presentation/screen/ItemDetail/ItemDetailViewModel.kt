package com.example.union.presentation.screen.ItemDetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.models.OrderDomain
import com.example.domain.models.ProductDomain
import com.example.union.presentation.ORDER_REPOSITORY
import com.example.union.presentation.PRODUCT_REPOSITORY
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ItemDetailViewModel : ViewModel() {
    fun insert(orderDomain: OrderDomain, onSuccess: () -> Unit) =
        viewModelScope.launch(Dispatchers.IO) {
            ORDER_REPOSITORY.insertOrder(orderDomain) {
                onSuccess
            }
        }

    fun update(productDomain: ProductDomain, onSuccess: () -> Unit) =
        viewModelScope.launch(Dispatchers.IO) {
            PRODUCT_REPOSITORY.updateProduct(productDomain) {
                onSuccess
            }
        }
}