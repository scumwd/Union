package com.example.union.presentation.screen.ItemDetail

import android.app.Application
import androidx.lifecycle.*
import com.example.domain.models.OrderDomain
import com.example.domain.models.ProductDomain
import com.example.union.presentation.ORDER_REPOSITORY
import com.example.union.presentation.PRODUCT_REPOSITORY
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.*
import java.util.*

class ItemDetailViewModel(application: Application) : AndroidViewModel(application) {

    val context = application
    lateinit var mAuth: FirebaseAuth

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

    suspend fun checkOrder(productId: String): Boolean {
        mAuth = FirebaseAuth.getInstance()
        val userId: String
        mAuth.currentUser?.uid.let { userId = it.toString() }
        var result = false
        val listOrder: List<OrderDomain> = ORDER_REPOSITORY.getListOrder()

        listOrder.forEach {
            if ((it.productID == productId && it.userId == userId)) {
                result = true
                return@forEach
            } else result = false
        }

        return result

    }

}
