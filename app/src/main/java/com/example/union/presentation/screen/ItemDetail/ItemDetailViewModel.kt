package com.example.union.presentation.screen.ItemDetail

import androidx.lifecycle.*
import com.example.domain.cloud.OrderInsertCloud
import com.example.domain.getFromDb.CheckOrderExists
import com.example.domain.models.OrderDomain
import com.example.domain.models.ProductDomain
import com.example.domain.save.GetOrderFromFireBase
import com.example.domain.update.UpdateProductInFireBase
import javax.inject.Inject

class ItemDetailViewModel @Inject constructor(
    private val getOrderFromFireBase: GetOrderFromFireBase,
    private val orderInsertCloud: OrderInsertCloud,
    private val updateProductInFireBase: UpdateProductInFireBase,
    private val checkOrderExists: CheckOrderExists
) : ViewModel() {

    fun insert(orderDomain: OrderDomain) {
        orderInsertCloud.insert(orderDomain)
    }

    fun update(productDomain: ProductDomain) {
        updateProductInFireBase.execute(productDomain)
    }

    fun getOrder() {
        getOrderFromFireBase.getAllProduct()
    }

    suspend fun checkOrder(productId: String): Boolean {
        return checkOrderExists.execute(productId)
    }

}
