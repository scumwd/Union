package com.example.domain.save

import com.example.domain.repository.OrderRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class GetOrderFromFireBase(private val orderRepository: OrderRepository) {

    fun getAllProduct() {
        GlobalScope.launch (Dispatchers.IO){
            val listFireBase = orderRepository.getOrdersFirebase()
            orderRepository.insertOrder(listFireBase) {}
        }
    }
}