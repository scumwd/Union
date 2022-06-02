package com.example.domain.getFromDb

import android.util.Log
import com.example.domain.models.OrderDomain
import com.example.domain.repository.OrderRepository

class CheckOrderExists(val orderRepository: OrderRepository) {

    suspend fun execute(productId: String): Boolean{
        val listOrder: OrderDomain? = orderRepository.getListOrder(productId)
        return listOrder!=null
    }
}