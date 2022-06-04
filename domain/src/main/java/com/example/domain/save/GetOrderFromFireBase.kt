package com.example.domain.save

import com.example.domain.repository.OrderRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class GetOrderFromFireBase(private val orderRepository: OrderRepository) {

    suspend fun getAllProduct(): Boolean {
        val listFireBase = orderRepository.getOrdersFirebase()
        orderRepository.insertOrder(listFireBase) {}
        return true
    }
}