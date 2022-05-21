package com.example.domain.repository

import androidx.lifecycle.LiveData
import com.example.domain.models.OrderDomain

interface OrderRepository {
    val allOrderDomain: LiveData<List<OrderDomain>>
    suspend fun insertOrder(orderDomain: OrderDomain, onSuccess: () -> Unit)
    suspend fun deleteOrder(orderDomain: OrderDomain, onSuccess: () -> Unit)
}