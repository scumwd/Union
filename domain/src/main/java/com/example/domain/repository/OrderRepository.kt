package com.example.domain.repository

import androidx.lifecycle.LiveData
import com.example.domain.models.OrderDomain

interface OrderRepository {

    fun allOrderDomain(): LiveData<List<OrderDomain>>

    suspend fun getListOrder(productId: String): OrderDomain?

    fun insertOrder(listCloud: List<OrderDomain?>, onSuccess: () -> Unit)

    suspend fun getOrderById(productId: String) : List<OrderDomain?>

    suspend fun insertOrderFireBase(orderDomain: OrderDomain)

    suspend fun getOrdersFirebase() : List<OrderDomain>
}