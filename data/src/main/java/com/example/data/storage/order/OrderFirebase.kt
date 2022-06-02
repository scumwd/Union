package com.example.data.storage.order

import com.example.data.models.OrderCloud
import com.example.data.models.OrderCloudData

interface OrderFirebase {

    suspend fun insertOrder(orderCloud: OrderCloud)

    suspend fun getOrders(): List<OrderCloudData?>

}