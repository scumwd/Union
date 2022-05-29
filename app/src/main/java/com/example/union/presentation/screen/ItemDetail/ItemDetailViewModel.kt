package com.example.union.presentation.screen.ItemDetail

import android.app.Application
import androidx.lifecycle.*
import com.example.domain.cloud.OrderInsertCloud
import com.example.domain.models.OrderDomain
import com.example.domain.models.ProductDomain
import com.example.domain.save.GetOrderFromFireBase
import com.example.domain.update.UpdateProductInFireBase
import com.example.union.presentation.ORDER_REPOSITORY
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.*
import java.util.*

class ItemDetailViewModel(application: Application) : AndroidViewModel(application) {

    val context = application
    lateinit var mAuth: FirebaseAuth
    lateinit var orderInsertCloud: OrderInsertCloud
    lateinit var upd: UpdateProductInFireBase
    lateinit var getOrderFromFireBase: GetOrderFromFireBase

    fun insert(orderDomain: OrderDomain) {
        orderInsertCloud = OrderInsertCloud()
        orderInsertCloud.insert(orderDomain)
    }

    fun update(productDomain: ProductDomain) {
        upd = UpdateProductInFireBase()
        upd.execute(productDomain)
    }

    fun getOrder() {
        getOrderFromFireBase = GetOrderFromFireBase()
        getOrderFromFireBase.getAllProduct(ORDER_REPOSITORY)
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
