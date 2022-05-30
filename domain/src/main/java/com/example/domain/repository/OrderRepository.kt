package com.example.domain.repository

import androidx.lifecycle.LiveData
import com.example.domain.models.OrderCloudData
import com.example.domain.models.OrderDomain

interface OrderRepository {

    fun allOrderDomain(userId: String): LiveData<List<OrderDomain>>

    suspend fun getListOrder(userId: String): List<OrderDomain>

    fun insertOrder(listCloud: MutableList<OrderCloudData?>, onSuccess: () -> Unit)

    suspend fun deleteOrder(orderDomain: OrderDomain, onSuccess: () -> Unit)
}