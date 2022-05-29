package com.example.domain.repository

import androidx.lifecycle.LiveData
import com.example.domain.models.OrderCloudData
import com.example.domain.models.OrderDomain

interface OrderRepository {

    val allOrderDomain: LiveData<List<OrderDomain>>

    suspend fun getListOrder(): List<OrderDomain>

    fun insertOrder(listCloud: MutableList<OrderCloudData?>, onSuccess: () -> Unit)

    suspend fun deleteOrder(orderDomain: OrderDomain, onSuccess: () -> Unit)
}